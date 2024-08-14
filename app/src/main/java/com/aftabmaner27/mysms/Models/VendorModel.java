package com.aftabmaner27.mysms.Models;

public class VendorModel {


    private int id ;
    private  String vendorname;
    private  String vendoraddress;
    private  String vendorGst;
    private  String vendorContactcno;

    public VendorModel(int id, String vendorname, String vendoraddress, String vendorGst, String vendorContactcno) {
        this.id = id;
        this.vendorname = vendorname;
        this.vendoraddress = vendoraddress;
        this.vendorGst = vendorGst;
        this.vendorContactcno = vendorContactcno;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getVendoraddress() {
        return vendoraddress;
    }

    public void setVendoraddress(String vendoraddress) {
        this.vendoraddress = vendoraddress;
    }

    public String getVendorGst() {
        return vendorGst;
    }

    public void setVendorGst(String vendorGst) {
        this.vendorGst = vendorGst;
    }

    public String getVendorContactcno() {
        return vendorContactcno;
    }

    public void setVendorContactcno(String vendorContactcno) {
        this.vendorContactcno = vendorContactcno;
    }



}
