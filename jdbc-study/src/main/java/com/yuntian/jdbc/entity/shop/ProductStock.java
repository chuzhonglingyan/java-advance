package com.yuntian.jdbc.entity.shop;

/**
 * @Auther: yuntian
 * @Date: 2019/12/8 0008 19:50
 * @Description:
 */
public class ProductStock {

    /**
     * 订单id
     */
    private int id;

    private String productId;

    private int stock;

    private int status;

    private int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ProductStock{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", stock=" + stock +
                ", status=" + status +
                ", version=" + version +
                '}';
    }
}


