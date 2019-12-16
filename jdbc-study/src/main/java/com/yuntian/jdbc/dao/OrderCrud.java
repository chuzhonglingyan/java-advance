package com.yuntian.jdbc.dao;

import com.yuntian.jdbc.entity.shop.Order;
import com.yuntian.jdbc.util.DBManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhangxiaoyu
 * @Description:
 */
public class OrderCrud {

    public static int insert(Order order) {
        Connection conn = DBManager.getConnection();
        int result = 0;
        String sql = "insert into `order` (user_name,total_price)  values(?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getUserName());
            pstmt.setDouble(2, order.getTotalPrice().doubleValue());
            result = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return result;
    }

    public static int insertGeneratedKey(Order order) {
        Connection conn = DBManager.getConnection();
        int result = 0;
        String sql = "insert into `order` (user_name,total_price)  values(?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, order.getUserName());
            pstmt.setDouble(2, order.getTotalPrice().doubleValue());
            result = pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getInt(1);
            }
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return result;
    }


    public static Order queryById(Integer id) {
        Connection conn = DBManager.getConn();
        String sql = "select * from  `order` where id=?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                Order order = new Order();
                order.setId(result.getInt("id"));
                order.setUserName(result.getString("user_name"));
                order.setTotalPrice(new BigDecimal(result.getDouble("total_price")));
                result.close();
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return null;
    }

    public static Order queryByUserName(String userName) {
        Connection conn = DBManager.getConn();
        String sql = "select * from  `order` where user_name=?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                Order order = new Order();
                order.setId(result.getInt("id"));
                order.setUserName(result.getString("user_name"));
                order.setTotalPrice(new BigDecimal(result.getDouble("total_price")));
                result.close();
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return null;
    }


    public static List<Order> queryAll() {
        Connection conn = DBManager.getConn();
        String sql = "select * from `order`";
        PreparedStatement pstmt = null;
        List<Order> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                Order order = new Order();
                order.setId(result.getInt("id"));
                order.setTotalPrice(new BigDecimal(result.getDouble("total_price")));
                list.add(order);
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return list;
    }

}
