package com.JamesCode.my_shopee.dao;

import com.JamesCode.my_shopee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT a.id id,b.id id1 " +
            "FROM user_info a " +
            "left join user_info b " +
            "on a.id = b.id;", nativeQuery = true)
    List<Object[]> getUsersWithCarts();

    @Query(value = "SELECT * " +
            "FROM user_info " +
            "WHERE username = :username", nativeQuery = true)
    List<User> getinfoByname(@Param("username") String username);
}
