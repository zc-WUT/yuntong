package com.yuntong.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.yuntong.springcloud.dao.EquipDataMapper;
import com.yuntong.springcloud.dao.EquipTimeDataMapper;
import com.yuntong.springcloud.domain.Equipment;
import com.yuntong.springcloud.dao.EquipmentMapper;
import com.yuntong.springcloud.service.EquipmentService;
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
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {
    @Autowired
    private EquipmentMapper equipDao;
    @Autowired
    private EquipDataMapper equipDataMapper;
    @Autowired
    private EquipTimeDataMapper equipTimeDataMapper;

    @Override
    public List<Equipment> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return equipDao.selectList( null);
    }

    @Override
    public List<Equipment> findByProductNum(String productNum) throws Exception {
        QueryWrapper<Equipment> wrapper =new QueryWrapper<>();
        wrapper.eq("productnum",productNum);
        return equipDao.selectList(wrapper);
    }


    @Override
    public void addEquip(Equipment equip) throws Exception {
        equipDao.insert(equip);
        String equipNum = equip.getEquipnum();
        //在equip_data表中添加一列
        equipDataMapper.addEquipNumToEquipData(equipNum);
        //在equip_time_data表中添加同样的列
        equipTimeDataMapper.addEquipNumToTimeData(equipNum);
    }

    @Override
    public void delEquip(int equipId) throws Exception {
        String equipNum =equipDao.selectById(equipId).getEquipnum();
        equipDao.deleteById(equipId);
        //在equip_data表中删除该列
        equipDataMapper.delEquipNumFromEquipData(equipNum);
        //在equip_time_data表中删除同样的列
        equipTimeDataMapper.delEquipNumFromTimeData(equipNum);
    }

    @Override
    public void updateEquip(Equipment equip) throws Exception {
        equipDao.updateById(equip);
    }

}
