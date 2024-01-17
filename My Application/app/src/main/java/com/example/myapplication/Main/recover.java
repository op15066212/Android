package com.example.myapplication.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Bean.User;
import com.example.myapplication.R;
import com.example.myapplication.SQList.userSQ;

public class recover extends AppCompatActivity {
    private EditText usernameE;
    private EditText passwordE;
    private EditText confirE;

    private userSQ fq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);
        //获取视图
        usernameE = findViewById(R.id.username9);
        passwordE = findViewById(R.id.password9);
        confirE = findViewById(R.id.confirm_password9);
        Button recoverB = findViewById(R.id.confirm_button9);
        Button cancelB = findViewById(R.id.cancel_button9);
        fq = new userSQ(this);

        SharedPreferences sharedPreferences = getSharedPreferences("login_info", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();

        recoverB.setOnClickListener(v -> recoverPasswordActivity());
        cancelB.setOnClickListener(v -> cancelActivity());
    }

    private void cancelActivity() {
        //创建对话框，询问是否取消重置密码
        new AlertDialog.Builder(recover.this)
                .setMessage("确定要取消重置密码吗？")
                .setPositiveButton("取消", null)
                .setNegativeButton("确定", (dialog, which) -> {
                    //关闭重置密码页面，并返回登录页面
                    Intent intent = new Intent(recover.this, login.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    }


    private void recoverPasswordActivity() {
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
            Toast.makeText(recover.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            usernameE.requestFocus();
            return;
        }
        //检查密码是否为空
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(recover.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            passwordE.requestFocus();
            return;
        }
        //检查确认密码是否为空
        if (TextUtils.isEmpty(confirm)) {
            Toast.makeText(recover.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            confirE.requestFocus();
            return;
        }
        //检查密码和确认密码是否匹配
        if (!password.equals(confirm)) {
            Toast.makeText(recover.this, "密码不匹配", Toast.LENGTH_SHORT).show();
            confirE.requestFocus();
            return;
        }

        // 重置密码用户
        User user = new User(username, password, "");
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
        Toast.makeText(recover.this, "重置密码成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(recover.this, login.class);
        startActivity(intent);
        //返回登录页面
        finish();
    }

    private boolean save(User user) {
        if (fq.containsByName(user.getUsername())) {
            user.setSex(fq.getUserByName(user.getUsername()).getSex());
            user.setId(fq.getUserByName(user.getUsername()).getId());
            fq.updateUser(user);
            return true;
        } else {
            //显示成功消息
            Toast.makeText(recover.this, "用户名不存在", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}