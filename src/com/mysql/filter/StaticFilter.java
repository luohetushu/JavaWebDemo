package com.mysql.filter;

import com.mysql.response.StaticResponse;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 页面静态化过滤器
 */
public class StaticFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        //。。。进行静态文件的是否存在判断 /htmls 中
        String htmlPath = this.filterConfig.getServletContext().getRealPath("/htmls");
        String htmlPage = "static.html";  //实际项目中根据不同的响应页面命名不同的静态文件
        File htmlFile = new File(htmlPath, htmlPage);

        if (htmlFile.exists()){
            //如果静态页面存在，则直接重定向到该静态页面
            httpResponse.sendRedirect(this.filterConfig.getServletContext().getContextPath()
                    + "/htmls/" + htmlPage);
            return;
        }

        //静态化处理 将处理请求的响应重定向到指定路径静态 .html 文件
        //如果不存在，则生成静态文件，实际操作是将响应的 .jsp 文件内容"打印"到指定的静态文件
        StaticResponse staticResponse = new StaticResponse(httpResponse, htmlFile.getAbsolutePath());
        //放行，实际操作是 将响应的 .jsp 文件内容"打印"到指定的静态文件
        filterChain.doFilter(servletRequest, staticResponse);
        //并重定向生成的静态文件
        httpResponse.sendRedirect(this.filterConfig.getServletContext().getContextPath()
                + "/htmls/" + htmlPage);

    }

    @Override
    public void destroy() {

    }
}
