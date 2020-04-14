package com.yuntian.io.bio;

import com.yuntian.io.nio.FileChannelTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Auther: yuntian
 * @Date: 2019/8/10 0010 18:00
 * @Description:
 */
public class ReadResourcesFile {


    public static void main(String[] args) throws IOException {
//        readResourcesFile("/file/Test01");
        long startTime=System.currentTimeMillis();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-startTime+"ms");
    }


    public static void readResourcesFile(String fileName) throws IOException {
        InputStream path = FileChannelTest.class.getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(path));
        String str = reader.readLine();
        StringBuffer stringBuffer = new StringBuffer();
        while (str != null) {
            stringBuffer.append(str).append("\n");
            str = reader.readLine();
        }
        System.out.println(stringBuffer.toString());
    }


}
