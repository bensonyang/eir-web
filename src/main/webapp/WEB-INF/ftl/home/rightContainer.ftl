<#--左容器-->
<#import "common/constants.ftl" as con>
<div class="eir-home-right col-md-3 col-sm-offset-1 col-sm-4">
    <#if con.loginState == con.login>
        <#include "home/profile.ftl" />
    <#else>
        <#include "home/login.ftl" />
    </#if>
    <div class="row eir-mytags">
        <div class="eir-ul list-unstyled text-center">
            <div class="eir-li" data-index="0">
                <li><label>互联网金融</label></li>
            </div>
            <div class="eir-li" data-index="1">
                <li><label>O2O</label></li>
            </div>
            <div class="eir-li" data-index="2">
                <li><label>互联网房地产</label></li>
            </div>
            <div class="eir-li" data-index="3">
                <li><label>智能生态</label></li>
            </div>
            <div class="eir-li" data-index="4">
                <li><label>互联网教育</label></li>
            </div>
            <div class="eir-li" data-index="5">
                <li><label>互联网医疗</label></li>
            </div>
        </div>
    </div>
</div>