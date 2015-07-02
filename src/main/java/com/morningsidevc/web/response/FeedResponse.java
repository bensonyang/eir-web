/**
 * 
 */
package com.morningsidevc.web.response;

import java.util.List;
import java.util.Map;

import com.morningsidevc.vo.Feed;

/**
 * @author yangna
 *
 */
public class FeedResponse {
	private Integer lastFeedIndex;
	private Integer totalFeedCount;
	
	private List<Feed> feeds;

	public Integer getLastFeedIndex() {
		return lastFeedIndex;
	}

	public void setLastFeedIndex(Integer lastFeedIndex) {
		this.lastFeedIndex = lastFeedIndex;
	}

	public Integer getTotalFeedCount() {
		return totalFeedCount;
	}

	public void setTotalFeedCount(Integer totalFeedCount) {
		this.totalFeedCount = totalFeedCount;
	}

	public List<Feed> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<Feed> feeds) {
		this.feeds = feeds;
	}
	
}
