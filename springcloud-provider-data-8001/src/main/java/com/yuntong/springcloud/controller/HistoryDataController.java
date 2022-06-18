package com.yuntong.springcloud.controller;

import com.github.pagehelper.PageInfo;
import com.yuntong.springcloud.domain.HistoryData;
import com.yuntong.springcloud.service.HistoryDataService;
import com.yuntong.springcloud.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/historyData")
@Api(tags="操作历史检测数据的接口")
public class HistoryDataController {

    @Autowired
    private HistoryDataService historyDataService;

    /**
     * 查询所有的历史数据
     *
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @ApiOperation("查询所有的历史数据")
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "10") int size) throws Exception {
        ModelAndView mv = new ModelAndView();
//        PageHelper.startPage(page,size);
        List<HistoryData> historyData = historyDataService.findAll(page, size);
        PageInfo<HistoryData> pageInfo = new PageInfo(historyData);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("history-all-data");
        return mv;
    }

    /**
     * 查询历史数据时选择时间段
     * 1.前端通过form表单提交一个selectTime，被后台接收到，格式为String
     * 2.分析该字符串的格式，截取成两段，一段表示前面的起始时间字符串，另一端为结束时间对应的字符串
     * @param page
     * @param size
     * @param selectTime
     * @return
     * @throws Exception
     */
    @ApiOperation("根据时间段查询历史数据")
    @RequestMapping("/selectTime")
    public ModelAndView selectTime(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "10") int size, @RequestParam(name = "selectTime", required = true)String selectTime) throws Exception {
        ModelAndView mv = new ModelAndView();
        //日期格式转换
        SimpleDateFormat in = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");

        String startTime=out.format(in.parse(selectTime.substring(0, 10)))+"\t"+"00:00:00";
        String endTime=out.format(in.parse(selectTime.substring(13, 23)))+"\t"+"23:59:59";


        List<HistoryData> historyData = historyDataService.findAllByTime(page, size, startTime, endTime);
        PageInfo pageInfo = new PageInfo(historyData);
        mv.addObject("selectTime", selectTime);
        mv.addObject("pageInfo2", pageInfo);
        mv.setViewName("history-all-data2");
        return mv;
    }

    /**
     * 查询图片，通过表的id对应的数据里的图片名称，在图片文件家里找到对应的图片
     *
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation("查询数据对应的图片")
    @RequestMapping("/findImage")
    public ModelAndView findImage(@RequestParam(name = "id", required = true) int id) throws Exception {
        ModelAndView mv = new ModelAndView();
        HistoryData historyData = historyDataService.findImage(id);
        String img ="/image/"+historyData.getImage();
        System.out.println(img);
        mv.addObject("image", img);
        mv.setViewName("history-image");
        return mv;
    }

    /**
     * 导出全部的历史数据
     *
     * @param response
     * @param request
     * @throws Exception
     */
    @ApiOperation("导出全部的历史数据")
    @RequestMapping("/exportExcel")
    @ResponseBody
    public void excel_down(HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<HistoryData> list = historyDataService.findAll();
        Workbook wb = fillExcelDataWithTemplate(list, "springcloud-provider-data-8001\\src\\main\\resources\\static\\excel\\historyData.xls");
/*        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy年MM月dd日");
        String str = sdf.format(new Date());*/
        ResponseUtil.export(response, wb, "历史数据" + ".xls");
    }

    /**
     * 导出全部的历史数据
     *
     * @param response
     * @param request
     * @throws Exception
     */
    @ApiOperation("导出时间段的历史数据")
    @RequestMapping("/exportExcelTime")
    @ResponseBody
    public void excel_down_time(HttpServletResponse response, HttpServletRequest request,@RequestParam(name = "selectTime",required = true) String selectTime) throws Exception {
        //日期格式转换
        SimpleDateFormat in = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy年MM月dd日");

        String name1 =sdf.format(in.parse(selectTime.substring(0, 10)));
        String name2 =sdf.format(in.parse(selectTime.substring(13, 23)));

        String startTime=out.format(in.parse(selectTime.substring(0, 10)))+"\t"+"00:00:00";
        String endTime=out.format(in.parse(selectTime.substring(13, 23)))+"\t"+"23:59:59";

        List<HistoryData> list = historyDataService.findAllByTime(startTime, endTime);
        Workbook wb = fillExcelDataWithTemplate(list, "springcloud-provider-data-8001\\src\\main\\resources\\static\\excel\\historyData.xls");
        ResponseUtil.export(response, wb, name1+"至"+name2 + "区间的数据"+".xls");
    }






    public static Workbook fillExcelDataWithTemplate(List<HistoryData> list, String templateFileUrl) throws Exception {

        Workbook wb = null;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(templateFileUrl));
            wb = new HSSFWorkbook(fs);
            // 取得 模板的 第一个sheet 页
            Sheet sheet = wb.getSheetAt(0);
            // 拿到sheet页有 多少列
            int cellNums = sheet.getRow(0).getLastCellNum();
            // 从第2行 开搞    下标1  就是第2行
            int rowIndex = 1;
            Row row;
            for (HistoryData historyData : list) {
                row = sheet.createRow(rowIndex);
                rowIndex++;
                row.createCell(0).setCellValue(historyData.getId());
                row.createCell(1).setCellValue(historyData.getProductnum());
                row.createCell(2).setCellValue(historyData.getEquipnum());
                row.createCell(3).setCellValue(historyData.getEquipname());
                row.createCell(4).setCellValue(historyData.getTestdate());
                row.createCell(5).setCellValue(historyData.getContainernum());
                row.createCell(6).setCellValue(historyData.getWorktime());
                row.createCell(7).setCellValue(historyData.getPosition());
                row.createCell(8).setCellValue(historyData.getTestdata());
                row.createCell(9).setCellValue(historyData.getTypeStr());
                row.createCell(10).setCellValue(historyData.getTestResultStr());
                row.createCell(11).setCellValue(historyData.getLifetime());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 格式化所有数据，千万别乱点！
     *
     * @return
     * @throws Exception
     */
    @ApiOperation("格式化所有数据，千万别乱点！")
    @RequestMapping("/truncate")
    public String truncate() throws Exception {
        historyDataService.truncate();
        return "redirect:findAll";
    }

}

