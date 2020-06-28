package com.mysql.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 自定义 JSP 标签
 */
public class MyTag3 extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();  //获取当前页面输出流
        out.write("*********************<br/>");
        getJspBody().invoke(out);  //获取当前标签内的 标签体内容 ，并使之输出到当前页面
        out.write("<br/>*********************");
    }
}
