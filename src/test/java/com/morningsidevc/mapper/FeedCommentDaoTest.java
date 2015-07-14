package com.morningsidevc.mapper;

import com.morningsidevc.dao.FeedCommentDao;
import com.morningsidevc.utils.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author float.lu
 */
public class FeedCommentDaoTest extends BaseTest {

    @Resource
    private FeedCommentDao feedCommentDao;

    @Test
    public void tasks(){
        feedCommentDao.selectFeedCommentCount(Arrays.asList(78,79));
    }
}
