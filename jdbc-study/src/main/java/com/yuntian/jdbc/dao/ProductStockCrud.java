package com.yuntian.jdbc.dao;

import com.yuntian.jdbc.entity.shop.ProductStock;
import com.yuntian.jdbc.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description: https://blog.csdn.net/w_linux/article/details/79689834
 */
public class ProductStockCrud {


    /**
     * 有并发问题
     *
     * @param addStock
     * @param productId
     * @return
     */
    public static int updateStock(int addStock, int productId) {
        ProductStock productStock = queryByProductId(productId);
        int stock = addStock + productStock.getStock();
        return update(stock, productId);
    }

    /**
     * 悲观锁
     * @param addStock
     * @param productId
     * @return
     */
    public static int updateStockOptimisticLock(int addStock, int productId) {
        Connection conn = DBManager.getConnection();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement pstmtSelect = null;
        PreparedStatement pstmtUpdate = null;
        String sqlSelect = "select * from  `product_stock` where product_id=? for update";
        String sqlUpdate = "update product_stock set stock=stock+? where product_id=?";

        int resultUpdate = 0;
        try {
            pstmtSelect = conn.prepareStatement(sqlSelect);
            pstmtSelect.setInt(1, productId);
            ResultSet resultSelect = pstmtSelect.executeQuery();
            ProductStock order = new ProductStock();
            if (resultSelect.next()) {
                order.setId(resultSelect.getInt("id"));
                order.setProductId(resultSelect.getString("product_id"));
                order.setStock(resultSelect.getInt("stock"));
                order.setStatus(resultSelect.getInt("status"));
                resultSelect.close();
            }

            pstmtUpdate = conn.prepareStatement(sqlUpdate);
            pstmtUpdate.setInt(1, addStock);
            pstmtUpdate.setInt(2, productId);
            resultUpdate = pstmtUpdate.executeUpdate();

            //提交事务（事务结束）
            conn.commit();
        } catch (Exception  e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                pstmtSelect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBManager.close(conn, pstmtUpdate);
        }
        return resultUpdate;
    }

    public static int update(int stock, int productId) {
        Connection conn = DBManager.getConnection();
        int result = 0;
        String sql = "update product_stock set stock=? where product_id=?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stock);
            pstmt.setInt(2, productId);
            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return result;
    }

    public static ProductStock queryByProductId(Integer productId) {
        Connection conn = DBManager.getConnection();
        String sql = "select * from  `product_stock` where product_id=?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                ProductStock order = new ProductStock();
                order.setId(result.getInt("id"));
                order.setProductId(result.getString("product_id"));
                order.setStock(result.getInt("stock"));
                order.setStatus(result.getInt("status"));
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
}
