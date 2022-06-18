package com.yuntong.springcloud.dao;

import com.yuntong.springcloud.domain.EquipData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
@Repository
public interface EquipDataMapper extends BaseMapper<EquipData> {
    @Update("alter table equip_data add (${equipNum} double(4,2))")
    void addEquipNumToEquipData(@Param("equipNum") String equipNum);

    @Update("alter table equip_data drop column ${equipNum}")
    void delEquipNumFromEquipData(@Param("equipNum") String equipNum);
}
