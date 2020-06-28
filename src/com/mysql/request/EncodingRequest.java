package com.mysql.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * ServletRequest 装饰类
 * 解决全站 GET 请求编码问题
 */
public class EncodingRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    public EncodingRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {

        String value = this.request.getParameter(name);

        if (value != null) {
            //设置请求编码
            value = new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        }

        return value;

    }
}
