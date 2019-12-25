package com.example.onememory.diy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onememory.R;

import java.util.List;

public class DiyItemAdapter extends RecyclerView.Adapter<DiyItemAdapter.ViewHolder> {
    private List<DiyItem> ItemList;
    public static boolean flag = false;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;

        public ViewHolder(View view) {
            super(view);
            itemImage = view.findViewById(R.id.MyItem);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_diy, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        // 点击事件监听器
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                DiyItem item = ItemList.get(position);
                DiyActivity.diy_icon.setImageResource(item.getIconID());
                DiyActivity.iconID = item.getIconID();
                flag = true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiyItem it = ItemList.get(position);
        holder.itemImage.setImageResource(it.getIconID());
//        holder.itemText.setText(it.getIconName());
    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }

    public DiyItemAdapter(List<DiyItem> ItemList) {
        this.ItemList = ItemList;
    }


}
