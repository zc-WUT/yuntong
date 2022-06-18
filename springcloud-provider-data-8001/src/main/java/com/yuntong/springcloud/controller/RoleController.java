package com.yuntong.springcloud.controller;


import com.yuntong.springcloud.domain.Role;
import com.yuntong.springcloud.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
@Controller
@RequestMapping("/role")
@Api(tags = "角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("查询所有角色")
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAllRole();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @ApiOperation("删除已有角色")
    @RequestMapping("/deleteRole")
    public String deleteRole(@RequestParam(name = "roleId", required = true) int roleId) throws Exception {
        roleService.deleteRoleById(roleId);
        return "redirect:findAll";
    }

    @ApiOperation("添加角色")
    @RequestMapping("/addRole")
    public String addRole(Role role) throws Exception {
        roleService.addRole(role);
        return "redirect:findAll";
    }

    @ApiOperation("id查询role，并且查询该role没有具备的权限，便于后面添加权限给role")
    @RequestMapping("findPermissionToAdd")
    public ModelAndView findPermissionToAdd(@RequestParam(name = "roleId", required = true) int roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据roleId查询role
        Role role = roleService.findPermissionToAdd(roleId);
        mv.addObject("role", role);
        mv.setViewName("role-permission-to-add");
        return mv;
    }

    @ApiOperation("给role添加权限")
    @RequestMapping("addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) int roleId, @RequestParam(name = "permissionIds", required = true) int[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll";
    }

    @ApiOperation("查询role已经具备的permission，便于后面删除role的permission")
    @RequestMapping("/findPermissionToDel")
    public ModelAndView findPermissionToDel(@RequestParam(name = "roleId", required = true) int roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findPermissionToDel(roleId);
        mv.addObject("role", role);
        mv.setViewName("role-permission-to-del");
        return mv;
    }

    @ApiOperation("删除role的permission")
    @RequestMapping("/deletePermission")
    public String deletePermission(@RequestParam(name = "roleId", required = true) int roleId, @RequestParam(name = "permissionIds", required = true) int[] permissionIds) throws Exception {
        roleService.deletePermission(roleId, permissionIds);
        return "redirect:findAll";
    }

    @ApiOperation("查询role的细节，具备什么permission以及说明")
    @RequestMapping("/findDetailsOfRole")
    public ModelAndView findById(@RequestParam(name = "id", required = true) int roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findPermissionToDel(roleId);
        mv.addObject("role", role);
        mv.setViewName("role-details-show");
        return mv;
    }
}

