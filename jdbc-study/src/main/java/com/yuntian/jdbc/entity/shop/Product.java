package com.yuntian.jdbc.entity.shop;

/**
 * @Auther: yuntian
 * @Date: 2019/12/8 0008 19:18
 * @Description:
 */
public class Product {


    private int id;

    private String name;

    /**
     * 千克/个
     */
    private double weightUnit;

    /**
     * 元/千克
     */
    private double weightUnitPrice;

    /**
     * 元/个
     */
    private double amountUnitPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(double weightUnit) {
        this.weightUnit = weightUnit;
    }

    public double getWeightUnitPrice() {
        return weightUnitPrice;
    }

    public void setWeightUnitPrice(double weightUnitPrice) {
        this.weightUnitPrice = weightUnitPrice;
    }

    public double getAmountUnitPrice() {
        return amountUnitPrice;
    }

    public void setAmountUnitPrice(double amountUnitPrice) {
        this.amountUnitPrice = amountUnitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weightUnit=" + weightUnit +
                ", weightUnitPrice=" + weightUnitPrice +
                ", amountUnitPrice=" + amountUnitPrice +
                '}';
    }
}
