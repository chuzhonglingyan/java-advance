package com.yuntian.jdbc.handler;

import com.yuntian.jdbc.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Auther: yuntian
 * @Date: 2019/12/7 0007 23:35
 * @Description:
 */
public class CRUDTemplate {


    public static <T> T executeQuery(String sql, IResultSetHandler<T> iResultSetHandler, Object... params) {
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            //给预编译语句赋值
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            result = preparedStatement.executeQuery();
            //转换结果
            return iResultSetHandler.handle(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBManager.close(connection,preparedStatement, result);
            }
        }
        return null;
    }
}
