package com.example.onememory.viewCard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onememory.R;
import com.example.onememory.Rylist.AddListActivity;
import com.example.onememory.apps.App;
import com.example.onememory.settings.Settings;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewCard extends Activity implements Serializable {
    private RecyclerView rv;
    private ArrayList<App> apps;
    private MyRecyclerViewAdapter adapter;
    private Button btn;
    // 获取当前App的位置
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_card);

        Intent intent = getIntent();
        apps = (ArrayList<App>) intent.getSerializableExtra("apps");

        position = intent.getIntExtra("app", 0);

        rv = findViewById(R.id.my_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // 设置横向滑动
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        rv.setLayoutManager(linearLayoutManager);
        adapter = new MyRecyclerViewAdapter(apps);
        // 指向当前点击App的位置
        rv.scrollToPosition(position);
        rv.setAdapter(adapter);
        setPlaceUp();
    }

    //设置状态栏和导航栏
    public void setPlaceUp() {
        // 顶部状态栏透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        // 设置状态栏文字为暗色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void toSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void toAdd(View view) {
        Intent intent = new Intent(this, AddListActivity.class);
        startActivity(intent);
    }
}
