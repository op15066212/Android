package com.example.myapplication.Main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Bean.User;
import com.example.myapplication.R;
import com.example.myapplication.SQList.userSQ;

public class login extends AppCompatActivity {
    public static String Lname = null;
    private EditText usernameE, passwordE;
    private Button loginB;

    private userSQ fq;

    public static void change(Button button, boolean T) {
        if (T) {
            button.setBackgroundColor(Color.parseColor("#FF9800"));
        } else {
            button.setBackgroundColor(Color.parseColor("#6200EE"));
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPreferences = getSharedPreferences("login_info", Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");

        usernameE = findViewById(R.id.username);
        passwordE = findViewById(R.id.password);
        loginB = findViewById(R.id.login);
        fq = new userSQ(this);
        usernameE.setText(savedUsername);
        passwordE.setText(savedPassword);
        TextView registerT = findViewById(R.id.register);
        TextView recoverPasswordT = findViewById(R.id.recoverPassword);

        loginB.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // 用户按下按钮
                change(loginB, true);
                LoginActivity();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                // 用户松开按钮
                change(loginB, false);
            }
            // 返回 false 表示不拦截触摸事件
            return false;
        });


        registerT.setOnClickListener(view -> registerActivity());

        recoverPasswordT.setOnClickListener(view -> recoverActivity());
    }

    private void recoverActivity() {
        Intent intent = new Intent(login.this, recover.class);
        startActivity(intent);
        finish();
    }

    private void LoginActivity() {
        // 获取输入的用户名和密码
        String username = usernameE.getText().toString();
        String password = passwordE.getText().toString();
        Log.d("301", username);
        Log.d("301", password);
        // 判断用户名和密码是否为
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(login.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO: 在此处添加与服务器交互的代码，进行登录验证
        if (!read(username, password)) {
            Toast.makeText(login.this, "登录失败", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("login_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();

        // 登录成功，跳转到主界面
        Toast.makeText(login.this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(login.this, foment.class);
        startActivity(intent);
        // 关闭当前Activity
        finish();
    } 

    private void registerActivity() {
        Intent intent = new Intent(login.this, register.class);
        startActivity(intent);
        finish();
    }

    private boolean read(String username, String password) {
        User user = fq.getUserByName(username);
        if (user != null && user.getPassword().equals(password)) {
            Lname = username;
            return true;
        }
        return false;
    }
}