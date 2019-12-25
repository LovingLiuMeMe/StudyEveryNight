package cn.lovingliu.springsecurity.controller;

import cn.lovingliu.springsecurity.common.ResponseMsg;
import cn.lovingliu.springsecurity.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author：LovingLiu
 * @Description: 商品的Controller
 * @Date：Created in 2019-12-25
 */
@RequestMapping("/product")
@RestController
@Slf4j
public class ProductController {
    @GetMapping
    public ResponseMsg list(){
        // 模拟 查询商品列表
        List<Product> productList = new ArrayList<>();
        for(int i=0; i<5;i++){
            Product product = new Product();
            product.setId(i);
            product.setName("商品"+i+"号");
            product.setSellPrice(i*100.0);
            productList.add(product);
        }

        ResponseMsg<List<Product>> responseMsg = new ResponseMsg();
        responseMsg.setCode(0);
        responseMsg.setData(productList);
        responseMsg.setMsg("获取商品列表成功");
        return responseMsg;
    }

    @GetMapping("/del/{id}")
    public ResponseMsg del(@PathVariable("id") Integer id){

        Product product = new Product();
        product.setId(id);
        product.setName("商品"+id+"号");
        product.setSellPrice(id*100.0);

        ResponseMsg<Product> responseMsg = new ResponseMsg();
        responseMsg.setCode(0);
        responseMsg.setData(product);
        responseMsg.setMsg("删除商品成功");
        log.warn("【商品删除成功 => id:{}】",id);
        return responseMsg;
    }

    @PostMapping("/save")
    public ResponseMsg save(@RequestBody Product product){
        Random random = new Random();
        int id = random.nextInt(100);
        product.setId(id);

        ResponseMsg<Product> responseMsg = new ResponseMsg();
        responseMsg.setCode(0);
        responseMsg.setData(product);
        responseMsg.setMsg("删除保存成功");
        log.warn("【商品保存成功 => product:{}】", product);
        return responseMsg;
    }
}
