package com.yuntian.jdbc.entity.shop;

import java.math.BigDecimal;

/**
 * @Auther: yuntian
 * @Date: 2019/12/8 0008 19:50
 * @Description:
 */
public class Order {

    /**
     * 订单id
     */
    private int id;

    private String userName;

    private BigDecimal totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}


