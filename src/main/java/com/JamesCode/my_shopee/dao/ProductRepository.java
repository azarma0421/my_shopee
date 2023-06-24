package com.JamesCode.my_shopee.dao;

import com.JamesCode.my_shopee.entity.Product;
import com.JamesCode.my_shopee.service.ProductServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value="select * from Product",nativeQuery = true)
    List<Product> getAllProducts();

    @Query(value="WITH mc" +
            " AS" +
            "  (" +
            "             SELECT     u.id," +
            "                        p.name," +
            "                        CASE" +
            "                                   WHEN p.onsale = 'Y' THEN p.cost * 0.9" +
            "                                   ELSE p.cost" +
            "                        end cost," +
            "                        c.num," +
            "                        p.onsale," +
            "                        p.src" +
            "             FROM       user u" +
            "             INNER JOIN cart c" +
            "             ON         u.id = c.userid" +
            "             INNER JOIN product p" +
            "             ON         c.productid = p.id" +
            "             WHERE      u.id = '1') , total" +
            " AS" +
            "  (" +
            "         SELECT floor(sum(cost*num)) total" +
            "         FROM   (" +
            "                           SELECT" +
            "                                      CASE" +
            "                                                 WHEN p.onsale = 'Y' THEN p.cost * 0.9" +
            "                                                 ELSE p.cost" +
            "                                      end cost," +
            "                                      c.num" +
            "                           FROM       user u" +
            "                           INNER JOIN cart c" +
            "                           ON         u.id = c.userid" +
            "                           INNER JOIN product p" +
            "                           ON         c.productid = p.id" +
            "                           WHERE      u.id = '1' ) m )" +
            "  SELECT    mc.id," +
            "            mc.name," +
            "            CONCAT('$', mc.cost) cost," +
            "            mc.num," +
            "            mc.onsale," +
            "            mc.src," +
            "            CONCAT('$',total) total" +
            "  FROM      mc" +
            "  LEFT JOIN total" +
            "  ON        1=1",nativeQuery = true)
    List<Object[]> getCart_Detail(@Param("userId") int userId);
}
