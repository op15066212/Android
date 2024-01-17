package com.example.myapplication.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Bean.User;
import com.example.myapplication.R;
import com.example.myapplication.SQList.userSQ;

public class register extends AppCompatActivity {
    private EditText usernameE;
    private EditText passwordE;
    private EditText confirE;
    private RadioGroup sexR;

    private userSQ fq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //获取视图
        usernameE = findViewById(R.id.username);
        passwordE = findViewById(R.id.password);
        confirE = findViewById(R.id.confirm_password);
        sexR = findViewById(R.id.sex_button);
        Button registerB = findViewById(R.id.confirm_button);
        Button cancelB = findViewById(R.id.cancel_button);
        fq = new userSQ(this);


        SharedPreferences sharedPreferences = getSharedPreferences("login_info", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();

        registerB.setOnClickListener(v -> registerActivity());
        cancelB.setOnClickListener(v -> cancelActivity());
    }

    private void cancelActivity() {
        //创建对话框，询问是否取消注册
        new AlertDialog.Builder(register.this)
                .setMessage("确定要取消注册吗？")
                .setPositiveButton("取消", null)
                .setNegativeButton("确定", (dialog, which) -> {
                    //关闭注册页面，并返回登录页面
                    Intent intent = new Intent(register.this, login.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    }


    private void registerActivity() {
        //重置错误
        usernameE.setError(null);
        passwordE.setError(null);
        confirE.setError(null);
        //获取输入的值
        String username = usernameE.getText().toString();
        String password = passwordE.getText().toString();
        String confirm = confirE.getText().toString();
        //检查用户名是否为空
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(register.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            usernameE.requestFocus();
            return;
        }
        //检查密码是否为空
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(register.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            passwordE.requestFocus();
            return;
        }
        //检查确认密码是否为空
        if (TextUtils.isEmpty(confirm)) {
            Toast.makeText(register.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            confirE.requestFocus();
            return;
        }
        //检查密码和确认密码是否匹配
        if (!password.equals(confirm)) {
            Toast.makeText(register.this, "密码不匹配", Toast.LENGTH_SHORT).show();
            confirE.requestFocus();
            return;
        }
        //获取选定的性别
        int sexRuttonId = sexR.getCheckedRadioButtonId();
        String sex = "";

        if (sexRuttonId == R.id.button1) {
            sex = "男";
        } else if (sexRuttonId == R.id.button2) {
            sex = "女";
        }

        if (sex.isEmpty()) {
            Toast.makeText(register.this, "性别不能为空", Toast.LENGTH_SHORT).show();
            sexR.requestFocus();
            return;
        }

        // 注册用户
        User user = new User(username, password, sex);
        // TODO: 将用户写入数据库或其他持久存储
        if (!save(user)) {
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("login_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();

        //显示成功消息
        Toast.makeText(register.this, "注册成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(register.this, login.class);
        startActivity(intent);
        //返回登录页面
        finish();
    }

    private boolean save(User user) {
        if (!fq.containsByName(user.getUsername())) {
            fq.insertUser(user);
            return true;
        } else {
            //显示成功消息
            Toast.makeText(register.this, "用户名已存在", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
