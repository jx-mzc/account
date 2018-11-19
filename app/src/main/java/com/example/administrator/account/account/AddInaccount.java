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
import com.example.administrator.account.dao.InaccountDAO;
import com.example.administrator.account.model.Tb_inaccount;

public class AddInaccount extends AppCompatActivity {

    EditText txtInMoney;
    EditText txtInTime;
    EditText txtInHandler;
    EditText txtInMark;
    Spinner spInType;
    Button btnInSave,btnInCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinaccount);// 设置布局文件
        //获取金额
        txtInMoney=(EditText)findViewById(R.id.txtInMoney);
        //获取时间
        txtInTime=(EditText)findViewById(R.id.txtInTime);

        txtInHandler = (EditText) findViewById(R.id.txtInHandler);// 获取付款方文本框
        txtInMark = (EditText) findViewById(R.id.txtInMark);// 获取备注文本框
        spInType = (Spinner) findViewById(R.id.spInType);// 获取类别下拉列表

        btnInSave=(Button)findViewById(R.id.btnInSave);

        btnInCancel=(Button)findViewById(R.id.btnInCancel);

        btnInSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
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
                //------------------------------------------------------

                InaccountDAO ido=new InaccountDAO();
                String inMoney=txtInMoney.getText().toString();
                if(!inMoney.equals("")){
                    Tb_inaccount tb_inaccount=new Tb_inaccount(
                            ido.getMaxId() + 1,
                            Double.parseDouble(inMoney),
                            txtInTime.getText().toString(),
                            spInType.getSelectedItem().toString(),
                            txtInHandler.getText().toString(),
                            txtInMark.getText().toString());

                    ido.add_data(tb_inaccount);
                    Toast.makeText(AddInaccount.this, "添加收入成功", Toast.LENGTH_LONG).show();
                }else
                {

                    Toast.makeText(AddInaccount.this, "收入金额不能为空", Toast.LENGTH_LONG).show();

                }


            }
        });//btnInSave



        btnInCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
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
