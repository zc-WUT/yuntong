package com.yuntong.springcloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.yuntong.springcloud.domain.Product;
import com.yuntong.springcloud.dao.ProductMapper;
import com.yuntong.springcloud.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
@Service
@Transactional
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
@Autowired
private ProductMapper productDao;

    @Override
    public List<Product> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return productDao.selectList(null);
    }

    @Override
    public void addProduct(Product product) throws Exception {
        productDao.insert(product);
    }

    @Override
    public void delProduct(int productId) throws Exception {
        productDao.deleteById(productId);
    }

    @Override
    public void update(Product product) throws Exception {
        productDao.updateById(product);
    }

    @Override
    public Product findById(int productId) throws Exception {
        return productDao.selectById(productId);
    }
}
