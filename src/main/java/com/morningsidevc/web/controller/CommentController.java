/**
 * 
 */
package com.morningsidevc.web.controller;

import com.morningsidevc.enums.HttpResponseStatus;
import com.morningsidevc.enums.WeiXinType;
import com.morningsidevc.po.gen.WeixinUserInfo;
import com.morningsidevc.service.FeedCommentService;
import com.morningsidevc.service.UserInfoService;
import com.morningsidevc.service.WeixinUserService;
import com.morningsidevc.vo.Comment;
import com.morningsidevc.vo.User;
import com.morningsidevc.web.request.AddCommentRequest;
import com.morningsidevc.web.response.DeleteCommentResponse;
import com.morningsidevc.web.response.JsonResponse;
import com.morningsidevc.web.response.MoreCommentResponse;
import com.morningsidevc.wechart.bo.RedirectBO;
import com.morningsidevc.wechart.service.WeChartMessageService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author yangna
 *
 */
@Controller
@RequestMapping("community")
public class CommentController extends BaseController{

    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    @Resource
    FeedCommentService feedCommentService;
    @Resource
    UserInfoService userInfoService;
    @Resource
    WeChartMessageService weChartMessageService;
    @Resource
    WeixinUserService weixinUserService;

    @ResponseBody
    @RequestMapping(value = "addcomment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResponse addComment(AddCommentRequest request){    	
    	JsonResponse response = new JsonResponse();
    	
    	if (!super.isLogin()) {
    		return new JsonResponse(HttpResponseStatus.nologinCode, HttpResponseStatus.nologinMsg);
    	}
    	
    	if (request.getContent() == null || request.getContent().trim().equalsIgnoreCase("")) {
    		return new JsonResponse(HttpResponseStatus.emptyInputCode, HttpResponseStatus.emptyInputMsg);
    	}
    	
        try {
            Comment comment = feedCommentService.addComment(request, getUserId());
            User user = userInfoService.load(getUserId());
            comment.setUserPic(user.getAvatarUrl());
            comment.setToUserPic("/static/images/pic1.jpeg");
            comment.setCanDelete(true);
            response.setCode(200);
            response.setMsg(comment);

            // 绑定微信用户发送模版消息通知有新的评论
            LOG.info("----toUserId: " + comment.getToUserId());
            WeixinUserInfo toUserWeixinInfo = weixinUserService.getWeixinUserInfoByUserId(comment.getToUserId());
            LOG.info("----toUserWeixinInfo: " + toUserWeixinInfo);

            if (toUserWeixinInfo != null && StringUtils.isNotBlank(toUserWeixinInfo.getUnionid())) {
                String toOpenId = weixinUserService.getWeixinUserOpenIdByUnionId(toUserWeixinInfo.getUnionid(), WeiXinType.WECHAT.getChannel());
                LOG.info("----toOpenId: " + toOpenId);
                String toUrl = RedirectBO.generateUserAuthorizeUrl("http://www.msvcplus.com/mfeed?feedId=" + request.getFeedId(), WeiXinType.WECHAT);
                weChartMessageService.sendCommentTemplateMessage(toOpenId, toUrl, user.getRealName(), comment.getCommentTime(), comment.getContent());
            }

        }catch (Exception e){
            response.setCode(500);
            response.setMsg("服务器错误");
            LOG.info("", e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "deletecomment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonResponse deleteComment(Integer commentId){
        JsonResponse response = new JsonResponse();
        
    	if (!super.isLogin()) {
    		return new JsonResponse(HttpResponseStatus.nologinCode, HttpResponseStatus.nologinMsg);
    	}      
        
        try {
            Assert.notNull(commentId);
            DeleteCommentResponse deleteCommentResponse = feedCommentService.deleteComment(commentId);
            Assert.notNull(deleteCommentResponse);
            response.setCode(200);
            response.setMsg(deleteCommentResponse);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg("服务器错误");
            LOG.info("", e);
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
            moreCommentResponse.setLastCommentIndex(lastCommentIndex(new ArrayList<Comment>(commentList)));
            response.setCode(200);
            response.setMsg(moreCommentResponse);
        }catch (Exception e){
            response.setCode(500);
            response.setMsg("服务器错误");
            LOG.info("", e);
        }
        return response;
    }

    private Integer lastCommentIndex(List<Comment> comments){
        if(CollectionUtils.isEmpty(comments)) return 0;
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
