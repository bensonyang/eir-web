require.config({
    baseUrl : "/static/",
    paths:{
        "jquery" : "components/jquery/jquery.min"
    },
    shim:{
        "jquery":{
            exports : "$"
        }
    }
});
define(['jquery'],function($){
    var AUTOTIME = 1500;
    var ZINDEX = 10000;
    function Toast(text,time){
        time = time || AUTOTIME;

        var CSS = {
            'display':'none',
            'position':'fixed',
            'z-index':ZINDEX,
            'top':'50%',
            'left':'50%',
            'min-width':'100px',
            'max-width':'160px',
            'padding':'10px',
            'border-radius':'5px',
            '-webkit-border-radius':'5px',
            'background':'#000',
            'color':'#fff',
            'text-align':'center',
            'font-size':'14px',
            'word-break':'break-all',
            '-webkit-box-sizing':'border-box',
            'box-sizing':'border-box'
        };

        var box = $('<div>'+text+'</div>');
        box.css(CSS).appendTo('body').css('margin','-'+ box.height()/2+'px 0 0 -'+ box.width()/2+'px' );
        box.show();
        var timer = setTimeout(function(){
            box.fadeOut(500);
            clearTimeout(timer);
        },time);

    }
    return Toast;
});