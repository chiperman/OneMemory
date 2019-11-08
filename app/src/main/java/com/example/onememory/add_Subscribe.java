package com.example.onememory;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class add_Subscribe extends Activity implements DatePicker.OnDateChangedListener {

    private TextView tv_date;
    private TextView show_select;
    private TextView method_select;
    private DatePicker.OnDateChangedListener listener = this;
    private int year, month, day;
    //在TextView上显示的字符
    private StringBuffer date;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subscribe);

        // 1.顶部沉浸式状态栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 2.沉浸式下方的三大金刚
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        // 3.顶部状态栏透明
        // 注意！！！1 和 3 不能同时使用
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        // 4.设置状态栏文字为暗色
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        show_select = findViewById(R.id.select);
        selectSubMethod();

        method_select = findViewById(R.id.method);
        methodSubMethod();


        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        context = this;
        date = new StringBuffer();
        tv_date = findViewById(R.id.tv_date);
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (date.length() > 0) { //清除上次记录的日期
                            date.delete(0, date.length());
                        }
                        tv_date.setText(date.append(String.valueOf(year)).append("年").append(String.valueOf(month)).append("月").append(day).append("日"));
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                final AlertDialog dialog = builder.create();
                View dialogView = View.inflate(context, R.layout.dialog_date, null);
                final DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.datePicker);

//              dialog.setTitle("设置日期");
                dialog.setView(dialogView);
                dialog.show();
                //初始化日期监听事件
                datePicker.init(year, month - 1, day, listener);
            }
        });
    }


    public void selectSubMethod() {
        show_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] method = new String[]{"按月订阅", "按季订阅", "按年订阅", "一次性买断"};
                AlertDialog.Builder builder = new AlertDialog.Builder(add_Subscribe.this);

                builder.setTitle("选择你的订阅方式");
                builder.setItems(method, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(getApplicationContext(), "你选择了" + method[i], Toast.LENGTH_SHORT).show();
                        show_select.setText(method[i]);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void methodSubMethod() {
        method_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] method = new String[]{"支付宝", "微信", "信用卡", "他人赠送"};
                AlertDialog.Builder builder = new AlertDialog.Builder(add_Subscribe.this);

                builder.setTitle("请选择你的支付方式");
                builder.setItems(method, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(getApplicationContext(), "你选择了" + method[i], Toast.LENGTH_SHORT).show();
                        method_select.setText(method[i]);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
    }

    public void toMain(View view) {
        Intent intent = new Intent(this, viewCard.class);
        startActivity(intent);
    }
}
