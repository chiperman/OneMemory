package com.example.onememory.viewCard;

import android.content.ContentValues;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onememory.MainActivity;
import com.example.onememory.R;
import com.example.onememory.apps.App;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<App> apps;
    PopupMenu popupMenu;
    boolean is_outTime, is_descriptionNULL, is_payMethodNULL, is_orderPeriodNULL;


    public MyRecyclerViewAdapter(ArrayList<App> apps) {
        this.apps = apps;
    }

    class myholder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        private LinearLayout card_linearLayout, card_main_linearLayout;
        private ImageView icon;
        private EditText description, price;
        private TextView name, orderTime,
                orderPeriod, payMethod, outTime;
        public Button btn, card_save;
        // 获取当前App的位置
        public int position;

        public myholder(View itemView) {
            super(itemView);
            card_save = itemView.findViewById(R.id.card_save);
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
            popupMenu = new PopupMenu(itemView.getContext(), btn);
            // 填充菜单
            popupMenu.inflate(R.menu.cardmenu);
            // 菜单项点击监听
            popupMenu.setOnMenuItemClickListener(this);
            // 显示恢复订阅选项
            if (is_outTime) popupMenu.getMenu().getItem(2).setVisible(true);
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
                case R.id.edit_card:
                    // 设置可编辑状态
                    description.setFocusableInTouchMode(true);
                    description.setFocusable(true);
                    description.requestFocus();

                    price.setFocusableInTouchMode(true);
                    price.setFocusable(true);
                    price.requestFocus();

                    orderTime.setFocusableInTouchMode(true);
                    orderTime.setFocusable(true);
                    orderTime.requestFocus();

                    orderPeriod.setFocusableInTouchMode(true);
                    orderPeriod.setFocusable(true);
                    orderPeriod.requestFocus();

                    payMethod.setFocusableInTouchMode(true);
                    payMethod.setFocusable(true);
                    payMethod.requestFocus();

                    card_save.setVisibility(View.VISIBLE);
                    break;

                case R.id.resume_card:
//                    ContentValues contentValues = new ContentValues();
//                    contentValues.put("id",apps.get(getAdapterPosition()).getId());
//                    contentValues.put("sub_time","0");
                    // 启用编辑
                    description.setFocusableInTouchMode(true);
                    description.setFocusable(true);
                    description.requestFocus();

                    price.setFocusableInTouchMode(true);
                    price.setFocusable(true);
                    price.requestFocus();

                    orderTime.setFocusableInTouchMode(true);
                    orderTime.setFocusable(true);
                    orderTime.requestFocus();

                    orderPeriod.setFocusableInTouchMode(true);
                    orderPeriod.setFocusable(true);
                    orderPeriod.requestFocus();

                    payMethod.setFocusableInTouchMode(true);
                    payMethod.setFocusable(true);
                    payMethod.requestFocus();
                    String descrip = description.getText().toString();
                    String money = price.getText().toString();
                    String sub_time = orderTime.getText().toString();
                    String sub_period = orderPeriod.getText().toString();
                    String pay_method = payMethod.getText().toString();

                    ContentValues contentValues = new ContentValues();

                    contentValues.put("id", apps.get(position).getId());
                    contentValues.put("description", descrip);
                    contentValues.put("money", money);
                    contentValues.put("money", money);
                    contentValues.put("money", money);
                    contentValues.put("sub_time", sub_time);
                    contentValues.put("sub_period", sub_period);
                    contentValues.put("pay_method", pay_method);
                    MainActivity.getDatabase().update("apps", contentValues, "id=?", new String[]{apps.get(position).getId() + ""});

                    apps.get(position).setDescription(descrip);
                    apps.get(position).setMoney(Float.parseFloat(money));
                    apps.get(position).setSubTime(sub_time);
                    apps.get(position).setSubPeriod(sub_period);
                    apps.get(position).setPayMethod(pay_method);

                    MainActivity.apps.get(position).setDescription(descrip);
                    MainActivity.apps.get(position).setMoney(Float.parseFloat(money));
                    MainActivity.apps.get(position).setSubTime(sub_time);
                    MainActivity.apps.get(position).setSubPeriod(sub_period);
                    MainActivity.apps.get(position).setPayMethod(pay_method);

                    Toast.makeText(description.getContext(), "续订成功！", Toast.LENGTH_SHORT).show();
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
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        ((myholder) holder).card_main_linearLayout.setBackgroundColor(Color.parseColor(apps.get(position).getBgColor()));
        // 背景颜色透明度
        ((myholder) holder).card_main_linearLayout.getBackground().setAlpha(215);
        ((myholder) holder).card_linearLayout.setBackgroundColor(Color.parseColor(apps.get(position).getBgColor()));
        ((myholder) holder).icon.setImageResource(apps.get(position).getIconId());
        ((myholder) holder).card_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 修改订阅信息
                String description = ((myholder) holder).description.getText().toString();
                String money = ((myholder) holder).price.getText().toString();
                String sub_time = ((myholder) holder).orderTime.getText().toString();
                String sub_period = ((myholder) holder).orderPeriod.getText().toString();
                String pay_method = ((myholder) holder).payMethod.getText().toString();

                ContentValues contentValues = new ContentValues();

                contentValues.put("id", apps.get(position).getId());
                contentValues.put("description", description);
                contentValues.put("money", money);
                contentValues.put("money", money);
                contentValues.put("money", money);
                contentValues.put("sub_time", sub_time);
                contentValues.put("sub_period", sub_period);
                contentValues.put("pay_method", pay_method);
                MainActivity.getDatabase().update("apps", contentValues, "id=?", new String[]{apps.get(position).getId() + ""});

                apps.get(position).setDescription(description);
                apps.get(position).setMoney(Float.parseFloat(money));
                apps.get(position).setSubTime(sub_time);
                apps.get(position).setSubPeriod(sub_period);
                apps.get(position).setPayMethod(pay_method);

                MainActivity.apps.get(position).setDescription(description);
                MainActivity.apps.get(position).setMoney(Float.parseFloat(money));
                MainActivity.apps.get(position).setSubTime(sub_time);
                MainActivity.apps.get(position).setSubPeriod(sub_period);
                MainActivity.apps.get(position).setPayMethod(pay_method);

                Toast.makeText(((myholder) holder).btn.getContext(), "修改成功！", Toast.LENGTH_SHORT).show();
            }
        });
        is_outTime = apps.get(position).getState() < 0 ? true : false;
        is_descriptionNULL = apps.get(position).getDescription().equals("") || apps.get(position).getDescription() == null ? false : true;
        is_payMethodNULL = apps.get(position).getPayMethod().equals("") || apps.get(position).getPayMethod() == null ? false : true;
        is_orderPeriodNULL = apps.get(position).getSubPeriod().equals("") || apps.get(position).getSubPeriod() == null ? false : true;

        // 用户设置属性
        if (is_descriptionNULL)
            ((myholder) holder).description.setText(apps.get(position).getDescription());

        if (is_payMethodNULL)
            ((myholder) holder).payMethod.setText(apps.get(position).getPayMethod());
        else ((myholder) holder).payMethod.setText("无");

        if (is_orderPeriodNULL)
            ((myholder) holder).orderPeriod.setText(apps.get(position).getSubPeriod());
        else ((myholder) holder).orderPeriod.setText("无");

        // 必要属性
        ((myholder) holder).name.setText(apps.get(position).getName());
        ((myholder) holder).price.setText(String.valueOf(apps.get(position).getMoney()));
        ((myholder) holder).orderTime.setText(apps.get(position).getSubTime());


        String outTime_description = "还剩" + apps.get(position).getState() + "天过期";

        if (is_outTime) ((myholder) holder).outTime.setText(R.string.overdue);
        else ((myholder) holder).outTime.setText(outTime_description);

        ((myholder) holder).position = position;
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }


}
