/**
 * Created by float.lu on 7/7/15.
 */
//require.js配置
require.config({
    baseUrl : "../../",
    paths:{
        "jquery" : "/static/components/jquery/jquery.min",
        "tooltip" : "/static/components/bootstrap/js/tooltip",
        "popover" : "/static/components/bootstrap/js/popover",
        "dropdown" : "/static/components/bootstrap/js/dropdown",
        "API" : "/static/js/API",
        "toast" : "/static/js/toast"
    },
    shim:{
        "jquery":{
            exports : "$"
        },
        "tooltip" : ["jquery"],
        "popover" : ["tooltip"],
        "dropdown" : ["tooltip"]
    }
});

require(["API","jquery","toast"], function(API, $, toast){


    var onClickUpdate = function(){
        var _this = $(this);
        var _tag = _this.text();
        var _parent = _this.closest('.eir-input-group');
        var _input = _parent.find('input[val]');
        if(_input.length == 0){
            _input = _parent.find('textarea[val]');
        }
        var request = new Object();
        request[_input.attr('name')] = _input.val();
        if(_tag == "保存"){
            _input.attr('disabled','disabled');
            $.ajax({
                type:"POST",
                url:API.updateUserInfo,
                data:request,
                success:function(data){
                    if(data.code == 200){
                        toast("更新成功");
                        _input.css('border','0px');
                    }else{
                        toast(data.msg);
                    }
                    _this.text("修改");
                }
            });
        }else{
            _input.removeAttr('disabled');
            _input.css('border','1px solid #e4e4e4');
            _this.text("保存");
        }
    }

    var onSexChange = function(){
        var _this = $(this);
        $.ajax({
            type:"POST",
            url:API.updateUserInfo,
            data:{gender:_this.val()},
            success:function(data){
                if(data.code == 200){
                    toast("更新成功");
                    _input.css('border','0px');
                }else{
                    toast(data.msg);
                }
            }
        });
    }


    $('a[op]').click(onClickUpdate);
    $('input[name=gender]').change(onSexChange);
});
