/**
 * 
 */
package com.morningsidevc.web.controller;

import com.morningsidevc.enums.HttpResponseStatus;
import com.morningsidevc.po.gen.FeedInfo;
import com.morningsidevc.service.FeedInfoService;
import com.morningsidevc.service.FeedLikeService;
import com.morningsidevc.web.response.FeedLikeResponse;
import com.morningsidevc.web.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author yangna
 *
 */
@Controller
@RequestMapping("community")
public class LikeController extends BaseController{

    private static final Logger LOG = LoggerFactory.getLogger(LikeController.class);

    @Resource
    FeedLikeService feedLikeService;
    @Resource
    FeedInfoService feedInfoService;

    @RequestMapping(value = "addlike", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResponse addLike(@RequestParam(value = "feedId", required = true)Integer feedId){
        JsonResponse response = new JsonResponse();
        
    	if (!super.isLogin()) {
    		return new JsonResponse(HttpResponseStatus.nologinCode, HttpResponseStatus.nologinMsg);
    	}       
        
        try {
            Integer likeId = feedLikeService.addlike(feedId, getUserId());
            Assert.state(likeId != null && likeId > 0);
            FeedInfo feedInfo = feedInfoService.loadFeedInfo(feedId);
            FeedLikeResponse feedLikeResponse = new FeedLikeResponse();
            feedLikeResponse.setFeedId(feedId);
            feedLikeResponse.setLikeId(likeId);
            feedLikeResponse.setLikeCount(feedInfo.getLikecount());
            response.setCode(200);
            response.setMsg(feedLikeResponse);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg("服务器错误!");
            LOG.info("", e);
        }
        return response;
    }
    @RequestMapping(value = "deletelike", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResponse deleteLike(@RequestParam(value = "feedId", required = true) Integer feedId){
        JsonResponse response = new JsonResponse();
        
    	if (!super.isLogin()) {
    		return new JsonResponse(HttpResponseStatus.nologinCode, HttpResponseStatus.nologinMsg);
    	}    
        
        try {
            Integer ret = feedLikeService.deletelike(feedId, getUserId());
            Assert.state(ret != null && ret > 0);
            FeedInfo feedInfo = feedInfoService.loadFeedInfo(feedId);
            FeedLikeResponse feedLikeResponse = new FeedLikeResponse();
            feedLikeResponse.setFeedId(feedId);
            feedLikeResponse.setLikeCount(feedInfo.getLikecount());
            response.setCode(200);
            response.setMsg(feedLikeResponse);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg("服务器错误!");
            LOG.info("", e);
        }
        return response;
    }

}
