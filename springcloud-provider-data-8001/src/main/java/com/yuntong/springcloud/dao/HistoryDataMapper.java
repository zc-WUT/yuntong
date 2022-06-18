package com.yuntong.springcloud.dao;

import com.yuntong.springcloud.domain.HistoryData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface HistoryDataMapper extends BaseMapper<HistoryData> {

    @Update("truncate table history_data")
    void truncateData() throws Exception;

}
