package com.yuntong.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuntong.springcloud.dao.*;
import com.yuntong.springcloud.domain.Permission;
import com.yuntong.springcloud.domain.Role;
import com.yuntong.springcloud.domain.RolePermission;
import com.yuntong.springcloud.domain.UsersRole;
import com.yuntong.springcloud.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private UsersRoleMapper usersRoleMapper;
    @Override
    public List<Role> findAllRole() throws Exception {
        return roleMapper.selectList(null);
    }

    @Override
    public void deleteRoleById(int roleId) throws Exception {
        roleMapper.deleteById(roleId);
        QueryWrapper<UsersRole> wrapper1 =new QueryWrapper<>();
        wrapper1.eq("roleid",roleId);
        usersRoleMapper.delete(wrapper1);
        QueryWrapper<RolePermission> wrapper2 =new QueryWrapper<>();
        wrapper2.eq("roleid",roleId);
        rolePermissionMapper.delete(wrapper2);
    }

    @Override
    public void addRole(Role role) throws Exception {
        role.setRolename("ROLE_"+role.getRolename());
        roleMapper.insert(role);
    }

    @Override
    public Role findPermissionToAdd(int roleId) throws Exception {
        Role role =roleMapper.selectById(roleId);
        List<Permission> permissionList = permissionMapper.findPermissionByRoleId(roleId);
        role.setPermissions(permissionList);
        return role;
    }

    @Override
    public void addPermissionToRole(int roleId, int[] permissionIds) throws Exception {
        for (int permissionId : permissionIds) {
            RolePermission rolePermission=new RolePermission();
            rolePermission.setRoleid(roleId).setPermissionid(permissionId);
            rolePermissionMapper.insert(rolePermission);
        }

    }

    @Override
    public Role findPermissionToDel(int roleId) throws Exception {
        Role role =roleMapper.selectById(roleId);
        List<Permission> permissionList = permissionMapper.findPermissionToDel(roleId);
        role.setPermissions(permissionList);
        return role;
    }

    @Override
    public void deletePermission(int roleId, int[] permissionIds) throws Exception {
        for (int permissionId : permissionIds) {
            QueryWrapper<RolePermission> wrapper =new QueryWrapper<>();
            wrapper.eq("permissionid",permissionId).eq("roleid",roleId);
            rolePermissionMapper.delete(wrapper);
        }
    }

}
