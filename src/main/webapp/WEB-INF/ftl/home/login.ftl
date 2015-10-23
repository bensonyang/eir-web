<#--封路模块-->
<div class="row eir-login text-center">
    <div class="eir-login-title">
        <span>用户登录</span>
    </div>
    <div class="eir-login-form">
        <form action="/community" method="post" id="loginForm">
            <div class="input-prepend">
                <!--<span class="add-on icon-grey"><i class="icon-envelope"></i></span>-->
                <input id="uid" class="span2" type="text" name="account" placeholder="会员账号/微信号">
            </div>
            <div class="input-prepend">
                <!--<span class="add-on icon-grey"><i class="icon-key"></i></span>-->
                <input id="pwd" class="span2" type="password" placeholder="密码">
            </div>
            <div class="go-reg"><a href="/reg">还没有账号？</a></div>
            <div class="input-prepend">
                <input class="btn btn-primary login" id="login" value="登录">
                <img onclick="javascript:window.location.href='https://open.weixin.qq.com/connect/qrconnect?appid=wx512439f72ac23d42&redirect_uri=http%3a%2f%2fwww.msvcplus.com%2fweixinlogin%3fchannel%3d1&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect'"  src="https://open.weixin.qq.com/zh_CN/htmledition/res/assets/res-design-download/icon24_appwx_logo.png" alt="" class="design_icon" style="vertical-align: bottom; margin-bottom: 5px;">
            </div>
            <input id="userId" type="hidden" name="userId">
        </form>
    </div>
</div>