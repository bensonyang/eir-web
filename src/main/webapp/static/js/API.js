/**
 * 接口url定义
 */
define(function(){
    return {
        moreFeed        :   '/community/morefeed',//加载更多url
        shuoFeed        :   '/community/shuofeed',//添加说说url
        linkFeed        :   '/community/linkfeed',//推荐网页url
        deleteFeed      :   '/community/deletefeed',//删除feed url
        addComment      :   '/community/addcomment',//添加评论url
        delComment      :   '/community/deletecomment',//删除评论url
        addLike         :   '/community/addlike',//点赞url
        delLike         :   '/community/deletelike',//取消点赞url
        moreComment     :   '/community/morecomment',//获取更多的评论url
        login           :   '/ajax/login',//登陆
        reg             :   '/ajax/reg',//注册
        home            :   '/community',//首页
        isAccountExist  :   '/ajax/validate/accountName',//账户是否已经存在
        logOut          :   '/ajax/logout',//退出
        pageCrawler     :   '/crawler/get',//爬去网页摘要
        meInfoCenter    :   '/meinfo/default',//个人中心
        updateUserInfo  :   '/meinfo/update',//更新个人信息
        picUpload       :   "/pic/upload",//上传图片
        pic             :   "/pic/{}",//加载图片
        regConnect      :   "/ajax/regbind",//注册连接
        loginConnect    :   "/ajax/loginbind",//登录连接
        tags            :   "/tags"
    };
});
