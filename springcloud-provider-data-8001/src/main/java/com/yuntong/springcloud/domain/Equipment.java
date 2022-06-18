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
@ApiModel(value="Equipment对象", description="")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "机构ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "岸桥编号")
    private String productnum;

    @ApiModelProperty(value = "机构编号")
    private String equipnum;

    @ApiModelProperty(value = "机构名称")
    private String equipname;

    @ApiModelProperty(value = "启动时间")
    private String starttime;

    @ApiModelProperty(value = "工作状态")
    private Integer equipstate;

    @ApiModelProperty(value = "备注")
    private String equipdesc;

    @TableField(exist = false)
    private String equipstatestr;

    public String getEquipstatestr() {
        if (equipstate==1){
            equipstatestr="开启";
        }else
        {
            equipstatestr="关闭";
        }
        return equipstatestr;
    }

    public void setEquipstatestr(String equipstatestr) {
        this.equipstatestr = equipstatestr;
    }




}
