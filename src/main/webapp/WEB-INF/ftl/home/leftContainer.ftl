<div class="col-md-offset-1 col-md-10 col-sm-10">
    <div class="row">
        <div class="row eir-options">
            <div class="col-md-4">
                <i class="icon-comment icon-grey active" id="write-feed"><a>写说说</a></i>
                <i class="icon-link icon-grey" id="recommend-link"><a>推荐网页</a></i>
            </div>
        </div>
    </div>
    <div class="row">
        <form>
            <div class="form-group">
                <div class="for-recommend-link eir-hide">
                    <input type="text" class="eir-recommend-link" placeholder="输入网址...">
                    <input type="button" class="btn btn-default eir-noradius eir-input-link" value="爬取"/>
                </div>
                <div class="link-page-content eir-hide" style="display: none;">
                    <div class="link-page-content-title"><span></span><div class="close">X</div></div>
                    <div class="link-page-content-abstract"></div>
                </div>
                <textarea class="eir-form-control main-textarea" rows="3"></textarea>
                <div class="col-md-12 col-xs-12 eir-box1">
                   <li class="icon-tags icon-grey">
                        <label class="eir-label">标签</label>
                    </li>
                    <span class="tags tag-container">
                    </span>
                    <a class="btn btn-default pull-right eir-comments-btn" type="submit">发表</a>
                </div>
            </div>
        </form>
    </div>
<#--    <div class="row tag-container eir-hide">
        <!--<input value="O2O" type="button" class="tag active">&ndash;&gt;
    </div>-->
    <div class="feed-container">
    </div>
    <!--place feed here-->
    <div class="row feed-end">
        加载ing...
    </div>
</div>
<#include "home/templates.ftl">