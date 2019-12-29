package cn.lovingliu.springsecurity.security.browser.controller;

import cn.lovingliu.springsecurity.security.browser.authentication.image.ImageCode;
import cn.lovingliu.springsecurity.security.browser.authentication.sms.SmsCode;
import cn.lovingliu.springsecurity.security.browser.service.SmsCodeSenderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Author：LovingLiu
 * @Description: 图片验证码接口
 * @Date：Created in 2019-12-26
 */
@RestController
@Slf4j
public class ValidateCodeController {
    private static final String SESSION_IMAGE_CODE_KEY = "IMAGE_CODE_KEY";
    private static final String SESSION_SMS_CODE_KEY = "SMS_CODE_KEY";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private SmsCodeSenderService smsCodeSenderService;

    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request,"mobile");
        // 1.生成短信验证码(长度为4)
        SmsCode smsCode = createSmsCode();
        smsCode.setTelephone(mobile);
        // 2.保存到Session
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_SMS_CODE_KEY,smsCode);
        // 3.发送到用户手
        smsCodeSenderService.send(mobile,smsCode.getCode());
    }

    @GetMapping("/code/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = createImageCode();
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_IMAGE_CODE_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }
    private ImageCode createImageCode(){
        int width = 67;
        int height = 23;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageCode(image, sRand, 60);
    }
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private SmsCode createSmsCode(){
        String code = RandomStringUtils.randomNumeric(4);
        SmsCode smsCode = new SmsCode(code,60);
        return smsCode;
    }
}
