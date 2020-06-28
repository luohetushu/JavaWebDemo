package com.mysql.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 自定义 JSP 标签
 */
public class SkipTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        //super.doTag();
        getJspContext().getOut().print("到我这为止，后面的内容没有了～～～");
        throw new SkipPageException();   //抛出该异常使该标签后面的内容不显示
    }
}
