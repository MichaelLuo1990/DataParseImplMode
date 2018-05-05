package com.example;

/**
 * Desc
 * Created by Michael on 2018/4/24.
 */

public class SQLStrFormatTest {

//    static String sqlStr = "SELECT\n" +
//            "\t\t\t  *\n" +
//            "\t\t\tFROM\n" +
//            "\t\t\t  sys_role\ta\n" +
//            "\t\t\t   where 1=1\n" +
//            "\n" +
//            "\n" +
//            "\n" +
//            "\n" +
//            "\t\t order by a.create_time desc";

    static String sqlStr = "select  \n" +
            "    id, birthday, department_id,subscribe, email, last_logintime, password, sex, status, username, \n" +
            "    phone, name, office_phone, address, isprincipal, iscommander, isleader, last_ip, \n" +
            "    del, duty, face, role_id,create_time, token,user_type,area_id, salt\n" +
            "   \n" +
            "\t    from sys_user \n" +
            "\t\twhere username = ? and del=0";

//    static String sqlStr = "insert into sys_user (id, birthday, department_id, \n" +
//            "      email, last_logintime, password, \n" +
//            "      sex, status, username, \n" +
//            "      phone, name, office_phone, \n" +
//            "      address, isprincipal, iscommander, \n" +
//            "      isleader, last_ip, del, \n" +
//            "      duty, face, role_id, \n" +
//            "      create_time, token, subscribe, \n" +
//            "      user_type,salt)\n" +
//            "    values (?, ?, ?, \n" +
//            "      ?, ?, ?, \n" +
//            "      ?, ?, ?, \n" +
//            "      ?, ?, ?, \n" +
//            "      ?, ?, ?, \n" +
//            "      ?, ?, ?, \n" +
//            "      ?, ?, ?, \n" +
//            "      ?, ?, ?, \n" +
//            "      ?,?)";

//    static String sqlStr = "insert into sys_user_role\n" +
//            "\t\t(\n" +
//            "\t\t\t`user_id`, \n" +
//            "\t\t\t`role_id`\n" +
//            "\t\t)\n" +
//            "\t\tvalues\n" +
//            "\t\t  \n" +
//            "\t\t(\n" +
//            "\t\t\t?, \n" +
//            "\t\t\t?\n" +
//            "\t\t)";

    public static void main(String args[]){
//        try {
//            String sqlStr = JacksonUtils.readRawJsonFile("javamodule/raw/sql.txt");
            System.out.println(sqlStr);
            System.out.println("===================================================");
            sqlStr=sqlStr.replaceAll("\\s+"," ");
            System.out.println(sqlStr);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
