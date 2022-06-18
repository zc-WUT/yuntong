package com.yuntong.springcloud.dao;

import com.yuntong.springcloud.domain.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select id,permissionname,url from permission where id not in(select permissionId from role_permission where roleId=#{roleId}) order by id asc")
    List<Permission> findPermissionByRoleId(int roleId) throws Exception;

    @Select("select id,permissionname,url from permission where id in(select permissionId from role_permission where roleId=#{roleId}) order by id asc")
    List<Permission> findPermissionToDel(int roleId)throws Exception;
}
