package com.chs.app_test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //测试gson解析 当后台返回的数据结构错误的时候不让gson保存。比如本来需要List类型，后台却返回了一个字符串这种情况
    @Test
    public void testGson(){
        String json = "{\"data\":[{\"id\":\"938\",\"orderid\":\"TRZD200317004\",\"fault_id\":\"18169\",\"fault_time\":\"1584425126\",\"department_id\":\"7001\",\"urgent\":\"2\",\"cause\":\"这是测试bug\",\"state\":\"1\",\"collection_id\":\"0\",\"repair_user\":\"16709\",\"task_type\":\"1\",\"repairstate\":\"2\",\"dispatch_users\":\"12607\",\"dispatch_type\":\"4\",\"device_ids\":\"\",\"close_state\":\"1\",\"man_hours\":\"0\",\"workprocess\":\"\",\"is_sale\":\"2\",\"parts_num\":\"0.00\",\"parts_sale_money\":\"0.0000000\",\"end_time\":\"0\",\"integral\":\"0\",\"integral_grab\":\"2\",\"fault_pic\":\"\",\"pause_state\":\"1\",\"pause_verify_uids\":\"\",\"is_overtime\":\"2\",\"stores_id\":\"7\",\"is_wjy_relation\":\"1\",\"dispatch_time\":\"1585298163\",\"confirm_json\":\"\",\"replace_json\":\"\",\"replace_json_name\":\"非代报\",\"repair_user_arr\":{\"id\":\"16709\",\"out_userid\":\"137\",\"name\":\"小海\",\"head_pic\":\"https:\\/\\/new-file.helloufu.com\\/file_admin\\/20200715\\/4_15948011392351392.jpeg\",\"department_name\":\"工程部\",\"duty_name\":\"测试\"},\"repair_user_help_arr\":[],\"device_lists\":[],\"instructions\":\"\",\"is_change_device_state\":1,\"is_appointment\":2,\"fault_time_name\":\"2020-03-17 14:05\",\"appointment_time_name\":\"2020-03-17 14:05\",\"place_name\":\"1号楼\",\"faulttype_name\":\"电气\",\"subgroup_name\":\"电气组\",\"repairstate_name\":\"已接单\",\"store_detail\":{\"id\":\"7\",\"attribute\":\"2\",\"stores_name\":\"KFC\",\"info\":\"优服科技-1单元--2-牛哥\"},\"urgent_name\":\"一般\",\"fault_id_name\":\"吴明星\",\"department_name\":\"产品部\",\"receipt_time_name\":\"2020-03-17 14:11\",\"dispatch_type_name\":\"\",\"assign_order_date\":\"\"},{\"id\":\"962\",\"orderid\":\"TRZD200322001\",\"fault_id\":\"12607\",\"fault_time\":\"1584847908\",\"department_id\":\"7001\",\"urgent\":\"2\",\"cause\":\"测试\",\"state\":\"1\",\"collection_id\":\"0\",\"repair_user\":\"16709\",\"task_type\":\"1\",\"repairstate\":\"2\",\"dispatch_users\":\"\",\"dispatch_type\":\"0\",\"device_ids\":\"\",\"close_state\":\"1\",\"man_hours\":\"0\",\"workprocess\":\"\",\"is_sale\":\"2\",\"parts_num\":\"1.00\",\"parts_sale_money\":\"0.0000000\",\"end_time\":\"0\",\"integral\":\"0\",\"integral_grab\":\"100\",\"fault_pic\":\"\",\"pause_state\":\"1\",\"pause_verify_uids\":\"\",\"is_overtime\":\"2\",\"stores_id\":\"0\",\"is_wjy_relation\":\"2\",\"dispatch_time\":\"1584848010\",\"confirm_json\":\"\",\"replace_json\":\"\",\"replace_json_name\":\"非代报\",\"repair_user_arr\":{\"id\":\"16709\",\"out_userid\":\"137\",\"name\":\"小海\",\"head_pic\":\"https:\\/\\/new-file.helloufu.com\\/file_admin\\/20200715\\/4_15948011392351392.jpeg\",\"department_name\":\"工程部\",\"duty_name\":\"测试\"},\"repair_user_help_arr\":[],\"device_lists\":[],\"instructions\":\"\",\"is_change_device_state\":1,\"is_appointment\":1,\"fault_time_name\":\"2020-03-22 11:31\",\"appointment_time_name\":\"\",\"place_name\":\"1号楼\",\"faulttype_name\":\"电气\",\"subgroup_name\":\"电气组\",\"repairstate_name\":\"已接单\",\"urgent_name\":\"一般\",\"fault_id_name\":\"满天星\",\"department_name\":\"工程部\",\"receipt_time_name\":\"2020-03-22 11:34\",\"dispatch_type_name\":\"\",\"assign_order_date\":\"\"}],\"total_number\":2,\"number\":2,\"returncode\":\"0\",\"returnmsg\":\"操作成功\",\"page\":\"1\",\"pagesize\":\"20\",\"pages\":1}";
        String json1 = "{\"data\":\"\",\"total_number\":2,\"number\":2,\"returncode\":\"0\",\"returnmsg\":\"操作成功\",\"page\":\"1\",\"pagesize\":\"20\",\"pages\":1}";
        Gson gson = new GsonBuilder().registerTypeAdapter(List.class,new ArrayJsonAdapter()).create();
        OrderBean orderBean = gson.fromJson(json1,OrderBean.class);
//        System.out.println(orderBean.getData().get(0).getCause());
        System.out.println(orderBean.getReturnmsg());
    }

    @Test
    public void testCalculate(){
        Utils utils = new Utils();
        assertEquals(2,utils.calculate(1,1));
        //3>1+1 是正确的
        assertTrue((3>utils.calculate(1,1)));
//        assertFalse((3>utils.calculate(1,1)));
        List<String> list = new ArrayList<>();
        list.add("2");
        assertTrue(list.size()>0);
    }

    @Test
    public void testToNumber_NotNullOrEmpty(){
        Utils utils = new Utils();
       assertNull(utils.toNumber(null));
       assertNull(utils.toNumber(""));
    }
    @Test
    public void testToNumber_hasSpace(){
        Utils utils = new Utils();
        assertEquals(new Integer("123"),utils.toNumber("123"));
        assertEquals(new Integer("123"),utils.toNumber("123 "));
        assertEquals(new Integer("123"),utils.toNumber(" 123 "));
    }
    @Test
    public void testToNumber_hasMiddleSpace(){
        Utils utils = new Utils();
        assertNull(utils.toNumber("12 3"));
        assertNull(utils.toNumber("12a3"));
    }
}