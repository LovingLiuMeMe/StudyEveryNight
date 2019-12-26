package cn.lovingliu.springsecurity.security.browser.authentication.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author：LovingLiu
 * @Description:
 * 图片验证码的实体类
 * @Date：Created in 2019-12-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCode {
    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image,String code,Integer expireIn){
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
