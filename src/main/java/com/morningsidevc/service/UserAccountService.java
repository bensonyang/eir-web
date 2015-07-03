package com.morningsidevc.service;

import com.morningsidevc.po.gen.Account;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-6-30, Time: 下午1:44
 */
public interface UserAccountService {


    int validate(String account, String password);


    int create(String email, String password);


    int updatePassword(int userId, String oldPassword, String newPassword);


    Account loadRyEmail(String email);



}
