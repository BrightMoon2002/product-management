package com.codegym.model;

public class TypeProduct {
    private int id;
    private String name;

    public TypeProduct(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TypeProduct() {
    }

    public TypeProduct(String name) {
        this.name = name;
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
}
