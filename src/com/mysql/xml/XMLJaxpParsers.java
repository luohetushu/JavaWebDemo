package com.mysql.xml;

import com.mysql.custom.MyDefaultHandler;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * XML 解析
 * 解析器之 JAXP（Java API for XML Processing） 解析标准实现
 * JAXP 开发包是 JavaSE 的一部分，由以下几个包及子包组成：
 *               org.w3c.dom: 提供 DOM 方式解析 XML 的标准接口
 *               org.xml.sax: 提供 SAX 方式解析 XML 的标准接口
 *               javax.xml.*: 提供解析 XML 文档的类
 */
public class XMLJaxpParsers {

    /**
     * 解析包：javax.xml.parsers: 提供解析器工厂类得到解析器类对象
     *     DOM（Document Object Model 文档对象模型）：javax.xml.parsers.DocumentBuilder 解析器类
     *                                             javax.xml.parsers.DocumentBuilderFactory 解析器工厂类
     *     SAX（Simple API for XML）：javax.xml.parsers.SAXParser 解析器类
     *                               javax.xml.parsers.SAXParserFactory 解析器工厂类
     */

    /**
     * DOM 解析：根据 xml 的层级结构，在内存中分配一个树形结构，将 xml 的根标签、子标签、属性和文本封装成对象
     * DOM（Document Object Model 文档对象模型）：javax.xml.parsers.DocumentBuilder 解析器类
     *                                         javax.xml.parsers.DocumentBuilderFactory 解析器工厂类
     *     javax.xml.parsers.DocumentBuilder 解析器类
     *         public abstract class DocumentBuilder {}  // 抽象类，通过 对应工厂类对象.newDocumentBuilder(); 获取实例化对象
     *         解析 XML 文档：1、public Document parse(String uri) throws SAXException, IOException {}
     *                            // uri 为 .xml 文件路径，将 XML 文档解析成 Document 对象
     *                      2、public Document parse(File f) throws SAXException, IOException {}
     *     javax.xml.parsers.DocumentBuilderFactory 解析器工厂类
     *         public abstract class DocumentBuilderFactory {} //抽象类，通过 DocumentBuilderFactory.newInstance(); 获取实例化对象
     */

    /**
     * org.w3c.dom.Node：
     *     public interface Node {}
     * org.w3c.dom.Document: // 解析器类对象将 XML 文档解析成 Document 对象，通过 Document 或父接口 Node 方法获取各种对象
     *     public interface Document extends Node {}
     * 重要方法： 获取标签：public Element getElementById(String elementId); //根据标签 id 获取标签
     *          1、查询标签内容：public NodeList getElementsByTagName(String tagname); //通过 Document 对象，根据标签名获取所有相同名的标签集合
     *                        public Node item(int index);  // 根据标签集合 NodeList 获取标签节点
     *                        public String getTextContent() throws DOMException; //根据标签节点获取标签内容
     *          2、添加子标签：public Element createElement(String tagName) throws DOMException; //通过 Document 对象创建子标签
     *                       public Text createTextNode(String data);  //设置创建的子标签的文本内容 (#PCDATA)
     *                       public Node appendChild(Node newChild) throws DOMException; //将标签内容添加人标签，或者将子标签添加入父标签
     *          3、修改标签内容：public void setTextContent(String textContent)throws DOMException; //根据标签节点设置标签内容
     *          4、删除子标签：首先获取子标签节点（根据子标签集合来获取），
     *                       public Node getParentNode();  //获取父标签节点
     *                       public Node removeChild(Node oldChild) throws DOMException; //使用父标签节点删除子标签
     */

    /**
     * 回写 .xml 文件，将操作后的数据更新到原有的 .xml 文件中
     * javax.xml.transform.TransformerFactory:
     *     public abstract class TransformerFactory {} // 抽象类，通过 TransformerFactory.newInstance; 获取实例化对象
     * javax.xml.transform.Transformer:
     *     public abstract class Transformer {} // 抽象类，通过 对应工厂类对象.newTransformer(); 获取实例化对象
     *     回写 .xml 文件：
     *         public abstract void transform(Source xmlSource, Result outputTarget) throws TransformerException;
     *             Source xmlSource：   源，new DOMSource(document)  // Document 对象
     *             Result outputTarget：目标，new StreamResult("res" + File.separator + "riders.xml") // .xml 文件路径
     */

    public static void main(String[] args) {
        //使用 DOM（Document Object Model 文档对象模型）解析方式解析 XML 文档
        //parserDom();
        //删除子标签
        //parserDomDelete();
        //遍历 .xml 文件中所有标签，包括根标签
        //listElements();

        //SAX 解析：采用事件驱动，从上到下一行一行边读边解析，解析到某一个对象，返回对象名称
        parserSAX();

    }

    /**
     * 使用 DOM（Document Object Model 文档对象模型）解析方式解析 XML 文档
     * 查询标签内容
     */
    private static void parserDom(){
        try {
            //1、创建 解析器工厂类对象
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //2、根据 解析器工厂类对象 创建 解析器类对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            //将 XML 文档解析成 Document 对象
            Document document = db.parse("res" + File.separator + "riders.xml");
            //根据标签名获取所有相同名的标签集合
            NodeList nameNodes = document.getElementsByTagName("name");
            for (int i = 0; i < nameNodes.getLength(); i++) {
                Node node = nameNodes.item(i); //标签节点
                //System.out.print(node.getNodeValue() + "、"); //null
                //System.out.print(node.getNodeName() + "、"); //name
                //System.out.print(node.getNodeType() + "、");  //1 ELEMENT_NODE，The node is an <code>Element</code>.
                //node.setTextContent("name" + i); //修改标签内容
                System.out.print(node.getTextContent() + "、"); //W、OOO、Fourze、Wizard、Gaim、Drive、Ghost、Ex-aid、Build、Zi-O、
            }
            System.out.println();

        } catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 使用 DOM（Document Object Model 文档对象模型）解析方式解析 XML 文档
     * 添加子标签
     */
    private static void parserDomAdd(){
        try {
            //1、创建 解析器工厂类对象
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //2、根据 解析器工厂类对象 创建 解析器类对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            //将 XML 文档解析成 Document 对象
            Document document = db.parse("res" + File.separator + "riders.xml");

            //在标签 heisei-rider 下添加子标签
            //根据标签名获取所有相同名的标签集合
            NodeList riderNodes = document.getElementsByTagName("heisei-rider");
            for (int i = 0; i < riderNodes.getLength(); i++) {
                Node node = riderNodes.item(i); //父标签节点
                //通过 Document 对象创建子标签
                Element element = document.createElement("enemy");
                //设置创建的子标签的文本内容 (#PCDATA)
                Text text = document.createTextNode("enemy" + i);
                //将子标签内容添加入子标签
                element.appendChild(text);
                //将创建的子标签添加入父标签
                node.appendChild(element);
            }

            //回写 .xml 文件
            //通过 TransformerFactory.newInstance; 获取实例化对象
            TransformerFactory tf = TransformerFactory.newInstance();
            //通过 对应工厂类对象.newTransformer(); 获取实例化对象
            Transformer trans = tf.newTransformer();
            //回写
            trans.transform(new DOMSource(document), new StreamResult("res" + File.separator + "riders.xml"));

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e){
            e.printStackTrace();
        }
    }

    /**
     * 使用 DOM（Document Object Model 文档对象模型）解析方式解析 XML 文档
     * 删除子标签
     */
    private static void parserDomDelete(){
        try {
            //1、创建 解析器工厂类对象
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //2、根据 解析器工厂类对象 创建 解析器类对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            //将 XML 文档解析成 Document 对象
            Document document = db.parse("res" + File.separator + "riders.xml");
            //根据标签名获取所有相同名的标签集合
            NodeList enemyNodes = document.getElementsByTagName("enemy");
            for (int i = 0; i < enemyNodes.getLength(); i++) {
                Node node = enemyNodes.item(i); //子标签节点
                //获取父节点
                Node parent = node.getParentNode(); //获取父标签节点
                parent.removeChild(node); //删除子标签
            }

            //回写 .xml 文件
            //通过 TransformerFactory.newInstance; 获取实例化对象
            TransformerFactory tf = TransformerFactory.newInstance();
            //通过 对应工厂类对象.newTransformer(); 获取实例化对象
            Transformer trans = tf.newTransformer();
            //回写
            trans.transform(new DOMSource(document), new StreamResult("res" + File.separator + "riders.xml"));

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e){
            e.printStackTrace();
        }
    }

    /**
     * 遍历 .xml 文件中所有标签，包括根标签
     */
    private static void listElements(){
        try {
            //1、创建 解析器工厂类对象
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //2、根据 解析器工厂类对象 创建 解析器类对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            //将 XML 文档解析成 Document 对象
            Document document = db.parse("res" + File.separator + "riders.xml");

            //递归遍历
            printElement(document);

        } catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
    }
    //递归遍历
    private static void printElement(Node node){
        if (node.getNodeType() == Node.ELEMENT_NODE){
            System.out.println("Node " + node.getNodeName() + " is a Element Node.");
        }
        //
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node1 = nodes.item(i);
            //System.out.print(node1.getNodeName() + "、");
            //递归遍历
            printElement(node1);
        }
    }

    /**
     * SAX 解析：采用事件驱动，从上到下一行一行边读边解析，解析到某一个对象，返回对象名称  // 只能查询，不能进行增、删、改操作
     * SAX （Simple API for XML）：javax.xml.parsers.SAXParser 解析器类
     *                            javax.xml.parsers.SAXParserFactory 解析器工厂类
     *     javax.xml.parsers.SAXParser 解析器类
     *         public abstract class SAXParser {}  //抽象类 通过 对应工厂类对象.newSAXParser()(); 获取实例化对象
     *         解析 .xml 文件：1、public void parse(String uri, DefaultHandler dh) throws SAXException, IOException {}
     *                                    // uri 为 .xml 文件路径，dh 为事件处理器
     *                        2、public void parse(File f, HandlerBase hb) throws SAXException, IOException {}
     *     javax.xml.parsers.SAXParserFactory 解析器工厂类
     *         public abstract class SAXParserFactory {} //抽象类，通过 SAXParserFactory.newInstance(); 获取实例化对象
     */
    /**
     * 事件处理器：
     * org.xml.sax.helpers.DefaultHandler:
     *     public class DefaultHandler implements EntityResolver, DTDHandler, ContentHandler, ErrorHandler {}
     *     // 没有构造方法，在使用的时候需要自定义事件处理器类继承 org.xml.sax.helpers.DefaultHandler 类，同时覆写三个方法：
     *     1、开始标签，自动查询：
     *        public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {}
     *        // qName 为查询到的标签名
     *     2、标签内容，自动查询
     *        public void characters(char[] ch, int start, int length) throws SAXException {}
     *     3、结束标签，自动查询
     *        public void endElement(String uri, String localName, String qName) throws SAXException {}
     *        // qName 为查询到的标签名
     */
     private static void parserSAX(){

         try {
             //抽象类，通过 SAXParserFactory.newInstance(); 获取解析器工厂类对象
             SAXParserFactory spf = SAXParserFactory.newInstance();
             //抽象类 通过 对应工厂类对象.newSAXParser()(); 获取解析器类对象
             SAXParser saxParser = spf.newSAXParser();

             //解析 .xml 文件 // uri 为 .xml 文件路径，dh 为事件处理器
             saxParser.parse("res" + File.separator + "riders.xml", new MyDefaultHandler());

         } catch (ParserConfigurationException | SAXException | IOException e) {
             e.printStackTrace();
         }
     }



}
