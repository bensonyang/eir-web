package com.morningsidevc.service;

import com.morningsidevc.utils.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author float.lu
 */
public class FeedLikeServiceTest extends BaseTest {
    @Resource
    private FeedLikeService feedLikeService;

    @Test
    public void like(){
        feedLikeService.addlike(0);
    }
}