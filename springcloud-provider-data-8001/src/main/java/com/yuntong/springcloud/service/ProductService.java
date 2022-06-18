package com.yuntong.springcloud.service;

import com.yuntong.springcloud.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
public interface ProductService extends IService<Product> {
    List<Product> findAll(int page, int size) throws Exception;

    void addProduct(Product product) throws Exception ;

    void delProduct(int productId) throws Exception;

    void update(Product product) throws Exception;

    Product findById(int productId) throws Exception;
}
