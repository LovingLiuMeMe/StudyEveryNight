package cn.lovingliu.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：LovingLiu
 * @Description: 商品实体类
 * @Date：Created in 2019-12-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private Double sellPrice;
}
