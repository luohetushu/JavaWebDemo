package com.mysql.xml;

import com.mysql.utils.Dom4JUtils;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXWriter;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析器之 Dom4J：解析开发包
 */
public class XMLDom4JParser {

    public static void main(String[] args) {
        //Dom4J 解析 XML 文件
        parserXML();
    }

    /**
     * 使用 DOM（Document Object Model 文档对象模型）解析方式解析 XML 文档
     * SAX 解析：采用事件驱动，从上到下一行一行边读边解析，解析到某一个对象，返回对象名称
     * SAXReader creates a Dom4J tree for SAX parsing event
     */
    /**
     * org.dom4j.Node
     *     public interface Node extends Cloneable {}
     * org.dom4j.Branch
     *     public interface Branch extends Node {}
     * org.dom4j.Document
     *     public interface Document extends Branch {}
     *     主要方法：1、获取根标签：Element getRootElement();
     *
     * 标签：
     * org.dom4j.Element
     *     public interface Element extends Branch {}
     *     主要方法：1、获取父标签：Element getParent();
     *              2、获取子标签：List<Element> elements(String var1); //获取指定标签名的所有子标签
     *                           Element element(String var1); //获取指定标签名的第一个子标签
     *                           List<Element> elements(); //获取所有子标签
     *              3、获取标签文本内容: String getText();
     *              4、添加指定名子标签：Element addElement(String var1);  //默认为该父标签的最后一个子标签
     *                 设置子标签文本内容：void setText(String var1); //使用子标签对象设置
     *              5、创建指定名标签：public static Element createElement(String name) {}  //org.dom4j.DocumentHelper 方法
     *                             // Element newElement = DocumentHelper.createElement("senior"); //
     *              6、删除子标签：boolean remove(Element var1); //使用父标签删除指定子标签
     *              7、根据属性名获取标签属性值：String attributeValue(String var1); //var1 属性名
     *              8、设置属性：void setAttributes(List<Attribute> var1);
     *
     * 属性：
     * org.dom4j.Attribute：
     *     public interface Attribute extends Node {}
     */
    /**
     * 回写 .xml 文件
     * org.dom4j.io.XMLWriter:
     *     public class XMLWriter extends XMLFilterImpl implements LexicalHandler {}
     *     构造方法：public XMLWriter(OutputStream out, OutputFormat format) throws UnsupportedEncodingException {}
     *              //OutputStream out: 可以是 .xml 文件输出流，new FileOutputStream("res" + File.separator + "riders.xml")
     *              //OutputFormat format: 输出打印格式：public static OutputFormat createPrettyPrint() {} //有缩进效果
     *                                                 public static OutputFormat createPrettyPrint() {} //压缩为一行
     *     回写方法：public void write(Document doc) throws IOException {}
     *             // Document document = new SAXReader().read("res" + File.separator + "riders.xml");
     */

    /**
     * SAXReader creates a Dom4J tree for SAX parsing event
     * 查询
     */
    private static void parserXML(){
        try {
            //SAXReader reader = new SAXReader();
            //public Document read(String systemId) throws DocumentException{} // .xml 文件路径
            //Document document = reader.read("res" + File.separator + "riders.xml");
            //获取 org.dom4j.Document 对象
            Document document = Dom4JUtils.getDocument(Dom4JUtils.XML_PATH);
            //获取根标签
            Element root = document.getRootElement();

            List<Node> nodes = document.selectNodes("//name");
            for (Node node : nodes) {
                System.out.print(node.getText() + "、");
            }
            System.out.println();

            Node node = document.selectSingleNode("//heisei-rider[@id='11']/xs1:name");
            System.out.println(node.getText()); //W
            Node sNode = document.selectSingleNode("//heisei-rider[@id='11']");
            System.out.println(sNode.asXML()); // asXML(): 将当前标签与子标签转换成字符串

            //获取指定标签名的所有子标签
            List<Element> elements = root.elements("heisei-rider");
            for (Element element: elements){
                String attr = element.attributeValue("id");
                if (attr != null && !attr.equals("")){
                    System.out.println(attr);
                }
                //获取指定标签名的第一个子标签
                Element child = element.element("name");
                //修改标签文本内容
                //child.setText("kamen-rider");
                //删除子标签
                //element.remove(child);
                //获取标签文本内容
                System.out.print(child.getText() + "、");
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * SAXReader creates a Dom4J tree for SAX parsing event
     * 添加并回写
     */
    private static void parserXMLAdd(){
        //回写 .xml 文件
        XMLWriter xmlWriter = null;
        try {
            //SAXReader reader = new SAXReader();
            //public Document read(String systemId) throws DocumentException{} // .xml 文件路径
            //Document document = reader.read("res" + File.separator + "riders.xml");
            //获取 org.dom4j.Document 对象
            Document document = Dom4JUtils.getDocument(Dom4JUtils.XML_PATH);
            //获取根标签
            Element root = document.getRootElement();

            //获取指定标签名的所有子标签
            List<Element> elements = root.elements("heisei-rider");
            for (Element element: elements){
                //获取指定标签名的第一个子标签
                Element child = element.element("name");
                //获取标签文本内容
                System.out.print(child.getText() + "、");
                //添加子标签 //默认为该父标签的最后一个子标签
                Element addElement = element.addElement("couple");
                //设置子标签内容
                addElement.setText("couples");

                //获取指定标签名的所有子标签
                List<Element> children = element.elements();
                //创建标签
                Element newElement = DocumentHelper.createElement("senior");
                newElement.setText("seniors");
                //在指定位置添加标签
                children.add(2, newElement);

            }

            //回写 .xml 文件
            xmlWriter = new XMLWriter(new FileOutputStream("res" + File.separator + "riders.xml"),
                    OutputFormat.createPrettyPrint());
            xmlWriter.write(document);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (xmlWriter != null){
                    xmlWriter.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    }

}
