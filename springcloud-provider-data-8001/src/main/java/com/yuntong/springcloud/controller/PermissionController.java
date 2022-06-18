package com.yuntong.springcloud.controller;


import com.yuntong.springcloud.domain.Permission;
import com.yuntong.springcloud.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/permission")
@Api(tags = "权限管理1")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @ApiOperation("创建新权限")
    @RequestMapping("/addPermission")
    public String addPermission(Permission permission) throws Exception {
        permissionService.addPermission(permission);
        return "redirect:findAll";
    }

    @ApiOperation("查询所有权限")
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList", permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    @ApiOperation("删除权限")
    @RequestMapping("/deletePermission")
    public String deletePermission(@RequestParam(name = "permissionId", required = true) int permissionId) throws Exception {
        permissionService.deleteById(permissionId);
        return "redirect:findAll";

    }
}

