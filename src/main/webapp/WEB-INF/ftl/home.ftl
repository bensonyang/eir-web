<#include "common/document.ftl"/>
<@html mainCss="static/css/home.css" mainJs="static/js/home" page="home">
<div class="">
    <div class="row">
        <#include "home/leftContainer.ftl"/>
          <#include "home/rightContainer.ftl"/>
    </div>
</div>
    <#include "home/mobileContent.ftl" />
</@html>

