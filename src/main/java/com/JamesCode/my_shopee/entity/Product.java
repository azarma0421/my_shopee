package com.JamesCode.my_shopee.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Product")
@Data
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
}
