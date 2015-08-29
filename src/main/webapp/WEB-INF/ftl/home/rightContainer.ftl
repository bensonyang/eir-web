<#--右容器-->
<#import "common/constants.ftl" as con>
<div class="eir-home-right col-md-3 col-sm-offset-1 col-sm-4">
    <#if con.loginState == con.login>
        <#include "home/profile.ftl" />
    <#else>
        <#include "home/login.ftl" />
    </#if>
    <div class="row eir-mytags">
        <div class="eir-ul list-unstyled text-center">
            <div class="eir-li active">
                <li class="icon-tag"><label>全部</label></li>
            </div>
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