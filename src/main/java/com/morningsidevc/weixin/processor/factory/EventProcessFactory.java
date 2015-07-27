package com.morningsidevc.weixin.processor.factory;

import com.morningsidevc.weixin.processor.WeiXinProcessor;

import java.util.Map;

/**
 * 事件分发器 Created by shichao.liao on 14-9-4.
 */
public class EventProcessFactory extends ProcessorFactory {

    @Override
    public WeiXinProcessor loadWeiXinProcessor(Map<String, String> requestMap) {
        /*
        String eventType = requestMap.get("Event");
        String eventKey = requestMap.get("EventKey");

        StringBuffer processorKey = new StringBuffer(eventType.toLowerCase());

        if (EventTypeEnum.SCAN.getValue().equalsIgnoreCase(eventType)) {

            int sceneId = Integer.parseInt(eventKey);

            //-- for Test JiShi
            if (sceneId > JiShiCodeBO.JISHI_SCENEID_THRESHOLD) {
                return ServiceLocator.getBean("simpleScanEventProcessor");
            }
            //-- end Test JiShi

            QRCodeDTO codeDTO = QRCodeBO.loadQRCodeDTO(sceneId, WeixinTypeEnum.getWeixinTypeEnumByName(weixinType));
            if (codeDTO == null) {
                return null;
            }
            processorKey.append("_");
            processorKey.append(codeDTO.getSceneType());
        }

        if (EventTypeEnum.CLICK.getValue().equalsIgnoreCase(eventType)) {
            processorKey.append("_");
            processorKey.append(eventKey);
        }

        return processors.get(processorKey.toString());
         */

        return null;
    }

}
