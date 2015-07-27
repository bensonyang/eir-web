/**
 * Created by float.lu on 7/7/15.
 */
//require.js配置
require.config({
    baseUrl : "../../",
    paths:{
        "jquery"        : "/static/components/jquery/jquery.min",
        "API"           : "/static/js/API",
        "jcrop"         : "/static/js/jscrop",
        "html2canvas"   : "/static/js/html2canvas",
        "toast" : "/static/js/toast"
    },
    shim:{
        "jquery":{
            exports : "$"
        },
        "html2canvas":{
            exports : "html2canvas"
        },
        "jcrop" : ["jquery"]
    }
});

require(["API","jquery","html2canvas","toast", "jcrop"], function(API, $, html2canvas, toast){

    var toDraw = document.querySelector("#preview-container");
    var saveImgBtn = $('#save-img');
    var fileToUpload = $('#fileToUpload');
    var fileToUploadBtn = $('#fileToUploadBtn');
    var target = document.querySelector('#target');
    var preview = document.querySelector('#jcrop-preview');
    //
    var jcrop_api;
    var boundx,
        boundy,
        $pcnt = $('#preview-pane .preview-container'),
        $pimg = $('#preview-pane .preview-container img'),
        xsize = $pcnt.width(),
        ysize = $pcnt.height();
    //

    fileToUploadBtn.click(function(){
        fileToUpload.click();
    });

    fileToUpload.change(function(){
        var file = this.files[0];
        if(!/image\/\w+/.test(file.type)){
            alert("文件必须为图片！");
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(e){
            //jcrop_api.setImage(this.result);
            $('.mepic-mainpic-img img').attr('src',this.result)
            preview.src = this.result;
            jcrop_api.destroy();
            $('.mepic-mainpic-img img').attr('style','');
            setTimeout(function(){
                $('#target').Jcrop({
                    onChange: updatePreview,
                    onSelect: updatePreview,
                    aspectRatio: xsize / ysize,
                    boxWidth:350
                },function(){
                    var bounds = this.getBounds();
                    boundx = bounds[0];
                    boundy = bounds[1];
                    jcrop_api = this;
                });
            },1000);
        }
    });


    saveImgBtn.click(function() {
        html2canvas(toDraw,{
            allowTaint: true,
            taintTest: false,
            onrendered:function (canvas) {
                $.ajax({
                    type:"POST",
                    url:API.picUpload,
                    data:{
                        base64Pic:canvas.toDataURL()
                    },
                    success:function(data){
                        if(data.code == 200){
                            toast("上传成功");
                            window.location.reload();
                        }else{
                            toast("上传失败");
                        }
                    }
                });
            }
        });
    });



    $('#target').Jcrop({
        onChange: updatePreview,
        onSelect: updatePreview,
        aspectRatio: xsize / ysize
    },function(){
        var bounds = this.getBounds();
        boundx = bounds[0];
        boundy = bounds[1];
        jcrop_api = this;
    });


    function updatePreview(c)
    {
        if (parseInt(c.w) > 0)
        {
            var rx = xsize / c.w;
            var ry = ysize / c.h;

            $pimg.css({
                width: Math.round(rx * boundx) + 'px',
                height: Math.round(ry * boundy) + 'px',
                marginLeft: '-' + Math.round(rx * c.x) + 'px',
                marginTop: '-' + Math.round(ry * c.y) + 'px'
            });
        }
    };


});
