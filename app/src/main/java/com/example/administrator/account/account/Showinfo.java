package com.example.administrator.account.account;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.account.R;
import com.example.administrator.account.dao.UserinfoDAO;
import com.example.administrator.account.model.Tb_userinfo;

public class Showinfo extends AppCompatActivity {

    private TextView TvUsername;
    private TextView sex ;
    private TextView sdept ;
    private Button cancel;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showinfo);
        TvUsername = (TextView)findViewById(R.id.username);
        cancel = (Button)findViewById(R.id.infoCancel);
        sex = (TextView) findViewById(R.id.sex);
        //专业
        sdept = (TextView) findViewById(R.id.sdept);// 获取类别下拉列表
        preferences = PreferenceManager.getDefaultSharedPreferences(Showinfo.this);
        String account = preferences.getString("account","");
        TvUsername.setText(account);
        UserinfoDAO userinfoDAO = new UserinfoDAO();
        Tb_userinfo userinfo = userinfoDAO.query_data(new Tb_userinfo(account,"","","")).get(0);
        sex.setText(userinfo.getSex());
        sdept.setText(userinfo.getSdept());
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        }
}
