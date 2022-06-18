package com.yuntong.springcloud.dao;

import com.yuntong.springcloud.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {

}
