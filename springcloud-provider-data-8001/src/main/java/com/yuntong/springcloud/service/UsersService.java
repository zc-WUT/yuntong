package com.yuntong.springcloud.service;

import com.yuntong.springcloud.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
public interface UsersService extends IService<Users>, UserDetailsService {

    List<Users> findAll() throws Exception;

    void addUser(Users user) throws Exception;

    void delUserById(int userId) throws Exception;

    Users detailOfUser(int userId) throws Exception;

    Users findRoleToAdd(int userId)throws Exception;

    void addRoleToUser(int userId, int[] roleIds)throws Exception;

    Users findRoleToDel(int userId)throws Exception;

    void delRoleFromUser(int userId, int[] roleIds)throws Exception;

    Users findById(int userId) throws Exception;

    void update(Users user) throws Exception;
}
