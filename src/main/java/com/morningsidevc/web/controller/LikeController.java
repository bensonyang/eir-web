/**
 * 
 */
package com.morningsidevc.web.controller;

import com.morningsidevc.service.FeedLikeService;
import com.morningsidevc.web.response.FeedLikeResponse;
import com.morningsidevc.web.response.JsonResponse;
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
public class LikeController {

    @Resource
    FeedLikeService feedLikeService;

    @RequestMapping(value = "addlike", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResponse addLike(@RequestParam(value = "feedId", required = true)Integer feedId){
        JsonResponse response = new JsonResponse();
        try {
            Integer likeId = feedLikeService.addlike(feedId);
            Assert.state(likeId != null && likeId > 0);
            FeedLikeResponse feedLikeResponse = new FeedLikeResponse();
            feedLikeResponse.setFeedId(feedId);
            feedLikeResponse.setLikeId(likeId);
            response.setCode(200);
            response.setMsg(feedLikeResponse);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg("服务器错误!");
        }
        return response;
    }
    @RequestMapping(value = "deletelike", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResponse deleteLike(@RequestParam(value = "feedId", required = true) Integer feedId){
        JsonResponse response = new JsonResponse();
        try {
            Integer ret = feedLikeService.deletelike(feedId);
            Assert.state(ret != null && ret > 0);
            FeedLikeResponse feedLikeResponse = new FeedLikeResponse();
            feedLikeResponse.setFeedId(feedId);
            response.setCode(200);
            response.setMsg(feedLikeResponse);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg("服务器错误!");
        }
        return response;
    }

}
