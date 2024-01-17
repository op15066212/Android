package com.example.myapplication.Main;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.myapplication.Fragmentusers.useradd;
import com.example.myapplication.Fragmentusers.userlist;
import com.example.myapplication.R;

import java.util.concurrent.atomic.AtomicReference;

public class fomentusers extends AppCompatActivity {

    LinearLayout zhuye1;
    LinearLayout gouwuzhe1;
    ImageView imag1;
    ImageView imag2;

    TextView out;

    public static void change(ImageView imag, Boolean T) {
        if (T) {
            imag.setColorFilter(Color.parseColor("#FFA500"), PorterDuff.Mode.SRC_IN);
        } else {
            imag.setColorFilter(Color.parseColor("#2196F3"), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fomentusers);

        zhuye1 = findViewById(R.id.zhuye);
        gouwuzhe1 = findViewById(R.id.gouwuche);
        imag1 = findViewById(R.id.zhuye_imag);
        imag2 = findViewById(R.id.gouwuche_image);
        out = findViewById(R.id.out);

        FragmentManager fm = getSupportFragmentManager();
        userlist h1 = new userlist(fomentusers.this);
        useradd h2 = new useradd(fomentusers.this);

        AtomicReference<FragmentTransaction> ft = new AtomicReference<>(fm.beginTransaction());
        ft.get().replace(R.id.tihuan, h1);
        ft.get().commit();
        change(imag1, true);
        change(imag2, false);

        zhuye1.setOnClickListener(v -> {
            ft.set(fm.beginTransaction());
            ft.get().replace(R.id.tihuan, h1);
            ft.get().commit();
            change(imag1, true);
            change(imag2, false);
        });

        gouwuzhe1.setOnClickListener(v -> {
            ft.set(fm.beginTransaction());
            ft.get().replace(R.id.tihuan, h2);
            ft.get().commit();
            change(imag1, false);
            change(imag2, true);
        });

        out.setOnClickListener(v -> {
            cancelActivity();
        });
    }

    private void cancelActivity() {
        //创建对话框，询问是否取消注册
        new AlertDialog.Builder(fomentusers.this)
                .setMessage("确定要取消注册吗？")
                .setPositiveButton("取消", null)
                .setNegativeButton("确定", (dialog, which) -> {
                    //关闭注册页面，并返回登录页面
                    Intent intent = new Intent(fomentusers.this, foment.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    }
}