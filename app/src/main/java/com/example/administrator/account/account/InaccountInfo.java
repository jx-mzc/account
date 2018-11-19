package com.example.administrator.account.account;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.account.R;
import com.example.administrator.account.dao.InaccountDAO;
import com.example.administrator.account.model.Tb_inaccount;

import java.util.List;

public class InaccountInfo extends AppCompatActivity {

    ListView lvinfo;// 创建ListView对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inaccountinfo);// 设置布局文件
        lvinfo=(ListView)findViewById(R.id.lvinaccountinfo);

        showinfo();

        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int arg2,
                                    long arg3) {

                String strInfo = String.valueOf(((TextView) view).getText());// 记录收入信息
                String strid = strInfo.substring(0, strInfo.indexOf(' '));// 从收入信息中截取收入编号
                Intent intent = new Intent(InaccountInfo.this, InfoManage.class);// 创建Intent对象
                intent.putExtra("id", strid);
                startActivity(intent);// 执行Intent操作


            }

        });//itemclick



    }//oncreate

    public void showinfo(){
        String[] strInfos = null;// 定义字符串数组，用来存储收入信息
        //创建收入DAO对象
        InaccountDAO ido=new InaccountDAO();
        //返回一个list
        List<Tb_inaccount> tb_inaccountlist = ido.getScrollData(0, ido.getMaxId());

        strInfos=new String[tb_inaccountlist.size()];

        int m=0;
        for(Tb_inaccount tb_inaccount:tb_inaccountlist){

            // 将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
            strInfos[m] = tb_inaccount.get_id() + "  |  " + tb_inaccount.getType()
                    + " " + String.valueOf(tb_inaccount.getMoney()) + "元     "
                    + tb_inaccount.getTime();

            m++;
        }

        ArrayAdapter adapter=new ArrayAdapter(InaccountInfo.this,android.R.layout.simple_list_item_1, strInfos);

        lvinfo.setAdapter(adapter);


    }//showinfo


    @Override
    protected void onResume() {
        /**
         * 设置为竖屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }
}
