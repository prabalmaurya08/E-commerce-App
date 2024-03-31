package com.example.firebase.modal;

public class MyCart_Modal {
    int TotalPrice;

    String  TotalQuantity;
    String productDate;


    String productName;
    String productPrice;
    String productTime;
    String DocId;



    public MyCart_Modal() {
    }


    public MyCart_Modal(int totalPrice, String totalQuantity, String productDate, String productName, String productPrice, String productTime, String docId) {

        TotalPrice = totalPrice;
        TotalQuantity = totalQuantity;
        this.productDate = productDate;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productTime = productTime;
        DocId = docId;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductTime() {
        return productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
    }

    public String getDocId() {
        return DocId;
    }

    public void setDocId(String docId) {
        DocId = docId;
    }
}
