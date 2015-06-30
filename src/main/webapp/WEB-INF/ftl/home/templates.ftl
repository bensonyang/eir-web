<script type="text/template" id="feedTemplate">
    <div class="row eir-feed" data-feedId="<%= feedId %>" data-feedtype="<%= feedType %>">
        <div class="row eir-feed-head" data-userid="<%= author.userId %>">
            <div class="eir-feed-pic">
                <img src="<%= author.avatarUrl %>">
            </div>
            <div class="eir-feed-name-group">
                <span class="eir-feed-name"><%= author.realName %></span>
                <span class="eir-feed-summary"><%= author.company %> <%= author.jobTiltle %></span>
            </div>
        </div>
        <div class="row eir-feed-content">
            <div class="row eir-feed-content-title">
                <%= msgBody.title %>
            </div>
            <div class="row eir-feed-content-content">
                <%= msgBody.content %><a href="<%= msgBody.link %>" class="btn btn-default eir-feed-content-link">网址链接</a>
            </div>
        </div>
        <div class="row eir-feed-options">
            <li class="icon-comment icon-grey"><a>评论</a><span><%= commentCount %></span></li>
            <li class="icon-thumbs-up icon-grey unliked">
                <% if(isLiked == 'true'){%>
                <a class="liked">已赞</a>
                <% }else{ %>
                <a class="unliked">赞</a>
                <% } %>
                <span><%= likeCount %></span>
            </li>
            <li class="icon-trash icon-grey">
                <a deleteFeed data-toggle="popover" data-placement="bottom" data-container="body" >删除</a>
            </li>
        </div>
        <div class=" eir-feed-item-container">
            <%
            if(comments != undefined){
            _.each(comments.data,function(comment){
            %>
            <div class="row eir-feed-comments-item">
                <div class="eir-feed-pic01">
                    <img src="<%= comment.userPic %>">
                </div>
                <div class="eir-feed-comments-item-content">
                                    <span class="eir-feed-comments-item-content-name">
                                        <span class="icon-grey">
                                            <a><%= comment.userName %></a>
                                        </span>
                                        <% if(comment.toUserId != undefined && comment.toUserId != '') %>
                                        回复
                                        <span class="icon-grey">
                                            <a>@<%= comment.toUserName %></a>
                                        </span>
                                        <%  %>
                                        : <%= comment.content %></span>
                                    <span class="eir-feed-comments-item-content-time icon-grey">
                                        <d><%= comment.commentTime %></d>
                                        <a data-toggle="popover" data-commentid="<%= comment.commentId %>" data-trigger="focus" role="button" tabindex="0" data-placement="bottom" data-container="body" >删除</a>
                                    </span>
                </div>
            </div>
            <%
            });
            }
            %>
            <div class="eir-get-more-comments">
                <div class=" icon-grey">
                    <a class="a-more-comments" data-feedid="<%= comments.feedId %>" data-lastindex="<%= comments.lastIndex %>">加载更多(<%= comments.totalCount %>)</a>
                </div>
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
            <label class="eir-feed-comments-label">我也说一句</label>
            <textarea class="col-md-12 col-xs-12" rows="3"></textarea>
            <a class="btn btn-default pull-right eir-feed-comments-comments eir-noradius">评论</a>
        </div>
    </div>
</script>
<script type="text/template" id="eir-feed-comments">
    <div class="row eir-feed-comments">
        <input type="text" placeholder="我也说一句">
    </div>
</script>