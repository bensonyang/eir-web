/**
 * 
 */
package com.morningsidevc.web.controller;

import java.util.*;

import javax.annotation.Resource;

import com.morningsidevc.dao.gen.WeiboMsgMapper;
import com.morningsidevc.enums.FeedType;
import com.morningsidevc.enums.HttpResponseStatus;
import com.morningsidevc.enums.MsgType;
import com.morningsidevc.po.gen.FeedInfo;
import com.morningsidevc.po.gen.WebPageMsg;
import com.morningsidevc.service.UserInfoService;
import com.morningsidevc.service.WebPageMsgService;
import com.morningsidevc.service.WeiboMsgService;
import com.morningsidevc.utils.DateTimeUtils;
import com.morningsidevc.vo.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.morningsidevc.po.gen.UserInfo;
import com.morningsidevc.service.FeedInfoService;
import com.morningsidevc.web.response.FeedResponse;
import com.morningsidevc.web.response.JsonResponse;

/**
 * @author yangna
 *
 */
@Controller
@RequestMapping("community")
public class FeedController extends BaseController{

	private static final Logger LOG = LoggerFactory.getLogger(FeedController.class);
	@Resource
    private FeedInfoService feedInfoService;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private WeiboMsgService weiboMsgService;
	@Resource
	private WebPageMsgService webPageMsgService;

	/* Ajax json */
	@RequestMapping(value = "morefeed", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResponse moreFeed(@RequestParam(value="startIndex", required=false) Integer startIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize,
								 Integer userId, Integer feedId) {
		// 设定起始Index
		if (startIndex == null || startIndex < 0) {
			startIndex = 0;
		}
		
		
		// 设定缺省结果大小
		if (pageSize == null || pageSize < 1) {
			pageSize = 9;
		}
		
		JsonResponse jsonResponse = new JsonResponse();
		FeedResponse feedResponse = new FeedResponse();

		try {
			Integer uid = null;
			if(userId == null && feedId == null){
				uid = getUserId();
			}else{
				uid = userId;
			}
			List<Feed> feedList = this.feedInfoService.findFeeds(startIndex, pageSize, uid, feedId);
			if (feedList != null && feedList.size() != 0) {
				feedResponse.setFeeds(feedList);
				feedResponse.setLastFeedIndex(startIndex+feedList.size()-1);
				feedResponse.setTotalFeedCount(feedList.size());
			}
			jsonResponse.setCode(200);
			jsonResponse.setMsg(feedResponse);
		}catch (Exception e){
			jsonResponse.setCode(500);
			LOG.info("", e);

		}
    	return jsonResponse;
	}


	@RequestMapping(value = "shuofeed", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResponse addShuoFeed(String tagName, String content){
		JsonResponse response = new JsonResponse();
		
    	if (!super.isLogin()) {
    		return new JsonResponse(HttpResponseStatus.nologinCode, HttpResponseStatus.nologinMsg);
    	}		
		
		try{
			Assert.state(StringUtils.isNotBlank(content));
			if(StringUtils.isNotBlank(tagName)){
				tagName = "无";
			}
			if(getUserId() == 0){
				response.setCode(300);
				return response;
			}
			FeedInfo feedInfo = feedInfoService.addFeed(getUserId(), content, tagName);
			Assert.notNull(feedInfo);
			FeedResponse feedResponse = new FeedResponse();
			Feed feed = new Feed();
			feed.setAddTime(DateTimeUtils.date2SmartFormat(feedInfo.getAddtime()));
			feed.setFeedId(feedInfo.getFeedid());
			feed.setMsgId(feedInfo.getMsgid());
			feed.setFeedType(Integer.valueOf(MsgType.SHUOFEED));
			feed.setCommentCount(0);
			feed.setLikeCount(0);
			feed.setTag(feedInfo.getTagname());
			feed.setAuthorId(feedInfo.getUserid());
			WeiboMsgBody weiboMsgBody = weiboMsgService.loadMsgBody(feedInfo.getMsgid());
			feed.setMsgBody(weiboMsgBody);
			User user = userInfoService.load(feedInfo.getUserid());
			feed.setAuthor(user);
			feed.setCanDelete(true);
			feed.setComment(new ArrayList<Comment>());
			feed.setLastCommentIndex(0);
			feedResponse.setFeeds(Arrays.asList(new Feed[]{feed}));
			response.setCode(200);
			response.setMsg(feedResponse);
		}catch (Exception e){
			response.setCode(500);
			response.setMsg("服务器错误");
			LOG.info("", e);
		}
		return response;
	}

	@RequestMapping(value = "linkfeed", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResponse linkfeed(String tagName, String title, String content, String link){
		JsonResponse response = new JsonResponse();

		if (!super.isLogin()) {
			return new JsonResponse(HttpResponseStatus.nologinCode, HttpResponseStatus.nologinMsg);
		}

		try{
			Assert.state(StringUtils.isNotBlank(content));
			if(StringUtils.isNotBlank(tagName)){
				tagName = "无";
			}
			if(getUserId() == 0){
				response.setCode(300);
				return response;
			}
			FeedInfo feedInfo = feedInfoService.addFeed(getUserId(),link,title,content,tagName);
			Assert.notNull(feedInfo);
			FeedResponse feedResponse = new FeedResponse();
			Feed feed = new Feed();
			feed.setAddTime(DateTimeUtils.date2SmartFormat(feedInfo.getAddtime()));
			feed.setFeedId(feedInfo.getFeedid());
			feed.setMsgId(feedInfo.getMsgid());
			feed.setFeedType(Integer.valueOf(MsgType.SHUOFEED));
			feed.setCommentCount(0);
			feed.setLikeCount(0);
			feed.setTag(feedInfo.getTagname());
			feed.setAuthorId(feedInfo.getUserid());
			WebPageMsgBody webPageMsg = webPageMsgService.loadMsgBody(feedInfo.getMsgid());
			feed.setMsgBody(webPageMsg);
			User user = userInfoService.load(feedInfo.getUserid());
			feed.setAuthor(user);
			feed.setCanDelete(true);
			feed.setComment(new ArrayList<Comment>());
			feed.setLastCommentIndex(0);
			feedResponse.setFeeds(Arrays.asList(new Feed[]{feed}));
			response.setCode(200);
			response.setMsg(feedResponse);
		}catch (Exception e){
			response.setCode(500);
			response.setMsg("服务器错误");
			LOG.info("", e);
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "deletefeed", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public JsonResponse deleteFeed(Integer feedId){
		JsonResponse response = new JsonResponse();
		
    	if (!super.isLogin()) {
    		return new JsonResponse(HttpResponseStatus.nologinCode, HttpResponseStatus.nologinMsg);
    	}	
		
		try{
			feedInfoService.deleteFeed(feedId);
			response.setCode(200);
			response.setMsg(feedId);
		}catch (Exception e){
			response.setCode(500);
			response.setMsg("服务器错误");
			LOG.info("", e);
		}
		return  response;
	}
}
