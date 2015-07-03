package com.morningsidevc.service;


import java.util.List;
import java.util.Map;

import com.morningsidevc.vo.User;

/**
 * @author float.lu
 */
public interface UserInfoService {
    User load(int id);
    
    Map<Integer, User> findUsers(List<Integer> userIds);
}
