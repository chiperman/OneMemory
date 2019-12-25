package com.example.onememory.viewCard;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onememory.MainActivity;
import com.example.onememory.R;
import com.example.onememory.apps.App;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<App> apps;

    public MyRecyclerViewAdapter(ArrayList<App> apps) {
        this.apps = apps;
    }

    class myholder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        private LinearLayout card_linearLayout, card_main_linearLayout;
        private ImageView icon;
        private TextView name, description, price, orderTime,
                orderPeriod, payMethod, outTime;
        private Button btn;
        // 获取当前App的位置
        public int position;

        public myholder(View itemView) {
            super(itemView);
            card_main_linearLayout = itemView.findViewById(R.id.recyclerview_item_main);
            card_linearLayout = itemView.findViewById(R.id.recyclerview_item_card);
            icon = itemView.findViewById(R.id.app_icon);
            name = itemView.findViewById(R.id.app_name);
            description = itemView.findViewById(R.id.xiangqing);
            price = itemView.findViewById(R.id.text_money);
            orderTime = itemView.findViewById(R.id.text_orderTime);
            orderPeriod = itemView.findViewById(R.id.text_orderPeriod);
            payMethod = itemView.findViewById(R.id.text_payMethod);
            outTime = itemView.findViewById(R.id.card_outTime);
            btn = itemView.findViewById(R.id.card_menu_Btn);

            btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View itemView) {

            // 创建弹出菜单对象
            PopupMenu popupMenu = new PopupMenu(itemView.getContext(), btn);
            // 填充菜单
            popupMenu.inflate(R.menu.cardmenu);
            // 菜单项点击监听
            popupMenu.setOnMenuItemClickListener(this);
            // 显示菜单
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.delete_card:
                    apps.remove(position);

                    MainActivity.deleteApp(position);
                    notifyItemRemoved(position);
                    if (apps.size() == 0) {
                        // 刪除完所有的項目，返回上一級
                        ((ViewCard) btn.getContext()).onBackPressed();
                    }
                    break;
            }
            return false;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        myholder holder = new myholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((myholder) holder).card_main_linearLayout.setBackgroundColor(Color.parseColor(apps.get(position).getBgColor()));
        // 背景颜色透明度
        ((myholder) holder).card_main_linearLayout.getBackground().setAlpha(215);
        ((myholder) holder).card_linearLayout.setBackgroundColor(Color.parseColor(apps.get(position).getBgColor()));
        ((myholder) holder).icon.setImageResource(apps.get(position).getIconId());

        // 用户设置属性
        boolean is_descriptionNULL = apps.get(position).getDescription().equals("") || apps.get(position).getDescription() == null ? false : true;
        if (is_descriptionNULL)
            ((myholder) holder).description.setText(apps.get(position).getDescription());

        boolean is_payMethodNULL = apps.get(position).getPayMethod().equals("") || apps.get(position).getPayMethod() == null ? false : true;
        if (is_payMethodNULL)
            ((myholder) holder).payMethod.setText(apps.get(position).getPayMethod());
        else ((myholder) holder).payMethod.setText("无");

        boolean is_orderPeriodNULL = apps.get(position).getSubPeriod().equals("") || apps.get(position).getSubPeriod() == null ? false : true;
        if (is_orderPeriodNULL)
            ((myholder) holder).orderPeriod.setText(apps.get(position).getSubPeriod());
        else ((myholder) holder).orderPeriod.setText("无");

        // 必要属性
        ((myholder) holder).name.setText(apps.get(position).getName());
        ((myholder) holder).price.setText(String.valueOf(apps.get(position).getMoney()));
        ((myholder) holder).orderTime.setText(apps.get(position).getSubTime());


        String outTime_description = "还剩" + apps.get(position).getState() + "天过期";
        boolean is_outTime = apps.get(position).getState() < 0 ? true : false;
        if (is_outTime) ((myholder) holder).outTime.setText(R.string.overdue);
        else ((myholder) holder).outTime.setText(outTime_description);

        ((myholder) holder).position = position;
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }
}
