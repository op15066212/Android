package com.example.myapplication.Fragmentusers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Adpter.UserplusAdapter;
import com.example.myapplication.Bean.User;
import com.example.myapplication.Main.Other.userUpdate;
import com.example.myapplication.R;
import com.example.myapplication.SQList.userSQ;

import java.util.ArrayList;
import java.util.List;


public class userlist extends Fragment {


    private static List<User> mUsers = new ArrayList<>();

    private UserplusAdapter adapter;

    private userSQ fq;

    private TextView searchInput;

    private Button search;
    private Button clear;

    public userlist() {
    }

    public userlist(Context context) {
        fq = new userSQ(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        searchInput = view.findViewById(R.id.searchuser);
        search = view.findViewById(R.id.confirm_button9);
        clear = view.findViewById(R.id.clear);
        mUsers = fq.getAllUsers();

        RecyclerView RecyclerView_user = view.findViewById(R.id.recycler_view_user);
        RecyclerView_user.setLayoutManager(new LinearLayoutManager(getContext()));
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
        return view;
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
            Toast.makeText(getContext(), "请输入关键字！", Toast.LENGTH_SHORT).show();
        } else {
            List<User> userList = fq.selectlike(keyword);
            if (userList.isEmpty()) {
                Toast.makeText(getContext(), "未查询到相关用户！", Toast.LENGTH_SHORT).show();
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
        new AlertDialog.Builder(requireActivity())
                .setMessage("确定要删除" + User.getUsername() + "吗？")
                .setNegativeButton("删除", (dialog, which) -> {
                    // 从列表中删除该项食品
                    mUsers.remove(User);
                    fq.deleteUser(User);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), User.getUsername() + "已被删除", Toast.LENGTH_SHORT).show();
                })
                .setPositiveButton("取消", null)
                .show();
    }

    public void recover(User User) {
        Intent intent = new Intent(getContext(), userUpdate.class);
        userUpdate.user = User;
        startActivity(intent);
        requireActivity().finish();
    }
}