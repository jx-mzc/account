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
import com.example.administrator.account.dao.OutaccountDAO;
import com.example.administrator.account.model.Tb_outaccount;

import java.util.ArrayList;
import java.util.List;

public class OutaccountInfo extends AppCompatActivity {

    public static final String FLAG = "id";// 定义一个常量，用来作为请求码

    ListView lv=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outaccountinfo);// 设置布局文件
        lv=(ListView)findViewById(R.id.lvoutaccountinfo);

        ShowInfo();

        lv.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int arg2,
                                    long arg3) {
                String stracc= String.valueOf(((TextView) view).getText());
                String strid=stracc.substring(0,stracc.indexOf(' '));
                Intent intent=new Intent(OutaccountInfo.this, InfoManage.class);
                intent.putExtra("id", strid);
                startActivity(intent);// 执行Intent操作
            }

        } );

    }//oncreate

    //查询支出信息
    private void ShowInfo(){

        ArrayAdapter<String> adapter=null;
        String[] strInfos = null;// 定义字符串数组，用来存储支出信息
        List<Tb_outaccount> list_outaccont=new ArrayList<Tb_outaccount>();
        //先创建支出信息DAO层数据
        OutaccountDAO occ=new OutaccountDAO();
        list_outaccont=occ.getScrollData(0,(int)occ.getCount());
        int i = 0;// 定义一个开始标识
        strInfos = new String[list_outaccont.size()];// 设置字符串数组的长度

        //循环遍历
        for(Tb_outaccount t:list_outaccont){
            strInfos[i]= t.getid() + "  |  " + t.getType()
                    + "    " + String.valueOf(t.getMoney()) + "元     "
                    + t.getTime();
            i++;
        }

        //adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list_outaccont);

        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,strInfos);
        lv.setAdapter(adapter);

    }


    //-----------------------------------------
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
