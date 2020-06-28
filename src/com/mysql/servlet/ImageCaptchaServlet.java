package com.mysql.servlet;

import com.mysql.utils.ImageCaptcha;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 获取图片验证码 Servlet
 */
public class ImageCaptchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);

        //获取图片验证码
        ImageCaptcha captcha = new ImageCaptcha();
        BufferedImage image = captcha.getImageCaptcha();  //图片验证码 缓冲区
        //将验证码文本保存在 session 域中
        req.getSession().setAttribute("session_captcha", captcha.getText());
        //将图片缓存区保存至指定输出流
        captcha.outImage(image, resp.getOutputStream());

    }
}
