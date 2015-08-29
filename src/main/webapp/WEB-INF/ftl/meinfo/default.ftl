<div class="row col-md-3 col-xs-3">
    <div class="left-menu">
        <div class="list-wapper">
            <div class="div-li div-li-title">账号设置</div>
            <div class="div-li active"><a href="/meinfo/default">我的信息</a></div>
            <div class="div-li"><a href="/meinfo/pic">头像管理</a></div>
            <div class="div-li"><a href="/community">返回首页</a></div>
            <div class="div-li-padding"></div>
        </div>
    </div>
</div>
<div class="row col-md-9 col-xs-9">
    <div class="row eir-input-group-container">
        <div class="row eir-input-group">
            <div class="l">我的昵称:</div>
            <input val name="nickname" type="text" placeholder="昵称" class="border-zero icon-grey" value="${user.nickname!}" disabled="disabled">
            <span class="op"><a op>修改</a></span>
            <div class="eir-clear-both"></div>
        </div>
        <div class="row eir-input-group">
            <div class="l">真实姓名:</div>
            <input val name="realname" type="text" placeholder="真实姓名" class="border-zero icon-grey" value="${user.realname!}" disabled="disabled">
            <span class="op"><a op>修改</a></span>
            <div class="eir-clear-both"></div>
        </div>
        <div class="row eir-input-group">
            <div class="l">手机号码:</div>
            <input val name="mobilenum" type="text" placeholder="手机号码" class="border-zero icon-grey" value="${user.mobilenum!}" disabled="disabled">
            <span class="op"><a op>修改</a></span>
            <div class="eir-clear-both"></div>
        </div>
        <div class="row eir-input-group">
            <div class="l">性别:</div>
            <uninput>
                <div class="radio-item">
                    <input type="radio" value="1" name="gender" <#if user.gender == 1>checked="checked" </#if>/><label class="radio-label icon-grey">男</label>
                </div>
                <div class="radio-item">
                    <input type="radio" value="2" name="gender" <#if user.gender == 2>checked="checked" </#if> /><label class="radio-label icon-grey">女</label>
                </div>
            </uninput>
            <div class="eir-clear-both"></div>
        </div>
        <div class="row eir-input-group">
            <div class="l">公司职位:</div>
            <input val name="jobtitle" type="text" placeholder="公司职位" class="border-zero icon-grey" value="${user.jobtitle!}" disabled="disabled">
            <span class="op"><a op>修改</a></span>
            <div class="eir-clear-both"></div>
        </div>
        <div class="row eir-input-group">
            <div class="l">公司名称:</div>
            <input val name="company" type="text" placeholder="公司名称" class="border-zero icon-grey" value="${user.company!}" disabled="disabled">
            <span class="op"><a op>修改</a></span>
            <div class="eir-clear-both"></div>
        </div>
        <div class="row eir-input-group">
            <label class="l">描述:</label>
            <uninput>
                <textarea val name="description" class="border-zero icon-grey" disabled="disabled">${user.description!}</textarea>
            </uninput>
            <span class="op"><a op>修改</a></span>
            <div class="eir-clear-both"></div>
        </div>
    </div>
</div>