package com.example.onememory.viewCard;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.onememory.MainActivity;
import com.example.onememory.R;
import com.example.onememory.apps.App;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {
    // app列表
    private ArrayList<App> apps;
    // 弹出式菜单
    PopupMenu popupMenu;
    // 时钟控件
    private TimePickerView pvTime;
    // boolean
    boolean is_outTime, is_descriptionNULL, is_payMethodNULL, is_orderPeriodNULL;


    public MyRecyclerViewAdapter(ArrayList<App> apps) {
        this.apps = apps;
    }

    class myholder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        // card_item的控件
        public LinearLayout card_linearLayout, card_main_linearLayout;
        public ImageView icon;
        public EditText description, price;
        public TextView name, orderTime,
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
                    Toasty.error(name.getContext(), "删除成功!", Toast.LENGTH_SHORT, true).show();
                    break;
                case R.id.edit_card:
                    // 设置可编辑状态
                    description.setVisibility(View.VISIBLE);
//                    description.setFocusable(true);
//                    description.setCursorVisible(true);
                    description.setFocusable(true);
                    description.setFocusableInTouchMode(true);
//                    description.setFocusable(true);
//                    description.requestFocus();

//                    price.setFocusable(true);
//                    price.setCursorVisible(true);
                    price.setFocusable(true);
                    price.setFocusableInTouchMode(true);
//                    price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
//                    price.requestFocus();

                    orderTime.setClickable(true);
//                    orderTime.requestFocus();

                    orderPeriod.setClickable(true);
//                    orderPeriod.requestFocus();

                    payMethod.setClickable(true);
//                    payMethod.requestFocus();

                    card_save.setVisibility(View.VISIBLE);
                    break;

                case R.id.resume_card:
                    Date date = new Date();
                    String sub_time = getTime(date);
                    String sub_period = orderPeriod.getText().toString();

                    ContentValues contentValues = new ContentValues();

                    contentValues.put("sub_time", sub_time);
                    contentValues.put("sub_period", sub_period);
                    MainActivity.getDatabase().update("apps", contentValues, "id=?", new String[]{apps.get(position).getId() + ""});

                    apps.get(position).setSubTime(sub_time);
                    apps.get(position).setSubPeriod(sub_period);

                    MainActivity.apps.get(position).setSubTime(sub_time);
                    MainActivity.apps.get(position).setSubPeriod(sub_period);

                    Toasty.success(description.getContext(), "续订成功!", Toast.LENGTH_SHORT, true).show();
                    Intent intent = new Intent(name.getContext(), MainActivity.class);
                    ((ViewCard) card_save.getContext()).startActivity(intent);
                    ((ViewCard) card_save.getContext()).finish();
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
        ((myholder) holder).position = position;
        ((myholder) holder).card_main_linearLayout.setBackgroundColor(Color.parseColor(apps.get(position).getBgColor()));
        // 背景颜色透明度
        ((myholder) holder).card_main_linearLayout.getBackground().setAlpha(215);
        ((myholder) holder).card_linearLayout.setBackgroundColor(Color.parseColor(apps.get(position).getBgColor()));
        ((myholder) holder).icon.setImageResource(apps.get(position).getIconId());
        ((myholder) holder).outTime.setTextColor(Color.parseColor(apps.get(position).getTextColor()));

        // 不可点击状态
//        ((myholder) holder).description.setCursorVisible(false);
        ((myholder) holder).description.setFocusable(false);
        ((myholder) holder).description.setFocusableInTouchMode(false);
//        ((myholder) holder).description.setFocusable(false);
//        ((myholder) holder).price.setCursorVisible(false);
        ((myholder) holder).price.setFocusable(false);
        ((myholder) holder).price.setFocusableInTouchMode(false);

//        ((myholder) holder).price.setFocusable(false);
//        ((myholder) holder).orderTime.setEnabled(false);
//        ((myholder) holder).orderPeriod.setEnabled(false);
//        ((myholder) holder).payMethod.setEnabled(false);

        // 保存按钮
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

                //数据库保存
                contentValues.put("id", apps.get(position).getId());
                contentValues.put("description", description);
                contentValues.put("money", money);
                contentValues.put("sub_time", sub_time);
                contentValues.put("sub_period", sub_period);
                contentValues.put("pay_method", pay_method);
                MainActivity.getDatabase().update("apps", contentValues, "id=?", new String[]{apps.get(position).getId() + ""});

                // item信息修改保存
                apps.get(position).setDescription(description);
                apps.get(position).setMoney(Float.parseFloat(money));
                apps.get(position).setSubTime(sub_time);
                apps.get(position).setSubPeriod(sub_period);
                apps.get(position).setPayMethod(pay_method);
                // 主页面信息修改保存
                MainActivity.apps.get(position).setDescription(description);
                MainActivity.apps.get(position).setMoney(Float.parseFloat(money));
                MainActivity.apps.get(position).setSubTime(sub_time);
                MainActivity.apps.get(position).setSubPeriod(sub_period);
                MainActivity.apps.get(position).setPayMethod(pay_method);

                Toasty.success(((myholder) holder).btn.getContext(), "保存成功!", Toast.LENGTH_SHORT, true).show();
                Intent intent = new Intent(((myholder) holder).btn.getContext(), MainActivity.class);
                ((ViewCard) ((myholder) holder).card_save.getContext()).startActivity(intent);
                ((ViewCard) ((myholder) holder).card_save.getContext()).finish();
//                ((ViewCard) ((myholder) holder).card_save.getContext()).onBackPressed();
            }
        });

        ((myholder) holder).orderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTimePicker((myholder) holder);
            }
        });
        ((myholder) holder).orderTime.setClickable(false);

        ((myholder) holder).orderPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectPickerView((myholder) holder);
            }
        });
        ((myholder) holder).orderPeriod.setClickable(false);

        ((myholder) holder).payMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMethodPickerView((myholder) holder);
            }
        });
        ((myholder) holder).payMethod.setClickable(false);

        is_outTime = apps.get(position).getState() < 0 ? true : false;
        is_descriptionNULL = apps.get(position).getDescription().equals("") || apps.get(position).getDescription() == null ? true : false;
        is_payMethodNULL = apps.get(position).getPayMethod().equals("") || apps.get(position).getPayMethod() == null ? true : false;
        is_orderPeriodNULL = apps.get(position).getSubPeriod().equals("") || apps.get(position).getSubPeriod() == null ? true : false;

        // 用户设置属性
        if (!is_descriptionNULL)
            ((myholder) holder).description.setText(apps.get(position).getDescription());
        else ((myholder) holder).description.setVisibility(View.GONE);

        if (!is_payMethodNULL)
            ((myholder) holder).payMethod.setText(apps.get(position).getPayMethod());
        else ((myholder) holder).payMethod.setText("无");

        if (!is_orderPeriodNULL)
            ((myholder) holder).orderPeriod.setText(apps.get(position).getSubPeriod());
        else ((myholder) holder).orderPeriod.setText("无");

        // 必要属性
        ((myholder) holder).name.setText(apps.get(position).getName());
        ((myholder) holder).price.setText(String.valueOf(apps.get(position).getMoney()));
        ((myholder) holder).orderTime.setText(apps.get(position).getSubTime());


        String outTime_description = "还剩" + apps.get(position).getState() + "天过期";

        if (is_outTime) ((myholder) holder).outTime.setText(R.string.overdue);
        else ((myholder) holder).outTime.setText(outTime_description);

    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    // *************订阅周期控件********************
    public void showSelectPickerView(final myholder holder) {
        // 要展示的数据
        final List<String> listData = getData();
        // 监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(holder.btn.getContext(), new OnOptionsSelectListener() {

            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                // 返回的分别是三个级别的选中位置
                // 展示选中数据
                holder.orderPeriod.setText(listData.get(options1));
            }
        })
                .setSelectOptions(0)// 设置选择第一个
                .setOutSideCancelable(true)// 点击控件以外为取消
                .setLineSpacingMultiplier(2.0f) // 间距
                .build();//创建
        // 把数据绑定到控件上面
        pvOptions.setPicker(listData);
        // 展示
        pvOptions.show();
    }

    // 订阅周期选择列表
    public List<String> getData() {
        List<String> list = new ArrayList<>();
        list.add("按月订阅");
        list.add("按季订阅");
        list.add("按年订阅");
        list.add("一次性买断");
        return list;
    }
    //**********************************************

    //**************订阅方式控件********************
    public void showMethodPickerView(final myholder holder) {
        // 要展示的数据
        final List<String> listData = getMethod();
        // 监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(holder.btn.getContext(), new OnOptionsSelectListener() {

            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                // 返回的分别是三个级别的选中位置
                // 展示选中数据
                holder.payMethod.setText(listData.get(options1));
            }
        })
                .setSelectOptions(0)// 设置选择第一个
                .setOutSideCancelable(true)// 点击控件以外为取消
                .setLineSpacingMultiplier(2.0f) // 间距
                .build();//创建

        // 把数据绑定到控件上面
        pvOptions.setPicker(listData);
        // 展示
        pvOptions.show();
    }

    // 添加选项
    private List<String> getMethod() {
        List<String> list = new ArrayList<>();
        list.add("支付宝");
        list.add("微  信");
        list.add("Apple Pay");
        list.add("信用卡");
        list.add("他人赠送");
        return list;
    }
    //**********************************************

    //**************订阅时间控件********************
    public void initTimePicker(final myholder holder) {//Dialog 模式下，在底部弹出
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(2010, 0, 1);
        pvTime = new TimePickerBuilder(holder.btn.getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
//                Toast.makeText(Add_Subscribe.this, getTime(date), Toast.LENGTH_SHORT).show();
                Log.i("pvTime", "onTimeSelect");
                holder.orderTime.setText(getTime(date));
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .addOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("pvTime", "onCancelClickListener");
                    }
                })
                .setItemVisibleCount(5) // 选项可见数目，若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setLineSpacingMultiplier(3.0f) // 每个选项行间距
                .isCyclic(false) //是否循环滚动
                .setDate(selectedDate)
                .setRangDate(startDate, selectedDate)
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.3f);
            }
        }
        pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    //**********************************************
}
