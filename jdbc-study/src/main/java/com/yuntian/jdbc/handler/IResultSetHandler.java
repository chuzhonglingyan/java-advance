package com.yuntian.jdbc.handler;

import java.sql.ResultSet;

/**
 * @Auther: yuntian
 * @Date: 2019/12/7 0007 23:39
 * @Description:
 */
public interface IResultSetHandler<T> {

    T handle(ResultSet result) throws Exception;
}
