// 设置可跨域访问

$(function(){
    const userId = $.cookie("user_id");
    request("/home/1", "get", null, true, function (result) {
        const usernameComponent = $('#username');
        const emailComponent = $('#email');
        const avatarComponent = $('#avatar');
        const userInfo = result.data.user;
        const blogList = result.data.blogs;
        const corpusList = result.data.corpuses;

        usernameComponent.text(userInfo.username);
        emailComponent.text(userInfo.email);
        avatarComponent.html('<img id="avatar" src="' + userInfo.avatar + '" alt="..." class="img-fluid rounded-circle">');
        $("#blogNum").text(userInfo.blogNum);
        $("#corpusNum").text(userInfo.corpusNum)
    }, function () {
        alert('error')
    });
});