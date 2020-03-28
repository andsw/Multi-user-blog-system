$(function () {
    loadBasicUserInfo();
});

function loadBlogs(pageNum, corpusId, userId) {
    if (userId != null) {
        request("/user/{0}/corpus/{1}/page/{2}/blog".format(userId, corpusId == null ? -1 : corpusId, pageNum),
                'get', null, true, function (result) {
                if (result.code === 200) {
                    const totalPageCount = result.data.totalPageNum;
                    const isMyBlog = result.data.myBlog;
                    const blogList = result.data.blogList;
                    let blogTableTbodyContent = "";

                    for (let blogItem in blogList) {
                        const blog = blogItem.blog;
                        blogTableTbodyContent += '<tr class="align-items-center blog_row">\n'
                                                 + '    <th scope="row" class="align-middle">1</th>\n'
                                                 + '    <td class="align-middle"><h2>' + blogItem.title + '</h2></td>\n'
                                                 + '    <td class="align-middle">\n'
                                                 + '    <input type="hidden" name="blogId" value="' + blog.id + '">\n'
                                                 + '    <input type="hidden" name="authorId" value="' + blog.userId + '">\n'
                                                 + '    <input type="hidden" name="corpusId" value="' + blog.corpusId + '">\n'
                                                 + '    <div class="comments userBehaviorNum"><i class="fa fa-comment-o userBehaviorNumImg"></i>' + blog.commentNum + '</div>\n'
                                                 + '    <div class="read userBehaviorNum"><i class="fa fa-eye userBehaviorNumImg"></i>' + blog.readNum + '</div>\n'
                                                 + '    <div class="like userBehaviorNum"><i class="fa fa-thumbs-up userBehaviorNumImg"></i>' + blog.loveNum + '</div>\n'
                                                 + '    <div class="collect userBehaviorNum"><i class="fa fa-folder-o userBehaviorNumImg"></i>' + blog.collectNum + '</div>\n'
                                                 + '    <div class="text-gray userBehaviorNum">字数：' + blog.wordNum + '</div>\n'
                                                 + '</td>\n';
                        //是用户自己的文章的话显示编辑删除按钮
                        if (isMyBlog) {
                            blogTableTbodyContent += '<td class="align-middle">\n'
                                                     + '    <button class="btn btn-primary btn-info text-white">编辑</button>\n'
                                                     + '    <button class="btn btn-primary btn-danger text-white">删除</button>\n'
                                                     + '</td></tr>';
                        } else {
                            // 否则显示赞按钮（点赞显示已点赞）和收藏按钮（收藏过显示已收藏）
                            blogTableTbodyContent += '<td class="align-middle">\n';
                            if (blogItem.loved) {
                                blogTableTbodyContent += '<button class="btn btn-primary btn-info text-white disabled">👍</button>\n';
                            } else {
                                blogTableTbodyContent += '<button class="btn btn-primary btn-info text-white">👍</button>\n';
                            }
                            if (blogItem.collected) {
                                blogTableTbodyContent += '<button class="btn btn-primary btn-danger text-white disabled">已收藏</button>\n'
                            } else {
                                blogTableTbodyContent += '<button class="btn btn-primary btn-danger text-white">收藏</button>\n';
                            }
                            blogTableTbodyContent += '</td></tr>';
                        }
                    }
                    $("#blog_table_tbody").html(blogTableTbodyContent);
                } else {
                    toastr.error("获取文章发生错误：" + result.message);
                }
            }, function () {
                toastr.error("未知错误：" + result.message);
            });
    } else {
        redirectTo(LOGIN_PATH, ToastLevel.INFO, "未登录，无法获取本人文章列表！跳转登录...");
    }
}