package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Adpter.DetailAdapter;
import com.example.myapplication.Adpter.ImagAdapter;
import com.example.myapplication.Bean.Food;
import com.example.myapplication.Bean.Imag;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class shop extends Fragment {
    private static List<Food> mFoods = new ArrayList<>();
    private static List<Imag> mImags = new ArrayList<>();
    private DetailAdapter adapter;
    private ImagAdapter adapter_Imag;


    public shop() {
    }

    public static void setFood(Food food) {
        mFoods.clear();
        food.setmInfo("这是一种非常美味的食物，您一定要尝试一下！");
        mFoods.add(0, food);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        RecyclerView RecyclerView_food = view.findViewById(R.id.recycler_view_2);
        RecyclerView_food.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DetailAdapter(mFoods);
        RecyclerView_food.setAdapter(adapter);
        adapter.setOnItemClickListener(new DetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Food food) {
                Toast.makeText(getActivity(), "长按删除" + food.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemLongClickListener(this::remove);
        RecyclerView RecyclerView_imag = view.findViewById(R.id.recycler_view_3);
        RecyclerView_imag.setLayoutManager(new LinearLayoutManager(getActivity()));
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

        return view;
    }

    public void remove(Food food) {
        new AlertDialog.Builder(getActivity())
                .setMessage("确定要删除" + food.getName() + "吗？")
                .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mFoods.remove(food);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), food.getName() + "已被删除", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("取消", null)
                .show();
    }

    public void setImag() {
        mImags.clear();
        mImags.add(new Imag(R.drawable.a, R.drawable.b));
        mImags.add(new Imag(R.drawable.c, R.drawable.d));
        mImags.add(new Imag(R.drawable.e, R.drawable.f));
        mImags.add(new Imag(R.drawable.g, R.drawable.i));
    }
}
