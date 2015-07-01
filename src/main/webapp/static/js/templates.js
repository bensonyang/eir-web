/*
模板定义
 */
require.config({
    baseUrl : "/static/",
    paths:{
        "jquery" : "components/jquery/jquery.min"
    },
    shim:{
        "jquery":{
            exports : "$"
        }
    }
});
define(['jquery'],function($){
    return {
        feedTemplate : $('#feedTemplate').html(),
        feedCommentMore : $('#eir-feed-comments-more').html(),
        feedComment : $('#eir-feed-comments').html()
    };
});
