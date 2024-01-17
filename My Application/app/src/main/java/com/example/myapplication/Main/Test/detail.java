package com.example.myapplication.Main.Test;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Adpter.DetailAdapter;
import com.example.myapplication.Adpter.ImagAdapter;
import com.example.myapplication.Bean.Food;
import com.example.myapplication.Bean.Imag;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class detail extends AppCompatActivity {
    private static List<Food> mFoods = new ArrayList<>();
    private static List<Imag> mImags = new ArrayList<>();
    private DetailAdapter adapter;
    private ImagAdapter adapter_Imag;

    private Button shopB;

    private Button cancelB;

    public static void setFood(Food food) {
        mFoods.clear();
        food.setmInfo("这是一种非常美味的食物，您一定要尝试一下！");
        mFoods.add(0, food);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        // 设置RecyclerView
        RecyclerView RecyclerView_food = findViewById(R.id.recycler_view_2);
        // 设置布局管理器，这里使用线性布局管理器
        RecyclerView_food.setLayoutManager(new LinearLayoutManager(this));
        // 创建适配器实例并设置给RecyclerView
        adapter = new DetailAdapter(mFoods);
        RecyclerView_food.setAdapter(adapter);

        // 设置RecyclerView的点击事件
        adapter.setOnItemClickListener(new DetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Food food) {
                // 当列表项被点击时执行该回调函数
                Toast.makeText(detail.this, "长按删除" + food.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnItemLongClickListener(new DetailAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(final Food food) {
                remove(food);
            }
        });


        shopB = findViewById(R.id.detail_shop_button);
        cancelB = findViewById(R.id.detail_cancel_button);
        shopB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopActivity();
            }
        });

        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelActivity();
            }
        });


        RecyclerView RecyclerView_imag = findViewById(R.id.recycler_view_3);
        // 设置布局管理器，这里使用线性布局管理器
        RecyclerView_imag.setLayoutManager(new LinearLayoutManager(this));
        // 创建适配器实例并设置给RecyclerView

        if (mFoods.isEmpty()) {
            mFoods.add(new Food(R.drawable.g,
                    "蛋炒饭",
                    "4.9分",
                    "500+",
                    "起送10元  配送0元",
                    "30分钟",
                    "¥10.00"));
        }
        setImag();
        adapter_Imag = new ImagAdapter(mImags);
        RecyclerView_imag.setAdapter(adapter_Imag);
    }

    public void remove(Food food) {
        // 弹出一个对话框确认是否删除该项食品
        new AlertDialog.Builder(detail.this)
                .setMessage("确定要删除" + food.getName() + "吗？")
                .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 从列表中删除该项食品
                        mFoods.remove(food);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(detail.this, food.getName() + "已被删除", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("取消", null)
                .show();
    }

    private void shopActivity() {
        Toast.makeText(detail.this, "购买成功", Toast.LENGTH_SHORT).show();
    }

    private void cancelActivity() {
        Intent intent = new Intent(detail.this, main.class);
        startActivity(intent);
        finish();
    }

    public void setImag() {
        mImags.clear();
        mImags.add(new Imag(R.drawable.a, R.drawable.b));
        mImags.add(new Imag(R.drawable.c, R.drawable.d));
        mImags.add(new Imag(R.drawable.e, R.drawable.f));
        mImags.add(new Imag(R.drawable.g, R.drawable.i));
    }
}