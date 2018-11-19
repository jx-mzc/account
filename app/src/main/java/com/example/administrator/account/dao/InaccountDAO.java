package com.example.administrator.account.dao;

import android.database.Cursor;

import com.example.administrator.account.model.Tb_inaccount;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class InaccountDAO {

    private Tb_inaccount tbInaccount = new Tb_inaccount();

    public InaccountDAO(){
        LitePal.getDatabase();
    }
    /**
     * 添加收入信息
     *
     * @param tb_inaccount
     */
    public void add_data(Tb_inaccount tb_inaccount) {
        tbInaccount.set_id(tb_inaccount.get_id());
        tbInaccount.setHandler(tb_inaccount.getHandler());
        tbInaccount.setMark(tb_inaccount.getMark());
        tbInaccount.setMoney(tb_inaccount.getMoney());
        tbInaccount.setTime(tb_inaccount.getTime());
        tbInaccount.setType(tb_inaccount.getType());
        tbInaccount.save();
    }

    /**
     * 更新收入信息
     *
     * @param tb_inaccount
     */
    public void update_data(Tb_inaccount tb_inaccount) {
        tbInaccount.setHandler(tb_inaccount.getHandler());
        tbInaccount.setMark(tb_inaccount.getMark());
        tbInaccount.setMoney(tb_inaccount.getMoney());
        tbInaccount.setTime(tb_inaccount.getTime());
        tbInaccount.setType(tb_inaccount.getType());
        tbInaccount.updateAll("id = ?", String.valueOf(tb_inaccount.get_id()));
    }
    /**
     * 查找收入信息
     *
     * @param tb_inaccount
     * @return
     */
    public Tb_inaccount query_data(Tb_inaccount tb_inaccount) {
        return  LitePal.find(Tb_inaccount.class,tb_inaccount.get_id());
    }
    /**
     * 删除收入信息
     *
     * @param tb_inaccount
     */
    public void detele_data(Tb_inaccount tb_inaccount){
        LitePal.delete(Tb_inaccount.class,tb_inaccount.get_id());
    }

    /**
     * 获取收入信息
     *
     * @param start
     *            起始位置
     * @param count
     *            每页显示数量
     * @return
     */
    public List<Tb_inaccount> getScrollData(int start, int count) {
        List<Tb_inaccount> tb_outaccount = new ArrayList<Tb_inaccount>();// 创建集合对象
        tb_outaccount = LitePal.limit(count).offset(start).find(Tb_inaccount.class);
        return tb_outaccount;
    }
    /**
     * 获取总记录数
     *
     * @return
     */
    public long getCount() {
//        Cursor cursor = LitePal.findBySQL("select count(id) from tb_inaccount");// 获取支出信息的记录数
//        if (cursor.moveToNext())// 判断Cursor中是否有数据
//        {
//            return cursor.getLong(0);// 返回总记录数
//        }
//        return 0;// 如果没有数据，则返回0
        return LitePal.count(Tb_inaccount.class);
    }
    /**
     * 获取收入最大编号
     *
     * @return
     */
    public int getMaxId() {
//        Cursor cursor = LitePal.findBySQL("select max(id) from tb_inaccount");// 获取支出信息表中的最大编号
//        while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
//            return cursor.getInt(0);// 获取访问到的数据，即最大编号
//        }
//        return 0;// 如果没有数据，则返回0
        return LitePal.max(Tb_inaccount.class,"id",int.class);
    }
}
