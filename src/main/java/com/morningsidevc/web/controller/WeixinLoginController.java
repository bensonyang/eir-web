/**
 *
 */
package com.morningsidevc.web.controller;

import com.google.gson.Gson;
import com.morningsidevc.dao.gen.WeixinUserInfoMapper;
import com.morningsidevc.po.WeixinUser;
import com.morningsidevc.po.gen.UserInfo;
import com.morningsidevc.po.gen.WeixinUserInfo;
import com.morningsidevc.service.UserAccountService;
import com.morningsidevc.service.UserInfoService;
import com.morningsidevc.service.WeixinUserService;
import com.morningsidevc.utils.LoginUtils;
import com.morningsidevc.web.response.JsonResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liang.liu
 */
@Controller
public class WeixinLoginController extends BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private WeixinUserService weixinUserService;
    @Resource
    private UserAccountService userAccountService;
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private WeixinUserInfoMapper weixinUserInfoMapper;


    @RequestMapping(value = "/weixinlogin", method = RequestMethod.GET)
    public String weixinlogin(Model model, String code, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(code)) {
            WeixinUser weixinUser = weixinUserService.authWeixinUserInfo(code);
            if (weixinUser == null || StringUtils.isNotBlank(weixinUser.getUnionId())) {
                return "redirect:/community";
            }

            WeixinUserInfo weixinUserInfo = weixinUserService.getWeixinUserInfoByUnionid(weixinUser.getUnionId());
            if (weixinUserInfo == null) {
                Gson gson = new Gson();
                model.addAttribute("weixinUserInfo", gson.toJson(weixinUser));
                return "bind";
            }

            LoginUtils.signon(weixinUserInfo.getUserid(), true, request, response);
        }

        return "redirect:/community";
    }

    /* Ajax json */
    @RequestMapping(value = "/ajax/regbind", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResponse regBind(String email, String password, UserInfo userInfo, String weixinInfo, HttpServletRequest request, HttpServletResponse response) {
        JsonResponse jsonResponse = new JsonResponse();
        try{
            int userId = userAccountService.create(email, password);
            if (userId <= 0) {
                jsonResponse.setCode(400);
                return jsonResponse;
            }
            userInfo.setUserid(userId);

            if (StringUtils.isNotBlank(weixinInfo)) {
                Gson gson = new Gson();
                WeixinUser weixinUser = gson.fromJson(weixinInfo, WeixinUser.class);
                WeixinUserInfo weixinUserInfo = generateWeixinUserInfo(weixinUser);
                weixinUserInfo.setUserid(userId);
                weixinUserInfoMapper.insertSelective(weixinUserInfo);

                userInfo.setAvatarurl(weixinUserInfo.getAvatarurl());
            }

            UserInfo newUser = userInfoService.createUser(userInfo);
            Assert.notNull(newUser);
            LoginUtils.signon(userId, true, request, response);
            jsonResponse.setCode(200);
        }catch (Exception e){
            jsonResponse.setCode(500);
            LOGGER.info("", e);
        }
        return jsonResponse;
    }

    /* Ajax json */
    @RequestMapping(value = "/ajax/loginbind", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResponse loginBind(String account, String password, String weixinInfo, HttpServletRequest request, HttpServletResponse response) {
        int userId = userAccountService.validate(account, password);
        JsonResponse jsonResponse = new JsonResponse();
        if (userId <= 0) {
            jsonResponse.setCode(400);
            return jsonResponse;
        }

        if (StringUtils.isNotBlank(weixinInfo)) {
            Gson gson = new Gson();
            WeixinUser weixinUser = gson.fromJson(weixinInfo, WeixinUser.class);
            WeixinUserInfo weixinUserInfo = generateWeixinUserInfo(weixinUser);
            weixinUserInfo.setUserid(userId);
            weixinUserInfoMapper.insertSelective(weixinUserInfo);

            UserInfo userInfo = userInfoService.loadUserInfoById(userId);
            userInfo.setAvatarurl(weixinUserInfo.getAvatarurl());
            userInfoService.updateUserInfoSelective(userInfo);
        }

        LoginUtils.signon(userId, true, request, response);
        jsonResponse.setCode(200);
        return jsonResponse;
    }

    private WeixinUserInfo generateWeixinUserInfo(WeixinUser weixinUser) {
        WeixinUserInfo weixinUserInfo = new WeixinUserInfo();
        weixinUserInfo.setOpenid(weixinUser.getOpenId());
        weixinUserInfo.setAvatarurl(weixinUser.getHeadImgUrl());
        weixinUserInfo.setWeixinusername(weixinUser.getNickName());
        weixinUserInfo.setGender((byte) weixinUser.getSex());
        weixinUserInfo.setProvince(weixinUser.getProvince());
        weixinUserInfo.setCity(weixinUser.getCity());
        weixinUserInfo.setCountry(weixinUser.getCountry());
        weixinUserInfo.setPrivilege(weixinUser.getPrivilege());
        weixinUserInfo.setUnionid(weixinUser.getUnionId());

        return weixinUserInfo;
    }

}
