$(function () {
    $("#login").click(function () {
        const loginData = {};
        loginData["username"] = $("#login-username").val();
        // loginData["email"] = $("#login-email").val();
        loginData["password"] = $("#login-password").val();
        request("/login", "post", JSON.stringify(loginData), false, function (result) {
            if (result.code === 302) {
                window.location.href = result.redirectUrl;
            } else {
                $("#login-exception").text(result.message);
            }
        }, function () {
            alert("请求超时");
        });
    });

});