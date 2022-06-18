package com.yuntong.springcloud.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@ApiModel(value="TestData对象", description="")
public class TestData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "岸桥编号")
    private String productnum;

    @ApiModelProperty(value = "机构编号")
    private String equipnum;

    @ApiModelProperty(value = "机构名称")
    private String equipname;

    @ApiModelProperty(value = "检测时间")
    private String testdate;

    @ApiModelProperty(value = "集装箱数量")
    private Integer containernum;

    @ApiModelProperty(value = "工作时长")
    private Double worktime;

    @ApiModelProperty(value = "损伤位置")
    private Double position;

    @ApiModelProperty(value = "损伤数据")
    private Double testdata ;

    @ApiModelProperty(value = "损伤类别")
    private Integer type;

    @ApiModelProperty(value = "预期寿命")
    private Double lifetime;

    @ApiModelProperty(value = "损伤照片")
    private String image;

    //检测结论
    @ApiModelProperty(value = "结论")
    @TableField(exist = false) //告诉mybatisplus不要将这个字符串也去映射数据库中的字段，因为数据库中根本没有这个字段
    private String testResultStr;

    @ApiModelProperty(value = "损伤类别")
    @TableField(exist = false)
    private String typeStr;

    @ApiModelProperty(value = "数据展示的背景颜色")
    @TableField(exist = false)
    private String backColor;

    @ApiModelProperty(value = "首页的电脑图片")
    @TableField(exist = false)
    private String backImg;


    public String getBackImg() {
        if (testdata==null){
            backImg = "purple.jpg";
        }else if (testdata < 2.0){
            backImg = "green.jpg";
        }else if (testdata < 3.0){
            backImg = "green.jpg";
        }else if (testdata < 4.0){
            backImg = "yellow.jpg";
        }else if (testdata < 5.0){
            backImg = "brown.jpg";
        }else {
            backImg = "red.jpg";
        }
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    public String getTestResultStr() {
        if (testdata!=null){
            if (testdata <2.0){
                testResultStr = "正常";
            }else if (testdata <3.0){
                testResultStr="较轻";
            }else if (testdata <4.0){
                testResultStr="较重";
            }else if (testdata <5.0){
                testResultStr="严重";
            }else {
                testResultStr="超标";;
            }
        }else{
            testResultStr="异常";
        }
        return testResultStr;
    }

    public void setTestResultStr(String testResultStr) {
        this.testResultStr = testResultStr;
    }

    public String getTypeStr() {
        if (type!=null){
            if (type==0){
                typeStr="正常";
            }else if (type==1){
                typeStr="综合";
            }else if (type==2){
                typeStr="断丝";
            }else if (type==3){
                typeStr="疲劳";
            }else {
                typeStr="无效";
            }
        }else{
            typeStr="无效";
        }
        return typeStr;
    }

    public void setTypeStr(String typeStr) {

        this.typeStr = typeStr;
    }

    public String getBackColor() {
        if (testdata==null){
            backColor = "#CA83BA";
        }else if (testdata < 2.0){
            backColor = "#00ca6d";
        }else if (testdata < 3){
            backColor = "#00ca6d";
        }else if (testdata < 4){
            backColor = "yellow";
        }else if (testdata < 5){
            backColor = "#CA5F1C";
        }else {
            backColor = "red";
        }
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }
}
