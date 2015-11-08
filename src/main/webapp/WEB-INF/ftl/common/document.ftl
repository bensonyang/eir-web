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
<div class="container-fluid">
    <div class="row eir-home-header">
        <div class="eir-brand">
        <#--    <h1>
                Morningside Community
            </h1>-->
        </div>
        <nav class="navbar navbar-default eir-nav" >
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed eir-nav-collapsed" data-target=".eir-mytags">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
            </div>

        </nav>
    </div>
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