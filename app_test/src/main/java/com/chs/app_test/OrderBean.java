package com.chs.app_test;

import java.util.List;

/**
 * @author chs
 * date: 2020-08-19 14:51
 * des:
 */
public class OrderBean {
    /**
     * data : [{"id":"938","orderid":"TRZD200317004","fault_id":"18169","fault_time":"1584425126","department_id":"7001","urgent":"2","cause":"这是测试bug","state":"1","collection_id":"0","repair_user":"16709","task_type":"1","repairstate":"2","dispatch_users":"12607","dispatch_type":"4","device_ids":"","close_state":"1","man_hours":"0","workprocess":"","is_sale":"2","parts_num":"0.00","parts_sale_money":"0.0000000","end_time":"0","integral":"0","integral_grab":"2","fault_pic":"","pause_state":"1","pause_verify_uids":"","is_overtime":"2","stores_id":"7","is_wjy_relation":"1","dispatch_time":"1585298163","confirm_json":"","replace_json":"","replace_json_name":"非代报","repair_user_arr":{"id":"16709","out_userid":"137","name":"小海","head_pic":"https://new-file.helloufu.com/file_admin/20200715/4_15948011392351392.jpeg","department_name":"工程部","duty_name":"测试"},"repair_user_help_arr":[],"device_lists":[],"instructions":"","is_change_device_state":1,"is_appointment":2,"fault_time_name":"2020-03-17 14:05","appointment_time_name":"2020-03-17 14:05","place_name":"1号楼","faulttype_name":"电气","subgroup_name":"电气组","repairstate_name":"已接单","store_detail":{"id":"7","attribute":"2","stores_name":"KFC","info":"优服科技-1单元--2-牛哥"},"urgent_name":"一般","fault_id_name":"吴明星","department_name":"产品部","receipt_time_name":"2020-03-17 14:11","dispatch_type_name":"","assign_order_date":""},{"id":"962","orderid":"TRZD200322001","fault_id":"12607","fault_time":"1584847908","department_id":"7001","urgent":"2","cause":"测试","state":"1","collection_id":"0","repair_user":"16709","task_type":"1","repairstate":"2","dispatch_users":"","dispatch_type":"0","device_ids":"","close_state":"1","man_hours":"0","workprocess":"","is_sale":"2","parts_num":"1.00","parts_sale_money":"0.0000000","end_time":"0","integral":"0","integral_grab":"100","fault_pic":"","pause_state":"1","pause_verify_uids":"","is_overtime":"2","stores_id":"0","is_wjy_relation":"2","dispatch_time":"1584848010","confirm_json":"","replace_json":"","replace_json_name":"非代报","repair_user_arr":{"id":"16709","out_userid":"137","name":"小海","head_pic":"https://new-file.helloufu.com/file_admin/20200715/4_15948011392351392.jpeg","department_name":"工程部","duty_name":"测试"},"repair_user_help_arr":[],"device_lists":[],"instructions":"","is_change_device_state":1,"is_appointment":1,"fault_time_name":"2020-03-22 11:31","appointment_time_name":"","place_name":"1号楼","faulttype_name":"电气","subgroup_name":"电气组","repairstate_name":"已接单","urgent_name":"一般","fault_id_name":"满天星","department_name":"工程部","receipt_time_name":"2020-03-22 11:34","dispatch_type_name":"","assign_order_date":""}]
     * total_number : 2
     * number : 2
     * returncode : 0
     * returnmsg : 操作成功
     * page : 1
     * pagesize : 20
     * pages : 1
     */

    private int total_number;
    private int number;
    private String returncode;
    private String returnmsg;
    private String page;
    private String pagesize;
    private int pages;
    private List<DataEntity> data;

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public String getReturnmsg() {
        return returnmsg;
    }

    public void setReturnmsg(String returnmsg) {
        this.returnmsg = returnmsg;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * id : 938
         * orderid : TRZD200317004
         * fault_id : 18169
         * fault_time : 1584425126
         * department_id : 7001
         * urgent : 2
         * cause : 这是测试bug
         * state : 1
         * collection_id : 0
         * repair_user : 16709
         * task_type : 1
         * repairstate : 2
         * dispatch_users : 12607
         * dispatch_type : 4
         * device_ids :
         * close_state : 1
         * man_hours : 0
         * workprocess :
         * is_sale : 2
         * parts_num : 0.00
         * parts_sale_money : 0.0000000
         * end_time : 0
         * integral : 0
         * integral_grab : 2
         * fault_pic :
         * pause_state : 1
         * pause_verify_uids :
         * is_overtime : 2
         * stores_id : 7
         * is_wjy_relation : 1
         * dispatch_time : 1585298163
         * confirm_json :
         * replace_json :
         * replace_json_name : 非代报
         * repair_user_arr : {"id":"16709","out_userid":"137","name":"小海","head_pic":"https://new-file.helloufu.com/file_admin/20200715/4_15948011392351392.jpeg","department_name":"工程部","duty_name":"测试"}
         * repair_user_help_arr : []
         * device_lists : []
         * instructions :
         * is_change_device_state : 1
         * is_appointment : 2
         * fault_time_name : 2020-03-17 14:05
         * appointment_time_name : 2020-03-17 14:05
         * place_name : 1号楼
         * faulttype_name : 电气
         * subgroup_name : 电气组
         * repairstate_name : 已接单
         * store_detail : {"id":"7","attribute":"2","stores_name":"KFC","info":"优服科技-1单元--2-牛哥"}
         * urgent_name : 一般
         * fault_id_name : 吴明星
         * department_name : 产品部
         * receipt_time_name : 2020-03-17 14:11
         * dispatch_type_name :
         * assign_order_date :
         */

        private String id;
        private String orderid;
        private String fault_id;
        private String fault_time;
        private String department_id;
        private String urgent;
        private String cause;
        private String state;
        private String collection_id;
        private String repair_user;
        private String task_type;
        private String repairstate;
        private String dispatch_users;
        private String dispatch_type;
        private String device_ids;
        private String close_state;
        private String man_hours;
        private String workprocess;
        private String is_sale;
        private String parts_num;
        private String parts_sale_money;
        private String end_time;
        private String integral;
        private String integral_grab;
        private String fault_pic;
        private String pause_state;
        private String pause_verify_uids;
        private String is_overtime;
        private String stores_id;
        private String is_wjy_relation;
        private String dispatch_time;
        private String confirm_json;
        private String replace_json;
        private String replace_json_name;
        private RepairUserArrEntity repair_user_arr;
        private String instructions;
        private int is_change_device_state;
        private int is_appointment;
        private String fault_time_name;
        private String appointment_time_name;
        private String place_name;
        private String faulttype_name;
        private String subgroup_name;
        private String repairstate_name;
        private StoreDetailEntity store_detail;
        private String urgent_name;
        private String fault_id_name;
        private String department_name;
        private String receipt_time_name;
        private String dispatch_type_name;
        private String assign_order_date;
        private List<?> repair_user_help_arr;
        private List<?> device_lists;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getFault_id() {
            return fault_id;
        }

        public void setFault_id(String fault_id) {
            this.fault_id = fault_id;
        }

        public String getFault_time() {
            return fault_time;
        }

        public void setFault_time(String fault_time) {
            this.fault_time = fault_time;
        }

        public String getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(String department_id) {
            this.department_id = department_id;
        }

        public String getUrgent() {
            return urgent;
        }

        public void setUrgent(String urgent) {
            this.urgent = urgent;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCollection_id() {
            return collection_id;
        }

        public void setCollection_id(String collection_id) {
            this.collection_id = collection_id;
        }

        public String getRepair_user() {
            return repair_user;
        }

        public void setRepair_user(String repair_user) {
            this.repair_user = repair_user;
        }

        public String getTask_type() {
            return task_type;
        }

        public void setTask_type(String task_type) {
            this.task_type = task_type;
        }

        public String getRepairstate() {
            return repairstate;
        }

        public void setRepairstate(String repairstate) {
            this.repairstate = repairstate;
        }

        public String getDispatch_users() {
            return dispatch_users;
        }

        public void setDispatch_users(String dispatch_users) {
            this.dispatch_users = dispatch_users;
        }

        public String getDispatch_type() {
            return dispatch_type;
        }

        public void setDispatch_type(String dispatch_type) {
            this.dispatch_type = dispatch_type;
        }

        public String getDevice_ids() {
            return device_ids;
        }

        public void setDevice_ids(String device_ids) {
            this.device_ids = device_ids;
        }

        public String getClose_state() {
            return close_state;
        }

        public void setClose_state(String close_state) {
            this.close_state = close_state;
        }

        public String getMan_hours() {
            return man_hours;
        }

        public void setMan_hours(String man_hours) {
            this.man_hours = man_hours;
        }

        public String getWorkprocess() {
            return workprocess;
        }

        public void setWorkprocess(String workprocess) {
            this.workprocess = workprocess;
        }

        public String getIs_sale() {
            return is_sale;
        }

        public void setIs_sale(String is_sale) {
            this.is_sale = is_sale;
        }

        public String getParts_num() {
            return parts_num;
        }

        public void setParts_num(String parts_num) {
            this.parts_num = parts_num;
        }

        public String getParts_sale_money() {
            return parts_sale_money;
        }

        public void setParts_sale_money(String parts_sale_money) {
            this.parts_sale_money = parts_sale_money;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getIntegral_grab() {
            return integral_grab;
        }

        public void setIntegral_grab(String integral_grab) {
            this.integral_grab = integral_grab;
        }

        public String getFault_pic() {
            return fault_pic;
        }

        public void setFault_pic(String fault_pic) {
            this.fault_pic = fault_pic;
        }

        public String getPause_state() {
            return pause_state;
        }

        public void setPause_state(String pause_state) {
            this.pause_state = pause_state;
        }

        public String getPause_verify_uids() {
            return pause_verify_uids;
        }

        public void setPause_verify_uids(String pause_verify_uids) {
            this.pause_verify_uids = pause_verify_uids;
        }

        public String getIs_overtime() {
            return is_overtime;
        }

        public void setIs_overtime(String is_overtime) {
            this.is_overtime = is_overtime;
        }

        public String getStores_id() {
            return stores_id;
        }

        public void setStores_id(String stores_id) {
            this.stores_id = stores_id;
        }

        public String getIs_wjy_relation() {
            return is_wjy_relation;
        }

        public void setIs_wjy_relation(String is_wjy_relation) {
            this.is_wjy_relation = is_wjy_relation;
        }

        public String getDispatch_time() {
            return dispatch_time;
        }

        public void setDispatch_time(String dispatch_time) {
            this.dispatch_time = dispatch_time;
        }

        public String getConfirm_json() {
            return confirm_json;
        }

        public void setConfirm_json(String confirm_json) {
            this.confirm_json = confirm_json;
        }

        public String getReplace_json() {
            return replace_json;
        }

        public void setReplace_json(String replace_json) {
            this.replace_json = replace_json;
        }

        public String getReplace_json_name() {
            return replace_json_name;
        }

        public void setReplace_json_name(String replace_json_name) {
            this.replace_json_name = replace_json_name;
        }

        public RepairUserArrEntity getRepair_user_arr() {
            return repair_user_arr;
        }

        public void setRepair_user_arr(RepairUserArrEntity repair_user_arr) {
            this.repair_user_arr = repair_user_arr;
        }

        public String getInstructions() {
            return instructions;
        }

        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }

        public int getIs_change_device_state() {
            return is_change_device_state;
        }

        public void setIs_change_device_state(int is_change_device_state) {
            this.is_change_device_state = is_change_device_state;
        }

        public int getIs_appointment() {
            return is_appointment;
        }

        public void setIs_appointment(int is_appointment) {
            this.is_appointment = is_appointment;
        }

        public String getFault_time_name() {
            return fault_time_name;
        }

        public void setFault_time_name(String fault_time_name) {
            this.fault_time_name = fault_time_name;
        }

        public String getAppointment_time_name() {
            return appointment_time_name;
        }

        public void setAppointment_time_name(String appointment_time_name) {
            this.appointment_time_name = appointment_time_name;
        }

        public String getPlace_name() {
            return place_name;
        }

        public void setPlace_name(String place_name) {
            this.place_name = place_name;
        }

        public String getFaulttype_name() {
            return faulttype_name;
        }

        public void setFaulttype_name(String faulttype_name) {
            this.faulttype_name = faulttype_name;
        }

        public String getSubgroup_name() {
            return subgroup_name;
        }

        public void setSubgroup_name(String subgroup_name) {
            this.subgroup_name = subgroup_name;
        }

        public String getRepairstate_name() {
            return repairstate_name;
        }

        public void setRepairstate_name(String repairstate_name) {
            this.repairstate_name = repairstate_name;
        }

        public StoreDetailEntity getStore_detail() {
            return store_detail;
        }

        public void setStore_detail(StoreDetailEntity store_detail) {
            this.store_detail = store_detail;
        }

        public String getUrgent_name() {
            return urgent_name;
        }

        public void setUrgent_name(String urgent_name) {
            this.urgent_name = urgent_name;
        }

        public String getFault_id_name() {
            return fault_id_name;
        }

        public void setFault_id_name(String fault_id_name) {
            this.fault_id_name = fault_id_name;
        }

        public String getDepartment_name() {
            return department_name;
        }

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public String getReceipt_time_name() {
            return receipt_time_name;
        }

        public void setReceipt_time_name(String receipt_time_name) {
            this.receipt_time_name = receipt_time_name;
        }

        public String getDispatch_type_name() {
            return dispatch_type_name;
        }

        public void setDispatch_type_name(String dispatch_type_name) {
            this.dispatch_type_name = dispatch_type_name;
        }

        public String getAssign_order_date() {
            return assign_order_date;
        }

        public void setAssign_order_date(String assign_order_date) {
            this.assign_order_date = assign_order_date;
        }

        public List<?> getRepair_user_help_arr() {
            return repair_user_help_arr;
        }

        public void setRepair_user_help_arr(List<?> repair_user_help_arr) {
            this.repair_user_help_arr = repair_user_help_arr;
        }

        public List<?> getDevice_lists() {
            return device_lists;
        }

        public void setDevice_lists(List<?> device_lists) {
            this.device_lists = device_lists;
        }

        public static class RepairUserArrEntity {
            /**
             * id : 16709
             * out_userid : 137
             * name : 小海
             * head_pic : https://new-file.helloufu.com/file_admin/20200715/4_15948011392351392.jpeg
             * department_name : 工程部
             * duty_name : 测试
             */

            private String id;
            private String out_userid;
            private String name;
            private String head_pic;
            private String department_name;
            private String duty_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOut_userid() {
                return out_userid;
            }

            public void setOut_userid(String out_userid) {
                this.out_userid = out_userid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public String getDepartment_name() {
                return department_name;
            }

            public void setDepartment_name(String department_name) {
                this.department_name = department_name;
            }

            public String getDuty_name() {
                return duty_name;
            }

            public void setDuty_name(String duty_name) {
                this.duty_name = duty_name;
            }
        }

        public static class StoreDetailEntity {
            /**
             * id : 7
             * attribute : 2
             * stores_name : KFC
             * info : 优服科技-1单元--2-牛哥
             */

            private String id;
            private String attribute;
            private String stores_name;
            private String info;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAttribute() {
                return attribute;
            }

            public void setAttribute(String attribute) {
                this.attribute = attribute;
            }

            public String getStores_name() {
                return stores_name;
            }

            public void setStores_name(String stores_name) {
                this.stores_name = stores_name;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }
    }
}
