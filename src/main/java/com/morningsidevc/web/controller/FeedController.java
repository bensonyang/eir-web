/**
 * 
 */
package com.morningsidevc.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.morningsidevc.po.gen.UserInfo;
import com.morningsidevc.service.FeedInfoService;
import com.morningsidevc.vo.Feed;
import com.morningsidevc.vo.LinkFeed;
import com.morningsidevc.vo.ShuoFeed;
import com.morningsidevc.web.response.FeedResponse;
import com.morningsidevc.web.response.JsonResponse;

/**
 * @author yangna
 *
 */
@Controller
public class FeedController {
	@Resource
    private FeedInfoService feedInfoService;

	/* Ajax json */
	@RequestMapping(value = "/community/morefeed", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResponse moreFeed(@RequestParam(value="lastIndex", required=false) Integer lastIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		if (lastIndex == null) {
			lastIndex = 0;
		}
		
		if (pageSize == null) {
			pageSize = 10;
		}
		
		JsonResponse jsonResponse = new JsonResponse();
		FeedResponse feedResponse = new FeedResponse();	
		
		List<Feed> feedList = this.feedInfoService.findFeeds(lastIndex, pageSize);
		if (feedList != null && feedList.size() != 0) {
			feedResponse.setFeeds(feedList);
			feedResponse.setLastFeedIndex(lastIndex+feedList.size());
			feedResponse.setTotalFeedCount(feedList.size());
		}
		
		jsonResponse.setCode(200);
		jsonResponse.setMsg(feedResponse);
		
    	return jsonResponse;
	}
	
	
}
