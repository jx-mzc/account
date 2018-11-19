package com.example.administrator.account.account;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.account.R;
import com.example.administrator.account.dao.CustomDAO;
import com.example.administrator.account.model.Tb_outaccount;

public class DetailCustomManage extends AppCompatActivity {

    TextView tvtitle, textView;// 创建两个TextView对象
    EditText txtMoney, txtTime, txtHA, txtMark;// 创建4个EditText对象
    Spinner spType;// 创建Spinner对象
    Button btnEdit, btnDel;// 创建两个Button对象
    String[] strInfos;// 定义字符串数组
    String id, strType;// 定义两个字符串变量，分别用来记录信息编号和管理类型

    private int mYear;// 年
    private int mMonth;// 月
    private int mDay;// 日

    //消费记录数据访问层对象
    CustomDAO cdao=new CustomDAO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailcustom);


        tvtitle = (TextView) findViewById(R.id.inouttitle);// 获取标题标签对象
        textView = (TextView) findViewById(R.id.tvInOut);// 获取地点显示标签
        txtMoney = (EditText) findViewById(R.id.txtInOutMoney);// 获取金额文本框
        txtTime = (EditText) findViewById(R.id.txtInOutTime);// 获取时间文本框
        spType = (Spinner) findViewById(R.id.spInOutType);// 获取类别下拉列表
        txtMark = (EditText) findViewById(R.id.txtInOutMark);// 获取备注文本框
        btnEdit = (Button) findViewById(R.id.btnInOutEdit);// 获取修改按钮
        btnDel = (Button) findViewById(R.id.btnInOutDelete);// 获取删除按钮
        txtHA = (EditText) findViewById(R.id.txtInOut);// 获取地点
        //修改按扭
        //	Button updatebtn=(Button)findViewById(R.id.btnInOutEdit);

        //删除 按扭
        //Button deletebtn=(Button)findViewById(R.id.btnInOutDelete);


        //获取ID值
        Intent intent=getIntent();
        id = intent.getStringExtra("id");// 获取传入的数据，并使用Bundle记录

        Tb_outaccount tb_outaccount = cdao.Singlefind(Integer.parseInt(id));

        tvtitle.setText("消费详细信息");

        textView.setText("地  点：");// 设置“地点/付款方”标签文本为“地 点：”
        txtMoney.setText(String.valueOf(tb_outaccount.getMoney()));// 显示金额
        txtTime.setText(tb_outaccount.getTime());// 显示时间
        spType.setPrompt(tb_outaccount.getType());// 显示类别
        txtHA.setText(tb_outaccount.getAddress());// 显示地点
        txtMark.setText(tb_outaccount.getMark());// 显示备注

        btnEdit = (Button) findViewById(R.id.btnInOutEdit);// 获取修改按钮
        btnDel = (Button) findViewById(R.id.btnInOutDelete);// 获取删除按钮

        //修改操作
        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Tb_outaccount tb_outaccount=new Tb_outaccount();

                tb_outaccount.setAddress(txtHA.getText().toString());
                tb_outaccount.setid(Integer.parseInt(id));// 设置编号
                tb_outaccount.setMoney(Double.parseDouble(txtMoney
                        .getText().toString()));// 设置金额
                tb_outaccount.setTime(txtTime.getText().toString());// 设置时间
                tb_outaccount.setType(spType.getSelectedItem().toString());// 设置类别
                tb_outaccount.setAddress(txtHA.getText().toString());// 设置地点
                tb_outaccount.setMark(txtMark.getText().toString());// 设置备注

                cdao.update(tb_outaccount);
                // 弹出信息提示
                Toast.makeText(DetailCustomManage.this, "〖数据〗修改成功！", Toast.LENGTH_SHORT)
                        .show();



            }
        });  ///修改方法

        btnDel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                cdao.detele(Integer.parseInt(id));

                // 弹出信息提示
                Toast.makeText(DetailCustomManage.this, "〖数据〗删除成功！", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(DetailCustomManage.this,CumstomManage.class);

                startActivity(intent);

            }
        });




    }

    @Override
    protected void onResume() {
        /**
         * 设置为竖屏
         */
        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }
}
