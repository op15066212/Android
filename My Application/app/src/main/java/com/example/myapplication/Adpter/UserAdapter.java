package com.example.myapplication.Adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Bean.User;
import com.example.myapplication.R;
import com.example.myapplication.View.UserViewHolder;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private final List<User> mUsers;
    // 声明一个OnItemClickListener变量来存储回调函数
    private OnItemClickListener mListener;
    // 声明一个OnItemClickListener变量来存储回调函数
    private OnItemLongClickListener mLongClickListener;

    // 声明一个OnItemLongClickListener变量来存储回调函数
    public UserAdapter(List<User> Users) {
        mUsers = Users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User User = mUsers.get(position);
        holder.username.setText(User.getUsername());
        holder.password.setText(User.getPassword());
        holder.sex.setText(User.getSex());

        // 给列表项设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    // 调用回调函数，并将当前位置的User对象作为参数传递给回调函数
                    mListener.onItemClick(User);
                }
            }
        });

        // 给列表项设置长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mLongClickListener != null) {
                    // 调用回调函数，并将当前位置的User对象作为参数传递给回调函数
                    mLongClickListener.onItemLongClick(User);
                    return true;
                }
                return false;
            }
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
    }

    // 定义一个接口来处理列表项的长按事件
    public interface OnItemLongClickListener {
        void onItemLongClick(User User);
    }
}
