<#import "common/constants.ftl" as con>
<#--
页面主容器,所有新建页面需引入该容器
mainCss:页面css路径
mainJs:页面js路径 需要符合require规范
page:页面名
-->
<#macro html mainCss mainJs page >
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${title!}</title>
    <meta name="description" content="">
    <!--<meta name="viewport" content="width=device-width">-->
    <meta content="initial-scale = 1.0, minimum-scale = 1.0, maximum-scale = 1.0, user-scalable = yes" name="viewport">
    <link rel="shortcut icon" href="/favicon.ico">
    <link rel="stylesheet" href="${con.basepath!}static/components/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="${con.basepath!}static/components/awesome/css/font-awesome.min.css" />
    <!--[if IE 7]>
    <link rel="stylesheet" href="${con.basepath!}static/components/awesome/css/font-awesome-ie7.min.css">
    <![endif]-->
    <link rel="stylesheet" href="${con.basepath!}${mainCss!}">
</head>
<body>
<!--<div class="container-fluid">-->
<div class="back-to-top eir-hide"></div>
<div class="container-fluid">
    <div class="nav-fix-pad"></div>
    <div class="eir-home-header <#if loginUser?? && page == "home"><#else>eir-notlogin</#if>">
        <#if loginUser?? || page != "home"> <#--如果已经登陆-->
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="container">
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="head-pic"><a href="#"><img src="${loginUser.avatarUrl}"></a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                    我的
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="#" onclick="goMeInfoCenter();" goMeInfoCenter>个人中心</a></li>
                                <#--<li role="separator" class="divider"></li>
                                <li><a href="#" onclick="goOut();" goOut>退出</a></li>-->
                                </ul>
                            </li>
                            <li><a href="#" onclick="goOut();">退出</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        <#else> <#--如果未登陆-->
            <#if page == "home">
                <#include "home/loginBox.ftl">
            </#if>
        </#if>
    </div>
    <#if page == "home">
        <#include "home/filter.ftl">
    </#if>
    <div class="container">
        <div class="row">
            <#nested>
        </div>
    </div>
</div>
<script src="${con.basepath!}static/js/require.js" defer async="true" data-main="${con.basepath!}${mainJs!}"></script>
</body>
</html>
</#macro>