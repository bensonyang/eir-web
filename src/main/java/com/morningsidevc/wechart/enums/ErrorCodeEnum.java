package com.morningsidevc.wechart.enums;

/**
 * Created by shichao.liao on 14-9-3.
 */
public enum ErrorCodeEnum {

    UNKNOWN_ERROR_CODE(-99999,"error code未知"),
    ACCESS_TOKEN_FETCH_FAILED(-5,"获取access_token失败"),
    NULL_ARGUMENT(-4,"空参数"),
    NULL_ERROR_CODE(-3,"无法获得error code"),
    NULL_RESPONSE_BODY(-2,"响应报文为空"),
    SYSTEM_BUSY(-1,"系统繁忙"),
    SUCCESS(0, "请求成功"),
    INVALID_TOKEN(40001, "AppSecret错误或access_token无效"),
    INVALID_CREDIT_TYPE(40002, "不合法的凭证类型"),
    INVALID_OPENID(40003, "不合法的OpenID"),
    INVALID_MEDIA_TYPE(40004, "不合法的媒体文件类型"),
    INVALID_FILE_TYPE(40005, "不合法的文件类型"),
    INVALID_FILE_SIZE(40006, "不合法的文件大小"),
    INVALID_MEDIA_FILE_ID(40007, "不合法的媒体文件id"),
    INVALID_MESSAGE_TYPE(40008, "不合法的消息类型"),
    INVALID_IMAGE_FILE_SIZE(40009, "不合法的图片文件大小"),
    INVALID_VOICE_FILE_SIZE(40010, "不合法的语音文件大小"),
    INVALID_VIDEO_FILE_SIZE(40011, "不合法的视频文件大小"),
    INVALID_THUMBNAILS_FILE_SIZE(40012, "不合法的缩略图文件大小"),
    INVALID_APPID(40013, "不合法的APPID"),
    INVALID_ACCESS_TOKEN(40014, "不合法的access_token"),
    INVALID_TABLE_TYPE(40015, "不合法的菜单类型"),
    INVALID_BUTTON_AMOUNT1(40016, "不合法的按钮个数"),
    INVALID_BUTTON_AMOUNT2(40017, "不合法的按钮个数"),
    INVALID_BUTTON_NAME_LENGTH(40018, "不合法的按钮名字长度"),
    INVALID_BUTTON_KEY_LENGTH(40019, "不合法的按钮KEY长度"),
    INVALID_BUTTON_URL_LENGTH(40020, "不合法的按钮URL长度"),
    INVALID_TABLE_VERSION(40021, "不合法的菜单版本号"),
    INVALID_SUB_TABLE_LEVEL(40022, "不合法的子菜单级数"),
    INVALID_SUB_TABLE_BUTTON_AMOUNT(40023, "不合法的子菜单按钮个数"),
    INVALID_SUB_TABLE_BUTTON_TYPE(40024, "不合法的子菜单按钮类型"),
    INVALID_SUB_TABLE_NAME_LENGTH(40025, "不合法的子菜单按钮名字长度"),
    INVALID_SUB_TABLE_KEY_LENGTH(40026, "不合法的子菜单按钮KEY长度"),
    INVALID_SUB_TABLE_URL_LENGTH(40027, "不合法的子菜单按钮URL长度"),
    INVALID_TABLE_USER(40028, "不合法的自定义菜单使用用户"),
    INVALID_OAUTH_CODE(40029, "不合法的oauth_code"),
    INVALID_REFRESH_TOKEN(40030, "不合法的refresh_token"),
    INVALID_OPENID_LIST(40031, "不合法的openid列表"),
    INVALID_OPENID_LIST_LENGTH(40032, "不合法的openid列表长度"),
    INVALID_REQUEST_CHARSET(40033, "不合法的请求字符，不能包含\\uxxxx格式的字符"),
    INVALID_ARGUMENT(40035, "不合法的参数"),
    INVALID_TEMPLATE_ID(40037, "不合法的模板ID"),
    INVALID_REQUEST_FORMAT(40038, "不合法的请求格式"),
    INVALID_URL_LENGTH(40039, "不合法的URL长度"),
    INVALID_GROUP_ID(40050, "不合法的分组id"),
    INVALID_GROUP_NAME(40051, "分组名字不合法"),
    MISSING_ACCESS_TOKEN(41001, "缺少access_token参数"),
    MISSING_APPID(41002, "缺少appid参数"),
    MISSING_REFRESH_TOKEN(41003, "缺少refresh_token参数"),
    MISSING_SECRET(41004, "缺少secret参数"),
    MISSING_MEDIA_FILE_DATA(41005, "缺少多媒体文件数据"),
    MISSING_MEDIA_ID(41006, "缺少media_id参数"),
    MISSING_SUB_TABLE_DATA(41007, "缺少子菜单数据"),
    MISSING_OAUTH_CODE(41008, "缺少oauth code"),
    MISSING_OPENID(41009, "缺少openid"),
    ACCESS_TOKEN_TIME_OUT(42001, "access_token超时"),
    REFRESH_TOKEN_TIME_OUT(42002, "refresh_token超时"),
    OAUTH_CODE_TIME_OUT(42003, "oauth_code超时"),
    NEED_GET_REQUEST(43001, "需要GET请求"),
    NEED_POST_REQUEST(43002, "需要POST请求"),
    NEED_HTTPS_REQUEST(43003, "需要HTTPS请求"),
    NEED_RECEIVER_SUBSCRIBE(43004, "需要接收者关注"),
    NEED_FRIEND_RELATIONSHIP(43005, "需要好友关系"),
    NULL_MEDIA_FILE(44001, "多媒体文件为空"),
    NULL_POST_BODY(44002, "POST的数据包为空"),
    NULL_NEWS_MESSAGE(44003, "图文消息内容为空"),
    NULL_TEXT_MESSAGE(44004, "文本消息内容为空"),
    MEDIA_FILE_SIZE_EXCEED(45001, "多媒体文件大小超过限制"),
    MESSAGE_SIZE_EXCEED(45002, "消息内容超过限制"),
    HEADLINE_LENGTH_EXCEED(45003, "标题字段超过限制"),
    DESCRIPTION_LENGTH_EXCEED(45004, "描述字段超过限制"),
    LINK_LENGTH_EXCEED(45005, "链接字段超过限制"),
    IMAGE_LINK_LENGTH_EXCEED(45006, "图片链接字段超过限制"),
    VOICE_PLAY_TIME_OUT(45007, "语音播放时间超过限制"),
    IMAGE_MESSAGE_AMOUNT_EXCEED(45008, "图文消息超过限制"),
    CALL_INTERFACE_TIMES_EXCEED(45009, "接口调用超过限制"),
    TABLE_AMOUNT_EXCEED(45010, "创建菜单个数超过限制"),
    REPLY_TIME_OUT(45015, "回复时间超过限制"),
    SYSTEM_GROUP_NOT_ALLOWED_CHANGE(45016, "系统分组，不允许修改"),
    GROUP_NAME_TOO_LONG(45017, "分组名字过长"),
    GROUP_AMOUNT_EXCEED(45018, "分组数量超过上限"),
    MEDIA_DATA_NOT_EXIST(46001, "不存在媒体数据"),
    TABLE_VERSION_NOT_EXIST(46002, "不存在的菜单版本"),
    TABLE_DATA_NOT_EXIST(46003, "不存在的菜单数据"),
    USER_NOT_EXIST(46004, "不存在的用户"),
    PARSE_JSON_XML_ERROR(47001, "解析JSON/XML内容错误"),
    API_NOT_AUTHORIZE(48001, "api功能未授权"),
    USER_NOT_AUTHORIZE_API(50001, "用户未授权该api");

    public final int value;
    public final String describe;

    public int getValue() {
        return value;
    }

    public String getDescribe() {
        return describe;
    }

    private ErrorCodeEnum(int value, String desc) {
        this.value = value;
        this.describe = desc;
    }

    public static ErrorCodeEnum getWeiXinErrorCodeEnum(int value) {
        for (ErrorCodeEnum wxec : ErrorCodeEnum.values()) {
            if (wxec.value == value) {
                return wxec;
            }
        }
        return UNKNOWN_ERROR_CODE;
    }

}
