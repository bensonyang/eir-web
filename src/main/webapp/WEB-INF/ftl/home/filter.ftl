<#--
    选中添加 eir-active
-->
<#if tagList??>
<div class="row filter">
    <div class=" col-md-offset-2 col-md-10 col-sm-10 ">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <#if tagList?size gt 2>
                    <#assign size=tagList?size - 1>
                    <li class="dropdown">
                        <a href="javascript:void();" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i>更多</i><span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <#list tagList as tag>
                                <#if tag_index lt size>
                                    <li><a href="javascript:void();" class="tag">${tag.tagName!}</a></li>
                                <#else>
                                </#if>
                            </#list>
                        </ul>
                    </li>
                    <#list tagList as tag>
                        <#if tag_index gt size - 1>
                            <li><a href="javascript:void();" class="tag">${tag.tagName!}</a></li>
                        </#if>
                    </#list>
                <#else><#--标签小于等于3的时候简单遍历样式-->
                    <#list tagList as tag>
                        <li><a href="javascript:void();" class="tag">${tag.tagName!}</a></li>
                    </#list>
                </#if>
                <li><a href="javascript:void();" class="tag">全部</a></li>
            </ul>
        </div>
    </div>
</div>
</#if>
