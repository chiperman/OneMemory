package com.example.onememory.addSubscribe;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.onememory.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Add_Subscribe extends Activity implements DatePicker.OnDateChangedListener, View.OnClickListener {

    private TextView tv_date;       // 订阅时间
    private TextView show_select;   // 订阅类型
    private TextView method_select; // 订阅方式
    private DatePicker.OnDateChangedListener listener = this;
    private int year, month, day;
    //在TextView上显示的字符
    private StringBuffer date;
    private Context context;
    private ImageView iv_back;
    private ImageView iv_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

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
        show_select.setOnClickListener(this);

        method_select = findViewById(R.id.method);
        method_select.setOnClickListener(this);

        iv_back = findViewById(R.id.sub_back);
        iv_back.setOnClickListener(this);

        iv_add = findViewById(R.id.sub_add);
        iv_add.setOnClickListener(this);

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

//    *****************中间弹框式*****************
//    public void selectSubMethod() {
//        show_select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String[] method = new String[]{"按月订阅", "按季订阅", "按年订阅", "一次性买断"};
//                AlertDialog.Builder builder = new AlertDialog.Builder(Add_Subscribe.this);
//
//                builder.setTitle("选择你的订阅方式");
//                builder.setItems(method, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int i) {
//                        Toast.makeText(getApplicationContext(), "你选择了" + method[i], Toast.LENGTH_SHORT).show();
//                        show_select.setText(method[i]);
//                    }
//                });
//                AlertDialog alert = builder.create();
//                alert.show();
//            }
//        });
//    }
//    *****************中间弹框式截止*****************


    //    *****************底部式*****************
    public void selectSubMethod() {
        show_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectPickerView();
            }
        });
    }

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
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
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

//    *****************中间弹框式*****************
//    public void methodSubMethod() {
//        method_select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String[] method = new String[]{"支付宝", "微信", "信用卡", "他人赠送"};
//                AlertDialog.Builder builder = new AlertDialog.Builder(Add_Subscribe.this);
//
//                builder.setTitle("请选择你的支付方式");
//                builder.setItems(method, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int i) {
//                        Toast.makeText(getApplicationContext(), "你选择了" + method[i], Toast.LENGTH_SHORT).show();
//                        method_select.setText(method[i]);
//                    }
//                });
//                AlertDialog alert = builder.create();
//                alert.show();
//            }
//        });
//    }
//    *****************中间弹框式截止*****************

    //    *****************底部式*****************
    public void methodSubMethod() {
        method_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMethodPickerView();
            }
        });
    }

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
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
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

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sub_back:
                onBackPressed();
                break;
            case R.id.sub_add:
                onBackPressed();
                break;
            case R.id.select:
                selectSubMethod();
            case R.id.method:
                methodSubMethod();
            default:
                break;
        }
    }
}
