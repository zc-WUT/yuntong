package com.yuntong.springcloud.controller;


import com.yuntong.springcloud.domain.Equipment;
import com.yuntong.springcloud.service.EquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/equipment")
@Api(tags="管理检测机构的接口")
public class EquipmentController {

    @Autowired
    private EquipmentService equipService;

    /**
     * 点击设备管理 查询所有的机构
     * @param page 起始页
     * @param size 每页的记录条数
     * @return
     * @throws Exception
     */
    @ApiOperation("设备管理 查询所有的机构")
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "10") int size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Equipment> eq = equipService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(eq);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("equip-page-list");
        return mv;
    }

    /**
     * 在机组管理的页面，点击岸桥后面的详情，获取岸桥编号productNum，通过岸桥编号查询其对应的机构
     * @param productNum
     * @return
     * @throws Exception
     */
    @ApiOperation("通过岸桥编号查询其对应的机构")
    @RequestMapping("/findByProductNum")
    public ModelAndView findByProductNum(@RequestParam(name = "productNum", required = true) String productNum) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Equipment> equip = equipService.findByProductNum(productNum);
        mv.addObject("equipList", equip);
        mv.setViewName("equip-show");
        return mv;
    }

    /**
     * 向数据库中添加机构，不仅在equipment里面添加机构数据
     * 还会分别在equip_data,equip_time_data里面，添加以equipnum命名的列
     * @param equip
     * @return
     * @throws Exception
     */
    @ApiOperation("向数据库中添加机构，不仅在equipment里面添加机构数据，还会分别在equip_data,equip_time_data里面，添加以equipnum命名的列")
    @RequestMapping("/addEquipment")
    public String addEquip(Equipment equip) throws Exception {
        equipService.addEquip(equip);
        return "redirect:findAll";
    }

    /**
     * 在设备管理中删除机构，不仅会在equipment里面删除机构数据
     * 还会分别在equip_data,equip_time_data里面，删除以equipnum命名的列
     * @param equipId
     * @return
     * @throws Exception
     */
    @ApiOperation("在设备管理中删除机构，不仅会在equipment里面删除机构数据，还会分别在equip_data,equip_time_data里面，删除以equipnum命名的列")
    @RequestMapping("/delEquip")
    public String delEquip(@RequestParam(name = "id", required = true) int equipId) throws Exception {
        equipService.delEquip(equipId);
        return "redirect:findAll";
    }

    /**
     * 更新机构
     * @param equip
     * @return
     * @throws Exception
     */
    @ApiOperation("更新机构")
    @RequestMapping("/update")
    public String delEquip(Equipment equip) throws Exception {
        equipService.updateEquip(equip);
        return "redirect:findAll";
    }

}

