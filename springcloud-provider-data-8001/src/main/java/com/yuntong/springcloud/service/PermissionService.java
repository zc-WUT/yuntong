package com.yuntong.springcloud.service;

import com.yuntong.springcloud.domain.Permission;
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
public interface PermissionService extends IService<Permission> {

    void addPermission(Permission permission) throws Exception;

    List<Permission> findAll()throws Exception;

    void deleteById(int permissionId)throws Exception;
}
