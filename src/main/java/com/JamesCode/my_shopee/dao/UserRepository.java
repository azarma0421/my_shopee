package com.JamesCode.my_shopee.dao;

import com.JamesCode.my_shopee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	// that's it ... no need to write any code LOL!
	
}
