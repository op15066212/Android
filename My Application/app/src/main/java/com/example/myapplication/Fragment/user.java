package com.example.myapplication.Fragment;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.myapplication.Main.fomentusers;
import com.example.myapplication.Main.login;
import com.example.myapplication.R;

import static com.example.myapplication.Main.login.Lname;


public class user extends Fragment {


    private Button logoutButton; // 退出登录按钮

    private TextView nick;
    private RelativeLayout shezhi;

    public user() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        // 初始化退出登录按钮
        logoutButton = view.findViewById(R.id.logout_button);
        nick = view.findViewById(R.id.nickname);
        shezhi = view.findViewById(R.id.shezhi);
        if (Lname != null) {
            nick.setText(Lname);
        }
        logoutButton.setOnClickListener(view1 -> showLogoutDialog());
        shezhi.setOnClickListener(v -> set());
        return view;
    }

    /**
     * 显示退出登录对话框
     */
    private void showLogoutDialog() {
        new AlertDialog.Builder(getContext())
                .setMessage("确定要退出登录吗？")
                .setPositiveButton("取消", null)
                .setNegativeButton("确定", (dialog, which) -> {
                    //返回登录页面
//                    if (getActivity() != null) {
//                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login_info", Context.MODE_PRIVATE);
//                        sharedPreferences.edit().clear().apply();
//                    }
                    Intent intent = new Intent(getContext(), login.class);
                    startActivity(intent);
                    requireActivity().finish();
                })
                .show();
    }

    private void set() {
        Intent intent = new Intent(getContext(), fomentusers.class);
        startActivity(intent);
        requireActivity().finish();
    }
}
