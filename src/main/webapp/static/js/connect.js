//require.js配置
require.config({
    //baseUrl : "/static/",
    paths:{
        "jquery" : "/static/components/jquery/jquery.min",
        "API" : "/static/js/API",
        "toast" : "/static/js/toast"
    },
    shim:{
        "jquery":{
            exports : "$"
        }
    }
});

//模块入口
require(["API","jquery","toast"], function(API, $, toast) {


});