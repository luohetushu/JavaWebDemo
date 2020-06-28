package com.mysql.test;

import com.mysql.bean.HeiseiRider;
import com.mysql.bean.KamenRider;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

/**
 * 将 JavaBean 对象转换成（序列化）为 XML 字符串
 * jar 包：xstream-1.4.12.jar  必须依赖包：xpp3_min-1.1.4c.jar
 */
public class BeenToXML {

    public static void main(String[] args) {

        //1、获取 XStream 对象：XStream xStream = new XStream();
        XStream xStream = new XStream();

        //3、设置别名：比如：xStream.alias("根标签名", List.class); xStream.alias("子标签名", JavaBean.class);
        xStream.alias("Riders", java.util.List.class);
        xStream.alias("KamenRider", com.mysql.bean.KamenRider.class);
        xStream.alias("HeiseiRider", HeiseiRider.class);

        //4、将 JavaBean 类中的指定成员属性设置为标签属性 : xStream.useAttributeFor(JavaBean.class, "成员属性名");
        xStream.useAttributeFor(KamenRider.class, "desc");
        xStream.useAttributeFor(HeiseiRider.class, "id");

        //5、去除 JavaBean 类中的某些成员属性，防止生成标签 :
        // xStream.addImpliciteCollection(JavaBean.class, "成员属性名");  // 去除的集合类成员属性
        xStream.addImplicitCollection(KamenRider.class, "riders");

        //6、防止子标签对应的 JavaBean 类中的某些属性生成标签
        //xStream.omitField(JavaBean.class, "成员属性名");  //基本类成员属性
        xStream.omitField(HeiseiRider.class, "video_time");
        xStream.omitField(HeiseiRider.class, "rider_num");

        //2、转化成 XML：String xml = xStream.toXML( JavaBean 对象 JavaBean 对象 List 集合);
        String xml = xStream.toXML(getKamenRiders());
        System.out.println(xml);

    }

    private static List<KamenRider> getKamenRiders(){
        List<KamenRider> riders = new ArrayList<>();

        KamenRider rider1 = new KamenRider();
        rider1.setDesc("They are new Heisei Riders.");
        rider1.addRider(new HeiseiRider(11, "W", "2010-2011", 3));
        rider1.addRider(new HeiseiRider(12, "OOO", "2011-2012", 2));
        rider1.addRider(new HeiseiRider(16, "Drive", "2015-2016", 3));
        rider1.addRider(new HeiseiRider(18, "Ex-Aid", "2017-2018", 4));

        KamenRider rider0 = new KamenRider();
        rider0.setDesc("They are old Heisei Riders.");
        rider0.addRider(new HeiseiRider(1, "Kuuga", "1999-2000", 1));
        rider0.addRider(new HeiseiRider(3, "Ryuki", "2001-2002", 13));
        rider0.addRider(new HeiseiRider(8, "Den-o", "2007-2008", 8));
        rider0.addRider(new HeiseiRider(9, "Kiva", "2008-2009", 5));
        rider0.addRider(new HeiseiRider(10, "Decade", "2009-2010", 2));

        riders.add(rider0);
        riders.add(rider1);

        return riders;
    }

}
