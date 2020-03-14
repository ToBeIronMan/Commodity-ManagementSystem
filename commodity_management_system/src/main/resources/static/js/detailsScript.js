var contextPath =$("#rootUrl").val();
/**
 * 分页异步加载数据页
 * @param page
 */
function loadData(page) {
    $("#selected").load(contextPath + "/commodity/list", buildQuery(page));
}
function buildQuery(page) {
    var query = {};
    query.page = typeof page == "undefined" ? 1 : page;
    query.size = 3;
    return query;
}


/**
 * 更新商品
 */
function updateCommodity() {
    var img = document.getElementById("uploadCommodity2").files[0];
    var readImg = new FileReader();
    readImg.readAsDataURL(img);
    var id=$("#commodityId").val();
    readImg.onload = function (readEvent) {
        var base64 = readEvent.target.result.split(",")[1];
        console.log(base64);
        var myFormDate=new FormData($("#updateCommodityForm")[0]);
        myFormDate.append("imageStr",base64);
        $.ajax({
            type: "post",
            url: contextPath+"/update",
            data: myFormDate,
            processData:false,
            contentType:false,
            datatype:"text",
            complete: function (html) {
                window.location=contextPath+"/selectOne/"+id;
            }
        });
    };
}