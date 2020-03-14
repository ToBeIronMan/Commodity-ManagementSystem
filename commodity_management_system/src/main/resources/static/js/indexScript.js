var contextPath =$("#rootUrl").val();
/**
 * 添加商品
 */
function addCommodity() {
    var img = document.getElementById("uploadCommodity").files[0];
    var readImg = new FileReader();
    readImg.readAsDataURL(img);
    readImg.onload = function (readEvent) {
        var base64 = readEvent.target.result.split(",")[1];
        console.log(img.name);
        console.log(base64);
        var myFormDate=new FormData($("#addCommodityForm")[0]);
        myFormDate.append("imageStr",base64);
        myFormDate.append("imageName",img.name);
        /* $("#imageStr").val(base64);
         $("#imageName").val(img.name);
         var form=$("#imageform");
         form.submit();*/
        $.ajax({
            type: "post",
            url: contextPath+"/insert",
            data: myFormDate,
            processData:false,
            contentType:false,
            datatype:"text",
            complete: function (html) {
                window.location=contextPath+"/index"
            }
        });
    };
}


/**
 * 根据商品名进行查询
 */
function searchCommodity() {
    var str=$("#search").val();
    console.log(str);
    $.ajax({
        type: "post",
        url: contextPath+"/search",
        data: {
            "search":str
        },
        datatype:"text",
        complete: function (html) {
            $("#selected").html(html.responseText)
        }
    });
}


function getTextByImage() {
    var img =$("#upload")[0].files[0];
    getBase64Image(img,contextPath+"/ImageOrc/getTextByImage");
}

function searchByImage() {
    var img =$("#upload")[0].files[0];
    getBase64Image(img,contextPath+"/ImageOrc/searchByImage");
}
//将图片进行编码后发送到后台
function getBase64Image(img,url) {
    var readImg = new FileReader();
    readImg.readAsDataURL(img);

    readImg.onload = function (readEvent) {
        var base64 = readEvent.target.result.split(",")[1];
        console.log(img.name);
        $.ajax({
            type: "post",
            url: url,
            data: {
                "bases": base64,
                "name": img.name
            },
            datatype:"text",
            complete: function (html) {
                $("#selected").html(html.responseText)
            }
        });
    };
}


/**
 * 得到单个商品详情
 * @param id
 */
/*function getCommodityDetail(id) {
    $.ajax({
        type: "post",
        url: "selectOne",
        data: {
            "id": id
        },
        datatype:"text",
        complete: function (data) {
            $("#selected").html(data.responseText)
        }
    });
}*/


/**
 * 动态效果
 */
if(!(/msie [6|7|8|9]/i.test(navigator.userAgent))) {
    new WOW().init();
};


