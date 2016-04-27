<div class="login-box">
    <div class="login-div login-name">
        <li class="po icon-user"></li>
        <input type="text" name="username" id="uid" placeholder="用户名/微信号">
    </div>
    <div class="login-div login-pwd">
        <li class="po icon-key"></li>
        <input type="password" name="password" id="pwd" placeholder="请输入密码">
    </div>
    <div class="login-mid">
      <#--  <a href="/reg" class="new">新用户注册</a>-->
        <a href="#" class="we" onclick="javascript:window.location.href='https://open.weixin.qq.com/connect/qrconnect?appid=wx512439f72ac23d42&redirect_uri=http%3a%2f%2fwww.msvcplus.com%2fweixinlogin%3fchannel%3d1&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect'" >微信登陆</a>
    </div>
    <div class="login-div">
        <input type="button" class="login-btn" id="login" value="登陆">
    </div>
    <input id="userId" type="hidden" name="userId">
</div>