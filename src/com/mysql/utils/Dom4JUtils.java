package com.mysql.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Dom4JUtils {

    public static final String XML_PATH = "res" + File.separator + "riders.xml";

    /**
     * 获取 org.dom4j.Document 对象
     * @param xmlPath .xml 文件路径
     * @return
     */
    public static Document getDocument(String xmlPath) throws DocumentException{
        SAXReader saxReader = new SAXReader();
        return saxReader.read(xmlPath);
    }

    /**
     * 回写 XML 文件
     * @param xmlPath   .xml 文件路径
     * @param document   org.dom4j.Document 对象
     */
    public static void writeXML(String xmlPath, Document document) throws IOException{
        XMLWriter xmlWriter = null;
        try {
            OutputFormat format = OutputFormat.createPrettyPrint(); //有缩进效果
            //OutputFormat format = OutputFormat.createCompactFormat(); //压缩成一行
            xmlWriter = new XMLWriter(new FileOutputStream(xmlPath), format);
            xmlWriter.write(document);
        } finally {
            if (xmlWriter != null){
                xmlWriter.close();
            }
        }
    }


}
