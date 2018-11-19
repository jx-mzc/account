package com.example.administrator.account.model;

import org.litepal.crud.LitePalSupport;

public class Tb_userinfo extends LitePalSupport {
    private String  username;	  //用户帐号
    private String userpwd ;    //用户密码
    private String  sex ;	     //用户性别
    private String sdept ;     //专业

    public Tb_userinfo(){
        super();
    }

    public Tb_userinfo(String username,String userpwd,String sex,String sdept){
        // super();
        this.username=username;
        this.userpwd=userpwd;
        this.sex=sex;
        this.sdept=sdept;

    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserpwd() {
        return userpwd;
    }
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSdept() {
        return sdept;
    }
    public void setSdept(String sdept) {
        this.sdept = sdept;
    }

}
