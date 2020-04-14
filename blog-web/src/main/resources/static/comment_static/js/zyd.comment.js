let replyId = 0;

/**
 * bootstrap版评论框
 *
 * @date 2018-01-05 10:57
 * @author zhyd(yadong.zhang0415#gmail.com)
 * @link https://github.com/zhangyd-c
 */
$.extend({
             comment: {
                 // 点击提交按钮后调用方法！
                 submit: function (target) {
                     // 判断是否登录，即当前是否存在userId和token
                     const userId = $.cookie("userId");
                     if (userId == null || $.cookie("mphjouplfo") == null) {
                         redirectTo(LOGIN_PATH, ToastLevel.INFO, "未登录，无法进入个人主页！跳转登录...");
                     }
                     // 然后再获取文章id
                     const blogId = getUrlParam("blogId");

                     // 读取html内容
                     const content = editor.txt.html();
                     console.log("content text : " + content);
                     // 读取 text
                     // var announcement_mag = editor.txt.text();
                     // console.log("content html : " + announcement_mag);

                     // replyId是parentId
                     request("/blog/" + blogId + "/user/" + userId + "/parent/" + replyId + "/comment", 'post',
                             content, false,
                             function (result) {
                                 if (result.code === 200) {
                                     const obj = result.data;
                                     const commentId = obj.commentId;
                                     let str = '$.comment.reply(' + commentId + ', this, ' + obj.userId + ', ' + usernameStr + ')'
                                     if (result.parentId === 0) {
                                         str = '$.comment.reply(' + commentId + ', this, 0, \'\')';
                                     }
                                     let comment = '<div class="comment-body" id="' + obj.id + '" style="margin-top: 10px">'
                                                   + '     <div class="cheader">'
                                                   + '        <a target="_blank" href="../index.html?userId=' + obj.userId + '">'
                                                   + '           <img al="头像"  class="userImage" src="' + obj.avatar + '">'
                                                   + '           <strong>' + obj.username + '</strong>'
                                                   + '        </a>'
                                                   + '        <div class="timer">'
                                                   + (obj.gender ? "<i class='gender fa fa-venus' style='color: red'/>" : "<i class='gender fa fa-mars' style='color: blue'/>")
                                                   + '           <i class="fa fa-clock-o fa-fw"></i> 刚刚'
                                                   + '        </div>'
                                                   + '     </div>'
                                                   + '     <div class="content">'
                                                   + obj.content
                                                   + '     </div>'
                                                   + '     <div class="sign">'
                                                   + '		<a href="#' + obj.id + '" class="comment-reply" onclick="' + str + '"><i class="fa'
                                                   + ' fa-reply'
                                                   + ' fa-fw"></i>回复</a>'
                                                   + '     </div></div>';
                                     if (obj.parentId === 0) {
                                         comment = "<li>" + comment + '</li>';
                                         $("#comment_ul").append(comment);
                                     } else {
                                         $("#" + obj.parentId).append(comment);
                                     }
                                     editor.txt.html('');
                                     Toastr.success('评论已发表！');
                                 } else {
                                     toastr("评论失败，原因 : " + result.message);
                                 }
                             },
                             function () {
                                 toastr("评论失败，发生未知错误！");
                             });
                 },
                 // 点击回复评论按钮时的前端动态变化
                 reply: function (rId, c, userId, username) {
					 replyId = rId;
                     $('#cancel-reply').show();
                     $('.comment-reply').show();
                     $(c).hide();
                     if (userId !== 0) {
                         // $('#content').html('<a href="/index.html?userId=' + userId + '">@' + username + '</a>');
                         editor.txt.html('<a href="/index.html?userId=' + userId + '">@' + username + '</a>');
                     }
                     // $(c).parents('.comment-body').append($('#comment-post'));
                     $(c).parent().parent().append($('#comment-post'));
                 },
                 // 点击取消回复评论按钮时的前端动态变化
                 cancelReply: function (c) {
					 replyId = 0;
                     editor.txt.html('');
                     $('#cancel-reply').hide();
                     $(c).parents(".comment-body").find('.comment-reply').show();
                     //			$(c).parent().parent().parent().find('.comment-reply').show();
                     $("#comment-place").append($('#comment-post'));
                 }
             }
         });

function loadComment() {
    const blogId = getUrlParam("blogId");
    request("/blog/" + blogId + "/comment?pageSize=10&pageNum=1", 'get', null, true,
            function (result) {
                if (result.code === 200) {
                    let commentUl = $("#comment_ul");
                    $.each(result.data, function (idx, obj) {
						const commentId = obj.id;
                        replyParentStr = '$.comment.reply(' + commentId + ', this, 0, \'\')';
                        let comment = '<li>'
                                      + '  <div class="comment-body" id="' + commentId + '">'
                                      + '     <div class="cheader">'
                                      + '        <a target="_blank" href="../index.html?userId=' + obj.parentCommentUserId + '">'
                                      + '           <img al="头像"  class="userImage" src="' + obj.avatar + '">'
                                      + '           <strong>' + obj.username + '</strong>'
                                      + '        </a>'
                                      + '        <div class="timer">'
                                      + (obj.gender ? "<i class='gender fa fa-venus' style='color: red'/>" : "<i class='gender fa fa-mars' style='color: blue'/>")
                                      + '           <i class="fa fa-clock-o fa-fw"></i> ' + obj.parentCreateTime
                                      + '           <i class="fa fa-map-marker fa-fw"></i>北京市朝阳区'
                                      + '        </div>'
                                      + '     </div>'
                                      + '     <div class="content">'
                                      + obj.parentContent
                                      + '     </div>'
                                      + '     <div class="sign">'
                                      + (obj.childrenComments.length > 0 ? '        <a href="" class="comment-reply" onclick=""><i class="fa fa-comments-o fa-fw"></i>查看回复</a>' : '')
                                      + '		<a href="#' + commentId + '" class="comment-reply" '
                                      + '       onclick="' + replyParentStr + '"><i class="fa fa-reply fa-fw"></i>回复</a>'
                                      + '     </div>';
                        $.each(obj.childrenComments, function (idx, obj) {
                            usernameStr = "'" + obj.username + "'";
                            let replyChildStr = '$.comment.reply(' + commentId + ', this, ' + obj.userId + ', ' + usernameStr + ')';
                            comment += '<div class="comment-body" id="' + obj.commentId + '" style="margin-top: 10px">'
                                       + '     <div class="cheader">'
                                       + '        <a target="_blank" href="../index.html?userId=' + obj.userId + '">'
                                       + '           <img al="头像"  class="userImage" src="' + obj.avatar + '">'
                                       + '           <strong>' + obj.username + '</strong>'
                                       + '        </a>'
                                       + '        <div class="timer">'
                                       + (obj.gender ? "<i class='gender fa fa-venus' style='color: red'/>" : "<i class='gender fa fa-mars' style='color: blue'/>")
                                       + '           <i class="fa fa-clock-o fa-fw"></i> ' + obj.createTime
                                       + '           <i class="fa fa-map-marker fa-fw"></i>北京市朝阳区'
                                       + '        </div>'
                                       + '     </div>'
                                       + '     <div class="content">'
                                       + obj.content
                                       + '     </div>'
                                       + '     <div class="sign">'
                                       + '		<a href="#' + obj.commentId + '" class="comment-reply" onclick="' + replyChildStr + '"><i class="fa'
                                       + ' fa-reply'
                                       + ' fa-fw"></i>回复</a>'
                                       + '     </div></div>';
                        });
                        comment += '  </div></li>';
                        commentUl.append(comment);
                    });
                } else {
                    console.log("加载评论发生异常:" + result.message);
                    toastr.error("加载评论发生异常:" + result.message);
                }
            },
            function () {
                toastr.error("加载评论发生未知异常！");
            });
}

$(function () {
    loadComment();
})