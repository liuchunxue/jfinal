package com.feng;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;
import java.util.List;

public class Jfinal {
    public Object jfinalInitMysqlDB() {
        String url="jdbc:mysql://mysql.uat.1.local:3306/ec_order_uat?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
        String username="chunxue";
        String password="h_4h0eV3s0kvuUVwhtbG";
        DruidPlugin druidPlugin = new DruidPlugin(url,username, password);
        druidPlugin.setDriverClass("com.mysql.jdbc.Driver");
        druidPlugin.start();
        ActiveRecordPlugin arp = new ActiveRecordPlugin("db",druidPlugin);
        arp.setShowSql(true);
        arp.start();
        return new Object();
    }
    public Object jfinalInitMssqlDB() {
        String url="jdbc:sqlserver://mssql.uat.1.local;DatabaseName=Inspharmacy";
        String username="ec_order_service_uat";
        String password="5v01Tk7xSnO_74CufjZ0i";
        DruidPlugin druidPlugin = new DruidPlugin(url,username, password);
        druidPlugin.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        druidPlugin.start();
        ActiveRecordPlugin arp = new ActiveRecordPlugin("sqlServer",druidPlugin);
        arp.setShowSql(true);
        arp.start();
        return new Object();
    }

    public static void main(String[] args) {
        Jfinal jf=new Jfinal();
        jf.jfinalInitMysqlDB();
        jf.jfinalInitMssqlDB();
        List<Record> rList = Db.find("select * from order_info limit 10");
        for(Record r:rList){
            Long id = r.getLong("id");
            System.out.println(id);
        }

        List<Record> goodsLsit = Db.use("sqlServer").find("select * from dbo.b_TGOODS");
        for(Record r:goodsLsit){
            String code = r.getStr("Code");
            String name = r.getStr("CommonName");
            System.out.println(code);
            System.out.println(name);
        }
        Record record=new Record();
    }
}