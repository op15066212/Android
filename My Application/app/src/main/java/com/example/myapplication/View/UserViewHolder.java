package com.example.myapplication.View;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView username;
    public TextView password;
    public TextView sex;

    public Button update;

    public Button delete;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        username = itemView.findViewById(R.id.user_name);
        password = itemView.findViewById(R.id.user_password);
        sex = itemView.findViewById(R.id.user_gender);
        update = itemView.findViewById(R.id.update);
        delete = itemView.findViewById(R.id.delete);
    }
}
