/**
 * 
 */
package com.morningsidevc.service.impl;

import com.morningsidevc.dao.gen.FeedInfoMapper;
import com.morningsidevc.dao.gen.FeedLikeMsgMapper;
import com.morningsidevc.po.gen.FeedInfo;
import com.morningsidevc.po.gen.FeedLikeMsg;
import com.morningsidevc.po.gen.FeedLikeMsgExample;
import com.morningsidevc.service.FeedInfoService;
import org.springframework.stereotype.Component;

import com.morningsidevc.service.FeedLikeService;
import org.springframework.transaction.annotation.Transactional;
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
    private FeedInfoService feedInfoService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Integer addlike(Integer feedId, Integer currentUserId) throws Exception{
        FeedInfo feedInfo = feedInfoService.loadFeedInfo(feedId);
        feedInfoService.addFeedLikeCountByOne(feedId);
        FeedLikeMsg oldFeedLikeMsg = findSpecialFeedLikeMsg(feedId, currentUserId);
        if(oldFeedLikeMsg != null){
            return  oldFeedLikeMsg.getLikeid();
        }
        FeedLikeMsg feedLikeMsg = new FeedLikeMsg();
        feedLikeMsg.setAddtime(new Date());
        feedLikeMsg.setFeedid(feedId);
        feedLikeMsg.setUserid(currentUserId);
        feedLikeMsg.setFeeduserid(feedInfo.getUserid());
        return feedLikeMsgMapper.insert(feedLikeMsg);
    }

    @Override
    @Transactional
    public Integer deletelike(Integer feedId, Integer currentUserId) throws Exception{
        feedInfoService.cutFeedLikeCountByOne(feedId);
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
    public Map<Integer, FeedLikeMsg> findIsLiked(List<Integer> feedIds, Integer currentUserId) throws Exception {
        FeedLikeMsgExample example = new FeedLikeMsgExample();
        example.createCriteria().andFeedidIn(feedIds).andUseridEqualTo(currentUserId);
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
