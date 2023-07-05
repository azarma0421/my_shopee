package com.JamesCode.my_shopee.service;

import com.JamesCode.my_shopee.dao.CartRepository;
import com.JamesCode.my_shopee.entity.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CartServiceImpl implements CartService{

    private CartRepository CartRepository;

    @Autowired
    public CartServiceImpl(CartRepository theCartRepository){
        CartRepository = theCartRepository;
    }

    // get cart by userId
//    @Override
//    public List<Cart> getCart(int userId) {
//
//        List<Cart> myCarts = new ArrayList<>();
//        List<Object[]> queryResult = CartRepository.getmyCart(userId,0);
//        for (Object[] result : queryResult) {
//            int id          = (int) result[0];
//            int UserId      = (int) result[1];
//            int ProductId   = (int) result[2];
//            int num         = (int) result[3];
//
//            Cart myCart = Cart.builder()
//                    .id(id)
//                    .productId(ProductId)
//                    .userId(UserId)
//                    .num(num)
//                    .build();
//            myCarts.add(myCart);
//        }
//
//        return myCarts;
//    }

    // get cart by userId,pid
    @Override
    public List<Cart> getmyCart(int userId,int pid) {

        List<Cart> myCarts = new ArrayList<>();
        List<Object[]> queryResult = CartRepository.getmyCart(userId,pid);
        for (Object[] result : queryResult) {
            int id          = (int) result[0];
            int UserId      = (int) result[1];
            int ProductId   = (int) result[2];
            int num         = (int) result[3];

            Cart myCart = Cart.builder()
                    .id(id)
                    .productId(ProductId)
                    .userId(UserId)
                    .num(num)
                    .build();
            myCarts.add(myCart);
        }

        return myCarts;
    }

    //add cart by userId,pid
    @Override
    public void addCart(int userId,int pid) {

        List<Cart> a = getmyCart(userId,pid);
        try{
            if (a.isEmpty()) {
                System.out.println("The list is empty.");
                addToCart(userId,pid,1);

            } else {
                System.out.println("The list is not empty.");
                addNumToCart(userId,pid);
            }
            System.out.println("Already add to cart.");
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Fail to add to cart.");
        }
    }

    // new product for cart
    @Override
    public void addToCart(int userId,int pid,int num) {
        CartRepository.addToCart(userId,pid,num);
        log.info("Parameter - userId:{}, pid:{},num:{}",userId,pid,num);
    }

    // already excise in cart
    @Override
    public void addNumToCart(int userId,int pid) {
        CartRepository.addNumToCart(userId,pid);
        log.info("[SQL] Parameter - userId:{}, pid:{}",userId,pid);
    }


    @Override
    public void delCart(int userId,int pid) {
        CartRepository.delCart(userId,pid);
        log.info("[SQL] Parameter - userId:{}, pid:{}",userId,pid);
    }
}
