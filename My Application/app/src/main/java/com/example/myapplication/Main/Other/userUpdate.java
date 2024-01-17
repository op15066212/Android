package com.example.myapplication.Main.Other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Bean.User;
import com.example.myapplication.Main.fomentusers;
import com.example.myapplication.R;
import com.example.myapplication.SQList.userSQ;

public class userUpdate extends AppCompatActivity {
    public static User user = new User("1", "1", "男");
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioGroup radioGroup;
    private EditText usernameE;
    private EditText passwordE;
    private RadioGroup sexR;
    private Button recoverB;
    private Button cancelB;
    private userSQ fq;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userupdate);

        //获取视图
        usernameE = findViewById(R.id.username9);
        passwordE = findViewById(R.id.password9);
        recoverB = findViewById(R.id.confirm_button9);
        cancelB = findViewById(R.id.cancel_button9);
        sexR = findViewById(R.id.sex_button);
        radioButton1 = findViewById(R.id.button1);
        radioButton2 = findViewById(R.id.button2);
        radioGroup = findViewById(R.id.sex_button);
        fq = new userSQ(this);

        SharedPreferences sharedPreferences = getSharedPreferences("login_info", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();

        usernameE.setText(user.getUsername());
        passwordE.setText(user.getPassword());


        String sex = user.getSex();
        int sexRuttonId = R.id.button1;

        if (sex != null && sex.equals("女")) {
            sexRuttonId = R.id.button2;
        }

        radioGroup.check(sexRuttonId);

        radioGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                if (radioButton.getId() != checkedId) {
                    radioButton.setChecked(false);
                }
            }
        });

        recoverB.setOnClickListener(v -> recoverPasswordActivity());
        cancelB.setOnClickListener(v -> cancelActivity());
    }

    private void cancelActivity() {
        //创建对话框，询问是否取消重置密码
        new AlertDialog.Builder(userUpdate.this)
                .setMessage("确定要取消重置密码吗？")
                .setPositiveButton("取消", null)
                .setNegativeButton("确定", (dialog, which) -> {
                    //关闭重置密码页面，并返回登录页面
                    Intent intent = new Intent(userUpdate.this, fomentusers.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    }


    private void recoverPasswordActivity() {
        //重置错误
        usernameE.setError(null);
        passwordE.setError(null);
        //获取输入的值
        String username = usernameE.getText().toString();
        String password = passwordE.getText().toString();
        //检查用户名是否为空
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(userUpdate.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            usernameE.requestFocus();
            return;
        }
        //检查密码是否为空
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(userUpdate.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            passwordE.requestFocus();
            return;
        }


        int sexRuttonId = sexR.getCheckedRadioButtonId();
        String sex = "";
        if (sexRuttonId == R.id.button1) {
            sex = "男";
        } else if (sexRuttonId == R.id.button2) {
            sex = "女";
        }

        if (sex.isEmpty()) {
            Toast.makeText(userUpdate.this, "性别不能为空", Toast.LENGTH_SHORT).show();
            sexR.requestFocus();
            return;
        }

        // 重置密码用户
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
        Toast.makeText(userUpdate.this, "重置密码成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(userUpdate.this, fomentusers.class);
        startActivity(intent);
        //返回登录页面
        finish();
    }

    private boolean save(User user) {
        if (fq.containsByName(user.getUsername())) {
            user.setId(fq.getUserByName(user.getUsername()).getId());
            fq.updateUser(user);
            return true;
        } else {
            //显示成功消息
            Toast.makeText(userUpdate.this, "用户名不存在", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}