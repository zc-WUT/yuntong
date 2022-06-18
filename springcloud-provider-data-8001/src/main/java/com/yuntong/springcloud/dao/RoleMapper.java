package com.yuntong.springcloud.dao;

import com.yuntong.springcloud.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select id,rolename,roledesc from role where id in(select roleid from users_role where userid=#{userId}) order by id asc")
    List<Role> selectRoleIdByUserId(int userId)throws Exception;

    @Select("select id,rolename,roledesc from role where id not in(select roleid from users_role where userid=#{userId}) order by id asc")
    List<Role> findRoleItoAdd(int userId);
}
