package com.morningsidevc.web.controller;

import com.morningsidevc.crawler.HTMLBean;
import com.morningsidevc.crawler.HTMLCrawlerUtils;
import com.morningsidevc.web.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author float.lu
 */
@Controller
@RequestMapping("crawler")
public class CrawlerController {

    private static Logger LOG = LoggerFactory.getLogger("crawler.controller");

    @RequestMapping(value = "get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResponse crawler(String url){
        JsonResponse response = new JsonResponse();
        try {
            HTMLBean html = HTMLCrawlerUtils.get(url);
            response.setCode(200);
            response.setMsg(html);
        }catch (Exception e){
            response.setCode(500);
            LOG.info("", e);
        }
        return response;
    }
}
