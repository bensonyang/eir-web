package com.morningsidevc.web.controller;

import com.morningsidevc.utils.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-6-30, Time: 下午5:41
 */
public class BaseController {

    /**
     * 从request中获取已解密的userId
     *
     * @return
     */
    protected int getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request == null) {
            return 0;
        }
        String userId = (String) request.getAttribute(Constants.BIZ_CONTEXT_USER_ID);
        if (StringUtils.isBlank(userId)) {
            return 0;
        }
        try {
            return Integer.parseInt(userId);
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }

    protected boolean isLogin(){
        int uid = getUserId();
        if(uid != 0){
            return true;
        }
        return false;
    }


}
