package com.JamesCode.my_shopee.dao;

import com.JamesCode.my_shopee.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query(value = "SELECT" +
            " * " +
            "FROM cart " +
            "WHERE Userid = :userId " +
            "AND ProductId = :pid", nativeQuery = true)
    List<Object[]> getmyCart(@Param("userId") int userId,
                       @Param("pid") int pid);

    @Modifying
    @Transactional
    @Query(value="INSERT INTO cart " +
            " (UserId, ProductId, num) " +
            "  VALUES (:userId," +
            " :pid," +
            " :num)", nativeQuery = true)
    void addToCart(@Param("userId") int userId,
                   @Param("pid") int pid,
                   @Param("num") int num);

    @Modifying
    @Transactional
    @Query(value="UPDATE " +
            "  cart m " +
            "  INNER JOIN cart c ON c.userid = :userId " +
            "  AND c.productid = :pid " +
            "SET " +
            "  m.num = (c.num + 1) " +
            "WHERE " +
            "  c.id = m.id", nativeQuery = true)
    void addNumToCart(@Param("userId") int userId,
                   @Param("pid") int pid);
//    @Modifying
//    @Query(value="INSERT INTO cart (ProductId, UserId, num)" +
//            "  VALUES (5, 5, 5)")
//    void addcart(@Param("value1") String value1)

}
