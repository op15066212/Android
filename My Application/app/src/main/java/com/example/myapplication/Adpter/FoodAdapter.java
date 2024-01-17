package com.example.myapplication.Adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Bean.Food;
import com.example.myapplication.R;
import com.example.myapplication.View.FoodViewHolder;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {

    private final List<Food> mFoods;
    // 声明一个OnItemClickListener变量来存储回调函数
    private OnItemClickListener mListener;
    // 声明一个OnItemClickListener变量来存储回调函数
    private OnItemLongClickListener mLongClickListener;

    // 声明一个OnItemLongClickListener变量来存储回调函数
    public FoodAdapter(List<Food> foods) {
        mFoods = foods;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        final Food food = mFoods.get(position);
        holder.imageView.setImageResource(food.getImageResource());
        holder.nameTextView.setText(food.getName());
        holder.ratingTextView.setText(food.getRating());
        holder.salesTextView.setText(food.getSales());
        holder.info.setText(food.getInfo());
        holder.time.setText(food.getTime());
        holder.priceTextView.setText(food.getPrice());

        // 给列表项设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    // 调用回调函数，并将当前位置的Food对象作为参数传递给回调函数
                    mListener.onItemClick(food);
                }
            }
        });

        // 给列表项设置长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mLongClickListener != null) {
                    // 调用回调函数，并将当前位置的Food对象作为参数传递给回调函数
                    mLongClickListener.onItemLongClick(food);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoods.size();
    }

    // 定义一个公共的方法来设置OnItemClickListener变量
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // 定义一个公共的方法来设置OnItemLongClickListener变量
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mLongClickListener = listener;
    }

    // 定义一个接口来处理列表项的点击事件
    public interface OnItemClickListener {
        void onItemClick(Food food);
    }

    // 定义一个接口来处理列表项的长按事件
    public interface OnItemLongClickListener {
        void onItemLongClick(Food food);
    }
}
