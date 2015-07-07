//require.js配置
require.config({
    //baseUrl : "/static/",
    paths:{
        "jquery" : "/static/components/jquery/jquery.min",
        "API" : "/static/js/API"
    },
    shim:{
        "jquery":{
            exports : "$"
        }
    }
});

//模块入口
require(["API","jquery"], function(API, $) {
    var _uid = $('#uid');
    var _pwd = $('#pwd');
    var _reg = $('#reg');
    _reg.click(function(){
        $.ajax({
            type    :"POST",
            url     :API.reg,
            data    :{email:_uid.val(),password:_pwd.val()},
            success :function(data){
                if(data.code == 200){
                    alert("注册成功!");
                    window.location.href = API.home;
                }
            }
        });
    });
});