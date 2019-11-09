package com.example.onememory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onememory.mainActivity.SubscribeAdapter;
import com.example.onememory.settings.settings;
import com.example.onememory.addSubscribe.add_Subscribe;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private ArrayList<String> app_name;
    private ArrayList<Integer> icon_res_ID;
    private ArrayList<Float> cost;
    private int[] imageID = {R.drawable.iqiyi, R.drawable.bilibili, R.drawable.sina, R.drawable.sspai};
    private String[] name = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private ImageButton ib_setting;
    private ImageButton ib_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ib_setting = findViewById(R.id.ib_setting);
        ib_add = findViewById(R.id.ib_add);
        ib_add.setOnClickListener(this);
        ib_setting.setOnClickListener(this);


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
        SubscribeAdapter adapter = new SubscribeAdapter(this, app_name, cost, icon_res_ID);
        final RecyclerView recyclerView = findViewById(R.id.rv_sub_app);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

    private void initAppInfo() {
        app_name = new ArrayList<>();
        cost = new ArrayList<>();
        icon_res_ID = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            app_name.add(name[i]);
            cost.add((float) i);
            if (i < imageID.length) {
                icon_res_ID.add(imageID[i]);
            }

        }

    }

    @Override
    public void onClick(View v) {
        // 填写
        Intent intent;
        switch (v.getId()) {
            case R.id.ib_add:
                intent = new Intent(MainActivity.this, add_Subscribe.class);
                startActivity(intent);
                break;
            case R.id.ib_setting:
                intent = new Intent(MainActivity.this, settings.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
