package com.yuntong.springcloud.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="EquipTimeData对象", description="")
public class EquipTimeData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("WORKTIME")
    private Double workTime;

    @TableField("QC010101")
    private Double QC010101;

    @TableField("QC010102")
    private Double QC010102;

    @TableField("QC010103")
    private Double QC010103;

    @TableField("QC010104")
    private Double QC010104;

    @TableField("QC010201")
    private Double QC010201;

    @TableField("QC010202")
    private Double QC010202;

    @TableField("QC010203")
    private Double QC010203;

    @TableField("QC010204")
    private Double QC010204;

    @TableField("QC010301")
    private Double QC010301;

    @TableField("QC010302")
    private Double QC010302;

    @TableField("QC020101")
    private Double QC020101;

    @TableField("QC020102")
    private Double QC020102;

    @TableField("QC020103")
    private Double QC020103;

    @TableField("QC020104")
    private Double QC020104;

    @TableField("QC020201")
    private Double QC020201;

    @TableField("QC020202")
    private Double QC020202;

    @TableField("QC020203")
    private Double QC020203;

    @TableField("QC020204")
    private Double QC020204;

    @TableField("QC020301")
    private Double QC020301;

    @TableField("QC020302")
    private Double QC020302;

    @TableField("QC030101")
    private Double QC030101;

    @TableField("QC030102")
    private Double QC030102;

    @TableField("QC030103")
    private Double QC030103;

    @TableField("QC030104")
    private Double QC030104;

    @TableField("QC030201")
    private Double QC030201;

    @TableField("QC030202")
    private Double QC030202;

    @TableField("QC030203")
    private Double QC030203;

    @TableField("QC030204")
    private Double QC030204;

    @TableField("QC030301")
    private Double QC030301;

    @TableField("QC030302")
    private Double QC030302;

    @TableField("QC040101")
    private Double QC040101;

    @TableField("QC040102")
    private Double QC040102;

    @TableField("QC040103")
    private Double QC040103;

    @TableField("QC040104")
    private Double QC040104;

    @TableField("QC040201")
    private Double QC040201;

    @TableField("QC040202")
    private Double QC040202;

    @TableField("QC040203")
    private Double QC040203;

    @TableField("QC040204")
    private Double QC040204;

    @TableField("QC040301")
    private Double QC040301;

    @TableField("QC040302")
    private Double QC040302;

    @TableField("QC050101")
    private Double QC050101;

    @TableField("QC050102")
    private Double QC050102;

    @TableField("QC050103")
    private Double QC050103;

    @TableField("QC050104")
    private Double QC050104;

    @TableField("QC050201")
    private Double QC050201;

    @TableField("QC050202")
    private Double QC050202;

    @TableField("QC050203")
    private Double QC050203;

    @TableField("QC050204")
    private Double QC050204;

    @TableField("QC050301")
    private Double QC050301;

    @TableField("QC050302")
    private Double QC050302;

    @TableField("QC060101")
    private Double QC060101;

    @TableField("QC060102")
    private Double QC060102;

    @TableField("QC060103")
    private Double QC060103;

    @TableField("QC060104")
    private Double QC060104;

    @TableField("QC060201")
    private Double QC060201;

    @TableField("QC060202")
    private Double QC060202;

    @TableField("QC060203")
    private Double QC060203;

    @TableField("QC060204")
    private Double QC060204;

    @TableField("QC060301")
    private Double QC060301;

    @TableField("QC060302")
    private Double QC060302;

    @TableField("QC070101")
    private Double QC070101;

    @TableField("QC070102")
    private Double QC070102;

    @TableField("QC070103")
    private Double QC070103;

    @TableField("QC070104")
    private Double QC070104;

    @TableField("QC070201")
    private Double QC070201;

    @TableField("QC070202")
    private Double QC070202;

    @TableField("QC070203")
    private Double QC070203;

    @TableField("QC070204")
    private Double QC070204;

    @TableField("QC070301")
    private Double QC070301;

    @TableField("QC070302")
    private Double QC070302;

    @TableField("QC080101")
    private Double QC080101;

    @TableField("QC080102")
    private Double QC080102;

    @TableField("QC080103")
    private Double QC080103;

    @TableField("QC080104")
    private Double QC080104;

    @TableField("QC080201")
    private Double QC080201;

    @TableField("QC080202")
    private Double QC080202;

    @TableField("QC080203")
    private Double QC080203;

    @TableField("QC080204")
    private Double QC080204;

    @TableField("QC080301")
    private Double QC080301;

    @TableField("QC080302")
    private Double QC080302;

    @TableField("QC090101")
    private Double QC090101;

    @TableField("QC090102")
    private Double QC090102;

    @TableField("QC090103")
    private Double QC090103;

    @TableField("QC090104")
    private Double QC090104;

    @TableField("QC090201")
    private Double QC090201;

    @TableField("QC090202")
    private Double QC090202;

    @TableField("QC090203")
    private Double QC090203;

    @TableField("QC090204")
    private Double QC090204;

    @TableField("QC090301")
    private Double QC090301;

    @TableField("QC090302")
    private Double QC090302;

    @TableField("QC100101")
    private Double QC100101;

    @TableField("QC100102")
    private Double QC100102;

    @TableField("QC100103")
    private Double QC100103;

    @TableField("QC100104")
    private Double QC100104;

    @TableField("QC100201")
    private Double QC100201;

    @TableField("QC100202")
    private Double QC100202;

    @TableField("QC100203")
    private Double QC100203;

    @TableField("QC100204")
    private Double QC100204;

    @TableField("QC100301")
    private Double QC100301;

    @TableField("QC100302")
    private Double QC100302;


}
