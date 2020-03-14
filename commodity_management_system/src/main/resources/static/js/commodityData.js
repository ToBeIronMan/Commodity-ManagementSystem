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
    return query;
}