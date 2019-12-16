package com.yuntian.io.nio.cs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author Administrator
 * @Auther: yuntian
 * @Date: 2019/8/16 0016 23:28
 * @Description:
 */
public class NioClient {

    private Selector selector;

    private final static int BUF_SIZE = 10240;

    private static ByteBuffer byteBuffer = ByteBuffer.allocate(BUF_SIZE);

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
                if (key.isValid() &&key.isConnectable()) {
                    doConnect(key, msg);
                } else if (key.isReadable()) {
                    return doRead(key);
                }
            }
        }
    }

    private void doConnect(SelectionKey key, String msg) throws IOException {
        if (msg == null) {
            return;
        }
        SocketChannel clientChannel = (SocketChannel) key.channel();
        if (clientChannel.isConnectionPending()) {
            clientChannel.finishConnect();
        }
        clientChannel.configureBlocking(false);
        if (clientChannel.finishConnect()) {
            //清空
            byteBuffer.clear();
            byteBuffer.put(msg.getBytes(StandardCharsets.UTF_8));
            //复位
            byteBuffer.flip();
            clientChannel.write(byteBuffer);
            clientChannel.close();
        }else {
            System.out.println("连接失败");
        }

    }

    private String doRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        clientChannel.read(byteBuffer);
        byte[] data = byteBuffer.array();
        String msg = new String(data).trim();
        System.out.println("服务端发送消息：" + msg);
        //关闭该通道
        clientChannel.close();
        //还需要关闭该通道注册的事件
        key.selector().close();
        return msg;
    }

    public static void main(String[] args) throws IOException {
        NioClient nioClient = new NioClient();
        nioClient.start(8888);
        for (int i = 0; i < 10; i++) {
            String reply = nioClient.sendAndGetReply("服务端：你好,"+i);
            System.out.println("执行完成一次发送返回"+i);
        }
    }
}
