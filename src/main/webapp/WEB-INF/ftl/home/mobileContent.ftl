<div class="container-fluid eir-tags-pop eir-hide">
    <div class="row eir-mytags">
        <div class="eir-ul list-unstyled text-center">
            <#if tagList?? && tagList?size gt 0>
            	<#list tagList as tag>
            		<div class="eir-li">
                	<li><label>${tag.tagName}</label></li>
            		</div>
            	</#list>
            </#if>
        </div>
    </div>
</div>