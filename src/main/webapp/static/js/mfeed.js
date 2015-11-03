//require.js配置
require.config({
    //baseUrl : "/static/",
    paths:{
        "jquery" : "/static/components/jquery/jquery.min",
        "tooltip" : "/static/components/bootstrap/js/tooltip",
        "popover" : "/static/components/bootstrap/js/popover",
        "underscore" : "/static/components/underscore/underscore-min",
        "API" : "/static/js/API",
        "templates" : "/static/js/templates",
        "toast" : "/static/js/toast"
    },
    shim:{
        "jquery":{
            exports : "$"
        },
        "tooltip" : ["jquery"],
        "popover" : ["tooltip"]
    }
});

//模块入口
require(["API","jquery","underscore","templates","toast","tooltip","popover"], function(API, $, _, templates,toast){

    var _lastFeedIndex = undefined;
    var _pageSize = 5;
    var _canMore = true;
    //加载更多的内容需要注册点击事件
    $(window).scroll(function(){
        if (($(document).height() - $(this).scrollTop() - $(this).height()) < 50){
            if(_canMore){
                _canMore = false;
            }
        }
    });
    //第一页内容
    var _muserId = $('#muserId').val();
    var _mfeedId = $('#mfeedId').val();
    $.ajax({
        type:"GET",
        url:API.moreFeed,
        data:{
            startIndex:0,
            pageSize:1,
            userId:_muserId,
            feedId:_mfeedId
        },
        success:function(data){
            if(data.code == 200){
                _lastFeedIndex = data.msg.lastFeedIndex;
                _.each(data.msg.feeds, function(data){
                    var compiled =  _.template(templates.feedTemplate);
                    $('.feed-container').append(compiled(data));
                    $('div[data-feedId='+ data.feedId +']').slideDown(500);
                    var _title = undefined;
                    if(data.feedType == 0){
                        _title = data.msgBody.content;
                    }else{
                        _title = data.msgBody.title;
                    }
                    $('title').text(_title);
                });
                $('.eir-feed-comments').focusin(HANDLERS.feedCommentFocusInHandler);//评论数据框聚焦
                $('.eir-feed .eir-feed-options .icon-thumbs-up .unliked').click(HANDLERS.likeFeedHandler);//Feed点赞
                $('.eir-feed .eir-feed-options .icon-thumbs-up .liked').click(HANDLERS.dellikeFeedHandler);//取消Feed点赞
                $('.eir-get-more-comments .a-more-comments').click(HANDLERS.getMoreFeedCommentsHandler);//获取更多Comments
                $('[data-toggle="popover"]').popover(popoverOps);//初始化删除Feed组件
                $('.eir-feed a[deleteFeed]').click(HANDLERS.deletebtnHandler);//删除Feed事件注册
                $('.eir-feed a[deleteComment]').click(HANDLERS.deletebtnHandler);//删除Feed事件注册
                $('.eir-feed a[backComment]').click(HANDLERS.backOnClickHandler); //回复按钮注册事件

            }else{
                toast(data.msg);
            }
        },
        error:function(){
            toast("服务器错误");
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
            }else if(data.code == 300){
                toast(data.msg);
            }else{
                toast("服务器错误");
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
            $('.main-textarea').show();
            $('.for-recommend-link').hide();
            $('.link-page-content').hide();
        },
        recommendLinkOnClickHandler   :   function recommendLink(){
            $(this).siblings().removeClass('active');
            $(this).addClass('active');
            $('.for-recommend-link').show();
            $('.eir-recommend-link').val('');
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

            var _title = $('.link-page-content-title span').text();
            var _abstract = $('.link-page-content-abstract').text();

            var _cur = getByteLen(_content);
            var _linkAbstract = getByteLen(_title) + getByteLen(_abstract);
            if(_cur == 0 && _linkAbstract == 0) {
                toast("请输入评论后再提交。");
                return false;
            } else if(_cur <= 280 && _linkAbstract <= 280){
                // 符合长度
            } else {
                toast("字数长度超出范围，请重新输入。");
                return false;
            }

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
                            title   : $.trim(_title),
                            content : $.trim(_abstract),
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
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] .eir-feed-options .icon-thumbs-up .unliked').click(HANDLERS.likeFeedHandler);
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] .eir-feed-options .icon-thumbs-up .liked').click(HANDLERS.dellikeFeedHandler);//取消Feed点赞
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] [data-toggle="popover"]').popover(popoverOps);//初始化删除Feed组件
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] a[deleteFeed]').click(HANDLERS.deletebtnHandler);//删除Feed事件注册
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] a[deleteComment]').click(HANDLERS.deletebtnHandler);//删除Feed事件注册
                    $('.form-group .main-textarea').val("");
                }else if(data.code == 300){
                    toast(data.msg);
                }else{
                    toast(data.msg);
                }
            }
            function linkFeedCallback(data){
                if(data.code == 200){
                    var compiled =  _.template(templates.feedTemplate);
                    $('.feed-container').prepend(compiled(data.msg.feeds[0]));
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +']').slideDown(500);
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] .eir-feed-comments').focusin(HANDLERS.feedCommentFocusInHandler);//新内容绑定评论框聚焦事件
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] .eir-feed-options .icon-thumbs-up .unliked').click(HANDLERS.likeFeedHandler);
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] .eir-feed-options .icon-thumbs-up .liked').click(HANDLERS.dellikeFeedHandler);//取消Feed点赞
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] [data-toggle="popover"]').popover(popoverOps);//初始化删除Feed组件
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] a[deleteFeed]').click(HANDLERS.deletebtnHandler);//删除Feed事件注册
                    $('div[data-feedId='+ data.msg.feeds[0].feedId +'] a[deleteComment]').click(HANDLERS.deletebtnHandler);//删除Feed事件注册
                    $('.link-page-content-title span').text("");
                    $('.link-page-content-abstract').text("");
                    $('.eir-recommend-link').val("");
                    $('.main-textarea').show();
                    $('.link-page-content').hide();
                    $('.for-recommend-link').show();
                }else if(data.code == 300){
                    toast(data.msg);
                }else{
                    toast(data.msg);
                }
            }
            function errorCallback(){

            }
            function getByteLen(val) {
                var len = 0;
                for (var i = 0; i < val.length; i++) {
                    if (val.charCodeAt(i) > 255)
                        len += 2;
                    else
                        len += 1;
                }
                return len;
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
                        _a.text("取消赞");
                        _a.removeClass("unliked");
                        _a.addClass("liked");
                        _a.unbind("click");
                        _a.click(HANDLERS.dellikeFeedHandler);//取消Feed点赞
                        _a.next().text(data.msg.likeCount);
                    }else if(data.code == 300){
                        toast(data.msg);
                    }else{
                        toast("服务器错误");
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
                    }else if(data.code == 300){
                        toast(data.msg);
                    }else{
                        toast("服务器错误");
                    }
                },
                error:function(){

                }
            });
        },
        feedCommentFocusInHandler : function(){
            return;
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
                            $('div[data-commentid='+ comment.commentId +'] a[backComment]').click(HANDLERS.backOnClickHandler); //回复按钮注册事件
                        });
                        _this.data('lastcommentindex',data.msg.lastCommentIndex);
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
                        toast("服务器错误");
                    }
                },
                error:function(){

                }
            });
        },
        commentToFeedHandler : function(backFlag){
            var _toUserId = $('.eir-feed-head').attr('data-touserid');
            if($('.on-comment').hasClass('eir-hide')){
                $('.on-comment').removeClass('eir-hide');
                $('.comment-content').focus();
                if(backFlag == 'back'){
                   return;
                }
                $('.comment-input-head l').text("回复:"+$('.eir-feed-name').text());
                return;
            }
            var _feed = $('.eir-feed');
            var _feedId = $('.eir-feed').data('feedid');
            var _comment_area = $(this).closest('.feed-container').find('.comment-content');
            var _content = _comment_area.val();
            if($.trim(_content) == ""){
                toast("评论内容不能为空");
                return;
            }
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
                        var compiled =  _.template(templates.feedCommentItem);
                        _feed.find('.eir-feed-item-container').prepend(compiled(data.msg));
                        $('div[data-commentid='+ data.msg.commentId +']').slideDown();
                        _feed.find('.eir-feed-comments-textarea').val("");
                        $('div[data-commentid='+ data.msg.commentId +'] [data-toggle="popover"]').popover(popoverOps);//初始化删除Feed组件
                        $('div[data-commentid='+ data.msg.commentId +'] a[deleteComment]').click(HANDLERS.deletebtnHandler);//删除按钮事件
                        $('div[data-commentid='+ data.msg.commentId +'] a[backComment]').click(HANDLERS.backOnClickHandler); //回复按钮注册事件
                        _comment_area.val('');
                        $('.on-comment').addClass('eir-hide');
                    }else if(data.code == 300){
                        toast(data.msg);
                    }else{
                        toast("服务器错误");
                    }
                },
                error:function(){

                }
            });
        },
        backOnClickHandler : function(){
            var _touserid = $(this).data('userid');
            $('.eir-feed-head').attr('data-touserid',_touserid);
            $('.comment-input-head l').text("回复:"+$(this).data('user'));
            HANDLERS.commentToFeedHandler('back');
        },
        loginHandler:function(){
            var _uid = $('#uid').val();
            var _pwd = $('#pwd').val();
            $.ajax({
                type:'GET',
                url:API.login,
                data:{
                    account :_uid,
                    password:_pwd
                },
                success:function(data){
                    if(data.code == 200){
                        window.location.reload();
                    }else{
                        toast("登陆失败");
                    }
                }
            });
        },
        logOutHandler:function(){
            $.get(API.logOut,{},function(data){
                if(data.code == 200){
                    window.location.reload();
                }
            });
        },
        inputLinkOnClickHandler:function(){
            var _linkIptDiv = $('.for-recommend-link');
            var _urlIpt = $('.eir-recommend-link');
            var _abstractDiv = $('.link-page-content');
            var _mainTextArea = $('.main-textarea');
            if($.trim(_urlIpt.val()) == "") return;
            $.ajax({
                type:'POST',
                url:API.pageCrawler,
                data:{url: $.trim(_urlIpt.val())},
                success:function(data){
                    if(data.code == 200){
                        var _title = data.msg.pageTitle;
                        var _abstract = data.msg.pageAbstract;
                        if($.trim(_title) == ""){
                            toast("当前输入没有获取到内容");
                        }else{
                            $('.link-page-content-title span').text(_title);
                            $('.link-page-content-abstract').text(_abstract);
                            _linkIptDiv.hide();
                            _abstractDiv.show();
                            _mainTextArea.hide();
                        }
                    }else{
                        toast("服务器错误");
                    }
                }
            });
        },
        closeAbstractDivHandler:function(){
            $('.link-page-content').hide();
            $('.link-page-content-title span').text("");
            $('.link-page-content-abstract').text("");
            $('.for-recommend-link').show();
            $('.eir-recommend-link').val('');
            $('.main-textarea').show();

        },
        meinfoOnClickHandler:function(){
            $(this).toggleClass('open');
            $('.dropdown-toggle').focus()
        },
        meinfoHide:function(){
            if($('a[goOut]:hover').length == 0 && $('a[goMeInfoCenter]:hover').length == 0){
                $('.meinfo').toggleClass('open');
            }
        },
        goMeInfoCenter:function() {
            window.location.href = API.meInfoCenter;
        },
        closeComment:function(){
            $('.on-comment').addClass('eir-hide');
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
    $('.eir-feed .eir-feed-options .icon-thumbs-up .unliked').click(HANDLERS.likeFeedHandler);//Feed点赞
    $('.eir-feed .eir-feed-options .icon-thumbs-up .liked').click(HANDLERS.dellikeFeedHandler);//取消Feed点赞
    $('.eir-feed-comments').focusin(HANDLERS.feedCommentFocusInHandler);//评论数据框聚焦
    $('.eir-get-more-comments .a-more-comments').click(HANDLERS.getMoreFeedCommentsHandler);//获取更多评论
    $('.eir-feed-comments .comment').click(HANDLERS.commentToFeedHandler);//Feed下面添加评论
    $('.eir-feef a[backComment]').click(HANDLERS.backOnClickHandler); //回复按钮注册事件
    $('#login').click(HANDLERS.loginHandler);//登陆按钮
    $('.for-recommend-link .eir-recommend-link').focusout(HANDLERS.inputLinkOnClickHandler);//获取网页内容
    $('.link-page-content-title .close').click(HANDLERS.closeAbstractDivHandler);//重新获取网页摘要
    $('.feed-container .close').click(HANDLERS.closeComment);//隐藏评论框
    //################################事件配置END#######################################

    window.goOut = HANDLERS.logOutHandler;
    window.goMeInfoCenter = HANDLERS.goMeInfoCenter;//个人中心

});
