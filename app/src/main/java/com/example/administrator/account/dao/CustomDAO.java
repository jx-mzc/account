package com.example.administrator.account.dao;

import android.database.Cursor;

import com.example.administrator.account.model.Tb_outaccount;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class CustomDAO {

    private Tb_outaccount tbOutaccount = new Tb_outaccount();

    public CustomDAO(){
        LitePal.getDatabase();
    }

    //查询返回多条记录方法
    public List<Tb_outaccount> getMutilCustomData(int start, int count) {
        List<Tb_outaccount> tb_outaccount = new ArrayList<Tb_outaccount>();// 创建集合对象
        tb_outaccount = LitePal.limit(start).offset(count).find(Tb_outaccount.class);
        return tb_outaccount;
    }

    //单个查询方法

    /* 查找支出信息
     *
     * @param id
     * @return
     */
    public  Tb_outaccount Singlefind(int id) {

        return  LitePal.find(Tb_outaccount.class,id);
    }
    /**
     * 添加信消费息
     *
     * @param tb_outaccount
     */
    public void add(Tb_outaccount tb_outaccount) {
        tbOutaccount.setid(tb_outaccount.getid());
        tbOutaccount.setAddress(tb_outaccount.getAddress());
        tbOutaccount.setMark(tb_outaccount.getMark());
        tbOutaccount.setMoney(tb_outaccount.getMoney());
        tbOutaccount.setTime(tb_outaccount.getTime());
        tbOutaccount.setType(tb_outaccount.getType());
        tbOutaccount.save();
    }
    /**
     * 更新消费信息
     *
     * @param tb_outaccount
     */
    public void update(Tb_outaccount tb_outaccount) {
        tbOutaccount.setAddress(tb_outaccount.getAddress());
        tbOutaccount.setMark(tb_outaccount.getMark());
        tbOutaccount.setMoney(tb_outaccount.getMoney());
        tbOutaccount.setTime(tb_outaccount.getTime());
        tbOutaccount.setType(tb_outaccount.getType());
        tbOutaccount.updateAll("id = ?", String.valueOf(tb_outaccount.getid()));
    }
    /**
     * 删除消费信息
     *
     * @param ids
     */
    public void detele(Integer... ids) {
        if (ids.length > 0)// 判断是否存在要删除的id
        {
            for (int i=0;i<ids.length;i++){
                LitePal.delete(Tb_outaccount.class, ids[i]);
            }
        }
    }
    /**
     * 获取消费最大编号
     *
     * @return
     */
    public int getMaxId() {
//        Cursor cursor = LitePal.findBySQL("select max(id) from tb_outaccount");// 获取支出信息表中的最大编号
//        while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
//            return cursor.getInt(0);// 获取访问到的数据，即最大编号
//        }
//        return 0;// 如果没有数据，则返回0
        return LitePal.max(Tb_outaccount.class,"id",int.class);
    }
}
