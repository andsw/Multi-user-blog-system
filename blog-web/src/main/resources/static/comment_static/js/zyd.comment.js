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
					 console.log('replyId : ' + replyId);
                 },
                 // 点击回复评论按钮时的前端动态变化
                 reply: function (rId, c) {
					 replyId = rId;
                     $('#cancel-reply').show();
                     $('.comment-reply').show();
                     $(c).hide();
                     // $(c).parents('.comment-body').append($('#comment-post'));
                     $(c).parent().parent().append($('#comment-post'));
                 },
                 // 点击取消回复评论按钮时的前端动态变化
                 cancelReply: function (c) {
					 replyId = 0;
                     $('#comment-pid').val("");
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
                                      + '		<a href="#comment-1" class="comment-reply" onclick="$.comment.reply(' + commentId + ', this)"><i class="fa fa-reply fa-fw"></i>回复</a>'
                                      + '     </div>';
                        $.each(obj.childrenComments, function (idx, obj) {
                            comment += '<div class="comment-body" id="' + obj.id + '" style="margin-top: 10px">'
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
                                       + '		<a href="#comment-1" class="comment-reply" onclick="$.comment.reply(' + commentId + ', this)"><i class="fa fa-reply fa-fw"></i>回复</a>'
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