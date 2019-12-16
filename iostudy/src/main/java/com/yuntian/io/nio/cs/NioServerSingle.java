package com.yuntian.io.nio.cs;

import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @Auther: yuntian
 * @Date: 2019/8/10 0010 00:32
 * @Description: SelectionKey.OP_ACCEPT
 * SelectionKey.OP_CONNECT
 * SelectionKey.OP_READ
 * SelectionKey.OP_WRITE
 * 事件（接受，连接，读，写）是否就绪。
 */
public class NioServerSingle {

    private static final int TIMEOUT = 3000;
    private Selector selector;
    private final static int BUF_SIZE = 10240;

    private AtomicInteger atomicInteger=new AtomicInteger(0);


    private void init(int port) throws IOException {
        //1.创建事件传送带
        selector = Selector.open();
        //2.创建ServerSocketChannel 是一个可以监听新进来的TCP连接的通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3.设置服务器通道为非阻塞方式
        serverSocketChannel.configureBlocking(false);
        //4. 绑定TCP地址
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", port);
        serverSocketChannel.bind(inetSocketAddress);
        //5. 把管道放到传送带上，并在传送带上注册一个感兴趣事件，此处传送带感兴趣事件为监听事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server服务启动" + inetSocketAddress.getHostName());
        System.out.println("Server服务启动" + inetSocketAddress.getAddress().getHostAddress());
        System.out.println("Server服务启动端口:" + inetSocketAddress.getPort());
        System.out.println("Server服务启动开始监听");
        System.out.println("--------------------------------");
    }

    public void start(int port) throws IOException {
        init(port);
    }

    public void acceptAndReply() throws IOException {
        while (true) {
            if (selector.select(TIMEOUT) == 0) {
                System.out.println("监听:" + LocalDateTime.now());
                continue;
            }
            //这是一个阻塞方法，一直等待直到有数据可读，返回值是key的数量（可以有多个）
            //循环到读事件必定发生阻塞的
            selector.select();
            //如果channel有数据了，将生成的key访入keys集合中
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                //得到集合中的一个key实例
                SelectionKey key = (SelectionKey) iterator.next();
                //事件遍历得到处理,要从事件集合删除
                iterator.remove();
                //接收就绪
                if (key.isValid()&&key.isAcceptable()) {
                    doAccept(key);
                }
                //读就绪
                else if (key.isValid()&&key.isReadable()) {
                    doRead(key);
                }
                //写就绪
                else if (key.isValid()&& key.isWritable()) {
                    doWrite(key);
                }
                //连接就绪
                else if (key.isValid() && key.isConnectable()) {
                    SocketChannel ch = (SocketChannel) key.channel();
                    if (ch.finishConnect()) {
                        System.out.println("建立连接成功");
                    } else {
                        System.out.println("建立连接失败");
                    }
                }
            }
        }
    }

    /**
     * 准备好接收新进入的连接  非阻塞
     *
     * @param key
     * @throws IOException
     */
    private void doAccept(SelectionKey key) throws IOException {
        //返回服务端的连接通道
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        //返回一个客户端的连接通道  非阻塞
        SocketChannel clientChannel = serverChannel.accept();
        //注册阻塞式
        clientChannel.configureBlocking(false);
        System.out.println("ServerSocketChannel：接收一个客户端连接,并打开数据通道:"+atomicInteger.addAndGet(1));
        //注册只读事件，同时提供了一个附件用做读写缓冲。
        clientChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }

    /**
     * 有数据可读的通道
     *
     * @param key
     * @throws IOException
     */
    private void doRead(SelectionKey key) throws IOException {
        System.out.println("触发读事件");
        SocketChannel clientChannel = (SocketChannel) key.channel();
        String data = null;
        try {
            data = readFormSocketChannel(clientChannel);
            System.out.println("从客户端发送过来的消息是：" + data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data != null) {
            replyResult(clientChannel, "ok,"+clientChannel.getRemoteAddress().toString());
        } else {
            replyResult(clientChannel, "failure,"+clientChannel.getRemoteAddress().toString());
        }
    }


    /**
     * 从通道读取数据到缓冲区
     *
     * @param clientChannel
     * @return
     * @throws IOException
     */
    private String readFormSocketChannel(SocketChannel clientChannel) throws IOException {
        //获得缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUF_SIZE);
        //SocketChannel 读取数据到缓冲区
        long bytesRead = clientChannel.read(byteBuffer);
        StringBuffer stringBuffer = new StringBuffer();
        while (bytesRead > 0) {
            //复位
            byteBuffer.flip();
            byte[] req = new byte[byteBuffer.remaining()];
            byteBuffer.get(req);
            stringBuffer.append(new String(req, StandardCharsets.UTF_8).trim());
            //清除
            byteBuffer.clear();
            bytesRead = clientChannel.read(byteBuffer);
        }
        if (bytesRead == -1) {
            clientChannel.close();
            System.out.println("一次读取完成");
        }
        return stringBuffer.toString();
    }

    /**
     * 响应读取数据状态
     *
     * @param clientChannel
     * @param repMsg
     * @throws IOException
     */
    private void replyResult(SocketChannel clientChannel, String repMsg) throws IOException {
        byte[] resp = repMsg.getBytes(StandardCharsets.UTF_8);
        ByteBuffer respBb = ByteBuffer.allocate(resp.length);
        respBb.put(resp);
        respBb.flip();
        clientChannel.register(selector, SelectionKey.OP_WRITE, respBb);
    }


    /**
     * 获得有数据可写的通道
     *
     * @param key
     * @throws IOException
     */
    private void doWrite(SelectionKey key) {
        System.out.println("触发写事件");
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
        SocketChannel clientChannel = (SocketChannel) key.channel();
        try {
            while (byteBuffer.hasRemaining()) {
                //缓冲区不满的情况下，就会触发WRITE事件，在那时候再写入，可以避免不要的消耗。
                int len = clientChannel.write(byteBuffer);
                if (len < 0) {
                    throw new EOFException();
                }
                //如果返回0，表示缓冲区满，那么注册WRITE事件
                if (len == 0) {
                    key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                    selector.wakeup();
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            byteBuffer.compact();
            try {
                clientChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("一次读写完成");
    }


    public static void main(String[] args) throws IOException {
        NioServerSingle nioServer = new NioServerSingle();
        nioServer.start(8999);
        nioServer.acceptAndReply();
    }
}
