package com.morningsidevc.wechart.bo;

import com.morningsidevc.crawler.HTMLBean;
import com.morningsidevc.crawler.HTMLCrawlerUtils;
import com.morningsidevc.enums.WeiXinType;
import com.morningsidevc.po.gen.WeixinUserInfo;
import com.morningsidevc.service.FeedInfoService;
import com.morningsidevc.service.WeixinUserService;
import com.morningsidevc.utils.SpringLocator;
import com.morningsidevc.wechart.enums.MsgTypeEnum;
import com.morningsidevc.wechart.replymessage.util.MsgConvertUtil;
import com.morningsidevc.wechart.replymessage.xml.XmlText;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shichao.liao on 14-9-27.
 */
public class WeChartAddFeedBO {

    private final static Logger logger = LoggerFactory.getLogger(WeChartAddFeedBO.class);

    private static Map<String, Date> AddFeedModeStateMap = new ConcurrentHashMap<String, Date>();

    private static FeedInfoService feedInfoService = SpringLocator.getBean("feedInfoService");

    private static WeixinUserService weixinUserService = SpringLocator.getBean("weixinUserService");


    public static boolean isAddFeedMode(Map<String, String> requestMap) {
        boolean isAddFeed = false;

        String fromUser = requestMap.get("FromUserName");
        if (StringUtils.isNotBlank(fromUser)) {
            try {
                isAddFeed = AddFeedModeStateMap.containsKey(fromUser);
            } catch (Exception e) {
                isAddFeed = false;
                logger.error("WeChartAddFeedBO.isAddFeedMode error!", e);
            }
        }

        return isAddFeed;
    }

    public static String enterAddFeedMode(Map<String, String> requestMap) {
        String fromUser = requestMap.get("FromUserName");
        if (StringUtils.isNotBlank(fromUser)) {
            AddFeedModeStateMap.put(fromUser, new Date());
            if (AddFeedModeStateMap.size() % 1000 == 0) {
                cleanOldUser();
            }
        }

        String message = "您好，现在您可以发布动态了（如需退出动态发布模式，请回复Q）";
        XmlText xmlText = XmlMessageBO.prepareXmlText(requestMap);
        xmlText.setContent(message);
        return MsgConvertUtil.parseMsg2XMLStr(xmlText);
    }

    private static void cleanOldUser() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        Date oldTime = calendar.getTime();
        Iterator<String> keyIterator = AddFeedModeStateMap.keySet().iterator();
        while (keyIterator.hasNext()) {
            if (oldTime.after(AddFeedModeStateMap.get(keyIterator.next()))) {
                keyIterator.remove();
            }
        }
    }

    public static String exitAddFeedMode(Map<String, String> requestMap) {
        String fromUser = requestMap.get("FromUserName");
        if (StringUtils.isNotBlank(fromUser)) {
            AddFeedModeStateMap.remove(fromUser);
        }

        String message = "您已退出动态发布模式～～";
        XmlText xmlText = XmlMessageBO.prepareXmlText(requestMap);
        xmlText.setContent(message);
        return MsgConvertUtil.parseMsg2XMLStr(xmlText);
    }

    public static String processInAddFeedMode(Map<String, String> requestMap) {
        String fromUser = requestMap.get("FromUserName");
        if (StringUtils.isBlank(fromUser)) {
            return "";
        }

        String replyMsg = "";
        String content = requestMap.get("Content");
        if (!MsgTypeEnum.TEXT.getValue().equals(requestMap.get("MsgType")) || StringUtils.isBlank(content)) {
            replyMsg = "目前动态发布模式紧支持文本和链接信息，如需退出动态发布模式请输入：Q";
        } else if ("Q".equals(content)) {
            exitAddFeedMode(requestMap);
            replyMsg = "您已离开动态发布模式～～～";
        } else {
            String unionId = weixinUserService.getWeixinUserUnionIdByOpenId(fromUser, WeiXinType.WECHAT.getChannel());
            if (StringUtils.isBlank(unionId)) {
                replyMsg = "账号绑定信息异常，请确认账号绑定情况或重新绑定～～";
            }
            WeixinUserInfo weixinUserInfo = weixinUserService.getWeixinUserInfoByUnionid(unionId);
            if (weixinUserInfo == null) {
                replyMsg = "账号绑定信息异常，请确认账号绑定情况或重新绑定～～～";

            } else {
                if (content.startsWith("http://") || content.startsWith("https://")) {
                    HTMLBean html = HTMLCrawlerUtils.get(content);
                    if (html == null || StringUtils.isBlank(html.getUrl()) || StringUtils.isBlank(html.getPageTitle()) || StringUtils.isBlank(html.getPageAbstract())) {
                        replyMsg = "获取推荐网址信息失败！";
                    } else {
                        try {
                            feedInfoService.addFeed(weixinUserInfo.getUserid(), html.getUrl(), html.getPageTitle(), html.getPageAbstract(), "");
                        } catch (Exception e) {
                            replyMsg = "抱歉，服务器添加推荐链接异常！";
                            logger.error("feedInfoService add link error!", e);
                        }
                    }
                } else {
                    try {
                        feedInfoService.addFeed(weixinUserInfo.getUserid(), content, "");
                    } catch (Exception e) {
                        replyMsg = "抱歉，服务器添加说说异常！";
                        logger.error("feedInfoService add message error!", e);
                    }
                }
            }
        }

        if (StringUtils.isNotEmpty(replyMsg)) {
            replyMsg = "发表成功～～";
        }

        XmlText xmlText = XmlMessageBO.prepareXmlText(requestMap);
        xmlText.setContent(replyMsg);
        return MsgConvertUtil.parseMsg2XMLStr(xmlText);
    }

}