package com.JamesCode.my_shopee.dao;

import com.JamesCode.my_shopee.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value="select * from Product",nativeQuery = true)
    List<Product> findAll();
}
