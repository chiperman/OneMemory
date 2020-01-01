package com.example.onememory.addSubscribe;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.onememory.MainActivity;
import com.example.onememory.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class Add_Subscribe extends Activity implements View.OnClickListener {

    private TimePickerView pvTime;
    private TextView tv_date;       // 订阅时间
    private TextView show_select;   // 订阅类型
    private TextView method_select; // 订阅方式
    private ImageView iv_back;
    private ImageView iv_add;
    private ImageView app_icon;
    private TextView sub_describe;
    private TextView sub_price;
    private TextView sub_time;
    private TextView sub_method;
    private TextView sub_pay;
    private TextView sub_name;
    private TextView add_describe;
    private TextView app_date;
    private TextView select;
    private TextView method;
    private TextView purchase_url;
    private CardView cv_AppCard;
    private EditText app_money;
    private Intent getIntent;
    private int AppIcon;
    private String AppName;
    private String bg_color;
    private String url;
    private String price;
    private String period;
    private String text_color;
    private String text_hintcolor;
    private Intent intent;
    private View divider1;
    private View divider2;
    private View divider3;
    private View divider4;
    private View divider5;
    private View divider6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        setPlaceUp();

        // 初始化
        init();

        // 获取上个页面传递的值
        getMyIntent();


    }

    public void init() {
        initTimePicker();
        tv_date = findViewById(R.id.tv_date);
        tv_date.setOnClickListener(this);

        show_select = findViewById(R.id.select);
        show_select.setOnClickListener(this);

        method_select = findViewById(R.id.method);
        method_select.setOnClickListener(this);

        iv_back = findViewById(R.id.sub_back);
        iv_back.setOnClickListener(this);

        iv_add = findViewById(R.id.sub_add);
        iv_add.setOnClickListener(this);

        purchase_url = findViewById(R.id.pruchase_url);
        purchase_url.setOnClickListener(this);

        app_money = findViewById(R.id.app_money);
        app_icon = findViewById(R.id.AppIconImg);
        sub_name = findViewById(R.id.AppName);
        cv_AppCard = findViewById(R.id.cv_AppCard);
        sub_describe = findViewById(R.id.describe);
        add_describe = findViewById(R.id.add_describe);
        sub_pay = findViewById(R.id.sub_pay);
        sub_method = findViewById(R.id.sub_method);
        sub_time = findViewById(R.id.sub_time);
        sub_price = findViewById(R.id.price);
        app_date = findViewById(R.id.tv_date);
        select = findViewById(R.id.select);
        method = findViewById(R.id.method);
        divider1 = findViewById(R.id.divider1);
        divider2 = findViewById(R.id.divider2);
        divider3 = findViewById(R.id.divider3);
        divider4 = findViewById(R.id.divider4);
        divider5 = findViewById(R.id.divider5);
        divider6 = findViewById(R.id.divider6);
    }

    //设置状态栏和导航栏
    public void setPlaceUp() {
        // 3.顶部状态栏透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        // 4.设置状态栏文字为暗色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void getMyIntent() {
        getIntent = getIntent();
        AppIcon = getIntent.getIntExtra("iconID", 0);

        if (AppIcon == 0) {
            AppIcon = getIntent.getIntExtra("AppIcon", 0);
            AppName = getIntent.getStringExtra("AppName");
            bg_color = getIntent.getStringExtra("bg_Color");
            text_color = getIntent.getStringExtra("text_Color");
            text_hintcolor = getIntent.getStringExtra("text_hintcolor");
            url = getIntent.getStringExtra("url");
            price = getIntent.getStringExtra("price");
            period = getIntent.getStringExtra("period");
            app_icon.setImageResource(AppIcon);
            sub_name.setText(AppName);
            cv_AppCard.setBackgroundColor(Color.parseColor(bg_color));
            //设置项目字体颜色
            sub_name.setTextColor(Color.parseColor(text_color));
            sub_describe.setTextColor(Color.parseColor(text_color));
            sub_price.setTextColor(Color.parseColor(text_color));
            sub_time.setTextColor(Color.parseColor(text_color));
            sub_method.setTextColor(Color.parseColor(text_color));
            sub_pay.setTextColor(Color.parseColor(text_color));
            if (!url.equals("")) {
                purchase_url.setVisibility(View.VISIBLE);
                Log.e("url:", url);

            }
            app_money.setText(price);
            select.setText(period);
            //设置选项提示字体颜色
            purchase_url.setTextColor(Color.parseColor(text_hintcolor));
            add_describe.setTextColor(Color.parseColor(text_hintcolor));
            add_describe.setHintTextColor(Color.parseColor(text_hintcolor));
            app_money.setTextColor(Color.parseColor(text_hintcolor));
            app_money.setHintTextColor(Color.parseColor(text_hintcolor));
            app_date.setTextColor(Color.parseColor(text_hintcolor));
            app_date.setHintTextColor(Color.parseColor(text_hintcolor));
            select.setTextColor(Color.parseColor(text_hintcolor));
            select.setHintTextColor(Color.parseColor(text_hintcolor));
            method.setTextColor(Color.parseColor(text_hintcolor));
            method.setHintTextColor(Color.parseColor(text_hintcolor));

            //设置分割线颜色
            divider1.setBackgroundColor(Color.parseColor(text_hintcolor));
            divider2.setBackgroundColor(Color.parseColor(text_hintcolor));
            divider3.setBackgroundColor(Color.parseColor(text_hintcolor));
            divider4.setBackgroundColor(Color.parseColor(text_hintcolor));
            divider5.setBackgroundColor(Color.parseColor(text_hintcolor));
            divider6.setBackgroundColor(Color.parseColor(text_hintcolor));
        } else {
            AppName = getIntent.getStringExtra("diyName");
            text_color = getIntent.getStringExtra("fontColor");
            bg_color = getIntent.getStringExtra("background");
            if ("".equals(bg_color) || bg_color == null) {
                bg_color = "#000000";
            }
            cv_AppCard.setBackgroundColor(Color.parseColor(bg_color));
            app_icon.setImageResource(AppIcon);
            sub_name.setText(AppName);

            //设置项目字体颜色
            sub_name.setTextColor(Color.parseColor(text_color));
            sub_describe.setTextColor(Color.parseColor(text_color));
            sub_price.setTextColor(Color.parseColor(text_color));
            sub_time.setTextColor(Color.parseColor(text_color));
            sub_method.setTextColor(Color.parseColor(text_color));
            sub_pay.setTextColor(Color.parseColor(text_color));

            //设置选项提示字体颜色
            purchase_url.setTextColor(Color.parseColor(text_color));
            add_describe.setTextColor(Color.parseColor(text_color));
            add_describe.setHintTextColor(Color.parseColor(text_color));
            app_money.setTextColor(Color.parseColor(text_color));
            app_money.setHintTextColor(Color.parseColor(text_color));
            app_date.setTextColor(Color.parseColor(text_color));
            app_date.setHintTextColor(Color.parseColor(text_color));
            select.setTextColor(Color.parseColor(text_color));
            select.setHintTextColor(Color.parseColor(text_color));
            method.setTextColor(Color.parseColor(text_color));
            method.setHintTextColor(Color.parseColor(text_color));
            //设置分割线颜色
            divider1.setBackgroundColor(Color.parseColor(text_color));
            divider2.setBackgroundColor(Color.parseColor(text_color));
            divider3.setBackgroundColor(Color.parseColor(text_color));
            divider4.setBackgroundColor(Color.parseColor(text_color));
            divider5.setBackgroundColor(Color.parseColor(text_color));
            divider6.setBackgroundColor(Color.parseColor(text_color));
        }
    }

    public void sendMyIntent() {
        // 传递给下个页面并且保存到数据库
        intent = new Intent(this, MainActivity.class);
        Log.e("AppIcon", AppIcon + "");
        Log.e("AppName", AppName);
        Log.e("bg_color", bg_color);
        Log.e("text_color", text_color);
        Log.e("add_describe", add_describe.getText().toString());
        Log.e("app_money", app_money.getText().toString().equals("") ? "0" : app_money.getText().toString());
        Log.e("tv_date", tv_date.getText().toString());
        Log.e("show_select", show_select.getText().toString());
        Log.e("method_select", method_select.getText().toString());


        intent.putExtra("AppIcon", AppIcon);
        intent.putExtra("AppName", AppName);
        intent.putExtra("bg_color", bg_color);

        intent.putExtra("text_color", text_color);
        intent.putExtra("add_describe", add_describe.getText().toString());
        intent.putExtra("app_money", app_money.getText().toString().equals("") ? "0" : app_money.getText().toString());
        intent.putExtra("tv_date", tv_date.getText().toString());
        intent.putExtra("show_select", show_select.getText().toString());
        intent.putExtra("method_select", method_select.getText().toString());

        ContentValues appsInfo = new ContentValues();
        appsInfo.put("name", AppName);
        appsInfo.put("iconId", AppIcon);
        appsInfo.put("bg_color", bg_color);
        appsInfo.put("text_color", text_color);
        appsInfo.put("description", add_describe.getText().toString());
        appsInfo.put("money", app_money.getText().toString().equals("") ? "0" : app_money.getText().toString());
        appsInfo.put("sub_time", tv_date.getText().toString());
        appsInfo.put("sub_period", show_select.getText().toString());
        appsInfo.put("pay_method", method_select.getText().toString());
        Log.e("Add", "正在插入数据库");
        MainActivity.getDatabase().insert("apps", null, appsInfo);

        Cursor cursor = MainActivity.getDatabase().query("apps", new String[]{"id"}, null, null, null, null, "id desc");
        cursor.moveToNext();
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        Log.e("insert ID", id + "");
        intent.putExtra("id", id);

    }

    //    *****************底部式*****************
    private void showSelectPickerView() {
        // 要展示的数据
        final List<String> listData = getData();
        // 监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(Add_Subscribe.this, new OnOptionsSelectListener() {

            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                // 返回的分别是三个级别的选中位置
                // 展示选中数据
                show_select.setText(listData.get(options1));
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
    private List<String> getData() {
        List<String> list = new ArrayList<>();
        list.add("按月订阅");
        list.add("按季订阅");
        list.add("按年订阅");
        list.add("一次性买断");
        return list;
    }
    //    *****************底部式弹窗截止*****************

    //    *****************底部式*****************
    public void showMethodPickerView() {
        // 要展示的数据
        final List<String> listData = getMethod();
        // 监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(Add_Subscribe.this, new OnOptionsSelectListener() {

            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                // 返回的分别是三个级别的选中位置
                // 展示选中数据
                method_select.setText(listData.get(options1));
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
    //    *****************底部式弹窗截止*****************


    private void initTimePicker() {//Dialog 模式下，在底部弹出
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(2010, 0, 1);
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
//                Toast.makeText(Add_Subscribe.this, getTime(date), Toast.LENGTH_SHORT).show();
                Log.i("pvTime", "onTimeSelect");
                tv_date.setText(getTime(date));
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
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sub_back:
                onBackPressed();
                break;
            case R.id.sub_add:
                if (app_money.getText().toString().equals("")) {
                    AlertDialog idialog2 = new AlertDialog.Builder(this)
                            .setTitle("填写信息")
                            .setMessage("请填写订阅金额后方可保存")
                            // 确定按钮
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                    idialog2.show();
                } else if (tv_date.getText().toString().equals("")) {
                    AlertDialog idialog = new AlertDialog.Builder(this)
                            .setTitle("填写信息")
                            .setMessage("请填写订阅时间后方可保存")
                            // 确定按钮
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                    idialog.show();
                } else if (select.getText().toString().equals("")) {
                    AlertDialog idialog = new AlertDialog.Builder(this)
                            .setTitle("填写信息")
                            .setMessage("请填写订阅周期后方可保存")
                            // 确定按钮
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                    idialog.show();
                } else {
                    // 传递下个页面的值
                    sendMyIntent();
                    startActivity(intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
                    finish();
                    Toasty.success(v.getContext(), "添加成功!", Toast.LENGTH_SHORT, true).show();
                }
                break;
            case R.id.tv_date:
                pvTime.show();
                break;
            case R.id.select:
                showSelectPickerView();
                break;
            case R.id.method:
                showMethodPickerView();
                break;
            case R.id.pruchase_url:
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
