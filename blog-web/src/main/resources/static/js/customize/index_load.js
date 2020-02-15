// 设置可跨域访问

$(function () {
    // const userId = $.cookie("user_id");
    const userId = 1;
    // 默认top 5 hottest blog
    let blog_num = 5;
    // 加载用户数据信息
    request("/home/user/" + userId, "get", null, true, function (result) {
        if (result.code === 200) {
            const usernameComponent = $('#username');
            const emailComponent = $('#email');
            const avatarComponent = $('#avatar');
            const userInfo = result.data.user;

            usernameComponent.text(userInfo.username);
            emailComponent.text(userInfo.email);
            avatarComponent.html('<img id="avatar" src="' + userInfo.avatar + '" alt="..." class="img-fluid rounded-circle">');
            $("#blogNum").text(userInfo.blogNum);
            blog_num = userInfo.blogNum;
            $("#corpusNum").text(userInfo.corpusNum);
            $("#wordNum").text(userInfo.wordNum);
            $("#fanNum").text(userInfo.fanNum);
            $("#subscriptionNum").text(userInfo.subNum);

            $("#readNum").text(result.data.readNum);
            $("#collectionNum").text(result.data.collectionNum);
            $("#likeNum").text(result.data.loveNum);

        } else {
            alert("获取用户信息发生错误！")
        }
        $(".data-loading").hide()
    }, function () {
        alert('error')
    });

    request("/home/blog/" + userId + "?blogNum=" + blog_num, "get", null, true, function (result) {
        if (result.code === 200) {
            console.log(result.data);
        } else {
            alert("获取热门文章发生错误！")
        }
        $(".data-loading").hide()
    }, function () {
        alert('error')
    });
});