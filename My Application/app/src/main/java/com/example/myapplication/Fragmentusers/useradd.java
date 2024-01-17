package com.example.myapplication.Fragmentusers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.example.myapplication.Bean.User;
import com.example.myapplication.Main.fomentusers;
import com.example.myapplication.R;
import com.example.myapplication.SQList.userSQ;

public class useradd extends Fragment {


    private EditText usernameE;
    private EditText passwordE;
    private EditText confirE;
    private RadioGroup sexR;
    private Button registerB;

    private Button cancelB;

    private userSQ fq;


    public useradd() {
        // Required empty public constructor
    }

    public useradd(Context context) {
        fq = new userSQ(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_useradd, container, false);
        usernameE = view.findViewById(R.id.username);
        passwordE = view.findViewById(R.id.password);
        confirE = view.findViewById(R.id.confirm_password);
        sexR = view.findViewById(R.id.sex_button);
        registerB = view.findViewById(R.id.confirm_button);
        cancelB = view.findViewById(R.id.cancel_button);

        registerB.setOnClickListener(v -> registerActivity());
        cancelB.setOnClickListener(v -> cancelActivity());
        return view;
    }

    private void cancelActivity() {
        new AlertDialog.Builder(requireActivity())
                .setMessage("确定要取消注册吗？")
                .setPositiveButton("取消", null)
                .setNegativeButton("确定", (dialog, which) -> {
                    //关闭注册页面，并返回登录页面
                    Intent intent = new Intent(getContext(), fomentusers.class);
                    startActivity(intent);
                    requireActivity().finish();
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
            Toast.makeText(getContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
            usernameE.requestFocus();
            return;
        }
        //检查密码是否为空
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
            passwordE.requestFocus();
            return;
        }
        //检查确认密码是否为空
        if (TextUtils.isEmpty(confirm)) {
            Toast.makeText(getContext(), "确认密码不能为空", Toast.LENGTH_SHORT).show();
            confirE.requestFocus();
            return;
        }
        //检查密码和确认密码是否匹配
        if (!password.equals(confirm)) {
            Toast.makeText(getContext(), "密码不匹配", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getContext(), "性别不能为空", Toast.LENGTH_SHORT).show();
            sexR.requestFocus();
            return;
        }
        // 注册用户
        User user = new User(username, password, sex);
        // TODO: 将用户写入数据库或其他持久存储
        if (!save(user)) {
            return;
        }
        usernameE.setText(null);
        passwordE.setText(null);
        confirE.setText(null);
        sexR.setId(-1);
        //显示成功消息
        Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
    }

    private boolean save(User user) {
        if (!fq.containsByName(user.getUsername())) {
            fq.insertUser(user);
            return true;
        } else {
            //显示成功消息
            Toast.makeText(getContext(), "用户名已存在", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}