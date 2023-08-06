package com.JamesCode.my_shopee.service;

import com.JamesCode.my_shopee.dao.UserRepository;
import com.JamesCode.my_shopee.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    @Override
    public List<DulUser> getUsersWithCarts() {

        List<DulUser> dulUsers = new ArrayList<>();
        List<Object[]> queryResult = userRepository.getUsersWithCarts();

        for (Object[] result : queryResult) {
            int id = (int) result[0];
            int id1 = (int) result[1];

            DulUser dulUser = new DulUser(id, id1);
            dulUsers.add(dulUser);
        }

        return dulUsers;
    }

    @Override
    public List<User> getUserProfile(String username) {
        return userRepository.getinfoByname(username);
    }
//    @Override
//    public User findById(int theId) {
//        Optional<User> result = userRepository.findById(theId);
//
//        User theUser = null;
//
//        if (result.isPresent()) {
//            theUser = result.get();
//        }
//        else {
//            // we didn't find the employee
//            throw new RuntimeException("Did not find User id - " + theId);
//        }
//
//        return theUser;
//    }

//    @Override
//    public void save(User theUser) {
//        userRepository.save(theUser);
//    }
//
//    @Override
//    public void deleteById(int theId) {
//        userRepository.deleteById(theId);
//    }

    //test
    public class DulUser {
        private int id;
        private int id1;
        public DulUser(int id, int id1) {
            this.id = id;
            this.id1 = id1;
        }
        // Getters and setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId1() {
            return id1;
        }

        public void setId1(int id1) {
            this.id1 = id1;
        }
    }
}
