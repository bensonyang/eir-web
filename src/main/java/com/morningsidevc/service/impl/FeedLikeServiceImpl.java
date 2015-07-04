/**
 * 
 */
package com.morningsidevc.service.impl;

import com.morningsidevc.dao.gen.FeedInfoMapper;
import com.morningsidevc.dao.gen.FeedLikeMsgMapper;
import com.morningsidevc.po.gen.FeedInfo;
import com.morningsidevc.po.gen.FeedLikeMsg;
import com.morningsidevc.po.gen.FeedLikeMsgExample;
import org.springframework.stereotype.Component;

import com.morningsidevc.service.FeedLikeService;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.*;

/**
 * @author yangna
 *
 */
@Component
public class FeedLikeServiceImpl implements FeedLikeService {

    @Resource
    private FeedLikeMsgMapper feedLikeMsgMapper;
    @Resource
    private FeedInfoMapper feedInfoMapper;

    @Override
    public Integer addlike(Integer feedId, Integer currentUserId) throws Exception{
        Assert.state(feedId != null);
        FeedInfo feedInfo = feedInfoMapper.selectByPrimaryKey(feedId);
        Assert.notNull(feedInfo);
        FeedLikeMsg oldFeedLikeMsg = findSpecialFeedLikeMsg(feedId, currentUserId);
        if(oldFeedLikeMsg != null){
            return  oldFeedLikeMsg.getLikeid();
        }
        feedInfo.setLikecount(feedInfo.getLikecount() + 1);
        feedInfoMapper.updateByPrimaryKey(feedInfo);
        FeedLikeMsg feedLikeMsg = new FeedLikeMsg();
        feedLikeMsg.setAddtime(new Date());
        feedLikeMsg.setFeedid(feedId);
        feedLikeMsg.setUserid(currentUserId);
        feedLikeMsg.setFeeduserid(feedInfo.getUserid());
        return feedLikeMsgMapper.insert(feedLikeMsg);
    }

    @Override
    public Integer deletelike(Integer feedId, Integer currentUserId) throws Exception{
        FeedInfo feedInfo = feedInfoMapper.selectByPrimaryKey(feedId);
        feedInfo.setLikecount(feedInfo.getLikecount() - 1);
        feedInfoMapper.updateByPrimaryKey(feedInfo);
        FeedLikeMsgExample example = new FeedLikeMsgExample();
        example.createCriteria().andUseridEqualTo(currentUserId).andFeedidEqualTo(feedId);
        return feedLikeMsgMapper.deleteByExample(example);

    }

    //获取当前用户对指定动态的点赞信息
    private FeedLikeMsg findSpecialFeedLikeMsg(Integer feedId, Integer userId){
        Assert.state(feedId != null && userId != null);
        FeedLikeMsgExample example = new FeedLikeMsgExample();
        example.createCriteria().andFeedidEqualTo(feedId)
                .andUseridEqualTo(userId);
        List<FeedLikeMsg> feedLikeMsgs = feedLikeMsgMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(feedLikeMsgs)){
            return null;
        }
        return feedLikeMsgs.get(0);
    }

    @Override
    public Map<Integer, FeedLikeMsg> findIsLiked(List<Integer> feedIds, Integer currentId) throws Exception {
        FeedLikeMsgExample example = new FeedLikeMsgExample();
        example.createCriteria().andUseridEqualTo(currentId).andFeedidIn(feedIds);
        List<FeedLikeMsg> feedLikeMsgs = feedLikeMsgMapper.selectByExample(example);
        return buildFeedLikeMap(feedLikeMsgs);
    }

    @Override
    public Integer countFeedCount(Integer feedId, Integer currentUserId) {
        FeedLikeMsgExample example = new FeedLikeMsgExample();
        example.createCriteria().andUseridEqualTo(currentUserId).andFeedidEqualTo(feedId);
        Integer count = feedLikeMsgMapper.countByExample(example);
        return count;
    }


    private Map<Integer, FeedLikeMsg> buildFeedLikeMap(List<FeedLikeMsg> feedLikeMsgs){
        Map<Integer, FeedLikeMsg> feedLikeMsgMap = new HashMap<Integer, FeedLikeMsg>();
        for(FeedLikeMsg feedLikeMsg : feedLikeMsgs){
            feedLikeMsgMap.put(feedLikeMsg.getFeedid(), feedLikeMsg);
        }
        return feedLikeMsgMap;
    }
}
