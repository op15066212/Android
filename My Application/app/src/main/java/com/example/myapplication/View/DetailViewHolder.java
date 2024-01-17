package com.example.myapplication.View;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;

public class DetailViewHolder extends RecyclerView.ViewHolder {
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


    public DetailViewHolder(@NonNull View itemView) {
        super(itemView);
        // 绑定布局中的控件
        imageView = itemView.findViewById(R.id.food_image_2);
        nameTextView = itemView.findViewById(R.id.food_name_2);
        ratingTextView = itemView.findViewById(R.id.rating_value);
        salesTextView = itemView.findViewById(R.id.sales_value);
        priceTextView = itemView.findViewById(R.id.price_value);
        info = itemView.findViewById(R.id.info_value);
        time = itemView.findViewById(R.id.time_value);
    }
}
