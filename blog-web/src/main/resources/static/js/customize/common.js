host = 'http://localhost:8080';

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

function redirectTo(redirectUrl) {
    window.location.href = redirectUrl;
}

function getUrlParam(name) {
    const reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    const r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}