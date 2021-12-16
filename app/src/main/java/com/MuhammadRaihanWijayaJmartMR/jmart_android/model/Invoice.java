package com.MuhammadRaihanWijayaJmartMR.jmart_android.model;

/**
 * The class Invoice extends Serializable
 * @author Raihan Wijaya
 * @description
 * Untuk menyimpan variabel dari Invoice
 * Ada juga enum Status dan Rating
 */

import java.util.Date;

public class Invoice extends Serializable {
    public static enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED;
    }

    public static enum Rating{
        NONE,BAD,NEUTRAL,GOOD;
    }

    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
}