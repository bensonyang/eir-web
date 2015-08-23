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
    <#nested>
<script src="${con.basepath!}static/js/require.js" defer async="true" data-main="${con.basepath!}${mainJs!}"></script>
</body>
</html>
</#macro>