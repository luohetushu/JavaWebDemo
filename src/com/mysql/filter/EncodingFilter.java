package com.mysql.filter;

import com.mysql.request.EncodingRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 全站编码过滤器
 */
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if (httpRequest.getMethod().equals("POST")){
            //设置 post 请求编码
            servletRequest.setCharacterEncoding("UTF-8");
            //放行
            filterChain.doFilter(servletRequest,servletResponse);
        } else if (httpRequest.getMethod().equals("GET")){
            //设置 get 请求编码
            EncodingRequest encodeRequest = new EncodingRequest(httpRequest);
            //放行
            filterChain.doFilter(encodeRequest,servletResponse);
        }




    }

    @Override
    public void destroy() {

    }
}
