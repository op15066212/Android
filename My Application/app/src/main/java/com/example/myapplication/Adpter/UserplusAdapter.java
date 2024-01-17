package com.example.myapplication.Adpter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Bean.User;
import com.example.myapplication.R;
import com.example.myapplication.View.UserViewHolder;

import java.util.List;

public class UserplusAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private final List<User> mUsers;
    // 声明一个OnItemClickListener变量来存储回调函数
    private OnItemClickListener mListener;
    // 声明一个OnItemClickListener变量来存储回调函数
    private OnItemLongClickListener mLongClickListener;

    // 声明一个OnItemLongClickListener变量来存储回调函数
    public UserplusAdapter(List<User> Users) {
        mUsers = Users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_plus, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final User User = mUsers.get(position);
        holder.username.setText(User.getUsername());
        holder.password.setText(User.getPassword());
        holder.sex.setText(User.getSex());


        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击了修改按钮，执行对应逻辑
                // 可以通过 listener 回调函数将当前位置和 User 对象传递出去，供外部处理
                if (mListener != null) {
                    mListener.onUpdateClick(position, User);
                }
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击了删除按钮，执行对应逻辑
                // 可以通过 listener 回调函数将当前位置和 User 对象传递出去，供外部处理
                if (mListener != null) {
                    mListener.onDeleteClick(position, User);
                }
            }
        });


        // 给列表项设置点击事件
        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                // 调用回调函数，并将当前位置的User对象作为参数传递给回调函数
                mListener.onItemClick(User);
            }
        });

        // 给列表项设置长按事件
        holder.itemView.setOnLongClickListener(v -> {
            if (mLongClickListener != null) {
                // 调用回调函数，并将当前位置的User对象作为参数传递给回调函数
                mLongClickListener.onItemLongClick(User);
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
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
        void onItemClick(User User);

        void onUpdateClick(int position, User user);

        void onDeleteClick(int position, User user);
    }

    // 定义一个接口来处理列表项的长按事件
    public interface OnItemLongClickListener {
        void onItemLongClick(User User);
    }

}
