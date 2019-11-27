package com.example.onememory.mainActivity;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onememory.R;
import com.example.onememory.app_item.AppItem;

import java.util.ArrayList;

public class SubscribeAdapter extends RecyclerView.Adapter<SubscribeAdapter.SubscribeViewHolder> {

    private Context mContext;
    private ArrayList<String> app_name;
    private ArrayList<Integer> icon_res_ID;
    private ArrayList<Float> cost;
    private ArrayList<String> bgColors;
    private ArrayList<String> textColors;
    private static final String TAG = "SubscribeAdapter";

    public SubscribeAdapter(Context context, ArrayList<String> app_name, ArrayList<Float> cost, ArrayList<Integer> icon_res_ID,
                            ArrayList<String> bgColors, ArrayList<String> textColors) {
        mContext = context;
        this.app_name = app_name;
        this.icon_res_ID = icon_res_ID;
        this.cost = cost;
        this.bgColors = bgColors;
        this.textColors = textColors;
    }

    @NonNull
    @Override
    public SubscribeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SubscribeViewHolder svHolder = new SubscribeViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.app_subscribe, parent, false));
        return svHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubscribeViewHolder holder, int position) {

        if (position < app_name.size()) {
            holder.appItem.setApp_name(app_name.get(position));
        }

        if (position < cost.size()) {
            holder.appItem.setCost(cost.get(position));
        } else {
            holder.appItem.setCost(-1);
        }

        if (position < icon_res_ID.size()) {
            Log.e(TAG, "iconID:" + icon_res_ID.get(position) + "");
            holder.appItem.setIconResID(icon_res_ID.get(position));
        } else {
            holder.appItem.setIconResID(0);
        }

        if (position < textColors.size()) {
            Log.e(TAG, "textColors:" + textColors.get(position) + "");
            holder.appItem.setFont_color(textColors.get(position));
        }

        if (position < bgColors.size()) {
            holder.appItem.setBg_tint(Color.parseColor(bgColors.get(position)));
        }


    }

    @Override
    public int getItemCount() {
        return app_name.size();
    }

    public void addApp(int imageResID, String appName, float cost) {
        app_name.add(appName);
        this.cost.add(cost);
        this.icon_res_ID.add(imageResID);
        notifyItemInserted(app_name.size() - 1);

    }


    class SubscribeViewHolder extends RecyclerView.ViewHolder {

        private AppItem appItem;

        public SubscribeViewHolder(@NonNull View itemView) {
            super(itemView);
            appItem = itemView.findViewById(R.id.sub_app);
        }
    }
}
