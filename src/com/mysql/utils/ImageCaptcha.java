package com.mysql.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 生成图片验证码
 */
public class ImageCaptcha {

    //图片宽高
    private int width = 70;
    private int height = 35;
    //随机，用于显示字符与字体的随机选择
    private Random random = new Random();
    //可选显示的字符，自己设置
    private String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNOPQRSTUVWXYZ";
    //随机选择的字符的字体
    private String[] fontNames = new String[]{"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
    //背景色
    private Color bgColor = new Color(255, 255, 255);
    //随机显示的文本
    private String text;  //从可选字符中随机挑选组成

    /**
     * 获取图片验证码 保存的缓存区
     * @return
     */
    public BufferedImage getImageCaptcha(){
        BufferedImage image = createImage();
        StringBuilder sb = new StringBuilder();
        //获取绘制环境
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        //往背景图中绘制四个字符
        int num = 4;
        for (int i = 0; i < num; i++) {
            String strChar = randomChar() + "";
            sb.append(strChar);
            float x = i * 1.0f * width / num;  //当前字符 x 轴
            float y = height - 5;
            graphics2D.setFont(randomFont());
            graphics2D.setColor(randomColor());
            graphics2D.drawString(strChar, x, y);
        }
        this.text = sb.toString();
        //绘制干扰线
        drawLine(image);
        return image;
    }

    /**
     * 获取绘制的文本，用来与用户输入的验证码对比
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * 保存图片至指定输出流
     * @param image
     * @param os
     */
    public void outImage(BufferedImage image, OutputStream os) throws IOException {
        ImageIO.write(image, "JPEG", os);
    }

    /**
     * 创建背景图片缓存区
     * @return
     */
    private BufferedImage createImage(){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        graphics2D.setColor(bgColor); //设置背景颜色
        graphics2D.fillRect(0, 0, width, height); //填充矩阵
        return image;
    }

    /**
     * 从可选字符中挑选随机字符
     * @return
     */
    private char randomChar(){
        int index = random.nextInt(codes.length());
        return codes.charAt(index);
    }

    /**
     *  从可选字体中挑选随机字体
     * @return
     */
    private Font randomFont(){
        int index = random.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int style = random.nextInt(4);//PLAIN 0 无样式; BOLD 1 ; ITALIC 2 ; BOLD|ITALIC 3
        int size = random.nextInt(5) + 24; //生成随机字号
        return new Font(fontName, style, size);
    }

    /**
     * 获取随机颜色，设置画笔添加文本的颜色
     * @return
     */
    private Color randomColor(){
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        return new Color(red, green, blue);
    }

    /**
     * 绘制干扰线
     * @param image
     */
    private void drawLine(BufferedImage image){
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        //绘制 3 条背景虚线
        for (int i = 0; i < 3; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            graphics2D.setStroke(new BasicStroke(1.5f));
            graphics2D.setColor(Color.blue);
            graphics2D.drawLine(x1, y1, x2, y2);
        }
    }

}
