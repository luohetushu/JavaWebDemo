//创建 XMLHttpRequest 异步对象
function getXMLHttpRequest(){
    try {
        return new XMLHttpRequest();  //大部分浏览器支持
    } catch (e) {
        try {
            return new ActiveXObject("Msxml2.XMLHTTP") //IE6.0
        } catch (e) {
            try {
                return new ActiveXObject("Microsoft.XMLHTTTP") //IE5.5 及以下版本
            } catch (e) {
                alert("未支持浏览器")
                throw e;
            }
        }
    }
}

/**
 * ajax 请求 option 对象
 * @param option，包含（@param method 请求方式：GET / POST
 *                     @param url 请求服务器地址 url
 *                     @param asyn 是否异步
 *                     @param param 请求参数，如果是 GET 方式，则为 null
 *                     @param type 服务器响应内容类型，文本、XML、Json
 *                     @param callBack 回调函数）
 * 注：.js 文件中，方法不能重载，对象传递与参数传递，方法不能同名
 */
function ajaxOption(option) {
    //Ajax 四步请求操作，获取服务器响应内容，并将内容显示在 p1 中
    //1、获取 XMLHttpRequest 对象
    var xmlHttp = getXMLHttpRequest();
    if (!option.method) {  //method 请求方式未传递
        option.method = "GET";
        option.param = null;
    }
    if (option.asyn === undefined) {  //asyn 是否异步 未定义
        option.asyn = true;
    }
    //2、打开与服务器的连接
    xmlHttp.open(option.method, option.url, option.asyn);
    //判断是否 POST 请求
    if (option.method === "POST") {
        //设置请求头
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    }
    //3、发送请求 如果是 GET 方式，则设置为 null，  // 传 null 是防止部分服务器无法发送
    if (!option.param) {  //param 请求参数，如果是 GET 方式，则为 null
        option.param = null;
    }
    xmlHttp.send(option.param);

    //创建 onreadystatechange 状态改变监听事件
    xmlHttp.onreadystatechange = function () {
        //状态为4（服务器响应结束），并且服务器响应状态码为200（响应成功）
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var data;  //服务器响应内容
            if (!option.type) { //type 服务器响应内容类型 未传递
                data = xmlHttp.responseText;
            } else if (option.type === "text") {
                data = xmlHttp.responseText;
            } else if (option.type === "xml") {
                data = xmlHttp.responseXML;
            } else if (option.type === "json") {
                var strJson = xmlHttp.responseText;
                data = eval("(" + strJson + ")");  //字符串转 json
            }

            //调用回调方法
            option.callBack(data)

        }
    }

}

/**
 * ajax 请求
 * @param method 请求方式：GET / POST
 * @param url 请求服务器地址 url
 * @param asyn 是否异步
 * @param param 请求参数，如果是 GET 方式，则为 null
 * @param type 服务器响应内容类型，文本、XML、Json
 * @param callBack 回调函数
 */
function ajaxParam(method, url, asyn, param, type, callBack) {
    //Ajax 四步请求操作，获取服务器响应内容，并将内容显示在 p1 中
    //1、获取 XMLHttpRequest 对象
    var xmlHttp = getXMLHttpRequest();
    //2、打开与服务器的连接
    xmlHttp.open(method, url, asyn);
    //判断是否 POST 请求

    if (method === "POST"){
        //设置请求头
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    }
    //3、发送请求 如果是 GET 方式，则设置为 null，  // 传 null 是防止部分服务器无法发送
    xmlHttp.send(param);

    //创建 onreadystatechange 状态改变监听事件
    xmlHttp.onreadystatechange = function () {
        //状态为4（服务器响应结束），并且服务器响应状态码为200（响应成功）
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200){
            var data;  //服务器响应内容
            if (type === "text"){
                data = xmlHttp.responseText;
            } else if (type === "xml"){
                data = xmlHttp.responseXML;
            } else if (type === "json"){
                var strJson = xmlHttp.responseText;
                data = eval("(" + strJson + ")");  //字符串转 json
            }

            //调用回调方法
            callBack(data)

        }
    }

}















