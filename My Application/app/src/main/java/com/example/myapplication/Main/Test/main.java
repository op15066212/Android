package com.example.myapplication.Main.Test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Adpter.FoodAdapter;
import com.example.myapplication.Bean.Food;
import com.example.myapplication.R;
import com.example.myapplication.SQList.foodSQ;

import java.util.ArrayList;
import java.util.List;

public class main extends AppCompatActivity {
    // 存储食品数据列表
    private List<Food> mFoods = new ArrayList<>();
    private FoodAdapter adapter;

    private foodSQ fq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fq = new foodSQ(this);

        if (fq.getAllFoods().size() == 0) {
            setFood();
        }

        mFoods = fq.getAllFoods();

        // 初始化食品数据列表
        //setFood();

        // 设置RecyclerView
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        // 设置布局管理器，这里使用线性布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 创建适配器实例并设置给RecyclerView
        adapter = new FoodAdapter(mFoods);
        mRecyclerView.setAdapter(adapter);

        // 设置RecyclerView的点击事件
        adapter.setOnItemClickListener(food -> {
            // 当列表项被点击时执行该回调函数
            Toast.makeText(main.this, "添加成功：" + food.getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(main.this, detail.class);
            detail.setFood(food);
            startActivity(intent);
            finish();
        });

        adapter.setOnItemLongClickListener(this::remove);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void remove(Food food) {
        // 弹出一个对话框确认是否删除该项食品
        new AlertDialog.Builder(main.this)
                .setMessage("确定要删除" + food.getName() + "吗？")
                .setNegativeButton("删除", (dialog, which) -> {
                    // 从列表中删除该项食品
                    mFoods.remove(food);
                    fq.deleteFood(food);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(main.this, food.getName() + "已被删除", Toast.LENGTH_SHORT).show();
                })
                .setPositiveButton("取消", null)
                .show();
    }

    // 初始化食品数据的方法
    public void setFood() {
        // 向列表中添加食品数据
        fq.insertFood(new Food(R.drawable.g,
                "蛋炒饭",
                "4.9分",
                "500+",
                "起送10元  配送0元",
                "30分钟",
                "¥10.00"));
        fq.insertFood(new Food(R.drawable.g,
                "鱼香肉丝",
                "4.8分",
                "200+",
                "起送8元  配送2元",
                "40分钟",
                "¥15.00"));
        fq.insertFood(new Food(R.drawable.g,
                "宫保鸡丁",
                "4.7分",
                "600+",
                "起送12元  配送1元",
                "50分钟",
                "¥18.00"));
        fq.insertFood(new Food(R.drawable.g,
                "kaka奶茶",
                "4.9分",
                "400+",
                "起送13元  配送1元",
                "50分钟",
                "¥10.00"));
        fq.insertFood(new Food(R.drawable.g,
                "KFC",
                "4.5分",
                "700+",
                "起送11元  配送1元",
                "30分钟",
                "¥20.00"));

    }
}
