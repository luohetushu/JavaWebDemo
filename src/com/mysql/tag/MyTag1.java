package com.mysql.tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;
import java.io.IOException;

/**
 * 自定义 JSP 标签
 */
public class MyTag1 implements SimpleTag {

    private PageContext pageContext; //可以用来获取其他内置对象
    private JspFragment jspFragment;
    private JspTag jspTag;

    /**
     * 每次执行标签时都会调用该方法
     * 所有 setXXX() 方法都在 doTag() 之前被服务器调用，所以在该方法中可以使用服务器传递的对象
     * @throws JspException
     * @throws IOException
     */
    @Override
    public void doTag() throws JspException, IOException {
        this.pageContext.getOut().print("Hello MyTag1");
    }

    /**
     * 设置父标签，服务器调用
     * @param jspTag
     */
    @Override
    public void setParent(JspTag jspTag) {

    }

    /**
     * 获取父标签，我们自己调用
     * @return
     */
    @Override
    public JspTag getParent() {
        return null;
    }

    /**
     * 设置 jsp 上下文，被服务器调用，基本传递的是 PageContext 对象
     * @param jspContext
     */
    @Override
    public void setJspContext(JspContext jspContext) {
        this.pageContext = (PageContext) jspContext;
    }

    /**
     * 设置标签体，被服务器调用
     * @param jspFragment
     */
    @Override
    public void setJspBody(JspFragment jspFragment) {
        this.jspFragment = jspFragment;
    }
}
