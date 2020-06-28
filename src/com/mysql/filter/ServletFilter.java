package com.mysql.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器，随服务器启动创建或关闭销毁
 * 单例
 */
public class ServletFilter implements Filter {

    /**
     * 创建之后执行，进行初始化配置   // Filter 在服务器启动时创建
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 过滤时调用方法 1、在客户端请求（调用资源）之前，执行过滤代码，filterChain.doFilter(request, response); 之前的代码
     *              2、放行客户端请求（调用资源）：filterChain.doFilter(request, response);
     *              3、在客户端请求（调用资源）结束之后，执行结束代码，filterChain.doFilter(request, response); 之后的代码
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        //过滤代码
        //....

        //放行
        filterChain.doFilter(request, response);

        //请求结束后代码
        //...
    }

    /**
     * 销毁之前，对非内存资源进行释放   // 在服务器关闭时销毁
     */
    @Override
    public void destroy() {

    }
}
