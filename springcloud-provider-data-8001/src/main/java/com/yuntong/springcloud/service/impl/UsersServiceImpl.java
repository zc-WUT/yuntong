package com.yuntong.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuntong.springcloud.dao.*;
import com.yuntong.springcloud.domain.Permission;
import com.yuntong.springcloud.domain.Role;
import com.yuntong.springcloud.domain.Users;
import com.yuntong.springcloud.domain.UsersRole;
import com.yuntong.springcloud.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
@Service
@Transactional
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UsersRoleMapper usersRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public List<Users> findAll() throws Exception {
        return usersMapper.selectList(null);
    }

    @Override
    public void addUser(Users user) throws Exception {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersMapper.insert(user);
    }

    @Override
    public void delUserById(int userId) throws Exception {
        usersMapper.deleteById(userId);
        QueryWrapper<UsersRole> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userId);
        usersRoleMapper.delete(wrapper);
    }

    @Override
    public Users detailOfUser(int userId) throws Exception {
        //先查询出User，再根据user的id查询出user已经具备的role；
        QueryWrapper<Users> wrapper =new QueryWrapper<>();
        wrapper.select("username","email","status","phonenum").eq("id",userId);
        Users users=usersMapper.selectOne(wrapper);
        List<Role> roles =roleMapper.selectRoleIdByUserId(userId);
        for (Role role : roles) {
            List<Permission> permissionList = permissionMapper.findPermissionToDel(role.getId());
            role.setPermissions(permissionList);
        }
        users.setRoles(roles);
        return users;
    }

    @Override
    public Users findRoleToAdd(int userId) throws Exception {
        Users users=new Users();
        users.setId(userId).setRoles(roleMapper.findRoleItoAdd(userId));
        return users;
    }

    @Override
    public void addRoleToUser(int userId, int[] roleIds) throws Exception {
        for (int roleId : roleIds) {
            UsersRole usersRole=new UsersRole();
            usersRole.setUserid(userId).setRoleid(roleId);
            usersRoleMapper.insert(usersRole);
        }
    }

    @Override
    public Users findRoleToDel(int userId) throws Exception {
        Users users=new Users();
        List<Role> roles = roleMapper.selectRoleIdByUserId(userId);
        users.setId(userId).setRoles(roles);
        return users;
    }

    @Override
    public void delRoleFromUser(int userId, int[] roleIds) throws Exception {
        for (int roleId : roleIds) {
            QueryWrapper<UsersRole> wrapper =new QueryWrapper<>();
            wrapper.eq("roleid",roleId).eq("userid",userId);
            usersRoleMapper.delete(wrapper);
        }
    }

    @Override
    public Users findById(int userId) throws Exception {
        QueryWrapper<Users> wrapper =new QueryWrapper<>();
        wrapper.select("id","username","email","status","phonenum").eq("id",userId);
        return usersMapper.selectOne(wrapper);
    }

    @Override
    public void update(Users user) throws Exception {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersMapper.updateById(user);
    }

    /**
     *
     * @param
     * @return
     * @throws UsernameNotFoundException
     */
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Users> wrapper =new QueryWrapper<>();
        wrapper.select("id","username","password","status").eq("username",username);
        Users users=usersMapper.selectOne(wrapper);
        List<Role> roles =roleMapper.selectRoleIdByUserId(users.getId());
        for (Role role : roles) {
            List<Permission> permissionList = permissionMapper.findPermissionToDel(role.getId());
            role.setPermissions(permissionList);
        }
        users.setRoles(roles);
        return users;
    }
}
