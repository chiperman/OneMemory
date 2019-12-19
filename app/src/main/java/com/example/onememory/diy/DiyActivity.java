package com.example.onememory.diy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.onememory.R;
import com.example.onememory.addSubscribe.Add_Subscribe;

import java.util.ArrayList;
import java.util.List;

public class DiyActivity extends Activity implements View.OnClickListener {
    private List<DiyItem> ItemList = new ArrayList<>();
    private ImageView diy_back;
    private Button confirm;
    public static ImageView diy_icon;
    public static int iconID;
    private EditText diy_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy);

        diy_icon = findViewById(R.id.diy_icon);
        diy_name = findViewById(R.id.diy_name);
        // 初始化RecyclerView和Item
        intItems();
        RecyclerView rv = findViewById(R.id.My_recycler);

        // RecyclerView利用布局管理器使用Grid网格布局，4列布局
        GridLayoutManager gm = new GridLayoutManager(this, 6);
        rv.setLayoutManager(gm);

        // 设置适配器
        DiyItemAdapter adapter = new DiyItemAdapter(ItemList);
        rv.setAdapter(adapter);

        diy_back = findViewById(R.id.diy_back);
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(this);
        diy_back.setOnClickListener(this);
        setPlaceUp();
    }

    //设置状态栏和导航栏
    public void setPlaceUp() {
        // 3.顶部状态栏透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        // 4.设置状态栏文字为暗色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void intItems() {
        DiyItem item0 = new DiyItem(R.drawable.bed);
        ItemList.add(item0);
        DiyItem item1 = new DiyItem(R.drawable.bed);
        ItemList.add(item1);
        DiyItem item2 = new DiyItem(R.drawable.bed);
        ItemList.add(item2);
        DiyItem item3 = new DiyItem(R.drawable.bed);
        ItemList.add(item3);
        DiyItem item4 = new DiyItem(R.drawable.bed);
        ItemList.add(item4);
        DiyItem item5 = new DiyItem(R.drawable.bed);
        ItemList.add(item5);
        DiyItem item6 = new DiyItem(R.drawable.bed);
        ItemList.add(item6);
        DiyItem item7 = new DiyItem(R.drawable.bed);
        ItemList.add(item7);
        DiyItem item8 = new DiyItem(R.drawable.bed);
        ItemList.add(item8);
        DiyItem item9 = new DiyItem(R.drawable.bed);
        ItemList.add(item9);
        DiyItem item10 = new DiyItem(R.drawable.bed);
        ItemList.add(item10);
        DiyItem item11 = new DiyItem(R.drawable.bed);
        ItemList.add(item11);
        DiyItem item12 = new DiyItem(R.drawable.bed);
        ItemList.add(item12);
        DiyItem item13 = new DiyItem(R.drawable.bed);
        ItemList.add(item13);
        DiyItem item14 = new DiyItem(R.drawable.bed);
        ItemList.add(item14);
        DiyItem item15 = new DiyItem(R.drawable.bed);
        ItemList.add(item15);
        DiyItem item16 = new DiyItem(R.drawable.bed);
        ItemList.add(item16);
        DiyItem item17 = new DiyItem(R.drawable.bed);
        ItemList.add(item17);
        DiyItem item18 = new DiyItem(R.drawable.bed);
        ItemList.add(item18);
        DiyItem item19 = new DiyItem(R.drawable.bed);
        ItemList.add(item19);
        DiyItem item20 = new DiyItem(R.drawable.bed);
        ItemList.add(item20);
        DiyItem item21 = new DiyItem(R.drawable.bed);
        ItemList.add(item21);
        DiyItem item22 = new DiyItem(R.drawable.bed);
        ItemList.add(item22);
        DiyItem item23 = new DiyItem(R.drawable.bed);
        ItemList.add(item23);
        DiyItem item24 = new DiyItem(R.drawable.bed);
        ItemList.add(item24);
        DiyItem item25 = new DiyItem(R.drawable.bed);
        ItemList.add(item25);
        DiyItem item26 = new DiyItem(R.drawable.bed);
        ItemList.add(item26);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.diy_back:
                onBackPressed();
                break;
            case R.id.confirm:
                Intent intent = new Intent(this, Add_Subscribe.class);
                intent.putExtra("iconID", iconID);
                intent.putExtra("diyName", diy_name.getText().toString());
                intent.putExtra("fontColor", "#000000");

                startActivity(intent);
        }
    }
}
