package com.example.administrator.account.dao;

import android.database.Cursor;
import com.example.administrator.account.model.Tb_outaccount;
import org.litepal.LitePal;
import java.util.ArrayList;
import java.util.List;

public class OutaccountDAO {
    private Tb_outaccount tbOutaccount = new Tb_outaccount();

    public OutaccountDAO(){
        LitePal.getDatabase();
    }
    /**
     * 添加支出信息
     *
     * @param tb_outaccount
     */
    public void add_data(Tb_outaccount tb_outaccount) {
        tbOutaccount.setid(tb_outaccount.getid());
        tbOutaccount.setAddress(tb_outaccount.getAddress());
        tbOutaccount.setMark(tb_outaccount.getMark());
        tbOutaccount.setMoney(tb_outaccount.getMoney());
        tbOutaccount.setTime(tb_outaccount.getTime());
        tbOutaccount.setType(tb_outaccount.getType());
        tbOutaccount.save();
    }

    /**
     * 更新支出信息
     *
     * @param tb_outaccount
     */
    public void update_data(Tb_outaccount tb_outaccount) {
        tbOutaccount.setAddress(tb_outaccount.getAddress());
        tbOutaccount.setMark(tb_outaccount.getMark());
        tbOutaccount.setMoney(tb_outaccount.getMoney());
        tbOutaccount.setTime(tb_outaccount.getTime());
        tbOutaccount.setType(tb_outaccount.getType());
        tbOutaccount.updateAll("id = ?", String.valueOf(tb_outaccount.getid()));
    }
    /**
     * 查找支出信息
     *
     * @param tb_outaccount
     * @return
     */
    public Tb_outaccount query_data(Tb_outaccount tb_outaccount) {
        return  LitePal.find(Tb_outaccount.class,tb_outaccount.getid());
    }
    /**
     * 删除支出信息
     *
     * @param tb_outaccount
     */
    public void detele_data(Tb_outaccount tb_outaccount){
        LitePal.delete(Tb_outaccount.class,tb_outaccount.getid());
    }

    /**
     * 获取支出信息
     *
     * @param start
     *            起始位置
     * @param count
     *            每页显示数量
     * @return
     */
    public List<Tb_outaccount> getScrollData(int start, int count) {
        List<Tb_outaccount> tb_outaccount = new ArrayList<Tb_outaccount>();// 创建集合对象
        tb_outaccount = LitePal.limit(count).offset(start).find(Tb_outaccount.class);
        return tb_outaccount;
    }
    /**
     * 获取总记录数
     *
     * @return
     */
    public long getCount() {
//        Cursor cursor = LitePal.findBySQL("select count(id) from tb_outaccount");// 获取支出信息的记录数
//        if (cursor.moveToNext())// 判断Cursor中是否有数据
//        {
//            return cursor.getLong(0);// 返回总记录数
//        }
//        return 0;// 如果没有数据，则返回0
        return LitePal.count(Tb_outaccount.class);
    }
    /**
     * 获取支出最大编号
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
