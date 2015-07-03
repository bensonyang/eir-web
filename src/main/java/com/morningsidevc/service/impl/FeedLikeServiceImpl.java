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
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    public Integer addlike(Integer feedId) {
        Integer userId = 0;
        FeedLikeMsgExample example = new FeedLikeMsgExample();
        example.createCriteria().andUseridEqualTo(userId).andFeedidEqualTo(feedId);
        return feedLikeMsgMapper.deleteByExample(example);
    }

    @Override
    public Integer deletelike(Integer feedId, Integer currentUserId) {
        Assert.state(feedId != null);
        FeedInfo feedInfo = feedInfoMapper.selectByPrimaryKey(feedId);
        Assert.notNull(feedInfo);
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

    //获取当前用户对指定动态的点赞信息
    private FeedLikeMsg findSpecialFeedLikeMsg(@RequestParam(value = "feedId", required = true)Integer feedId, Integer userId){
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
}
