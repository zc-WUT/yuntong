package com.yuntong.springcloud.service;

import com.yuntong.springcloud.domain.HistoryData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
public interface HistoryDataService extends IService<HistoryData> {
    List<HistoryData> findAll(int page, int size) throws Exception;

    List<HistoryData> findAll() throws Exception;

    List<HistoryData> findAllByTime(int page, int size, String startTime, String endTime)throws Exception;

    List<HistoryData> findAllByTime(String startTime, String endTime)throws Exception;

    HistoryData findImage(int id)throws Exception;

    void truncate() throws Exception ;
}
