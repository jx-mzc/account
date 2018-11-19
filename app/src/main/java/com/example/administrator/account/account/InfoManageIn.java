package com.example.administrator.account.account;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.account.R;
import com.example.administrator.account.dao.InaccountDAO;
import com.example.administrator.account.model.Tb_inaccount;

public class InfoManageIn extends AppCompatActivity {

    TextView tvtitle, textView;// 创建两个TextView对象
    EditText txtMoney, txtTime, txtHA, txtMark;// 创建4个EditText对象
    Spinner spType;// 创建Spinner对象
    Button btnEdit, btnDel,btnCancel;// 创建两个Button对象
    String[] strInfos;// 定义字符串数组
    String id, strType;// 定义两个字符串变量，分别用来记录信息编号和管理类型

    private int mYear;// 年
    private int mMonth;// 月
    private int mDay;// 日

    InaccountDAO iad=new InaccountDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomanage);// 设置布局文件

        tvtitle = (TextView) findViewById(R.id.inouttitle);// 获取标题标签对象
        textView = (TextView) findViewById(R.id.tvInOut);// 获取地点显示标签
        txtMoney = (EditText) findViewById(R.id.txtInOutMoney);// 获取金额文本框
        txtTime = (EditText) findViewById(R.id.txtInOutTime);// 获取时间文本框
        spType = (Spinner) findViewById(R.id.spInOutType);// 获取类别下拉列表
        txtMark = (EditText) findViewById(R.id.txtInOutMark);// 获取备注文本框
        btnEdit = (Button) findViewById(R.id.btnInOutEdit);// 获取修改按钮
        btnDel = (Button) findViewById(R.id.btnInOutDelete);// 获取删除按钮
        btnCancel = (Button)findViewById(R.id.btnInCancel);//获取返回按钮
        txtHA = (EditText) findViewById(R.id.txtInOut);// 获取地点


        //获取传值的ID
        Intent intent=getIntent();
        id = intent.getStringExtra("id");// 获取传入的数据，并使用Bundle记录


        //Log.i("id=   ", id);
        //通过ID获取这条记录详细信息


        Tb_inaccount tb_inaccount = iad.query_data(new Tb_inaccount(Integer.parseInt(id),0,null,null,null,null));

        tvtitle.setText("收入详细信息");

        textView.setText("付款方：");// 设置“地点/付款方”标签文本为“地 点：”


        txtMoney.setText(String.valueOf(tb_inaccount.getMoney()));// 显示金额
        txtTime.setText(tb_inaccount.getTime());// 显示时间
        spType.setPrompt(tb_inaccount.getType());// 显示类别
        txtHA.setText(tb_inaccount.getHandler());// 显示付款方
        txtMark.setText(tb_inaccount.getMark());// 显示备注

        btnEdit = (Button) findViewById(R.id.btnInOutEdit);// 获取修改按钮
        btnDel = (Button) findViewById(R.id.btnInOutDelete);// 获取删除按钮

        //-------------------------------------修改操作


        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                InaccountDAO ido=new InaccountDAO();

                Tb_inaccount tb_inaccount =new Tb_inaccount();
                tb_inaccount.setHandler(txtHA.getText().toString());
                tb_inaccount.setMark(txtMark.getText().toString());
                tb_inaccount.setMoney(Double.valueOf(txtMoney.getText().toString()));
                tb_inaccount.set_id(Integer.parseInt(id));// 设置编号
                tb_inaccount.setTime(txtTime.getText().toString());// 设置时间
                tb_inaccount.setType(spType.getSelectedItem().toString());
                ido.update_data(tb_inaccount);

                Toast.makeText(InfoManageIn.this, "修改成功", Toast.LENGTH_SHORT).show();

            }
        });

        //----------------------------删除操作------------
        btnDel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                InaccountDAO ido=new InaccountDAO();

                ido.detele_data(new Tb_inaccount(Integer.parseInt(id),0,null,null,null,null));

                // 弹出信息提示
                Toast.makeText(InfoManageIn.this, "〖数据〗删除成功！", Toast.LENGTH_SHORT)
                        .show();
                Intent intent=new Intent(InfoManageIn.this,InaccountInfo.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }//oncreate


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
