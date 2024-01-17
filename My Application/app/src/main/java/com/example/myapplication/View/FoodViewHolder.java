package com.example.myapplication.View;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;

public class FoodViewHolder extends RecyclerView.ViewHolder {
    // 商品图片
    public ImageView imageView;
    // 商品名称
    public TextView nameTextView;
    // 商品评分
    public TextView ratingTextView;
    // 商品销量
    public TextView salesTextView;

    // 配送信息
    public TextView info;

    // 配送时间
    public TextView time;

    // 商品价格
    public TextView priceTextView;


    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        // 绑定布局中的控件
        imageView = itemView.findViewById(R.id.food_image);
        nameTextView = itemView.findViewById(R.id.food_name);
        ratingTextView = itemView.findViewById(R.id.food_rating);
        salesTextView = itemView.findViewById(R.id.food_sales);
        info = itemView.findViewById(R.id.food_delivery_info);
        time = itemView.findViewById(R.id.food_delivery_time);
        priceTextView = itemView.findViewById(R.id.food_price);
    }
}
