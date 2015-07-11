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
    //定义提示文字
    var TIPS = {
        accountExist    :   "该账户已经存在",
        serverErr       :   "服务器错误",
        pwdNotEquals    :   "两次密码不同",
        uidNotNull      :   "用户名不能为空",
        pwdNotNull      :   "密码不能为空"
    };


    //事件处理器
    var HANDLERS = {
        uidFocusOut : function(){
            var _uid    =   $('#uid').val();
            var _alert  =   $('.alert');
            if(_uid != ""){
                $.get(API.isAccountExist,{
                        email:_uid
                    },
                    function(data){
                        if(data.code == 400){
                            _alert.text(TIPS.accountExist);
                            _alert.fadeIn(500);
                        }else if(data.code == 500){
                            _alert.text(TIPS.serverErr);
                        }else{
                            _alert.fadeOut(500);
                        }
                    });
            }
        },
        pwdFocusOut :  function(){
            var _pwd = $('#pwd');
            var _ppwd = $('#ppwd');
            var _alert  =   $('.alert');
            if(_ppwd.val() != "" && _pwd.val() != ""){
                if(_ppwd.val() != _pwd.val()){
                    _alert.text(TIPS.pwdNotEquals);
                    _alert.fadeIn(500);
                }else{
                    _alert.fadeOut(500);
                }
            }
        },
        reg : function(){
            var _uid = $('#uid');
            var _pwd = $('#pwd');
            var _ppwd = $('#ppwd');
            var _reg = $('#reg');
            var _alert  =   $('.alert');
            if($.trim(_uid.val()) == ""){
                _alert.text(TIPS.uidNotNull);
                _alert.fadeIn(500);
            }else if($.trim(_pwd) == "" || $.trim(_ppwd) == ""){
                _alert.text(TIPS.pwdNotNull);
                _alert.fadeIn(500);
            }else if($.trim(_pwd.val()) != $.trim(_ppwd.val())){
                _alert.text(TIPS.pwdNotEquals);
                _alert.fadeIn(500);
            }else{
                _alert.hide();
            }
            if(_alert.css('display') == 'none'){
                _reg.attr('disabled', 'disabled');
                $.ajax({
                    type    :"POST",
                    url     :API.reg,
                    data    :{email: $.trim(_uid.val()),password: $.trim(_pwd.val())},
                    success :function(data){
                        if(data.code == 200){
                            $('.eir-reg').hide();
                            $('.eir-reg-success').fadeIn(500);
                            setTimeout(function(){
                                window.location.href = API.home;
                            },1500);
                        }
                    }
                });
            }
        }
    };

    //事件注册
    $('#uid').focusout(HANDLERS.uidFocusOut);
    $('#ppwd').focusout(HANDLERS.pwdFocusOut);
    $('#pwd').focusout(HANDLERS.pwdFocusOut);
    $('#reg').click(HANDLERS.reg);

});