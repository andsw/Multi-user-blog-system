host = 'http://localhost:8080';

function request(path, method, data, async, successMethod, failureMethod) {
    console.log(host + path);
    $.ajax({
        url: host + path,
        method: method,
        data: data,
        async: async,
        success: successMethod,
        error: failureMethod
    })
}