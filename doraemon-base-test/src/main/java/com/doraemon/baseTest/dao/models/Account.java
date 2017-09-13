package com.doraemon.baseTest.dao.models;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by sloan on 2017/5/27.
 */
@Data
@Table(name = "account")
public class Account {

    @Id
    private Long    id;
    private String  name;
    private String  account;
    private String  email;
    private String  passwd;
    private String  department;
    private Long  companyId;
    private String companyName;
    private String  phone;
    private Integer type;
    private Date    createTime;
    private Date    updateTime;
    private Integer enable;

    public static boolean isValidEmail(String email) {
        if (null == email) {
            return false;
        }
        return email.matches("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}");
    }

    public static boolean isVaildPassword(String passwd)
    {
        if(null == passwd){
            return false;
        }

        return (!passwd.matches("[0-9]{7,16}+"))
                &&passwd.length()>7&&passwd.length()<16;
    }



    public static boolean isValidAccount(String account) {
        if (null == account) {
            return false;
        }
        System.out.println("判斷結果："+account.matches("\\^[a-zA-Z][a-zA-Z0-9!@#$%^&*()_]{5,16}$"));
        return account.matches("\\^[a-zA-Z][a-zA-Z0-9!@#$%^&*()_]{5,16}$");
    }


}
