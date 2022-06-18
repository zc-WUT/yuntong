package com.yuntong.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.yuntong.springcloud.domain.HistoryData;
import com.yuntong.springcloud.dao.HistoryDataMapper;
import com.yuntong.springcloud.service.HistoryDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
@Service
@Transactional
public class HistoryDataServiceImpl extends ServiceImpl<HistoryDataMapper, HistoryData> implements HistoryDataService {

    @Autowired
    private HistoryDataMapper historyDataMapper;

    @Override
    public List<HistoryData> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return historyDataMapper.selectList(null);
    }

    /**
     * 和有page的findAll(page,size)不同在于，没有参数的findAll()是用来生成Excel文件的
     * @return
     * @throws Exception
     */
    @Override
    public List<HistoryData> findAll() throws Exception {
        return historyDataMapper.selectList(null);
    }

    /**
     * 范围查询，查询testdate between startTime and endTime之间的数据，带着分页数据返回
     * @param page
     * @param size
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @Override
    public List<HistoryData> findAllByTime(int page, int size, String startTime, String endTime) throws Exception {
        PageHelper.startPage(page,size);
        QueryWrapper<HistoryData> wrapper =new QueryWrapper<>();
        wrapper.between("testdate",startTime,endTime);
        return historyDataMapper.selectList(wrapper);
    }

    @Override
    public List<HistoryData> findAllByTime(String startTime, String endTime) throws Exception {
        QueryWrapper<HistoryData> wrapper =new QueryWrapper<>();
        wrapper.between("testdate",startTime,endTime);
        return historyDataMapper.selectList(wrapper);
    }

    @Override
    public HistoryData findImage(int id) throws Exception {
        return historyDataMapper.selectById(id);
    }

    @Override
    public void truncate() throws Exception {
        historyDataMapper.truncateData();
    }
}
