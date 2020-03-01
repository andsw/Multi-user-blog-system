$(function () {
    $("#login").click(function () {
        const loginData = {};
        loginData["username"] = $("#login-username").val();
        // loginData["email"] = $("#login-email").val();
        loginData["password"] = $("#login-password").val();
        request("/login", "post", JSON.stringify(loginData), false, function (result) {
            if (result.code === 200) {
                let redirectUrlAfterLogin = getUrlParam("redirectUrl");
                if (redirectUrlAfterLogin === null) {
                    redirectTo("/index.html");
                } else {
                    redirectTo(redirectUrlAfterLogin);
                }
            } else {
                $("#login-exception").text(result.message);
            }
        }, function () {
            alert("请求超时");
        });
    });

});