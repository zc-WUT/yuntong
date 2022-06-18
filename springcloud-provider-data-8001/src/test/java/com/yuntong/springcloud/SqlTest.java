package com.yuntong.springcloud;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.yuntong.springcloud.dao.*;
import com.yuntong.springcloud.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SqlTest {
    @Autowired
    private ProductMapper productdao;
    @Autowired
    private EquipmentMapper equipDao;
    @Autowired
    DataSource dataSource;
    @Autowired
    private EquipTimeDataMapper equipTimeDataMapper;
    @Autowired
    private EquipDataMapper equipDataMapper;
    @Autowired
    private HistoryDataMapper historyDataMapper;
    @Autowired
    private TestDataMapper testDataMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UsersRoleMapper usersRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Test
    public void testOfEquipment(){
/*        Equipment equipment =new Equipment();
        equipment.setId(1);
        equipment.setEquipdesc("第一组设备的起升检测机构");
        equipDao.updateById(equipment);
        QueryWrapper<Equipment> wrapper =new QueryWrapper();
        wrapper.eq("productnum","QC01");
        List<Equipment> equipments = equipDao.selectList(wrapper);
        equipments.forEach(System.out::println);*/
        equipDao.selectList((Wrapper<Equipment>) null).forEach(System.out::println);
    }

    @Test
    public void testOfDate2() throws ParseException {
        String selectTime ="10/02/2012 - 11/03/2012";
        SimpleDateFormat in = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");

        String startTime = out.format(in.parse(selectTime.substring(0, 10)));
        String endTime = out.format(in.parse(selectTime.substring(13, 23)));

        startTime=startTime+"\t"+"00:00:00";
        endTime=endTime+"\t"+"23:59:59";
        System.out.println(startTime+"-----------"+endTime);
    }


    @Test
    public void testOfProduct(){
        List<Product> products = productdao.selectList(null);
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    public void testOfEquipData(){
        QueryWrapper<Equipment> wrapper1 = new QueryWrapper<>();
        wrapper1.select("equipNum").eq("productNum", "QC01");
        List<Equipment> equipmentList = equipDao.selectList(wrapper1);
        List<String> equipNums = new ArrayList<>();
        equipNums.add("CONTAINERNUM"); //将固定列加入到数组中
        for (Equipment equipment : equipmentList) {
            equipNums.add(equipment.getEquipnum());
        }
        QueryWrapper<EquipData> wrapper2 = new QueryWrapper<>();
        String listToString =equipNums.toString();
        //去除listToString里的[,] 这三个符号后得到QC010101 QC010102 QC010103 QC010104 QC010201 QC010202 QC010203 QC010204 QC010301 QC010302
        String equipNumsToString=listToString.replaceAll("\\p{Punct}","");
        //System.out.println(equipNumsToString);
        //equipNumsToString.split("\\s+")----> QC010101, QC010102, QC010103, QC010104, QC010201, QC010202, QC010203, QC010204, QC010301, QC010302
        wrapper2.select(equipNumsToString.split("\\s+"));//把equipNums的集合里每一个参数都传入该方法中
        equipDataMapper.selectList(wrapper2).forEach(System.out::println);
    }

    @Test
    public void testOfSelectDate(){
        QueryWrapper<HistoryData> wrapper =new QueryWrapper<>();
        wrapper.between("testdate","2020-08-23 00:00:00","2020-08-23 23:59:59");
        historyDataMapper.selectList(wrapper);
    }

    @Test
    public void testOfHistory(){
        List<HistoryData> historyData = historyDataMapper.selectList(null);
        for (HistoryData historyDatum : historyData) {
            System.out.println(historyData);
        }
    }

    @Test
    public void testOfTestData(){
        //先从equipment表中查询productNum所对应的equipNum,全部封装到List集合中
        QueryWrapper<Equipment> wrapper1 = new QueryWrapper<>();
        wrapper1.select("equipnum").eq("productnum", "QC01");
        //从testData中查询equipnum为equipNum的数据里的-->testdata最大的数据
        List<TestData> testDatas = new ArrayList<>();
        for (Equipment equipment : equipDao.selectList(wrapper1)) {
            TestData testData = new TestData();
            String equipNum = equipment.getEquipnum(); //获取一个equipment对象的equipum属性值
            //通过自己写的sql从test_data表中查询出该equipnum所对应的数据集中的最大的testdata
            testData.setEquipnum(equipNum).setTestdata(Double.valueOf(testDataMapper.selectMaxEquipData(equipNum)));
            testDatas.add(testData);
        }
        for (TestData testData : testDatas) {
            System.out.println(testData);
        }
    }

    @Test
    public void testOf22(){
        List<String> equipNums = new ArrayList<>();
        equipNums.add("QC010101");
        equipNums.add("WORKTIME"); //将固定列加入到数组中
        QueryWrapper<EquipTimeData> wrapper2 = new QueryWrapper<>();
        String equipNumsToString = equipNums.toString().replaceAll("\\p{Punct}", "");
        wrapper2.select(equipNumsToString.split("\\s+"));//把equipNums的集合里每一个参数都传入该方法中
        equipTimeDataMapper.selectList(wrapper2).forEach(System.out::println);
    }

    @Test
    public void testOfUser() throws Exception {
        QueryWrapper<Users> wrapper =new QueryWrapper<>();
        wrapper.select("id","username","password","status").eq("username","yuntong");
        Users users=usersMapper.selectOne(wrapper);
        List<Role> roles =roleMapper.selectRoleIdByUserId(users.getId());
        for (Role role : roles) {
            List<Permission> permissionList = permissionMapper.findPermissionToDel(role.getId());
            role.setPermissions(permissionList);
        }
        users.setRoles(roles);
        System.out.println(users);
    }

    @Test
    public void testOfRole(){
/*        QueryWrapper<RolePermission> wrapper =new QueryWrapper<>();
        wrapper.eq("permissionid",2).eq("roleid",2);
        rolePermissionMapper.delete(wrapper);*/
        RolePermission rolePermission=new RolePermission();
        rolePermission.setRoleid(2).setPermissionid(2);
        rolePermissionMapper.insert(rolePermission);
    }

    @Test
    public void testOfDataSource() throws SQLException {

        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testOfDate(){
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy年MM月dd日" );
        String str = sdf.format(new Date());
        System.out.println(str);
    }

}
