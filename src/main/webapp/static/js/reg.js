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
    //定义提示文字
    var TIPS = {
        accountExist    :   "该账户已经存在",
        serverErr       :   "服务器错误",
        pwdNotEquals    :   "两次密码不同",
        uidNotNull      :   "用户名不能为空",
        pwdNotNull      :   "密码不能为空",
        nickNameNull    :   "昵称不能为空",
        realNameNull    :   "真是姓名不能为空",
        jobtitleNull    :   "公司职位不能为空",
        companyNull     :   "公司不能为空",
        genderNull      :   "性别必须选择哦"
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
            var _uid = $('#uid');//注册账号
            var _pwd = $('#pwd');//注册密码
            var _ppwd = $('#ppwd');//重复密码
            var _nickname = $('#nickname');//昵称
            var _realname = $('#realname');//真是姓名
            var _jobtitle = $('#jobtitle');//公司职位
            var _company = $('#company');//公司
            var _gender = $('input[type=radio]:checked');//性别
            var _reg = $('#reg');//注册按钮

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
            }else if($.trim(_nickname.val()) == ""){
                _alert.text(TIPS.nickNameNull);
                _alert.fadeIn(500);
            }else if($.trim(_realname.val()) == ""){
                _alert.text(TIPS.realNameNull);
                _alert.fadeIn(500);
            }else if($.trim(_jobtitle.val()) == ""){
                _alert.text(TIPS.jobtitleNull);
                _alert.fadeIn(500);
            }else if($.trim(_company.val()) == ""){
                _alert.text(TIPS.companyNull);
                _alert.fadeIn(500);
            }else if(_gender == undefined || $.trim(_gender.val()) == ""){
                _alert.text(TIPS.genderNull);
                _alert.fadeIn(500);
            }else{
                _alert.hide();
            }
            if(_alert.css('display') == 'none'){
                _reg.attr('disabled', 'disabled');
                $.ajax({
                    type    :"POST",
                    url     :API.reg,
                    data    :{
                        email       :   $.trim(_uid.val()),
                        password    :   $.trim(_pwd.val()),
                        nickname    :   $.trim(_nickname.val()),
                        realname    :   $.trim(_realname.val()),
                        jobtitle    :   $.trim(_jobtitle.val()),
                        company     :   $.trim(_company.val()),
                        gender      :   $.trim(_gender.val())
                    },
                    success :function(data){
                        if(data.code == 200){
                            $('.eir-reg').hide();
                            $('.eir-reg-success').fadeIn(500);
                            setTimeout(function(){
                                window.location.href = API.home;
                            },1500);
                        }else{
                            _reg.attr('disabled', '');
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