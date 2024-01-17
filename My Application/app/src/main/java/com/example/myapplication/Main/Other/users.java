package com.example.myapplication.Main.Other;

import android.annotation.SuppressLint;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Adpter.UserAdapter;
import com.example.myapplication.Bean.User;
import com.example.myapplication.Main.foment;
import com.example.myapplication.R;
import com.example.myapplication.SQList.userSQ;

import java.util.ArrayList;
import java.util.List;

public class users extends AppCompatActivity {
    private static List<User> mUsers = new ArrayList<>();
    private UserAdapter adapter;

    private userSQ fq;

    private EditText usernameE;
    private EditText passwordE;
    private RadioGroup sexR;
    private Button registerB;

    private Button cancelB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        fq = new userSQ(this);
        usernameE = findViewById(R.id.username);
        passwordE = findViewById(R.id.password);
        sexR = findViewById(R.id.sex_button);
        registerB = findViewById(R.id.confirm_button);
        cancelB = findViewById(R.id.cancel_button);


        mUsers = fq.getAllUsers();

        // 设置RecyclerView
        RecyclerView RecyclerView_user = findViewById(R.id.recycler_view_user);
        // 设置布局管理器，这里使用线性布局管理器
        RecyclerView_user.setLayoutManager(new LinearLayoutManager(this));
        // 创建适配器实例并设置给RecyclerView
        adapter = new UserAdapter(mUsers);
        RecyclerView_user.setAdapter(adapter);


        // 设置RecyclerView的点击事件
        adapter.setOnItemClickListener(User -> {
            // 当列表项被点击时执行该回调函数
            Toast.makeText(users.this, "长按删除" + User.getUsername(), Toast.LENGTH_SHORT).show();
        });

        adapter.setOnItemLongClickListener(this::remove);

        registerB.setOnClickListener(v -> registerActivity());
        cancelB.setOnClickListener(v -> cancelActivity());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void remove(User User) {
        // 弹出一个对话框确认是否删除该项食品
        new AlertDialog.Builder(users.this)
                .setMessage("确定要删除" + User.getUsername() + "吗？")
                .setNegativeButton("删除", (dialog, which) -> {
                    // 从列表中删除该项食品
                    mUsers.remove(User);
                    fq.deleteUser(User);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(users.this, User.getUsername() + "已被删除", Toast.LENGTH_SHORT).show();
                })
                .setPositiveButton("取消", null)
                .show();
    }

    private void cancelActivity() {
        //创建对话框，询问是否取消注册
        new AlertDialog.Builder(users.this)
                .setMessage("确定要取消注册吗？")
                .setPositiveButton("取消", null)
                .setNegativeButton("确定", (dialog, which) -> {
                    //关闭注册页面，并返回登录页面
                    Intent intent = new Intent(users.this, foment.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    }


    private void registerActivity() {
        //重置错误
        usernameE.setError(null);
        passwordE.setError(null);
        //获取输入的值
        String username = usernameE.getText().toString();
        String password = passwordE.getText().toString();
        //检查用户名是否为空
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(users.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            usernameE.requestFocus();
            return;
        }
        //检查密码是否为空
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(users.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            passwordE.requestFocus();
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
            Toast.makeText(users.this, "性别不能为空", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(users.this, "添加成功", Toast.LENGTH_SHORT).show();

        usernameE.setText(null);
        passwordE.setText(null);
        Intent intent = new Intent(users.this, users.class);
        startActivity(intent);
        finish();
    }

    private boolean save(User user) {
        if (!fq.containsByName(user.getUsername())) {
            fq.insertUser(user);
            mUsers = fq.getAllUsers();
            return true;
        } else {
            //显示成功消息
            Toast.makeText(users.this, "用户名已存在", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}