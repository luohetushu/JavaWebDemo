package com.mysql.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * javax.servlet.ServletContext，域对象监听器
 * 监听域对象的"创建"与"销毁"，以及域属性的"添加、移除与覆盖"
 */
public class ServletListener implements ServletContextListener, ServletContextAttributeListener {

    /**
     * 一个 Java web 项目中只有一个 ServletContext 域对象，随服务器启动创建或关闭销毁
     * 可以在该监听方法中进行一些初始化操作，比如数据库的连接啥的
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    /**
     * 一个 Java web 项目中只有一个 ServletContext 域对象，随服务器启动创建或关闭销毁
     * 可以在该监听方法中进行一些初始化操作，比如数据库的连接啥的
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("添加了名为 " + event.getName() + " 的属性，值为 " + event.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("重新设置了名为 " + event.getName()
                + " 的属性，其原先值为 " + event.getValue()
                + "，新覆盖的值为 " + event.getServletContext().getAttribute(event.getName()));
    }
}
