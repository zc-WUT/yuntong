package com.yuntong.springcloud.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 张弛
 * @since 2020-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UsersRole对象", description="")
public class UsersRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户的ID")
    private Integer userid;

    @ApiModelProperty(value = "角色的ID")
    private Integer roleid;


}
