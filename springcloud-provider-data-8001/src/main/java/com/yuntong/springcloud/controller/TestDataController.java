package com.yuntong.springcloud.controller;

import com.yuntong.springcloud.domain.TestData;
import com.yuntong.springcloud.service.TestDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/testData")
@Api(tags="操作实时的钢丝绳检测数据的接口")
public class TestDataController {
    @Autowired
    private TestDataService testDataService;

    /**
     * 工况预警的第三层
     * 通过前端传入的equipNum，在est_data表中查询equipNum对应的全部数据
     * @param equipNum
     * @return
     * @throws Exception
     */
    @ApiOperation("传入的equipNum，在est_data表中查询equipNum对应的全部数据")
    @RequestMapping("/findAllEquipData")
    public ModelAndView findAll(@RequestParam(name = "equipNum",required = true) String equipNum) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<TestData> eqn =testDataService.findAll(equipNum);
        mv.addObject("equipNum", equipNum);
        mv.addObject("EquipData", eqn);
        mv.setViewName("test-all-data-equip");
        return mv;
    }

    /**
     * 工况预警的第三层
     * 通过Ajax请求传入equipNum，在test_data表中查询equipNum对应的全部数据,转换成json格式传递给页面，可视化
     * @param equipNum
     * @return
     * @throws Exception
     */
    @ApiOperation("返回json类型的equipNum，在test_data表中对应的全部数据")
    @RequestMapping("/findAllEquipDataShow")
    @ResponseBody
    public List<TestData> findAllEquipDataShow(@RequestParam(name = "equipNum", required = true) String equipNum) throws Exception {
        List<TestData> eqn =testDataService.findAll(equipNum);
/*        Gson gson = new Gson();
        String json = gson.toJson(eqn);*/
        return eqn;
    }

    /**
     * 工况预警的第二层
     * 通过前端传入的productNum，查询表test_data中每一个机构（如QC010101）对应的最大损伤数据
     * @param productNum
     * @return
     * @throws Exception
     */
    @ApiOperation("传入productNum，查询表test_data中每一个机构（如QC010101）对应的最大损伤数据")
    @RequestMapping("/findLargeOfEquip")
    public ModelAndView findLargeOfEquip(@RequestParam(name = "productNum",required = true) String productNum) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<TestData> elqn =testDataService.findLargeOfEquip(productNum);
        mv.addObject("productNum",productNum);
        mv.addObject("EquipLargeData", elqn);
        mv.setViewName("test-max-data-equip");
        return mv;
    }

    /**
     * 工况预警的第二层，productnum对应的每一个机构的最大损伤，进行可可视化
     * @param productNum
     * @return
     * @throws Exception
     */
    @ApiOperation("返回json类型的productnum对应的每一个机构的最大损伤数据")
    @RequestMapping("/findLargeOfEquipShow")
    @ResponseBody
    public List<TestData> findLargeOfEquipShow(@RequestParam(name = "productNum",required = true) String productNum) throws Exception {
        List<TestData> elqn =testDataService.findLargeOfEquip(productNum);
/*        Gson gson = new Gson();
        String json = gson.toJson(elqn);*/
        return elqn;
    }


    /**
     * 工况预警的第一层
     * 直接查询test_data表中每一个productNum对应的最大损伤数据
     * @return
     * @throws Exception
     */
    @ApiOperation("直接查询test_data表中每一个productNum对应的最大损伤数据")
    @RequestMapping("/findMaxOfPro")
    public ModelAndView findMaxOfPro() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<TestData> maxOfPro =testDataService.findMaxOfPro();
        mv.addObject("maxOfPro", maxOfPro);
        mv.setViewName("test-max-data-product");
        return mv;
    }

    /**
     * main主页的ajax请求传入每一个设备的编号，根据每一个编号分别返回对应的检测数据，背景颜色字段
     * @param productNum
     * @return
     * @throws Exception
     */
    @ApiOperation("返回json类型的productnum对应的最大损伤所对应的背景颜色")
    @RequestMapping("/findMaxOfProShow")
    @ResponseBody
    public TestData findMaxOfProShow(@RequestParam(name = "productNum",required = true) String productNum) throws Exception {
        TestData elqn =testDataService.findMaxOfPro(productNum);
/*        Gson gson = new Gson();
        String json = gson.toJson(elqn);*/
        return elqn;
    }

}


