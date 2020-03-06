
var testEditor;
$(function () {
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