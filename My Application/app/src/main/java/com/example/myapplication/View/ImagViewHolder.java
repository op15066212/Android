package com.example.myapplication.View;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;

public class ImagViewHolder extends RecyclerView.ViewHolder {
    // 商品图片
    public ImageView imageView_a;
    public ImageView imageView_b;


    public ImagViewHolder(@NonNull View itemView) {
        super(itemView);
        // 绑定布局中的控件
        imageView_a = itemView.findViewById(R.id.image_view_a);
        imageView_b = itemView.findViewById(R.id.image_view_b);
    }
}
