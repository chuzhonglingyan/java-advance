package com.yuntian.jdbc.util;

import java.sql.Connection;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Auther: yuntian
 * @Date: 2019/12/16 0016 20:40
 * @Description:
 */
public class ConnectionPool {

    public static final int MIN_SIZE = 10;

    public static final int MAX_SIZE = 20;

    /***
     * 缓存连接池
     */
    private static ArrayBlockingQueue<Connection> connectionList = new ArrayBlockingQueue<>(MAX_SIZE);


    public static  int getSize(){
        return connectionList.size();
    }


    public static void add(Connection connection) {
        connectionList.add(connection);
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionList.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取一次连接:"+ Objects.requireNonNull(connection).toString()+",线程:"+Thread.currentThread());
        return connection;
    }


    public static Queue<Connection> getConnectionList() {
        return connectionList;
    }
}
