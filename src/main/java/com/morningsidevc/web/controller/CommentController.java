/**
 * 
 */
package com.morningsidevc.web.controller;

import com.morningsidevc.service.FeedCommentService;
import com.morningsidevc.vo.Comment;
import com.morningsidevc.web.request.AddCommentRequest;
import com.morningsidevc.web.response.JsonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author yangna
 *
 */
@Controller
@RequestMapping("community")
public class CommentController extends BaseController{

    @Resource
    FeedCommentService feedCommentService;

    @ResponseBody
    @RequestMapping(value = "addcomment", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JsonResponse addComment(AddCommentRequest request){
        JsonResponse response = new JsonResponse();
        try {
            Comment comment = feedCommentService.addComment(request, getUserId());
            Assert.notNull(comment);
            response.setCode(200);
            response.setMsg(comment);
        }catch (Exception e){
            response.setCode(200);
            response.setMsg("服务器错误");
        }
        return response;
    }
}
