package com.JamesCode.my_shopee.dao;

import com.JamesCode.my_shopee.entity.User;
import com.JamesCode.my_shopee.service.SettingServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//暫時先用User 之後會調整為其他用法
@Repository
public interface SettingRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT " +
            "  a.id, " +
            "  a.username, " +
            "  a.password, " +
            "  CASE b.enabled WHEN 1 THEN 'Enable' WHEN 2 THEN 'Disable' END status, " +
            "  a.mail " +
            "FROM " +
            "  user_info a " +
            "  LEFT JOIN users b ON a.username = b.username;" , nativeQuery = true)
    List<Object[]> get_Setmember();

    @Query(value = "SELECT " +
            "    id, category, name, cost, num, onsale, src " +
            "FROM " +
            "    product" , nativeQuery = true)
    List<Object[]> get_Setproduct();
}
