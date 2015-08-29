<div class="row col-md-3 col-xs-3">
    <div class="left-menu">
        <div class="list-wapper">
            <div class="div-li div-li-title">账号设置</div>
            <div class="div-li"><a href="/meinfo/default">我的信息</a></div>
            <div class="div-li active"><a href="/meinfo/pic">头像管理</a></div>
            <div class="div-li"><a href="/community">返回首页</a></div>
            <div class="div-li-padding"></div>
        </div>
    </div>
</div>
<div class="row col-md-9 col-xs-9">
    <div class="mepic-title">
        头像
    </div>
    <div class="subline01"></div>
    <div class="mepic-op">
        <input type="button" value="选择头像" id="fileToUploadBtn">
        <input type="file" id="fileToUpload">
    </div>
    <div class="mepic-mainpic">
        <div class="mepic-mainpic-img">
            <img src="/static/images/mepic2.png" id="target" alt="" width="100%"/>
        </div>
        <div id="preview-pane">
            <div class="preview-container" id="preview-container">
                <#if user.avatarUrl?has_content>
                    <img src="${user.avatarUrl!}" id="jcrop-preview" class="jcrop-preview" alt="Preview" />
                <#else>
                    <img src="/static/images/mepic.jpg" id="jcrop-preview" class="jcrop-preview" alt="Preview" />
                </#if>
            </div>
        </div>
    </div>
    <div class="mepic-save-div">
        <input type="button" value="保存" id="save-img">
    </div>
</div>