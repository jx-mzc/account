package com.example.administrator.account.model;

import org.litepal.crud.LitePalSupport;

public class Tb_flag extends LitePalSupport {
    private int _id;// 存储便签编号
    private String flag;// 存储便签信息

    public Tb_flag(){

        super();
    }

    public Tb_flag(int id,String flag){
        super();
        this._id=id;
        this.flag=flag;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
