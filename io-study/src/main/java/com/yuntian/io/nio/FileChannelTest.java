package com.yuntian.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Auther: yuntian
 * @Date: 2019/8/10 0010 17:32
 * @Description: FileChannel 从文件中读写数据。
 */
public class FileChannelTest {


    public static void main(String[] args) throws IOException {
        String fileName = getResourcePath(FileChannelTest.class.getClassLoader(), "file/Test01");
        readFile(fileName);
    }


    public static String getResourcePath(ClassLoader classLoader, String fileNam) {
        return classLoader.getResource(fileNam).getPath();
    }


    /**
     * 从通道读取数据到缓冲区，从缓冲区写入数据到通道。
     * 缓冲区本质上是一块可以写入数据，然后可以从中读取数据的内存
     * @param fileName
     * @throws IOException
     */
    public static void readFile(String fileName) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile(fileName, "rw");
        FileChannel inFileChannel = aFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = inFileChannel.read(buffer);
        StringBuffer stringBuffer = new StringBuffer();
        while (read != -1) {
            //buf.flip() 的调用，从写模式切换到读模式
            buffer.flip();

            byte[] reminByte=new byte[buffer.remaining()];
            buffer.get(reminByte);
            stringBuffer.append(new String(reminByte,0,reminByte.length, StandardCharsets.UTF_8));
            //只是重置了position为0和limit的位置
            buffer.clear();
            //然后反转Buffer,接着再从Buffer中读取数据
            read = inFileChannel.read(buffer);
        }
        inFileChannel.close();
        System.out.println(stringBuffer.toString());
    }

}
