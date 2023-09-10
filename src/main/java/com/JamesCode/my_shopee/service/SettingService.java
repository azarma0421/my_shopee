package com.JamesCode.my_shopee.service;

import java.util.List;

public interface SettingService {

    public List<SettingServiceImpl.Set_Member> get_SetMember();

    public List<SettingServiceImpl.Set_Product> get_SetProduct();

    public List<SettingServiceImpl.Set_Records> get_SetRecords();
}