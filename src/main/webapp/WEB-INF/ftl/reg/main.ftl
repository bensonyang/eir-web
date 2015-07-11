<div class="col-md-7">
    <div class="reg-left-img-container">
        <img src="${basepath!}static/images/books.svg" width="100%" height="100%">
    </div>
</div>
<div class="col-md-5 col-xs-12">
    <div class="row eir-reg-success text-center eir-hide">
        注册成功!
    </div>
    <div class="row eir-reg text-center">
        <div class="eir-reg-form">
            <form action="/community" method="post" id="loginForm">
                <div class="input-prepend">
                    <input id="uid" class="span2" type="text" name="account" placeholder="会员账号/微信号">
                </div>
                <div class="input-prepend">
                    <input id="pwd" class="span2" type="password" placeholder="密码">
                </div>
                <div class="input-prepend">
                    <input id="ppwd" class="span2" type="password" placeholder="确认密码">
                </div>
                <div class="input-prepend">
                    <input id="reg" class="btn btn-success eir-noradius" value="注册">
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