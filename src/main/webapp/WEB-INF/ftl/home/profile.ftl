<#--个人简介模块-->
<div class="row eir-profile">
    <div class="eir-profile-head">
        <div class="eir-feed-pic">
            <img src="${loginUser.avatarUrl}">
        </div>
        <div class="eir-profile-name-group">
            <span class="eir-name">${loginUser.realName}</span>
            <span class="eir-summary">${loginUser.company}&nbsp;&nbsp;${loginUser.jobTitle}</span>
        </div>
        <div class="go-logout">
            <li class="dropdown icon-cog meinfo icon-grey">
                <a href="#" class="dropdown-toggle">
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#" onclick="goMeInfoCenter();" goMeInfoCenter >个人中心</a></li>
                    <li><a href="#" onclick="goOut();" goOut>退出</a></li>
                </ul>
            </li>
        </div>
        <!--
        <div class="go-logout">
            <a class="btn btn-default logout" href="#" title="退出" role="button">
                <li class="icon-off icon-grey"></li>
            </a>
        </div>
        -->
        <div class="eir-clear-both"></div>
        <div class="eir-line-hor">
        </div>
        <div class="row eir-feed-count-and-comments">
            <div class="col-md-6 col-xs-6 eir-feed-count">
                <div class="d">发表数</div>
                                    <span>
                                        <#if counterMap??>
                                        	${counterMap['FeedCounter']!0}
                                        </#if>
                                    </span>
            </div>
            <div class="eir-line-ver">
            </div>
            <div class="eir-feed-comments-count">
                <div class="d">评论数</div>
                                    <span>
                                        <#if counterMap??>
                                        	${counterMap['CommentCounter']!0}
                                        </#if>
                                    </span>
            </div>
        </div>
    </div>
</div>