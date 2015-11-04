<#--
    选中添加 eir-active
-->
<#if tagList??>
<div class="filter">
    <div class="container">
        <div class="row">
            <div class="col-md-offset-1 col-md-10 col-sm-10 filter-inside">
                <ul class="nav navbar-nav">
                    <li class="m-tag"><a href="javascript:void(0)"><l>全部</l><span class="caret"></span></a></li>
                    <#list tagList as tag>
                        <li class="m-hide"><a href="javascript:void(0)" class="tag">${tag.tagName!}</a></li>
                    </#list>
                    <li class="m-hide"><a href="javascript:void(0)" class="tag eir-active">全部</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<#--
<div class="row filter">
<div class=" col-md-offset-2 col-md-10 col-sm-10 filter-inside">
    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <#list tagList as tag>
                <li><a href="javascript:void(0)" class="tag">${tag.tagName!}</a></li>
            </#list>

            <#if tagList?size gt 2>
                <#assign size=tagList?size - 1>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i>更多</i><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <#list tagList as tag>
                            <#if tag_index lt size>
                                <li><a href="javascript:void(0)" class="tag">${tag.tagName!}</a></li>
                            <#else>
                            </#if>
                        </#list>
                    </ul>
                </li>
                <#list tagList as tag>
                    <#if tag_index gt size - 1>
                        <li><a href="javascript:void(0)" class="tag">${tag.tagName!}</a></li>
                    </#if>
                </#list>
            <#else>
                <#list tagList as tag>
                    <li><a href="javascript:void(0)" class="tag">${tag.tagName!}</a></li>
                </#list>
            </#if>
            <li><a href="javascript:void(0)" class="tag eir-active">全部</a></li>
        </ul>
    </div>
</div>
</div>
-->
</#if>
