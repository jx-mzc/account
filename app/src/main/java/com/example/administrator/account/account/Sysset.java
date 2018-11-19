package com.example.administrator.account.account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.account.R;
import com.example.administrator.account.dao.PwdDao;
import com.example.administrator.account.model.Tb_pwd;

public class Sysset extends AppCompatActivity {

    EditText ETpwd=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sysset);

        ETpwd=(EditText)findViewById(R.id.txtPwd);

        Button btnOK=(Button)findViewById(R.id.btnSet);
        Button btnCancel=(Button)findViewById(R.id.btnsetCancel);

        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String pwd=ETpwd.getText().toString();
                PwdDao pwddao = new PwdDao();// 创建PwdDAO对象

                Tb_pwd tb_pwd=new Tb_pwd(pwd);

                if(pwddao.count()==0)
                {
                    pwddao.addPwd(tb_pwd);

                }else{
                    pwddao.updatePwd(tb_pwd);
                }
                Toast.makeText(Sysset.this, "〖密码〗设置成功！", Toast.LENGTH_SHORT).show();
            }
        });  //btnOK

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });//btnCancel
    }
}
