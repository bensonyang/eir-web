//require.js配置
require.config({
    //baseUrl : "/static/",
    paths:{
        "jquery" : "/static/components/jquery/jquery.min",
        "tooltip" : "/static/components/bootstrap/js/tooltip",
        "popover" : "/static/components/bootstrap/js/popover",
        "underscore" : "/static/components/underscore/underscore-min",
        "API" : "/static/js/API",
        "templates" : "/static/js/templates"
    },
    shim:{
        "jquery":{
            exports : "$"
        },
        "tooltip" : ["jquery"],
        "popover" : ["tooltip"]
    }
});


var data = undefined;
//模块入口
require(["API","jquery","underscore","templates","tooltip","popover"], function(API, $, _, templates){
    var _lastFeedIndex = undefined;
    var _pageSize = 5;
    var _canMore = true;
    //加载更多的内容需要注册点击事件
    $(window).scroll(function(){
        if (($(document).height() - $(this).scrollTop() - $(this).height()) < 50){
            if(_canMore){
                _canMore = false;
                $.get(API.moreFeed,{
                    startIndex:_lastFeedIndex + 1,
                    pageSize:_pageSize
                },function(data){
                    _lastFeedIndex = data.msg.lastFeedIndex;
                    _.each(data.msg.feeds, function(data){
                        var compiled =  _.template(templates.feedTemplate);
                        $('.feed-container').append(compiled(data));
                        $('div[data-feedId='+ data.feedId +']').slideDown(500);
                        $('div[data-feedId='+ data.feedId +'] .eir-feed-comments').focusin(HANDLERS.feedCommentFocusInHandler);//评论数据框聚焦
                        $('div[data-feedId='+ data.feedId +'] .eir-feed-options .icon-thumbs-up.unliked a').click(HANDLERS.likeFeedHandler);
                        $('div[data-feedId='+ data.feedId +'] .eir-feed-options .icon-thumbs-up.liked a').click(HANDLERS.dellikeFeedHandler);//取消Feed点赞
                        $('div[data-feedId='+ data.feedId +'] [data-toggle="popover"]').popover(popoverOps);//初始化删除Feed组件
                        $('div[data-feedId='+ data.feedId +'] a[deleteFeed]').click(HANDLERS.deletebtnHandler());//删除Feed事件注册
                        $('div[data-feedId='+ data.feedId +'] a[deleteComment]').click(HANDLERS.deletebtnHandler());//删除Feed事件注册
                        $('div[data-feedId='+ data.feedId +'] a[backComment]').click(HANDLERS.backOnClickHandler); //回复按钮注册事件
                    });
                    setTimeout(function(){
                       _canMore = true;
                    },1000);
                });
            }

        }
    });
    //第一页内容
    $.ajax({
        type:"GET",
        url:API.moreFeed,
        data:{
            startIndex:0,
            pageSize:5
        },
        success:function(data){
            if(data.code == 200){
                _lastFeedIndex = data.msg.lastFeedIndex;
                _.each(data.msg.feeds, function(data){
                    var compiled =  _.template(templates.feedTemplate);
                    $('.feed-container').append(compiled(data));
                    $('div[data-feedId='+ data.feedId +']').slideDown(500);
                });
                $('.eir-feed-comments').focusin(HANDLERS.feedCommentFocusInHandler);//评论数据框聚焦
                $('.eir-feed .eir-feed-options .icon-thumbs-up.unliked a').click(HANDLERS.likeFeedHandler);//Feed点赞
                $('.eir-feed .eir-feed-options .icon-thumbs-up.liked a').click(HANDLERS.dellikeFeedHandler);//取消Feed点赞
                $('.eir-get-more-comments .a-more-comments').click(HANDLERS.getMoreFeedCommentsHandler);//获取更多Comments
                $('[data-toggle="popover"]').popover(popoverOps);//初始化删除Feed组件
                $('.eir-feed a[deleteFeed]').click(HANDLERS.deletebtnHandler);//删除Feed事件注册
                $('.eir-feed a[deleteComment]').click(HANDLERS.deletebtnHandler);//删除Feed事件注册
                $('.eir-feed a[backComment]').click(HANDLERS.backOnClickHandler); //回复按钮注册事件
            }else{
                alert(data.msg);
            }
        },
        error:function(){
            alert("服务器错误");
        }
    });

    //删除Feed
    window.currentDeleteFeedId = undefined;
    window.currentDeleteCommentId = undefined;
    window.currentPopover = undefined;
    window.isDeleteFeed = undefined;
    window.popoverRemove = function(){
        var _url  = undefined;
        var _data = {};
        var _callback = function(){};
        var _deleteFeedCallback = function(data){
            if(data.code == 200){
                $('div[data-feedId='+ data.msg +']').slideUp(500);
            }else{
                alert("服务器错误");
            }
            window.currentPopover.popover('hide');
        };
        var _deleteCommentCallback = function(data){
            var _deleteCommentId = data.msg.commentId;
            $('div[data-commentid='+_deleteCommentId+'].eir-feed-comments-item').slideUp(500);
            window.currentPopover.popover('hide');
            setTimeout(function(){
                $('.popover').css('z-index',-100);
            },1000);
        };
        if(window.isDeleteFeed){
            _url = API.deleteFeed;
            _data = {feedId:window.currentDeleteFeedId};
            _callback = _deleteFeedCallback;
        }else{
            _url = API.delComment;
            _data = {commentId: window.currentDeleteCommentId};
            _callback = _deleteCommentCallback;
        }
        $.ajax({
            type : "POST",
            url : _url,
            data:_data,
            success:_callback,
            error:function(){
                window.currentPopover.popover('hide');
            }
        });
    }
    window.cancelPopover = function(){
        if(window.currentPopover != undefined){
            window.currentPopover.popover('hide');
            setTimeout(function(){
                $('.popover').css('z-index',-100);
            },1000);
        }
    }

    //popover组件
    var popoverOps = {
        title:'是否确认删除？',
        content:"<a class='btn btn-danger eir-pop-btn' onclick=\"popoverRemove();\">确定</a><a class='btn btn-default eir-pop-btn' onclick=\"cancelPopover();\" >取消</a>",
        html:true,
        template:'<div class="popover" role="tooltip">' +
        '<div class="arrow">' +
        '</div>' +
        '<h3 class="popover-title"></h3>' +
        '<div class="popover-content">' +
        '</div>' +
        '</div>'
    };
    $('[data-toggle="popover"]').popover(popoverOps)


    //################################事件处理器配置BEGIN#######################################
    var HANDLERS = {
        popoverInitHandler     :   function popoverInit(){
            $('.eir-tags-pop').slideToggle();
        },
        writeFeedOnClickHandler  :   function writeFeedClick(){
            $(this).siblings().removeClass('active');
            $(this).addClass('active');
            $('.for-recommend-link').hide();
        },
        recommendLinkOnClickHandler   :   function recommendLink(){
            $(this).siblings().removeClass('active');
            $(this).addClass('active');
            $('.for-recommend-link').show();
        },
        clickTagHandler    :   function clickTag(){//单选tag
            $(this).siblings().find('li').removeClass('icon-tag');
            $(this).siblings().find('label').removeClass('icon-tag');
            $(this).find('li').addClass('icon-tag');
            $(this).find('label').addClass('active');
            $('.tags').find('a[data-index]').remove();
            var html = '<a class="btn btn-default eir-tag" data-index='+ $(this).data('index') + '>' + $(this).find('label').text() + '</a>';
            $(html).appendTo('.tags');

        },
        commentsOnClickHandler  :   function commentsOnClickHandler(){
            var _content = $('.form-group .main-textarea').val();
            var _tag = $('.form-group a[data-index]').text();
            var _link = $('.form-group .eir-recommend-link').val();
            var feedTypeId = $('.eir-options i.active').attr('id');
            switch(feedTypeId){
                case "write-feed":
                    $.ajax({
                        type:"POST",
                        url:API.shuoFeed,
                        data:{
                            tagName : _tag,
                            content : _content
                        },
                        success:shuoFeedCallback,
                        error:errorCallback
                    });
                    break;
                case "recommend-link":
                    $.ajax({
                        type : "POST",
                        url : API.linkFeed,
                        data:{
                            tagName : _tag,
                            content : _content,
                            link : _link
                        },
                        success : linkFeedCallback,
                        error : errorCallback
                    });
                    break;
            }
            function shuoFeedCallback(data){
                if(data.code == 200){
                    var compiled =  _.template(templates.feedTemplate);
                    $('.feed-container').prepend(compiled(data.msg.feeds[0]));
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +']').slideDown(500);
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] .eir-feed-comments').focusin(HANDLERS.feedCommentFocusInHandler);//新内容绑定评论框聚焦事件
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] .eir-feed-options .icon-thumbs-up.unliked a').click(HANDLERS.likeFeedHandler);
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] .eir-feed-options .icon-thumbs-up.liked a').click(HANDLERS.dellikeFeedHandler);//取消Feed点赞
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] [data-toggle="popover"]').popover(popoverOps);//初始化删除Feed组件
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] a[deleteFeed]').click(HANDLERS.deletebtnHandler);//删除Feed事件注册
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] a[deleteComment]').click(HANDLERS.deletebtnHandler);//删除Feed事件注册
                    $('.form-group .main-textarea').val("");
                }else{
                    alert(data.msg);//TODO 优化弹框
                }
            }
            function linkFeedCallback(data){

            }
            function errorCallback(){

            }
        },
        deletebtnHandler : function deletebtnHandler(){
            window.currentDeleteFeedId = $(this).closest('.eir-feed').data('feedid');
            window.currentPopover = $(this);
            if($(this).data('commentid') == undefined){//是删除feed还是comment
                window.isDeleteFeed = true;
            }else{
                window.isDeleteFeed = false;
                window.currentDeleteCommentId = $(this).data('commentid');
            }
            $('.popover').css('z-index',1000);
        },
        likeFeedHandler : function likeFeedHandler(){
            var _feedId = $(this).closest('.eir-feed').data('feedid');
            var _a = $(this);
            $.ajax({
                type:'POST',
                url:API.addLike,
                data:{feedId:_feedId},
                success:function(data){
                    if(data.code == 200){
                        _a.text("已赞");
                        _a.removeClass("unliked");
                        _a.addClass("liked");
                        _a.unbind("click");
                        _a.click(HANDLERS.dellikeFeedHandler);//取消Feed点赞
                        _a.next().text(data.msg.likeCount);
                    }else{
                        alert("服务器错误");
                    }
                },
                error:function(){

                }
            });
        },
        dellikeFeedHandler : function dellikeFeedHandler(){
            var _feedId = $(this).closest('.eir-feed').data('feedid');
            var _a = $(this);
            $.ajax({
                type:'POST',
                url:API.delLike,
                data:{feedId:_feedId},
                success:function(data){
                    if(data.code == 200){
                        _a.text("赞");
                        _a.removeClass("liked");
                        _a.addClass("unliked");
                        _a.unbind("click");
                        _a.click(HANDLERS.likeFeedHandler);
                        _a.next().text(data.msg.likeCount);
                    }else{
                        alert("服务器错误");
                    }
                },
                error:function(){

                }
            });
        },
        feedCommentFocusInHandler : function(){
            var _feed = $(this).closest('.eir-feed');
            var _touserid = $(this).data('userid');
            var compiled =  _.template(templates.feedCommentMore);
            $(this).closest('.eir-feed-comments').replaceWith(compiled({type:"goComment",touserid:_touserid})).fadeIn(1000);
            _feed.find('textarea[backComment]').focus();
            _feed.find('a[goComment]').click(HANDLERS.commentToFeedHandler);
        },
        getMoreFeedCommentsHandler  : function(){
            var _this = $(this);
            var _btnContainer = $(this).closest('.eir-get-more-comments');
            var _lastCommentIndex = $(this).data('lastcommentindex');
            var _feedId = $(this).data('feedid');
            $.ajax({
                type:"POST",
                url:API.moreComment,
                data:{
                    lastCommentIndex:_lastCommentIndex,
                    feedId:_feedId,
                    pageSize:5
                },
                success:function(data){
                  if(data.code == 200){
                      _.each(data.msg.comments, function(comment){
                          if(comment.toUserName ==undefined){
                              comment.toUserName = '';
                          }
                          var compiled =  _.template(templates.feedCommentItem);
                          _btnContainer.before(compiled(comment));
                          $('div[data-commentid='+ comment.commentId +']').slideDown();
                          $('a[data-commentid='+comment.commentId+']').popover(popoverOps);//初始化删除Feed组件
                          $('a[data-commentid='+comment.commentId+']').click(HANDLERS.deletebtnHandler);//删除Feed事件注册
                      });
                      _this.attr('data-lastcommentindex',data.msg.lastFeedIndex);
                      var _c = _this.find('c');
                      if(_c != undefined){
                          var currentCount = parseInt(_this.find('c').text());
                          if(currentCount - data.msg.size > 0){
                              _c.text(currentCount - data.msg.size);
                          }else{//移除加载更多
                            _btnContainer.remove();
                          }
                      }

                  }else{
                      alert("服务器错误");
                  }
                },
                error:function(){

                }
            });
        },
        commentToFeedHandler : function(){
            var _this = $(this);
            var _feed = $(this).closest('.eir-feed');
            var _feedId = $(this).closest('.eir-feed').data('feedid');
            var _content = $(this).parent().find('.eir-feed-comments-textarea').val();
            var _toUserId = $(this).data('touserid');
            $.ajax({
                type:"POST",
                url:API.addComment,
                data:{
                    feedId:_feedId,
                    content:_content,
                    toUserId:_toUserId
                },
                success:function(data){
                    if(data.code == 200){
                        if(_toUserId != undefined && _toUserId != ""){
                            _this.closest('.eir-feed-comments-more').slideUp(500);
                            setTimeout(function(){
                                _this.closest('.eir-feed-comments-more').remove();
                            },1000);
                        }
                        var compiled =  _.template(templates.feedCommentItem);
                        _feed.find('.eir-feed-item-container').prepend(compiled(data.msg));
                        $('div[data-commentid='+ data.msg.commentId +']').slideDown();
                        _feed.find('.eir-feed-comments-textarea').val("");
                        $('div[data-commentid='+ data.msg.commentId +'] [data-toggle="popover"]').popover(popoverOps);//初始化删除Feed组件
                        $('div[data-commentid='+ data.msg.commentId +'] a[deleteComment]').click(HANDLERS.deletebtnHandler);//删除按钮事件
                    }else{

                    }
                },
                error:function(){

                }
            });
        },
        backOnClickHandler : function(){
            var _feed = $(this).closest('.eir-feed');
            var _touserid = $(this).data('userid');
            var compiled =  _.template(templates.feedCommentMore);
            $(this).closest('.eir-feed-comments-item').after(compiled({type:"backComment",touserid:_touserid})).fadeIn(1000);
            _feed.find('textarea[backComment]').focus()
            _feed.find('a[backComment]').click(HANDLERS.commentToFeedHandler);
        }
    };
    //################################事件处理器配置END#######################################


    //################################事件配置BEGIN#######################################
    $('.eir-nav-collapsed').click(HANDLERS.popoverInitHandler);
    $('#write-feed').click(HANDLERS.writeFeedOnClickHandler);
    $('#recommend-link').click(HANDLERS.recommendLinkOnClickHandler);
    $('.eir-mytags .eir-li').click(HANDLERS.clickTagHandler);
    $('.eir-comments-btn').click(HANDLERS.commentsOnClickHandler);//发表说说
    $('.eir-feed a[deleteFeed]').click(HANDLERS.deletebtnHandler);//删除按钮事件
    $('.eir-feed a[deleteComment]').click(HANDLERS.deletebtnHandler);//删除按钮事件
    $('.eir-feed .eir-feed-options .icon-thumbs-up.unliked a').click(HANDLERS.likeFeedHandler);//Feed点赞
    $('.eir-feed .eir-feed-options .icon-thumbs-up.liked a').click(HANDLERS.dellikeFeedHandler);//取消Feed点赞
    $('.eir-feed-comments').focusin(HANDLERS.feedCommentFocusInHandler);//评论数据框聚焦
    $('.eir-get-more-comments .a-more-comments').click(HANDLERS.getMoreFeedCommentsHandler);//获取更多评论
    $('.eir-feed .eir-feed-comments-comments').click(HANDLERS.commentToFeedHandler);//Feed下面添加评论
    $('.eir-feef a[backComment]').click(HANDLERS.backOnClickHandler); //回复按钮注册事件
    //################################事件配置END#######################################
});
data = {
    "code":200,
    "msg":{
        "feeds":[
            {
                "addTime":"2015-07-04 15:09:43",
                "author":{
                    "company":"IBM",
                    "jobTitle":"CTO",
                    "realName":"陆晨",
                    "userId":0
                },
                "authorId":0,
                "comment":[

                ],
                "commentCount":0,
                "feedId":34,
                "feedType":0,
                "lastCommentIndex":0,
                "likeCount":0,
                "liked":false,
                "msgBody":{
                    "content":"呵呵额",
                    "msgId":34
                },
                "msgId":34,
                "tag":""
            }
        ],
        "lastFeedIndex":-1,
        "totalFeedCount":0
    }
};

var t = {
    code: 200,
    msg:{
        lastFeedIndex:1,//当前页最后一个Feed索引
        totalFeedCount:111,//总Feed数
        feeds: [
            {
                feedId: 123,
                feedType: 0,
                msgBody: {
                    msgId: 666,
                    title:"猫眼格瓦拉百度淘宝们怎么打?",
                    content: "在已经大幕拉开的暑期档，价格补贴的恶战会再次重燃，到 年底究竟还能留下谁呢?",
                    "link": "http: //***"
                },
                "author": {
                    "userId": 123456,
                    "realName": "Cheng",
                    "jobTiltle": "CEO",
                    "company": "huhaha",
                    "avatarUrl": "/static/images/pic1.jpeg"
                },
                comment:{
                    feedId:123,
                    lastIndex:2,
                    totalCount:111,
                    data:[
                        {
                            commentId:1,
                            userPic:"/static/images/pic1.jpeg",//作者头像图片
                            userId:1,//作者ID
                            userName:"float.lu",//作者名字
                            toUserPic:"/static/images/pic1.jpeg",//被@的作者图片
                            toUserId:123,//被@的作者
                            toUserName:"cheng",//被@的作者名字
                            content:"不错哟",//评论内容
                            commentTime:"2015-6-15"
                        },
                        {
                            commentId:1,
                            userPic:"/static/images/pic1.jpeg",//作者头像图片
                            userId:123,//作者ID
                            userName:"cheng",//作者名字
                            toUserPic:"/static/images/pic1.jpeg",//被@的作者图片
                            toUserId:1,//被@的作者
                            toUserName:"cheng",//被@的作者名字
                            content:"不错哟",//评论内容
                            commentTime:"2015-6-16"
                        }
                    ]
                },
                "addTime": "2015-6-15",
                "commentCount": 0,
                "likeCount": 0,
                "tag": "O2O",
                tagId:1,//标签ID
                isLiked:"true"//是否已赞
            }
        ]
    }

};


