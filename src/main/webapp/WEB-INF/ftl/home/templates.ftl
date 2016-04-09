<script type="text/template" id="feedTemplate">
    <div class="row eir-feed eir-hide" data-feedId="<%= feedId %>" data-feedtype="<%= feedType %>">
        <% if(!_.isUndefined(tag) && !_.isEmpty(tag)){
        var color = '#3c2415';
        %>
        <span class="feed-tag" style="color:<%= color %>;border:1px solid <%= color %>;"><span class="arrow" style="border-right: 10px solid <%= color %>;"></span><%= tag %></span>
        <% } %>
        <div class="row eir-feed-head" data-userid="<%= author.userId %>">
            <div class="eir-feed-pic">
                <img src="<%= author.avatarUrl %>">
            </div>
            <div class="eir-feed-name-group">
                <span class="eir-feed-name"><%= author.realName %></span>
                <span class="eir-feed-summary"><%= author.company %> <%= author.jobTitle %></span>
            </div>
        </div>
        <div class="row eir-feed-content">
            <div class="row eir-feed-content-title">
                <%= msgBody.title %>
            </div>
            <div class="row eir-feed-content-content">
                <%= msgBody.content %>
                <% if(msgBody.link != undefined && msgBody.link != ""){ %>
                <a href="<%= msgBody.link %>" target="_blank" class="btn btn-default eir-feed-content-link">网址链接</a>
                <% } %>
            </div>
        </div>
        <div class="row eir-feed-options">
            <li class="icon-comment icon-grey"><a>评论</a><span><%= commentCount %></span></li>
            <li class="icon-thumbs-up icon-grey">
                <% if(liked){%>
                <a class="liked">取消赞</a>
                <% }else{ %>
                <a class="unliked">赞</a>
                <% } %>
                <span><%= likeCount %></span>
            </li>
            <%
                if(canDelete){
            %>
            <li class="icon-trash icon-grey">
                <a deleteFeed data-toggle="popover" data-placement="bottom" data-container="body" >删除</a>
            </li>
            <% } %>
            <li class="icon-time icon-grey pull-right">
                <d><%= addTime %></d>
            </li>

        </div>
        <div class="eir-feed-item-container">
            <%
            if(comment != undefined){
            _.each(comment,function(_comment){
            %>
            <div class="row eir-feed-comments-item" data-commentid="<%= _comment.commentId %>">
                <div class="eir-feed-pic01">
                    <img src="<%= _comment.userPic %>">
                </div>
                <div class="eir-feed-comments-item-content">
                                    <span class="eir-feed-comments-item-content-name">
                                        <span class="icon-grey">
                                            <a><%= _comment.userName %></a>
                                        </span>
                                        <% if(_comment.toUserName != undefined && _comment.toUserName != '') %>
                                        回复
                                        <span class="icon-grey">
                                            <a>@<%= _comment.toUserName %></a>
                                        </span>
                                        <% _comment.userName %>
                                        : <%= _comment.content %></span>
                                    <span class="eir-feed-comments-item-content-time icon-grey">
                                        <d><%= _comment.commentTime %></d>
                                        <% if(_comment.canDelete){ %>
                                        <a deleteComment data-toggle="popover" data-commentid="<%= _comment.commentId %>" data-placement="bottom" data-container="body" >删除</a>
                                        <% } %>
                                        <a backComment data-userid="<%= _comment.userId %>">回复</a>
                                    </span>
                </div>
            </div>
            <%
            });
            }
            %>
            <div class="eir-get-more-comments">
                <% if(2 < commentCount){ %>
                <div class=" icon-grey">
                    <a class="a-more-comments" data-feedid="<%= feedId %>" data-lastcommentindex="<%= lastCommentIndex %>">加载更多(<c><%= commentCount - 2 %></c>)</a>
                </div>
                <% } %>
            </div>
        </div>
        <div class="row eir-feed-comments">
            <input type="text" placeholder="我也说一句">
        </div>
    </div>
</script>
<script type="text/template" id="eir-feed-comments-more">
    <div class="row eir-feed-comments-more">
        <div class="form-group">
            <!--<label class="eir-feed-comments-label">我也说一句</label>-->
            <textarea placeholder="输入评论..." <%= type %> class="col-md-12 col-xs-12 eir-feed-comments-textarea" rows="3"></textarea>
            <a <%= type %> data-touserid="<%= touserid %>" class="btn btn-default pull-right eir-feed-comments-comments eir-noradius">评论</a>
        </div>
    </div>
</script>
<script type="text/template" id="eir-feed-comments">
    <div class="row eir-feed-comments">
        <input type="text" placeholder="我也说一句">
    </div>
</script>
<script type="text/template" id="eir-feed-comments-item">
    <div class="row eir-feed-comments-item eir-hide" data-commentid="<%= commentId %>">
        <div class="eir-feed-pic01">
            <img src="<%= userPic %>">
        </div>
        <div class="eir-feed-comments-item-content">
                <span class="eir-feed-comments-item-content-name">
                    <span class="icon-grey">
                        <a><%= userName %></a>
                    </span>
                    <% if(toUserName != undefined && toUserName != '') %>
                    回复
                    <span class="icon-grey">
                        <a>@<%= toUserName %></a>
                    </span>
                    <% userName %>
                    : <%= content %></span>
                <span class="eir-feed-comments-item-content-time icon-grey">
                    <d><%= commentTime %></d>
                    <% if(canDelete){ %>
                    <a deleteComment data-toggle="popover" data-commentid="<%= commentId %>" data-placement="bottom" data-container="body" >删除</a>
                    <% } %>
                    <a backComment data-userid="<%= userId %>">回复</a>
                </span>
        </div>
    </div>
</script>
<script type="text/template" id="eir-tag-buttons">
    <%
        _.each(msg,function(_tag){
    %>
    <input value="<%= _tag.tagName %>" type="button" class="tag" data-tagid="<%= _tag.tagId %>">
    <%
    });
    %>
</script>