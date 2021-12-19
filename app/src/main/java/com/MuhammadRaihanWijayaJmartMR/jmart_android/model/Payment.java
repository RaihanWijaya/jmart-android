package com.MuhammadRaihanWijayaJmartMR.jmart_android.model;

/**
 * The class Payment extends Invoice
 * @author Raihan Wijaya
 * @description
 * Untuk menyimpan variabel dari Payment
 * Ada juga inner class Record
 */

import java.util.ArrayList;
import java.util.Date;

public class Payment extends Invoice{
    public Shipment shipment;
    public int productCount;
    public ArrayList<Record> history = new ArrayList<Record>();

    public  static class Record {
        public final Date date;
        public String massage;
        public Status status;

        public Record( Status status, String massage) {
            this.date = java.util.Calendar.getInstance().getTime();
            this.status = status;
            this.massage = massage;
        }
    }

    public String getBuyerId() {
        return String.valueOf(buyerId);
    }

    public String getProductId() {
        return String.valueOf(productId);
    }

    public String getProductCount() {
        return String.valueOf(productCount);
    }

    public ArrayList<Record> getHistory() {
        return history;
    }

    @Override
    public String toString() {
        return String.valueOf(this.productId);
    }
}