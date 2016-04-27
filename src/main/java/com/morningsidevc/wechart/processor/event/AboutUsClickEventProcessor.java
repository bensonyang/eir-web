package com.morningsidevc.wechart.processor.event;

import com.morningsidevc.wechart.bo.XmlMessageBO;
import com.morningsidevc.wechart.processor.WeChartBaseProcessor;
import com.morningsidevc.wechart.replymessage.util.MsgConvertUtil;
import com.morningsidevc.wechart.replymessage.xml.XmlText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-29, Time: 下午3:11
 */
public class AboutUsClickEventProcessor extends WeChartBaseProcessor {

    private final static Logger logger = LoggerFactory.getLogger(AboutUsClickEventProcessor.class);

    @Override
    public String processRequest(Map<String, String> requestMap) {
        XmlText xmlText = XmlMessageBO.prepareXmlText(requestMap);
        xmlText.setContent("晨兴资本是中国最早从事早期风险投资的机构之一，我们寻找、支持、激励孤独的创业者，且共享他们卓越的远见，为其提供我们的洞察力、行业经验，以及在创业中从精神到所有经营运作的支持。Morningside, on YOUR side!\nBP请发送至：tmt@morningsidevc.com");
        return MsgConvertUtil.parseMsg2XMLStr(xmlText);
    }
}
