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
import com.example.administrator.account.dao.OutaccountDAO;
import com.example.administrator.account.model.Tb_outaccount;

public class AddOutaccount extends AppCompatActivity {

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
        setContentView(R.layout.addoutaccount);// 设置布局文件
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

                    //1连接数据库
                    //2定义好插入SQL语句
                    //3	执行db.execSQL
                    //4。关闭数据库连接
				/*
					db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
					// 执行添加收入信息操作
					db.execSQL(
							"insert into tb_inaccount (_id,money,time,type,handler,mark) values (?,?,?,?,?,?)",
							new Object[] { tb_inaccount.get_id(), tb_inaccount.getMoney(),
									txtInMoney.getText().toString(), tb_inaccount.getType(),
									tb_inaccount.getHandler(), tb_inaccount.getMark() });
	                  */




                    //创建数据库连接
                    OutaccountDAO odao=new OutaccountDAO();
                    // 创建Tb_outaccount对象
                    Tb_outaccount tb_outaccount = new Tb_outaccount(odao.getMaxId() + 1,
                            Double.parseDouble(strMoney), txtTime.getText().toString(), spinnerlb.getSelectedItem().toString(),
                            txtAddress.getText().toString(), txtMark.getText().toString());

                     odao.add_data(tb_outaccount);

                    // 弹出信息提示
                    Toast.makeText(AddOutaccount.this, "〖新增支出〗数据添加成功！",
                            Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(AddOutaccount.this, "请输入金额", Toast.LENGTH_SHORT).show();
                }

            }
        });///btnSave

        btncancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();


            }
        });//btncancel

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
