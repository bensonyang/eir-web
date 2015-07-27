package com.morningsidevc.web.controller;

import com.morningsidevc.po.gen.UserInfo;
import com.morningsidevc.service.UserInfoService;
import com.morningsidevc.web.response.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author float.lu
 */
@Controller
@RequestMapping("meinfo")
public class MeInfoController extends BaseController{

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("default")
    public String meInfoCenter(Model model){
        UserInfo user = userInfoService.loadUserInfoById(getUserId());
        model.addAttribute("user", user);
        return "meinfo";
    }

    @RequestMapping("pic")
    public String mePic(Model model){
        return "mepic";
    }


    @RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResponse update(UserInfo userInfo){
        JsonResponse response = new JsonResponse();
        try {
            userInfo.setUserid(getUserId());
            userInfoService.updateUserInfoSelective(userInfo);
            response.setCode(200);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg("服务器错误");
        }
        return response;
    }

}
