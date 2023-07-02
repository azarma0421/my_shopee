package com.JamesCode.my_shopee.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name="Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "UserId")
    private int userId;

    @Column(name = "ProductId")
    private int productId;

    @Column(name = "num")
    private int num;
}
