package com.yuntong.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuntong.springcloud.dao.RolePermissionMapper;
import com.yuntong.springcloud.domain.Permission;
import com.yuntong.springcloud.dao.PermissionMapper;
import com.yuntong.springcloud.domain.RolePermission;
import com.yuntong.springcloud.service.PermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void addPermission(Permission permission) throws Exception {
        permissionMapper.insert(permission);
    }

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionMapper.selectList(null);
    }

    @Override
    public void deleteById(int permissionId) throws Exception {
        permissionMapper.deleteById(permissionId);
        QueryWrapper<RolePermission> wrapper=new QueryWrapper<>();
        wrapper.eq("permissionid",permissionId);
        rolePermissionMapper.delete(wrapper);
    }
}
