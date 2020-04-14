package com.yuntian.io.nio.cs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @Auther: yuntian
 * @Date: 2019/8/16 0016 23:28
 * @Description:
 */
public class NioClientSingle {

    private Selector selector;

    private final static int BUF_SIZE = 10240;


    private SocketChannel clientChannel;

    private void init(int port) throws IOException {
        this.selector = Selector.open();
        clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false);
        clientChannel.connect(new InetSocketAddress("localhost", port));
    }

    public void start(int port) throws IOException {
        init(port);
    }

    public String sendAndGetReply(String msg) throws IOException {
        if (clientChannel == null) {
            System.out.println("还未建立连接");
            return "";
        }
        clientChannel.register(selector, SelectionKey.OP_CONNECT);
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isValid() && key.isConnectable()) {
                    doConnect(key);
                } else if (key.isWritable()) {
                    doWrite(key, msg);
                } else if (key.isReadable()) {
                    return doRead(key);
                }
            }
        }
    }

    private void doWrite(SelectionKey key, String msg) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        System.out.println("发送:" + msg+","+clientChannel.getLocalAddress().toString());
        byte[] req =msg.getBytes(StandardCharsets.UTF_8);
        ByteBuffer bb = ByteBuffer.allocate(req.length);
        bb.put(req);
        bb.flip();
        clientChannel.write(bb);
        clientChannel.register(selector, SelectionKey.OP_READ);
    }

    private void doConnect(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        if (clientChannel.finishConnect()) {
            clientChannel.register(selector, SelectionKey.OP_WRITE);
        } else {
            System.out.println("连接失败");
            clientChannel.close();
        }
    }

    private String doRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer bb = ByteBuffer.allocate(1024);
        try{
            int readBytes = clientChannel.read(bb);
            if (readBytes==-1){
                clientChannel.close();
                System.out.println("连接关闭");
            }else {
                bb.flip();
                byte[] resp = new byte[readBytes];
                bb.get(resp);
                String respMsg = new String(resp, StandardCharsets.UTF_8);
                //关闭该通道
                clientChannel.close();
                //还需要关闭该通道注册的事件
                key.selector().close();
                return  respMsg;
            }
        }catch (Exception e){
            System.err.println("异常："+key.isValid());
        }

        return "";
    }

    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(0);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                NioClientSingle nioClient = new NioClientSingle();
                try {
                    nioClient.start(8999);
                    String reply = nioClient.sendAndGetReply("服务端,你好!");
                    System.out.println("返回:" + reply+","+atomicInteger.addAndGet(1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
