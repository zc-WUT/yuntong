package com.yuntong.springcloud.controller;


import com.github.pagehelper.PageInfo;
import com.yuntong.springcloud.domain.Product;
import com.yuntong.springcloud.service.ProductService;
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
@RequestMapping("/product")
@Api(tags="操作岸桥的接口")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 机组管理页面，用来查询所有机组
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @ApiOperation("查询所有机组")
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "10") int size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * 机组管理页面，添加机组
     * @param product
     * @return
     * @throws Exception
     */
    @ApiOperation("添加机组")
    @RequestMapping("/addProduct")
    public String addProduct(Product product) throws Exception {
        productService.addProduct(product);
        return "redirect:findAll";
    }

    /**
     * 机组管理页面，删除机组
     * @param productId
     * @return
     * @throws Exception
     */
    @ApiOperation("删除机组")
    @RequestMapping("/delProduct")
    public String delProduct(@RequestParam(name = "id",required = true) int productId) throws Exception {
        productService.delProduct(productId);
        return "redirect:findAll";
    }

    /**
     * 更新机组
     * @param product
     * @return
     * @throws Exception
     */
    @ApiOperation("更新机组")
    @RequestMapping("/update")
    public String update(Product product) throws Exception {
        productService.update(product);
        return "redirect:findAll";
    }

    @ApiOperation("根据id查询product，用来下一步的更新操作")
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) int productId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(productId);
        mv.addObject("product", product);
        mv.setViewName("product-update");
        return mv;
    }
}

