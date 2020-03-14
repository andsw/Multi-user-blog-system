let blogEditor;

$(function() {
    blogEditor = editormd("blog-editormd", {
        width   : "100%",
        height  : "100%",
        codeFold : false,
        syncScrolling : "single",
        // 关闭图片上传功能
        imageUpload: true,
        imageUploadURL : "./php/upload.php",

        emoji: true,
        taskList: true,
        // Using [TOCM]
        tocm: true,
        // 开启科学公式TeX语言支持，默认关闭
        tex: true,
        // 开启流程图支持，默认关闭
        flowChart: true,
        // 开启时序/序列图支持，默认关闭,
        sequenceDiagram: true,
        // 这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置
        // 可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
        saveHTMLToTextarea : true,

        // 设置主题, TODO： 前端设置编辑器主题
        // theme: "dark",
        // editorTheme: "pastel-on-dark",
        // previewTheme: "dark",

        // 编辑器全屏
        // onload: function () {
        //     this.fullscreen();
        // },

        onload: function() {
            toastr.info('asdf');
        },

        onfullscreen : function() {
            toastr.info("ESC退出全屏");
            $(".header").hide();
            // console.log("onfullscreen =>", this, this.id, this.settings);
        },
        onfullscreenExit : function() {
            $(".header").show();
            // alert("onfullscreenExit");
            // console.log("onfullscreenExit =>", this, this.id, this.settings);
        },

        // 定制toolbar按钮
        toolbarIcons: function () {
            return ["undo", "redo", "|", "hr", "link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "emoji", "|",
                    "watch", "preview", "fullscreen", "clear", "search"]
        }
    });
    $("#submit_blog").click(function () {
        const blogContent = blogEditor.getMarkdown();
        console.log(blogContent)
    });
});