package com.example.myapplication.Main;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.myapplication.Fragment.home;
import com.example.myapplication.Fragment.shop;
import com.example.myapplication.Fragment.user;
import com.example.myapplication.R;

import java.util.concurrent.atomic.AtomicReference;

public class foment extends AppCompatActivity {

    LinearLayout zhuye1;
    LinearLayout gouwuzhe1;
    LinearLayout yonghu1;
    ImageView imag1;
    ImageView imag2;
    ImageView imag3;

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
        setContentView(R.layout.activity_foment);

        zhuye1 = findViewById(R.id.zhuye);
        gouwuzhe1 = findViewById(R.id.gouwuche);
        yonghu1 = findViewById(R.id.yonghu);
        imag1 = findViewById(R.id.zhuye_imag);
        imag2 = findViewById(R.id.gouwuche_image);
        imag3 = findViewById(R.id.yonghu_image);

        FragmentManager fm = getSupportFragmentManager();
        AtomicReference<FragmentTransaction> ft = new AtomicReference<>(fm.beginTransaction());
        home h1 = new home(foment.this);
        shop h2 = new shop();
        user h3 = new user();


        ft.get().replace(R.id.tihuan, h1);
        ft.get().commit();
        change(imag1, true);
        change(imag2, false);
        change(imag3, false);

        zhuye1.setOnClickListener(v -> {
            ft.set(fm.beginTransaction());
            ft.get().replace(R.id.tihuan, h1);
            ft.get().commit();
            change(imag1, true);
            change(imag2, false);
            change(imag3, false);
        });

        gouwuzhe1.setOnClickListener(v -> {
            ft.set(fm.beginTransaction());
            ft.get().replace(R.id.tihuan, h2);
            ft.get().commit();
            change(imag1, false);
            change(imag2, true);
            change(imag3, false);
        });

        yonghu1.setOnClickListener(v -> {
            ft.set(fm.beginTransaction());
            ft.get().replace(R.id.tihuan, h3);
            ft.get().commit();
            change(imag1, false);
            change(imag2, false);
            change(imag3, true);
        });
    }
}