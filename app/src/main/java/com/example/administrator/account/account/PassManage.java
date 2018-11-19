package com.example.administrator.account.account;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.account.R;
import com.example.administrator.account.dao.CustomDAO;
import com.example.administrator.account.model.Tb_outaccount;

import java.util.List;

public class PassManage extends AppCompatActivity {

    ListView lv; //准备用于接收显示消费记录
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custominfo);

        lv=(ListView)findViewById(R.id.listView1);

        showcustomerinfo();

        lv.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int arg2,
                                    long arg3) {
                String stracc= String.valueOf(((TextView) view).getText());
                String strid=stracc.substring(0,stracc.indexOf('|'));
                Intent intent=new Intent(PassManage.this, DetailCustomManage.class);
                intent.putExtra("id", strid);
                startActivity(intent);// 执行Intent操作

            }

        } );



    }

    //查询支出信息
    private void showcustomerinfo(){

        ArrayAdapter<String> adapter=null;
        String[] strInfos = null;// 定义字符串数组，用来存储支出信息
        List<Tb_outaccount> list_outaccont=null;

        //先创建消费信息DAO层数据
        CustomDAO occ=new CustomDAO();
        list_outaccont=occ.getMutilCustomData(0, 20);
        int i = 0;// 定义一个开始标识
        strInfos = new String[list_outaccont.size()];// 设置字符串数组的长度

        //循环遍历
        for(Tb_outaccount t:list_outaccont){
            strInfos[i]= t.getid() + "|"+"消费者：" + t.getType()
                    + " " + String.valueOf(t.getMoney()) + "元     "
                    + t.getTime();
            i++;
        }

        //adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list_outaccont);

        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,strInfos);
        lv.setAdapter(adapter);

    }


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
