package com.JamesCode.my_shopee.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="category")
    private String category;

    @Column(name="name")
    private String name;

    @Column(name="cost")
    private int cost;

    @Column(name="num")
    private int num;

    @Column(name="onsale")
    private String onsale;

    @Column(name="src")
    private String src;

    public Product() {
    }
    public Product(String category, String name, int cost, int num, String onsale, String src) {
        this.category = category;
        this.name = name;
        this.cost = cost;
        this.num = num;
        this.onsale = onsale;
        this.src = src;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getOnsale() {
        return onsale;
    }

    public void setOnsale(String onsale) {
        this.onsale = onsale;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
