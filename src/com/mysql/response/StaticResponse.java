package com.mysql.response;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 页面静态化处理
 * 在 静态化过滤器 StaticFilter 中使用
 */
public class StaticResponse extends HttpServletResponseWrapper {

    private HttpServletResponse response;
    private PrintWriter printWriter;

    /**
     * HttpServletResponse 装饰
     * @param response
     * @param staticPath  静态化处理，静态 .html 文件路径
     */
    public StaticResponse(HttpServletResponse response, String staticPath) throws IOException {
        super(response);
        this.response = response;
        //创建与指定文件路径绑定的打印流
        this.printWriter = new PrintWriter(staticPath, StandardCharsets.UTF_8);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return this.printWriter;
    }
}
