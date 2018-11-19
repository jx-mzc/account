package com.example.administrator.account.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.account.R;
import com.example.administrator.account.dao.UserinfoDAO;
import com.example.administrator.account.model.Tb_userinfo;

public class LoginActivity extends AppCompatActivity {

    private EditText etusername;
    private EditText etpwd;
    private Button btnlogin;
    private Button btnregister;
    private Button btncancel;
    private CheckBox rememberPass;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        btncancel=(Button)findViewById(R.id.btncancel);
        etusername =(EditText)findViewById(R.id.etusername);
        etpwd =(EditText)findViewById(R.id.etpassword);
        btnregister=(Button)findViewById(R.id.btnregister);
        preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        rememberPass = (CheckBox)findViewById(R.id.remember_pass);
        //实现记住密码功能
        boolean isRemember = preferences.getBoolean("remember_password",false);
        if (isRemember){
            String account = preferences.getString("account","");
            String password = preferences.getString("password","");
            etusername.setText(account);
            etpwd.setText(password);
            rememberPass.setChecked(true);
        }

        /**
         * 登录
         */
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取账户
                String username = etusername.getText().toString();
                //获取密码
                String pwd=etpwd.getText().toString();
                if (username.equals("")){  //用户名不为空
                    etusername.setFocusable(true);
                    Toast.makeText(LoginActivity.this,"请输入登录账户！",Toast.LENGTH_SHORT).show();
                }else if (pwd.equals("")){//密码不为空
                    etpwd.setFocusable(true);
                    Toast.makeText(LoginActivity.this,"请输入登录密码！",Toast.LENGTH_SHORT).show();
                }else {
                    //创建数据库连接
                    UserinfoDAO udao=new UserinfoDAO();
                    Tb_userinfo tb_userinfo = new Tb_userinfo(username,pwd,null,null);

                    if (udao.query_data(tb_userinfo).isEmpty()){//如果用户名不存在
                        etusername.setText("");
                        etpwd.setText("");
                        Toast.makeText(LoginActivity.this,"该账户不存在！",Toast.LENGTH_SHORT).show();
                    }else if (udao.match_pwd(tb_userinfo)){//用户名和密码都正确
                        editor = preferences.edit();
                        if (rememberPass.isChecked()){
                            editor.putBoolean("remember_password",true);
                            editor.putString("account",username);
                            editor.putString("password",pwd);
                        }else {
                            editor.clear();
                        }
                        editor.apply();
                        //创建一个意图
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else {//密码错误
                        etpwd.setText("");
                        Toast.makeText(LoginActivity.this, "密码错误!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /**
         * 注册
         */
        btnregister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //创建一个意图
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String returnData = data.getStringExtra("alert");
                    Toast.makeText(LoginActivity.this,returnData,Toast.LENGTH_LONG).show();
                }
        }
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
