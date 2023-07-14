package com.JamesCode.my_shopee.service;

import com.JamesCode.my_shopee.dao.ProductRepository;
import com.JamesCode.my_shopee.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository theProductRepository){
        productRepository = theProductRepository;
    }


    @Override
    public List<Product> getProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public List<Cart_Detail> getCart_Detail(int userId) {

        List<Cart_Detail> cart_Detail = new ArrayList<>();
        List<Object[]> queryResult = productRepository.getCart_Detail(userId);

        BigDecimal decimalValue = null;

        for (Object[] result : queryResult) {
            int id          = (int) result[0];
            String name     = (String) result[1];
            String cost     = (String) result[2];
            int num         = (int) result[3];
            Character onsale= (Character) result[4];
            String src      = (String) result[5];
            String total     = (String) result[6];

            Cart_Detail detail
                    = new Cart_Detail(id, name,cost,num,onsale,src,total);
            cart_Detail.add(detail);
        }

        return cart_Detail;
    }

    public class Cart_Detail{
        private int id;
        private String name;
        private String cost;
        private int num;
        private Character onsale;
        private String src;
        private String total;

        public Cart_Detail(int id, String name, String cost, int num, Character onsale, String src, String total) {
            this.id = id;
            this.name = name;
            this.cost = cost;
            this.num = num;
            this.onsale = onsale;
            this.src = src;
            this.total = total;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public Character getOnsale() {
            return onsale;
        }

        public void setOnsale(Character onsale) {
            this.onsale = onsale;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }
}
