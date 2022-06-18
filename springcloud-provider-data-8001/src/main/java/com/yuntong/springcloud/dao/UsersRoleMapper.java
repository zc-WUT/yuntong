package com.yuntong.springcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuntong.springcloud.domain.Role;
import com.yuntong.springcloud.domain.UsersRole;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张弛
 * @since 2020-08-28
 */
@Repository
public interface UsersRoleMapper extends BaseMapper<UsersRole> {


}
