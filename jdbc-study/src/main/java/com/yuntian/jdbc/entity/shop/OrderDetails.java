package com.yuntian.jdbc.entity.shop;

/**
 * @Auther: yuntian
 * @Date: 2019/12/8 0008 20:17
 * @Description:
 */
public class OrderDetails {

    private int id;

    /**
     * 订单id
     */
    private int  orderId;

    /**
     * 商品id
     */
    private int  productId;


    private String  productName;


    private int  productWeight;

    private int  productAmount;

    private int  productMeasure;

    private double  productSale;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(int productWeight) {
        this.productWeight = productWeight;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public int getProductMeasure() {
        return productMeasure;
    }

    public void setProductMeasure(int productMeasure) {
        this.productMeasure = productMeasure;
    }

    public double getProductSale() {
        return productSale;
    }

    public void setProductSale(double productSale) {
        this.productSale = productSale;
    }


    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productWeight=" + productWeight +
                ", productAmount=" + productAmount +
                ", productMeasure=" + productMeasure +
                ", productSale=" + productSale +
                '}';
    }
}
