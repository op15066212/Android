package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Adpter.FoodAdapter;
import com.example.myapplication.Bean.Food;
import com.example.myapplication.R;
import com.example.myapplication.SQList.foodSQ;

import java.util.List;

public class home extends Fragment {
    private List<Food> mFoods;
    private FoodAdapter adapter;
    private foodSQ fq;

    public home() {
    }

    public home(Context context) {
        fq = new foodSQ(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        if (fq.getAllFoods().size() == 0) {
            setFood();
        }
        mFoods = fq.getAllFoods();
        RecyclerView mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FoodAdapter(mFoods);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(food -> {
            Toast.makeText(getContext(), "添加成功：" + food.getName(), Toast.LENGTH_SHORT).show();
            shop.setFood(food);
        });
        adapter.setOnItemLongClickListener(this::remove);
        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void remove(Food food) {
        new AlertDialog.Builder(getContext())
                .setMessage("确定要删除" + food.getName() + "吗？")
                .setNegativeButton("删除", (dialog, which) -> {
                    // 从列表中删除该项食品
                    mFoods.remove(food);
                    fq.deleteFood(food);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), food.getName() + "已被删除", Toast.LENGTH_SHORT).show();
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
