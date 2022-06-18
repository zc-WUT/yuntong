package com.yuntong.springcloud.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Product对象", description="")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "岸桥ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @ApiModelProperty(value = "岸桥编号")
    private String productnum;

    @ApiModelProperty(value = "启动时间")
    private String starttime;

    @ApiModelProperty(value = "岸桥备注")
    private String productdesc;

    @ApiModelProperty(value = "工作状态")
    private Integer productstate;

    @ApiModelProperty(value = "负责人名称")
    private String manager;

    @TableField(exist = false)
    private String productstatedesc;

    @TableField(exist = false)
    private String productname;

    public String getProductname() {
        productname="岸桥"+productnum;
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductstatedesc() {
        if (productstate==1){
            productstatedesc="开启";
        }else {
            productstatedesc="关闭";
        }
        return productstatedesc;
    }

    public void setProductstatedesc(String productstatedesc) {
        this.productstatedesc = productstatedesc;
    }



}
