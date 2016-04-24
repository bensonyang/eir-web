<div class="col-md-7">
    <div class="reg-left-img-container">
        <img src="/static/images/books.svg" width="100%" height="100%">
    </div>
</div>
<div class="col-md-5 col-xs-12">
    <div class="row eir-reg-success text-center eir-hide">
        注册成功!
    </div>
    <div class="row eir-reg text-center">
        <div class="eir-reg-form haveno-form">
            <div class="eir-reg-title">
                <span>完善填写个人信息</span>&nbsp;<a class="haveno">已有账号？</a>
            </div>
            <form action="/community" method="post" id="loginForm">
            <#--
           <div class="input-prepend">
               <input id="uid" class="span2" type="text" name="account" placeholder="会员账号">
           </div>
         <div class="input-prepend">
               <input id="pwd" class="span2" type="password" placeholder="密码">
           </div>
           <div class="input-prepend">
               <input id="ppwd" class="span2" type="password" placeholder="确认密码">
           </div>
                <div class="input-prepend">
                    <input id="nickname" class="span2" type="text" name="nickname" placeholder="昵称">
                </div>
                <div class="input-prepend">
                    <input id="realname" class="span2" type="text" name="realname" placeholder="真实姓名" >
                </div>
                <div class="input-prepend">
                    <input id="jobtitle" class="span2" type="text" name="jobtitle" placeholder="公司职位">
                </div>-->
                <div class="input-prepend">
                    <input id="mobilenum" class="span2" type="text" name="mobilenum" placeholder="手机号码">
                </div>
                <div class="input-prepend">
                    <input id="company" class="span2" type="text" name="company" placeholder="公司名称">
                </div>
          <#--      <div class="input-prepend">
                    <div class="radio-box">
                        <input type="radio" name="gender" value="1" /><label class="radio-label">男</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" name="gender" value="2" /><label class="radio-label">女</label>
                    </div>
                </div>-->
                <div class="input-prepend">
                    <input id="reg" class="btn btn-success eir-noradius" value="连接">
                </div>
            </form>

        </div>
        <div class="eir-reg-form eir-hide have-form">
            <div class="eir-reg-title">
                <span>连接已有账号</span>&nbsp;<a class="have">还没有账号？</a>
            </div>
            <form action="/community" method="post" id="loginForm">
                <div class="input-prepend">
                    <input id="connect-uid" class="span2" type="text" name="account" placeholder="会员账号/微信号">
                </div>
                <div class="input-prepend">
                    <input id="connect-pwd" class="span2" type="password" placeholder="密码">
                </div>
                <div class="input-prepend">
                    <input id="connect-reg" class="btn btn-success eir-noradius" value="连接">
                </div>
            </form>

        </div>
    </div>
    <div class="text-center eir-alert">
        <div class="alert alert-danger alert-dismissible eir-hide" role="alert">
            错误：两次密码不一致
        </div>
    </div>
</div>
<input type="hidden" value="${weixinInfo!}" id="weixinInfo">
<input type="hidden" value="${channel!}" id="channel">
<input type="hidden" value="${redir!}" id="redir">