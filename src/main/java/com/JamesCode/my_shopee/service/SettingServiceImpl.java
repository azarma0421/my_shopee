package com.JamesCode.my_shopee.service;

import com.JamesCode.my_shopee.dao.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettingServiceImpl implements SettingService {

    private SettingRepository settingRepository;

    @Autowired
    public SettingServiceImpl( SettingRepository theRepository){
        settingRepository = theRepository;
    }

    @Override
    public List<Set_Member> get_SetMember() {

        List<Set_Member> result_list = new ArrayList<>();
        List<Object[]> queryResult = settingRepository.get_Setmember();

        for(Object[] result : queryResult){
            int id = (int) result[0];
            String username = (String) result[1];
            String password = (String) result[2];
            String status = (String) result[3];
            String mail = (String) result[4];

            Set_Member tem = new Set_Member(id, username, password, status, mail);
            result_list.add(tem);
        }
        return result_list;

    }

    @Override
    public List<Set_Product> get_SetProduct() {
        List<Set_Product> result_list = new ArrayList<>();
        List<Object[]> queryResult = settingRepository.get_Setproduct();

        for(Object[] result : queryResult){
            int id = (int) result[0];
            String Category = (String) result[1];
            String Name = (String) result[2];
            int Price = (int) result[3];
            int Num = (int) result[4];
            String onsale = (String) (result[5]+"");
            String src = (String) result[6];


            Set_Product tem = new Set_Product(id, Category, Name, Price, Num, onsale, src);
            result_list.add(tem);
        }
        return result_list;
    }

    @Override
    public List<Set_Records> get_SetRecords() {
        return null;
    }

    public class Set_Member {

        private int id;
        private String username;
        private String password;
        private String status;
        private String mail;

        public Set_Member(int id, String username, String password, String status, String mail) {
            this.id=id;
            this.username=username;
            this.password=password;
            this.status=status;
            this.mail=mail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }
    }

    public class Set_Product {
        private int id;
        private String category;
        private String name;
        private int price;
        private int num;
        private String onsale;
        private String src;

        public Set_Product(int id, String category, String name, int price, int num, String onsale, String src) {
            this.id = id;
            this.category = category;
            this.name = name;
            this.price = price;
            this.num = num;
            this.onsale = onsale;
            this.src = src;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getOnsale() {
            return onsale;
        }

        public void setOnsale(String onsale) {
            this.onsale = onsale;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }

    public class Set_Records {

    }
}
