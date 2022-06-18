package com.yuntong.springcloud.controller;


import com.google.gson.Gson;
import com.yuntong.springcloud.domain.EquipData;
import com.yuntong.springcloud.domain.EquipTimeData;
import com.yuntong.springcloud.service.EquipDataService;
import com.yuntong.springcloud.service.EquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *                                  这里把EquipData和EquipTimeData放在一起进行事务操作
 * @author 张弛
 * @since 2020-08-24
 */
@Controller
@RequestMapping("/equipData")
@Api(tags="操作钢丝绳分别关于集装箱数量，工作时长的检测数据的接口")
public class EquipDataController {


    @Autowired
    private EquipDataService equipDataService;
    @Autowired
    private EquipmentService equipmentService;

    @ApiOperation("通过前端传入的岸桥编号去跳转对应的可视化界面")
    @RequestMapping("/dataShowEcharts")
    public ModelAndView dataShowEcharts(@RequestParam(name = "productNum", required = true,defaultValue = "QC01") String productNum){
        ModelAndView mv =new ModelAndView();
        mv.addObject("productNum",productNum);
        String view="data-show-echarts-"+productNum;
        mv.setViewName(view);
        return mv;
    }

    /**
     * 将损伤-箱数的检测结果提交给Echarts进行展示
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation("返回json字符串类型的损伤-箱数的检测结果")
    @RequestMapping(value = "/containerDataShow")
    @ResponseBody
    public String findDataOfContainer(@RequestParam(name = "productNum", required = true,defaultValue = "QC01") String productNum) throws Exception {
        List<EquipData> containerNumList = equipDataService.findDataOfContainer(productNum);
        Gson gson = new Gson();
        String json = gson.toJson(containerNumList);
        System.out.println(json);
        return json;

    }

    /**
     * 将损伤-工作时间的检测结果提交给Echarts进行展示
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation("返回json字符串类型的损伤-工作时间的检测结果")
    @RequestMapping(value = "/workTimeDataShow")
    @ResponseBody
    public String findDataOfWorkTime(@RequestParam(name = "productNum", required = true,defaultValue = "QC01") String productNum) throws Exception {
        List<EquipTimeData> timeDataList = equipDataService.findDataOfWorkTime(productNum);
        Gson gson = new Gson();
        String json = gson.toJson(timeDataList);
        System.out.println(json);
        return json;

    }

    /**
     * 将损伤-工作时间，损伤-箱数分别以表格的形式展示
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation("将损伤-工作时间，损伤-箱数分别以表格的形式展示")
    @RequestMapping(value = "/ReportOfData")
    public ModelAndView ReportOfData(@RequestParam(name = "productNum", required = true,defaultValue = "QC01") String productNum) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<EquipData> containerNumList = equipDataService.findDataOfContainer(productNum);
        List<EquipTimeData> timeDataList = equipDataService.findDataOfWorkTime(productNum);
        mv.addObject("productNum", productNum);
        mv.addObject("containerNumList", containerNumList);
        mv.addObject("timeDataList", timeDataList);
        String view ="report-data-select-"+productNum;
        mv.setViewName(view);
        return mv;
    }

    /**
     * 设备管理里的查看数据功能，点击进去就可以查看euipnun在equip_data,equip_test_data里对应的列的数据
     * @param equipNum
     * @return
     * @throws Exception
     */
    @ApiOperation("设备管理里的查看数据功能")
    @RequestMapping(value = "/findEquipDataByEquipNum")
    public ModelAndView findEquipDataByequipNum(@RequestParam(name = "equipNum", required = true) String equipNum) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<EquipData> containerNumList = equipDataService.findContainerDataByEquipNum(equipNum);
        List<EquipTimeData> timeDataList = equipDataService.findTimeDataByEquipNum(equipNum);
        mv.addObject("containerNumList", containerNumList);
        mv.addObject("timeDataList", timeDataList);
        mv.setViewName("equip-detail-data");
        return mv;
    }
}

