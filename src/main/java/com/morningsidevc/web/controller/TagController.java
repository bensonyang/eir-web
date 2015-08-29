package com.morningsidevc.web.controller;

import com.morningsidevc.service.TagInfoService;
import com.morningsidevc.vo.Tag;
import com.morningsidevc.web.response.JsonResponse;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author float.lu
 */
@Controller
public class TagController extends BaseController{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(TagController.class);

    @Resource
    private TagInfoService tagInfoService;

    @ResponseBody
    @RequestMapping(value = "tags", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResponse deleteFeed(Integer feedId) {
        JsonResponse response = new JsonResponse();
        try {
            List<Tag> tagList = tagInfoService.findTags();
            response.setCode(200);
            response.setMsg(tagList);
        }catch (Exception e){
            response.setCode(500);
            LOG.info("find tags meet an exception:", e);
        }
        return response;
    }
}
