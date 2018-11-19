package com.example.administrator.account.account;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.account.R;
import com.example.administrator.account.dao.UserinfoDAO;
import com.example.administrator.account.model.Tb_userinfo;

public class RegisterActivity extends AppCompatActivity {

    private Button btnregister;
    private Button btncancel;
    private EditText usernameET;
    private EditText userpwdET;
    private RadioGroup radgroup;
    private RadioButton sexRB ;
    private Spinner sdeptSP ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        btnregister=(Button)findViewById(R.id.button1);
        btncancel=(Button)findViewById(R.id.button2);
        //用户名
        usernameET =(EditText)findViewById(R.id.editText1);
        //密码
        userpwdET=(EditText)findViewById(R.id.editText2);
        //性别
        radgroup= (RadioGroup) findViewById(R.id.radioGroup);
        for (int i = 0; i < radgroup.getChildCount(); i++) {
            RadioButton sexRb = (RadioButton) radgroup.getChildAt(i);
            if (sexRb.isChecked()) {
                sexRB = sexRb;
                break;
            }
        }

        //专业
        sdeptSP = (Spinner) findViewById(R.id.spinner1);// 获取类别下拉列表
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameET.getText().toString().equals("")){
                    usernameET.setFocusable(true);
                    Toast.makeText(RegisterActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                }else if(userpwdET.getText().toString().equals("")){
                    userpwdET.setFocusable(true);
                    Toast.makeText(RegisterActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else {
                    //创建数据库连接
                    UserinfoDAO udao=new UserinfoDAO();
                    // 创建tb_userinfo对象
                    Tb_userinfo tb_userinfo = new Tb_userinfo(usernameET.getText().toString(), userpwdET.getText().toString(), sexRB.getText().toString(), sdeptSP.getSelectedItem().toString());
                    udao.add_data(tb_userinfo);
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    intent.putExtra("alert","注册成功，请登录！");
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
