package com.example.onememory;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onememory.Rylist.AddListActivity;
import com.example.onememory.database.OneDatabaseHelper;
import com.example.onememory.mainActivity.SubscribeAdapter;
import com.example.onememory.settings.Settings;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    public static ArrayList<String> app_names = new ArrayList<>();
    public static ArrayList<Integer> iconIDs = new ArrayList<>();
    public static ArrayList<Float> costs = new ArrayList<>();
    public static ArrayList<String> bgColors = new ArrayList<>();
    public static ArrayList<String> textColors = new ArrayList<>();
    public static ArrayList<String> describe = new ArrayList<>();
    public static ArrayList<String> date = new ArrayList<>();
    public static ArrayList<String> shows = new ArrayList<>();
    public static ArrayList<String> methods = new ArrayList<>();
    public static SQLiteDatabase database;


    private ImageView iv_setting;
    private ImageView iv_add;

    public static SQLiteDatabase getDatabase() {
        return database;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initAppList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        OneDatabaseHelper oneDatabaseHelper = new OneDatabaseHelper(this, "One", null, 3);
        database = oneDatabaseHelper.getWritableDatabase();


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

    // 在主页面按下 Back 按钮后，重新打开 App 直接打开 Mainactivity，不经过 Splash 页面
    public void onBackPressed() {
        // Do NOT call super.onBackPressed()
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
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

        // 从数据库读取
        if (!app_names.isEmpty()) {
            return;
        }
        String[] columns = new String[]{"id", "name", "iconId", "description", "money", "sub_time", "sub_period", "pay_method", "bg_color", "text_color"};
        Cursor cursor = database.query("apps", columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            app_names.add(cursor.getString(cursor.getColumnIndex("name")));
            iconIDs.add(cursor.getInt(cursor.getColumnIndex("iconId")));
            costs.add(cursor.getFloat(cursor.getColumnIndex("money")));
            bgColors.add(cursor.getString(cursor.getColumnIndex("bg_color")));
            textColors.add(cursor.getString(cursor.getColumnIndex("text_color")));
            describe.add(cursor.getString(cursor.getColumnIndex("description")));
            date.add(cursor.getString(cursor.getColumnIndex("sub_time")));
            shows.add(cursor.getString(cursor.getColumnIndex("sub_period")));
            methods.add(cursor.getString(cursor.getColumnIndex("pay_method")));
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
//                finish();
                break;
            default:
                break;
        }
    }
}
