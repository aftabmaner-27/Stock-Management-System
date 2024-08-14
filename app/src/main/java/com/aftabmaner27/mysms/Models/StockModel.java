package com.aftabmaner27.mysms.Models;

public class StockModel {

    private int id ;
    private  String productname;
    private  String productdescription;
    private  String quantity;
    private  String price;
    private  String gst;
    private  String totalAmount;

    public StockModel(int id, String productname, String productdescription, String quantity, String price, String gst, String totalAmount) {
        this.id = id;
        this.productname = productname;
        this.productdescription = productdescription;
        this.quantity = quantity;
        this.price = price;
        this.gst = gst;
        this.totalAmount = totalAmount;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }



}
