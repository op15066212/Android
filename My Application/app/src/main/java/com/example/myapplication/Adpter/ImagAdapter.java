package com.example.myapplication.Adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Bean.Imag;
import com.example.myapplication.R;
import com.example.myapplication.View.ImagViewHolder;

import java.util.List;

public class ImagAdapter extends RecyclerView.Adapter<ImagViewHolder> {

    private final List<Imag> mImags;
    // 声明一个OnItemClickListener变量来存储回调函数
    private OnItemClickListener mListener;
    // 声明一个OnItemClickListener变量来存储回调函数
    private OnItemLongClickListener mLongClickListener;

    // 声明一个OnItemLongClickListener变量来存储回调函数
    public ImagAdapter(List<Imag> Imags) {
        mImags = Imags;
    }

    @NonNull
    @Override
    public ImagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_imag, parent, false);
        return new ImagViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagViewHolder holder, int position) {
        final Imag Imag = mImags.get(position);
        holder.imageView_a.setImageResource(Imag.getmImag_a());
        holder.imageView_b.setImageResource(Imag.getmImag_b());

        // 给列表项设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    // 调用回调函数，并将当前位置的Imag对象作为参数传递给回调函数
                    mListener.onItemClick(Imag);
                }
            }
        });

        // 给列表项设置长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mLongClickListener != null) {
                    // 调用回调函数，并将当前位置的Imag对象作为参数传递给回调函数
                    mLongClickListener.onItemLongClick(Imag);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImags.size();
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
        void onItemClick(Imag Imag);
    }

    // 定义一个接口来处理列表项的长按事件
    public interface OnItemLongClickListener {
        void onItemLongClick(Imag Imag);
    }
}
