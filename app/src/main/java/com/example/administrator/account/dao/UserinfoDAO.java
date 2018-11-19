package com.example.administrator.account.dao;

import org.litepal.LitePal;

import com.example.administrator.account.model.Tb_userinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Userinfo dao.
 */
public class UserinfoDAO {

    private Tb_userinfo tbUserinfo = new Tb_userinfo();

    /**
     * Instantiates a new Userinfo dao.
     */
    public UserinfoDAO(){
        LitePal.getDatabase();
    }

    /**
     * 注册用户信息
     *
     * @param tb_userinfo the tb userinfo
     */
    public void add_data(Tb_userinfo tb_userinfo) {
        // 执行添加收入信息操作

        tbUserinfo.setUsername(tb_userinfo.getUsername());
        tbUserinfo.setSex(tb_userinfo.getSex());
        tbUserinfo.setUserpwd(tb_userinfo.getUserpwd());
        tbUserinfo.setSdept(tb_userinfo.getSdept());
        tbUserinfo.save();
    }

    /**
     * Update data.
     *
     * @param tb_userinfo the tb userinfo
     */
    public void update_data(Tb_userinfo tb_userinfo){
        tbUserinfo.setUserpwd(tb_userinfo.getUserpwd());
        tbUserinfo.updateAll("username like ?",tb_userinfo.getUsername());
    }

    /**
     * Delete data.
     *
     * @param tb_userinfo the tb userinfo
     */
    public void delete_data(Tb_userinfo tb_userinfo){
        LitePal.deleteAll(Tb_userinfo.class,"username like ?",tb_userinfo.getUsername());
    }

    /**
     * Query data list.
     *
     * @param tb_userinfo the tb userinfo
     * @return the list
     */
    public List<Tb_userinfo> query_data(Tb_userinfo tb_userinfo){
        List<Tb_userinfo> tb_userinfoList = new ArrayList<Tb_userinfo>();
        tb_userinfoList = LitePal.where("username like ? or userpwd like ?",
                tb_userinfo.getUsername(),tb_userinfo.getUserpwd()).find(Tb_userinfo.class);
        return tb_userinfoList;
    }

    /**
     * Match pwd boolean.
     *
     * @param tb_userinfo the tb userinfo
     * @return the boolean
     */
    public boolean match_pwd(Tb_userinfo tb_userinfo){
        List<Tb_userinfo> tb_userinfoList = new ArrayList<Tb_userinfo>();
        tb_userinfoList = LitePal.where("username like ? and userpwd like ?",
                tb_userinfo.getUsername(),tb_userinfo.getUserpwd()).find(Tb_userinfo.class);
        if (tb_userinfoList.isEmpty()){
            return false;
        }
        return true;
    }
}
