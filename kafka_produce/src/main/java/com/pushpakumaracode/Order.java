package com.pushpakumaracode;


public class Order {
    private int id;
    private String name;
    private String address;
    private int qty;

    public Order() {
    }

    public Order(int id, String name, String address, int qty) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.qty = qty;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
