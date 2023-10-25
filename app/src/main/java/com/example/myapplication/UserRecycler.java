package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataModel.ClosetItem;
import com.example.myapplication.DataModel.DataConverter;

import java.util.List;

public class UserRecycler extends RecyclerView.Adapter<UserViewHolder> {

    List<ClosetItem> data;

    public UserRecycler(List<ClosetItem> closetItems) {
        data = closetItems;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user_item_layout,
                parent,
                false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int i) {
        ClosetItem closetItem = data.get(i);
        byte[] imageByteArray = closetItem.getImage();
        if (imageByteArray != null) {
            holder.imageView.setImageBitmap(DataConverter.convertByteArray2Image(imageByteArray));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
