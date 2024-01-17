package com.example.myapplication.Main.Test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Main.Other.users;
import com.example.myapplication.Main.login;
import com.example.myapplication.R;

public class my extends AppCompatActivity {

    private Button outB;

    private TextView nick;

    private RelativeLayout shezhi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        outB = findViewById(R.id.logout_button);
        nick = findViewById(R.id.nickname);
        shezhi = findViewById(R.id.shezhi);


        outB.setOnClickListener(v -> out());

        shezhi.setOnClickListener(v -> set());
    }

    private void set() {
        Intent intent = new Intent(my.this, users.class);
        startActivity(intent);
        finish();
    }

    private void out() {
        //创建对话框，询问是否取消注册
        new AlertDialog.Builder(my.this)
                .setMessage("确定要退出登录吗？")
                .setPositiveButton("取消", null)
                .setNegativeButton("确定", (dialog, which) -> {
                    //返回登录页面
//                    SharedPreferences sharedPreferences = getSharedPreferences("login_info", Context.MODE_PRIVATE);
//                    sharedPreferences.edit().clear().apply();
                    Intent intent = new Intent(my.this, login.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    }
}