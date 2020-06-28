package com.mysql.bean;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

public class HeiseiRider implements Serializable, HttpSessionBindingListener, HttpSessionActivationListener {
    int id;
    String name;
    String video_time;
    int rider_num;

    public HeiseiRider() {
    }

    public HeiseiRider(int id, String name, String video_time, int rider_num) {
        this.id = id;
        this.name = name;
        this.video_time = video_time;
        this.rider_num = rider_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideo_time() {
        return video_time;
    }

    public void setVideo_time(String video_time) {
        this.video_time = video_time;
    }

    public int getRider_num() {
        return rider_num;
    }

    public void setRider_num(int rider_num) {
        this.rider_num = rider_num;
    }

    /**
     * HttpSession 域对象绑定 JavaBean 对象时监听方法
     * @param event
     */
    @Override
    public void valueBound(HttpSessionBindingEvent event) {

    }

    /**
     * HttpSession 域对象解绑 JavaBean 对象时监听方法
     * @param event
     */
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {

    }

    /**
     * 当对象感知被钝化时调用该方法，前提是对象有被 HttpSession 域对象绑定
     * @param httpSessionEvent
     */
    @Override
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {

    }

    /**
     * 当对象感知被活化时调用该方法，前提是对象有被 HttpSession 域对象绑定
     * @param httpSessionEvent
     */
    @Override
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public String toString() {
        return "KamenRider{ " + this.name + " plays in " + this.video_time + ", has " + this.rider_num + " rides. }";
    }
}
