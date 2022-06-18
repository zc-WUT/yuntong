package com.yuntong.springcloud.service;

import com.yuntong.springcloud.domain.Role;
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
public interface RoleService extends IService<Role> {

    List<Role> findAllRole() throws Exception;

    void deleteRoleById(int roleId) throws Exception;

    void addRole(Role role)throws Exception;

    Role findPermissionToAdd(int roleId)  throws Exception;

    void addPermissionToRole(int roleId, int[] permissionIds)throws Exception;

    Role findPermissionToDel(int roleId) throws Exception;

    void deletePermission(int roleId, int[] permissionIds)throws Exception;

}
