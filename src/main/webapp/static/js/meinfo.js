/**
 * Created by float.lu on 7/7/15.
 */
//require.js配置
require.config({
    baseUrl : "../../",
    paths:{
        "jquery" : "/static/components/jquery/jquery.min",
        "API" : "/static/js/API",
    },
    shim:{
        "jquery":{
            exports : "$"
        }
    }
});

require(["API","jquery"], function(API, $){

});
