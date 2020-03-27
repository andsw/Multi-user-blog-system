
var testEditor;
$(function () {
    const blogId = getUrlParam("blogId");
    if (blogId != null) {
        request("/blog/" + blogId, 'get', null, false,
                function (result) {
                    console.log(result);
                    if (result.code === 200) {
                        const blogInfo = result.data.blog;
                        $("title")[0].text = blogInfo.title;
                        $("#blog_title").append(blogInfo.title);
                        $("#blog_content").val(result.data.content);
                    } else {
                        toastr.error(result.message);
                    }
                }, function () {
                toastr.error("获取文章发生未知错误！")
            });
    } else {
        toastr.warn("没有指定文章！");
    }
    testEditor = editormd.markdownToHTML("doc-content", {//注意：这里是上面DIV的id
        width: "100%",
        height: 900,
        htmlDecode: "style,script,iframe",
        emoji: true,
        taskList: true,
        tex: true, // 默认不解析
        flowChart: true, // 默认不解析
        sequenceDiagram: true, // 默认不解析
        codeFold: true,

        // 设置主题
        theme: "dark",
        editorTheme: "pastel-on-dark",
        previewTheme: "dark"
    });
});