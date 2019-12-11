package com.example.onememory.mainActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onememory.MainActivity;
import com.example.onememory.R;
import com.example.onememory.app_item.AppItem;
import com.example.onememory.apps.App;
import com.example.onememory.viewCard.ViewCard;

import java.io.Serializable;
import java.util.ArrayList;

public class SubscribeAdapter extends RecyclerView.Adapter<SubscribeAdapter.SubscribeViewHolder> implements Serializable {

    private Context mContext;
    private ArrayList<App> apps;
    private SubscribeAdapter adapter;
    private static final String TAG = "SubscribeAdapter";

    public SubscribeAdapter(Context context, ArrayList<App> apps) {
        mContext = context;
        adapter = this;
        this.apps = apps;
    }

    @NonNull
    @Override
    public SubscribeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SubscribeViewHolder svHolder = new SubscribeViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.app_subscribe, parent, false));
        return svHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SubscribeViewHolder holder, int position) {
        holder.appItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.appItem.getContext(), ViewCard.class);
                intent.putExtra("app",apps.get(holder.getAdapterPosition()));
                intent.putExtra("apps",apps);
                holder.appItem.getContext().startActivity(intent);
            }
        });
        if (position < apps.size()) {
            holder.appItem.setApp_name(apps.get(position).getName());
            holder.appItem.setCost(apps.get(position).getMoney());
            holder.appItem.setIconResID(apps.get(position).getIconId());
            holder.appItem.setFont_color(apps.get(position).getTextColor());
            holder.appItem.setBg_tint(Color.parseColor(apps.get(position).getBgColor()));
        } else {
            holder.appItem.setCost(-1);
            holder.appItem.setIconResID(0);
        }



    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public void addApp(App app) {
        apps.add(app);
        notifyItemInserted(apps.size() - 1);
    }

    public void deleteApp(int position){

        MainActivity.getDatabase().delete("apps","id=?",new String[]{apps.get(position).getId()+""});
        apps.remove(position);
        notifyItemRemoved(position);
    }


    class SubscribeViewHolder extends RecyclerView.ViewHolder {

        private AppItem appItem;

        public SubscribeViewHolder(@NonNull View itemView) {
            super(itemView);
            appItem = itemView.findViewById(R.id.sub_app);
        }
    }
}
