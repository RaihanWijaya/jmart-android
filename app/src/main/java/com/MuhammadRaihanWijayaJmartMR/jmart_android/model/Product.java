package com.MuhammadRaihanWijayaJmartMR.jmart_android.model;

/**
 * The class Product extends serializable
 * @author Raihan Wijaya
 * @description Untuk menyimpan variabel Product
 */

public class Product extends Serializable {
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;

    @Override
    public String toString() {
        return this.name;
    }
}
