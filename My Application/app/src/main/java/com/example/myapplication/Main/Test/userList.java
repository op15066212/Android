package com.example.myapplication.Main.Test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Adpter.UserplusAdapter;
import com.example.myapplication.Bean.User;
import com.example.myapplication.Main.Other.userUpdate;
import com.example.myapplication.R;
import com.example.myapplication.SQList.userSQ;

import java.util.ArrayList;
import java.util.List;

public class userList extends AppCompatActivity {
    private static List<User> mUsers = new ArrayList<>();
    private UserplusAdapter adapter;

    private userSQ fq;

    private TextView searchInput;

    private Button search;
    private Button clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        searchInput = findViewById(R.id.searchuser);
        search = findViewById(R.id.confirm_button9);
        clear = findViewById(R.id.clear);
        fq = new userSQ(this);

        mUsers = fq.getAllUsers();

        // 设置RecyclerView
        RecyclerView RecyclerView_user = findViewById(R.id.recycler_view_user);
        // 设置布局管理器，这里使用线性布局管理器
        RecyclerView_user.setLayoutManager(new LinearLayoutManager(this));
        // 创建适配器实例并设置给RecyclerView
        adapter = new UserplusAdapter(mUsers);
        RecyclerView_user.setAdapter(adapter);

        adapter.setOnItemClickListener(new UserplusAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User User) {

            }

            @Override
            public void onUpdateClick(int position, User user) {
                recover(user);
            }

            @Override
            public void onDeleteClick(int position, User user) {
                remove(user);
            }
        });

        search.setOnClickListener(v -> selectlike());

        clear.setOnClickListener(v -> clear());
    }


    @SuppressLint("NotifyDataSetChanged")
    public void clear() {
        searchInput.setText("");
        mUsers.clear(); // 先清空原有列表
        mUsers.addAll(fq.getAllUsers()); // 添加新的查询结果
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void selectlike() {
        String keyword = searchInput.getText().toString().trim();
        if (TextUtils.isEmpty(keyword)) {
            Toast.makeText(getApplicationContext(), "请输入关键字！", Toast.LENGTH_SHORT).show();
        } else {
            List<User> userList = fq.selectlike(keyword);
            if (userList.isEmpty()) {
                Toast.makeText(getApplicationContext(), "未查询到相关用户！", Toast.LENGTH_SHORT).show();
            } else {
                // 处理查询到的用户数据
                mUsers.clear(); // 先清空原有列表
                mUsers.addAll(userList); // 添加新的查询结果
                adapter.notifyDataSetChanged(); // 更新RecyclerView
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    public void remove(User User) {
        // 弹出一个对话框确认是否删除该项食品
        new AlertDialog.Builder(userList.this)
                .setMessage("确定要删除" + User.getUsername() + "吗？")
                .setNegativeButton("删除", (dialog, which) -> {
                    // 从列表中删除该项食品
                    mUsers.remove(User);
                    fq.deleteUser(User);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(userList.this, User.getUsername() + "已被删除", Toast.LENGTH_SHORT).show();
                })
                .setPositiveButton("取消", null)
                .show();
    }

    public void recover(User User) {
        Intent intent = new Intent(userList.this, userUpdate.class);
        userUpdate.user = User;
        startActivity(intent);
        //返回登录页面
        finish();
    }
}