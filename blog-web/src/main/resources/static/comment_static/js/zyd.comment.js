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
			 console.log("----");
			 toastr.info("...");
			 let $this = $(target);
			 $this.button('loading');
			 // 提交按钮的点击事件
			 $("#detail-form-btn").click(function () {
				 $.ajax({
							type: "get",
							url: "./server/comment.json",
							async: true,
							success: function (json) {
								if (json.statusCode === 200) {
									console.log(json.message);
								} else {
									console.error(json.message);
								}
								$('#detail-modal').modal('hide');

								setTimeout(function () {
									$this.html("<i class='fa fa-check'></i>" + json.message);
									setTimeout(function () {
										$this.button('reset');
										window.location.reload();
									}, 1000);
								}, 1000);
							},
							error: function (data) {
								console.error(data);
							}
						});
			 });
		 },
		 // 点击回复评论按钮时的前端动态变化
		 reply: function (pid, c) {
			 $('#comment-pid').val(pid);
			 $('#cancel-reply').show();
			 $('.comment-reply').show();
			 $(c).hide();
			 $(c).parents('.comment-body').append($('#comment-post'));
			 //			$(c).parent().parent().parent().append($('#comment-post'));
		 },
		 // 点击取消回复评论按钮时的前端动态变化
		 cancelReply: function (c) {
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
	console.log(window.location.href);
    request("/blog/" + blogId + "/comment?pageSize=10&pageNum=1", 'get', null, true,
            function (result) {
                if (result.code === 200) {
                    let commentUl = $("#comment_ul");
                    $.each(result.data, function (idx, obj) {
                        commentUl.append('<li>'
                                         + '  <div class="comment-body" id="obj.id">'
                                         + '     <div class="cheader">'
                                         + '        <a target="_blank" href="https://github.com/zhangyd-c">'
                                         + '           <img class="userImage" src="' + obj.avatar + '">'
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
                                         + '        <a href="#comment-1" class="comment-reply" onclick="$.comment.reply(1, this)"><i class="fa fa-reply fa-fw"></i>回复</a>'
                                         + '     </div>'
                                         + '  </div>'
                                         + '</li>');
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