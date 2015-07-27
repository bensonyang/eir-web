package com.morningsidevc.web.controller;

import com.morningsidevc.po.gen.Pic;
import com.morningsidevc.po.gen.UserInfo;
import com.morningsidevc.service.PicService;
import com.morningsidevc.service.UserInfoService;
import com.morningsidevc.web.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author float.lu
 */
@Controller
@RequestMapping("pic")
public class PicController extends BaseController{

    private static Logger LOG = LoggerFactory.getLogger(PicController.class);

    @Resource
    private PicService picService;
    @Resource
    private UserInfoService userInfoService;

    //produces = "application/octet-stream;charset=UTF-8" , produces = MediaType.IMAGE_JPEG_VALUE
    @RequestMapping(value = "{picId}", method = RequestMethod.GET , produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] load(@PathVariable("picId") Integer pidId) {
        Pic pic = new Pic();
        try {
            pic = picService.loadPic(pidId);
            }catch (Exception e){
            LOG.info("", e);
        }
        return pic.getPicdata();
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResponse upload(String base64Pic) {
        JsonResponse response = new JsonResponse();
        try {
            String[] datas = base64Pic.split(",");
            Pic pic = new Pic();
            pic.setPicdata(Base64Utils.decodeFromString(datas[datas.length - 1]));
            picService.insertPic(pic);
            UserInfo userInfo = new UserInfo();
            userInfo.setAvatarurl("/pic/" + pic.getPicid());
            userInfo.setUserid(getUserId());
            userInfoService.updateUserInfoSelective(userInfo);
            response.setCode(200);
            response.setMsg(pic.getPicid());
        }catch (Exception e){
            LOG.info("", e);
            response.setCode(500);
        }
        return response;
    }

}
