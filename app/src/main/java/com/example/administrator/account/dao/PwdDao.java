package com.example.administrator.account.dao;

import android.database.Cursor;

import com.example.administrator.account.model.Tb_pwd;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class PwdDao {

    private Tb_pwd tbPwd = new Tb_pwd();

    public PwdDao(){
        LitePal.getDatabase();
    }
    /**添加密码*/
    public void addPwd(Tb_pwd tb_pwd){
        tbPwd.setPassword(tb_pwd.getPassword());
        tbPwd.save();
    }

    //修改密码
    public void updatePwd(Tb_pwd tb_pwd){
        tbPwd.setPassword(tb_pwd.getPassword());
        tbPwd.updateAll("");
    }

    //查询密码

    public List<Tb_pwd> find(){
        List<Tb_pwd> tb_pwds = new ArrayList<Tb_pwd>();
        tb_pwds = LitePal.findAll(Tb_pwd.class);
        return tb_pwds;
    }

    //统计
    public long count (){
        Cursor cursor=LitePal.findBySQL("select count(password) from Tb_pwd");
        if(cursor.moveToNext()){
            return cursor.getLong(0) ;

        }
        return 0;
    }
}
