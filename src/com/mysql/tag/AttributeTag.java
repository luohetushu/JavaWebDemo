package com.mysql.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 自定义带属性的标签
 */
public class AttributeTag extends SimpleTagSupport {

    //添加属性
    private boolean test;

    /**
     * 该方法会在 doTag() 调用之前被 服务器调用
     * @param test
     */
    public void setTest(boolean test) {
        this.test = test;
    }

    public boolean isTest() {
        return test;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (isTest()){
            getJspBody().invoke(null);  //传 null 时，表示 将当前标签内的 标签体内容输出到当前页面
        }
    }
}
