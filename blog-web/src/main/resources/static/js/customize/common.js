HOST = 'http://localhost:8080';
// 用户个人主页
USER_HOMEPAGE_PATH = '/index.html';
//博客系统主页
HOMEPAGE_PATH = '/home.html';
// 登录页面
LOGIN_PATH = '/login.html';
// 注册
REGISTER_PATH = '/register.html';
const ToastLevel = {
    ERROR: 1,
    WARN: 2,
    INFO: 3,
};


function request(path, method, data, async, successMethod, failureMethod) {
    console.log(host + path);
    $.ajax({
        url: host + path,
        method: method,
        data: data,
        type: 'json',
        contentType: 'application/json;charset=UTF-8',
        async: async,
        success: successMethod,
        error: failureMethod
    })
}

function redirectTo(redirectUrl, toastLevel, message = "", timeout = 2000) {
    if (message === "") {
        window.location.href = redirectUrl;
    } else {
        if (toastLevel === ToastLevel.ERROR) {
            toastr.error(message);
        } else if (toastLevel === ToastLevel.WARN) {
            toastr.warn(message);
        } else if (toastLevel === ToastLevel.INFO) {
            toastr.info(message);
        } else {
            toastr.text(message);
        }
        setTimeout(function () {
            window.location.href = redirectUrl;
        }, timeout);
    }
}

function getUrlParam(name) {
    const reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    const r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}