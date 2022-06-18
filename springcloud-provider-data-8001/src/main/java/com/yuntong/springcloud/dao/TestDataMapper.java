package com.yuntong.springcloud.dao;

import com.yuntong.springcloud.domain.TestData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
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
public interface TestDataMapper extends BaseMapper<TestData> {
    @Select("SELECT MAX(testdata) FROM test_data WHERE equipnum=#{equipNum}")
    Double selectMaxEquipData(String equipNum);

    @Select("select Max(testdata) FROM test_data WHERE productnum=#{productnum}")
    Double selectMax(String productnum);
}
