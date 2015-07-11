/**
 * 
 */
package com.morningsidevc.web.controller;

import com.morningsidevc.service.FeedCommentService;
import com.morningsidevc.vo.Comment;
import com.morningsidevc.web.request.AddCommentRequest;
import com.morningsidevc.web.response.DeleteCommentResponse;
import com.morningsidevc.web.response.JsonResponse;
import com.morningsidevc.web.response.MoreCommentResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    @RequestMapping(value = "addcomment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResponse addComment(AddCommentRequest request){
        JsonResponse response = new JsonResponse();
        try {
            Comment comment = feedCommentService.addComment(request, getUserId());
            Assert.notNull(comment);
            comment.setUserPic("/static/images/pic1.jpeg");
            comment.setToUserPic("/static/images/pic1.jpeg");
            comment.setCanDelete(true);
            response.setCode(200);
            response.setMsg(comment);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg("服务器错误");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "deletecomment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResponse deleteComment(Integer commentId){
        JsonResponse response = new JsonResponse();
        try {
            Assert.notNull(commentId);
            DeleteCommentResponse deleteCommentResponse = feedCommentService.deleteComment(commentId);
            Assert.notNull(deleteCommentResponse);
            response.setCode(200);
            response.setMsg(deleteCommentResponse);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg("服务器错误");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "morecomment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResponse moreComment(Integer lastCommentIndex, Integer feedId, Integer pageSize){
        JsonResponse response = new JsonResponse();
        try {
            Assert.notNull(lastCommentIndex);Assert.notNull(feedId);Assert.notNull(pageSize);
            MoreCommentResponse moreCommentResponse = new MoreCommentResponse();
            List<Comment> commentList = feedCommentService.moreComment(lastCommentIndex,feedId,pageSize,getUserId());
            moreCommentResponse.setComments(commentList);
            moreCommentResponse.setSize(commentList.size());
            moreCommentResponse.setLastCommentIndex(lastCommentIndex(commentList));
            response.setCode(200);
            response.setMsg(moreCommentResponse);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg("服务器错误");
        }
        return response;
    }

    private Integer lastCommentIndex(List<Comment> comments){
        if(CollectionUtils.isEmpty(comments)) return Integer.MAX_VALUE;
        if(comments.size() == 1) return comments.get(0).getCommentId();//只有一个 不用排序
        Collections.sort(comments, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return o1.getCommentId().compareTo(o2.getCommentId());
            }
        });
        return comments.get(0).getCommentId();
    }


}
