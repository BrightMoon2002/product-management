package com.codegym.model;

public class Product {
    private int id;
    private String name;
    private String description;
    private String producer;
    private double price;
    private TypeProduct typeProduct;

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public Product() {
    }

    public Product(int id, String name, String description, String producer, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.price = price;
    }

    public Product(String name, String description, String producer, double price) {
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.price = price;
    }

    public Product(int id, String name, String description, String producer, double price, TypeProduct typeProduct) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.price = price;
        this.typeProduct = typeProduct;
    }

    public Product(String name, String description, String producer, double price, TypeProduct typeProduct) {
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.price = price;
        this.typeProduct = typeProduct;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}