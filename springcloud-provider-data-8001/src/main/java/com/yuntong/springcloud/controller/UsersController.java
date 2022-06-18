package com.yuntong.springcloud.controller;


import com.yuntong.springcloud.domain.Users;
import com.yuntong.springcloud.service.UsersService;
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
 * 前端控制器
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
@Controller
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * 自定义登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginSuccess")
    public String loginSuccess(){
        return "main";
    }

    @RequestMapping("/loginFailure")
    public String loginFailure(){
        return "failure/failure";
    }


    /**
     * 查询所有用户
     *
     * @return
     * @throws Exception
     */
    @ApiOperation("查询所有用户")
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Users> userList = usersService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 添加用户操作
     *
     * @param user
     * @return
     * @throws Exception
     */
    @ApiOperation("添加用户操作")
    @RequestMapping("/addUser")
    public String addUser(Users user) throws Exception {
        usersService.addUser(user);
        return "redirect:findAll";
    }

    /**
     * 在users表中删除user，并且在users_role表中删除该userId所对应的数据
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @ApiOperation("在users表中删除user，并且在users_role表中删除该userId所对应的数据")
    @RequestMapping("/delUserById")
    public String delUserById(@RequestParam(name = "userId", required = true) int userId) throws Exception {
        usersService.delUserById(userId);
        return "redirect:findAll";
    }

    @ApiOperation("查询出id对应的user，用于后续更新user")
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id", required = true) int userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据用户id查询用户
        Users user= usersService.findById(userId);
        mv.addObject("user", user);
        mv.setViewName("user-update");
        return mv;
    }

    @ApiOperation("更新用户")
    @RequestMapping("/update")
    public String update(Users user) throws Exception {
       usersService.update(user);
        return "redirect:findAll";
    }

    /**
     * 通过user的id查询对应的user，以及它的详细信息，具备什么角色，角色又具备了什么权限
     * @param userId
     * @return
     * @throws Exception
     */
    @ApiOperation("查询出id对应的user，以及它的详细信息，具备什么角色，角色又具备了什么权限")
    @RequestMapping("/detailOfUser")
    public ModelAndView detailOfUser(@RequestParam(name = "userId", required = true) int userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Users Users = usersService.detailOfUser(userId);
        mv.addObject("user", Users);
        mv.setViewName("user-show");
        return mv;
    }
    
    /**
     * 查询出user没有具备的role，便于后面进行添加role给user
     * @param userId
     * @return
     * @throws Exception
     */
    @ApiOperation("查询出id对应的user，以及它没有具备的角色")
    @RequestMapping("/findURoleToAdd")
    public ModelAndView findRoleToAdd(@RequestParam(name = "userId", required = true) int userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据用户id查询用户
        Users user= usersService.findRoleToAdd(userId);
        mv.addObject("user", user);
        mv.setViewName("user-role-add");
        return mv;
    }
    
    /**
     *添加role给user
     * @param userId
     * @param roleIds
     * @return
     * @throws Exception
     */
    @ApiOperation("将user没有具备的角色添加给user")
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) int userId, @RequestParam(name = "roleIds", required = true) int[] roleIds) throws Exception {
        usersService.addRoleToUser(userId, roleIds);
        return "redirect:findAll";
    }
    
    /**
     * 查询user具备的role，便于后面减少user的role
     * @param userId
     * @return
     * @throws Exception
     */
    @ApiOperation("查询出id对应的user，以及它已经具备的角色")
    @RequestMapping("/findURoleToDel")
    public ModelAndView findRoleToDel(@RequestParam(name = "id", required = true) int userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Users Users = usersService.findRoleToDel(userId);
        mv.addObject("user", Users);
        mv.setViewName("user-role-del");
        return mv;
    }

    /**
     * 减少user的role
     * @param userId
     * @param roleIds
     * @return
     * @throws Exception
     */
    @ApiOperation("删除user的角色")
    @RequestMapping("/delRoleFromUser")
    public String delRoleFromUser(@RequestParam(name = "userId", required = true) int userId, @RequestParam(name = "roleIds", required = true) int[] roleIds) throws Exception {
        usersService.delRoleFromUser(userId, roleIds);
        return "redirect:findAll";
    }

}

