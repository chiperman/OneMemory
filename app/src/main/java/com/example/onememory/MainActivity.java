package com.example.onememory;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onememory.mainActivity.SubscribeAdapter;
import com.example.onememory.settings.Settings;
import com.example.onememory.Rylist.AddListActivity;


import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private static ArrayList<String> app_names = new ArrayList<>();
    private static ArrayList<Integer> iconIDs = new ArrayList<>();
    private static ArrayList<Float> costs = new ArrayList<>();
    private static ArrayList<String> bgColors = new ArrayList<>();
    private static ArrayList<String> textColors = new ArrayList<>();
    private static ArrayList<String> describe = new ArrayList<>();
    private static ArrayList<String> date = new ArrayList<>();
    private static ArrayList<String> shows = new ArrayList<>();
    private static ArrayList<String> methods = new ArrayList<>();

    private ImageView iv_setting;
    private ImageView iv_add;

    @Override
    protected void onResume() {
        super.onResume();
        initAppList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_setting = findViewById(R.id.iv_setting);
        iv_setting.setOnClickListener(this);
        iv_add = findViewById(R.id.iv_add);
        iv_add.setOnClickListener(this);


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

        initAppInfo();

        initAppList();

    }

    private void initAppList() {
        SubscribeAdapter adapter = new SubscribeAdapter(this, app_names, costs, iconIDs, bgColors, textColors);
        final RecyclerView recyclerView = findViewById(R.id.rv_sub_app);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

    private void initAppInfo() {
        Intent intent = getIntent();
        int AppIcon;
        String AppName, bg_color, text_color, add_describe, tv_date, show_select, method_select;
        float app_money;
        AppIcon = intent.getIntExtra("AppIcon", 0);
        AppName = intent.getStringExtra("AppName");
        bg_color = intent.getStringExtra("bg_color");
        text_color = intent.getStringExtra("text_color");
        add_describe = intent.getStringExtra("add_describe");
        tv_date = intent.getStringExtra("tv_date");
        show_select = intent.getStringExtra("show_select");
        method_select = intent.getStringExtra("method_select");
        String money = intent.getStringExtra("app_money") != null ? intent.getStringExtra("app_money") : "0";
        app_money = Float.parseFloat(money);
        if (AppName != null) {
            iconIDs.add(AppIcon);
            app_names.add(AppName);
            costs.add(app_money);
            bgColors.add(bg_color);
            textColors.add(text_color);
            describe.add(add_describe);
            date.add(tv_date);
            shows.add(show_select);
            methods.add(method_select);
        }




    }

    @Override
    public void onClick(View v) {
        // 填写
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_setting:
                intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                break;
            case R.id.iv_add:
                intent = new Intent(MainActivity.this, AddListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
