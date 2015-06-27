//require.js配置
require.config({
    baseUrl : "/static/components",
    paths:{
        "jquery" : "jquery/jquery.min",
        "tooltip" : "bootstrap/js/tooltip",
        "popover" : "bootstrap/js/popover"
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
require(["jquery","tooltip","popover"], function($){

    //事件配置
    $('.eir-nav-collapsed').click(popoverInit);
    $('#write-feed').click(writeFeedClick);
    $('#recommend-link').click(recommendLink);
    $('.eir-mytags .eir-li').click(clickTag);

    //popover组件
    var popoverOps = {
        title:'是否确认删除？',
        content:"<a class='btn btn-danger eir-pop-btn'>确定</a><a class='btn btn-default eir-pop-btn'>取消</a>",
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

    function popoverInit(){
        $('.eir-tags-pop').slideToggle();
    }

    function writeFeedClick(){
        $(this).siblings().removeClass('active');
        $(this).addClass('active');
        $('.for-recommend-link').hide();
    }

    function recommendLink(){
        $(this).siblings().removeClass('active');
        $(this).addClass('active');
        $('.for-recommend-link').show();
    }

    function clickTag(){
        $(this).find('li').toggleClass('icon-tag');
        $(this).find('label').toggleClass('active');
        var isExist = $('.tags').find('a[data-index=' + $(this).data('index') +']');
        if(isExist.length == 0){
            var html = '<a class="btn btn-default eir-tag" data-index='+ $(this).data('index') + '>' + $(this).find('label').text() + '</a>';
            $(html).appendTo('.tags');
        }else{
            $(isExist).remove();
        }

    }
});
