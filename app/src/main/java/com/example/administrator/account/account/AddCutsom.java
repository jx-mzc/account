package com.example.administrator.account.account;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.account.R;
import com.example.administrator.account.dao.CustomDAO;
import com.example.administrator.account.model.Tb_outaccount;

public class AddCutsom extends AppCompatActivity {

    EditText txtMoney,txtTime,txtAddress,txtMark;
    Spinner spinnerlb;
    Button btnSave;
    Button btncancel;
    private int mYear;// 年
    private int mMonth;// 月
    private int mDay;// 日

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcustom);// 设置布局文件

        //主要实现思路
        //1、先获取界面控件
        //2、再获取控件的值
        //3、调用添加方法
        //4、添加成功

        txtMoney=(EditText)findViewById(R.id.txtMoney);
        txtTime=(EditText)findViewById(R.id.txtTime);
        spinnerlb=(Spinner)findViewById(R.id.spType);
        txtAddress=(EditText)findViewById(R.id.txtAddress);
        txtMark=(EditText)findViewById(R.id.txtMark);

        btncancel=(Button)findViewById(R.id.btnCancel);
        btnSave=(Button)findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String strMoney = txtMoney.getText().toString();// 获取金额文本框的值
                if (!strMoney.equals("")) {// 判断金额不为空

                    //创建数据库连接
                    CustomDAO odao=new CustomDAO();
                    // 创建Tb_outaccount对象
                    Tb_outaccount tb_outaccount = new Tb_outaccount(
                            odao.getMaxId() + 1, Double.parseDouble(strMoney), txtTime.getText().toString(), spinnerlb.getSelectedItem().toString(),
                            txtAddress.getText().toString(), txtMark.getText().toString());

                    odao.add(tb_outaccount);

                    // 弹出信息提示
                    Toast.makeText(AddCutsom.this, "〖消费记录〗数据添加成功！",
                            Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(AddCutsom.this, "请输入金额", Toast.LENGTH_SHORT).show();
                }

            }  //onclick

        });//btn

        btncancel.setOnClickListener(new View.OnClickListener() {
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
