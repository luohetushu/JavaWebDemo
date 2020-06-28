package com.mysql.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 自定义 JSP 标签
 */
public class MyTag2 extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        //super.doTag();
        getJspContext().getOut().print("Hello MyTag2");
    }
}
