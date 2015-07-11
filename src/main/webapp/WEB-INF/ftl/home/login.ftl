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
                <input id="login" class="btn btn-primary" value="登录">
            </div>
            <input id="userId" type="hidden" name="userId">
        </form>
    </div>
</div>