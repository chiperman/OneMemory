package com.example.onememory.diy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onememory.R;
import com.example.onememory.addSubscribe.Add_Subscribe;

import java.util.ArrayList;
import java.util.List;

public class DiyActivity extends Activity implements View.OnClickListener {
    private List<DiyItem> ItemList = new ArrayList<>();
    private ImageView diy_back;
    private ImageView color1;
    private ImageView color2;
    private ImageView color3;
    private ImageView color4;
    private ImageView color5;
    private ImageView color6;
    private ImageView color7;
    private ImageView color8;
    private ImageView color9;
    private ImageView color10;
    private ImageView color11;
    private ImageView color12;

    private Button confirm;
    public static ImageView diy_icon;
    public static int iconID;
    private EditText diy_name;
    private String backgroundcolor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_diy);

        // 初始化RecyclerView和Item
        intItems();
        RecyclerView rv = findViewById(R.id.My_recycler);

        // RecyclerView利用布局管理器使用Grid网格布局，4列布局
        GridLayoutManager gm = new GridLayoutManager(this, 6);
        rv.setLayoutManager(gm);

        // 设置适配器
        DiyItemAdapter adapter = new DiyItemAdapter(ItemList);
        rv.setAdapter(adapter);

        init();
        setPlaceUp();
    }

    public void init() {
        diy_back = findViewById(R.id.diy_back);
        diy_icon = findViewById(R.id.diy_icon);
        diy_name = findViewById(R.id.diy_name);
        confirm = findViewById(R.id.confirm);
        color1 = findViewById(R.id.color1);
        color2 = findViewById(R.id.color2);
        color3 = findViewById(R.id.color3);
        color4 = findViewById(R.id.color4);
        color5 = findViewById(R.id.color5);
        color6 = findViewById(R.id.color6);
        color7 = findViewById(R.id.color7);
        color8 = findViewById(R.id.color8);
        color9 = findViewById(R.id.color9);
        color10 = findViewById(R.id.color10);
        color11 = findViewById(R.id.color11);
        color12 = findViewById(R.id.color12);

        color1.setOnClickListener(this);
        color2.setOnClickListener(this);
        color3.setOnClickListener(this);
        color4.setOnClickListener(this);
        color5.setOnClickListener(this);
        color6.setOnClickListener(this);
        color7.setOnClickListener(this);
        color8.setOnClickListener(this);
        color9.setOnClickListener(this);
        color10.setOnClickListener(this);
        color11.setOnClickListener(this);
        color12.setOnClickListener(this);
        confirm.setOnClickListener(this);
        diy_back.setOnClickListener(this);
    }

    //设置状态栏和导航栏
    public void setPlaceUp() {
        // 3.顶部状态栏透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        // 4.设置状态栏文字为暗色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void intItems() {
        DiyItem item0 = new DiyItem(R.drawable.alarm);
        ItemList.add(item0);
        DiyItem item1 = new DiyItem(R.drawable.bankcard);
        ItemList.add(item1);
        DiyItem item2 = new DiyItem(R.drawable.beer);
        ItemList.add(item2);
        DiyItem item3 = new DiyItem(R.drawable.book);
        ItemList.add(item3);
        DiyItem item4 = new DiyItem(R.drawable.bowl);
        ItemList.add(item4);
        DiyItem item5 = new DiyItem(R.drawable.brush);
        ItemList.add(item5);
        DiyItem item6 = new DiyItem(R.drawable.calculator);
        ItemList.add(item6);
        DiyItem item7 = new DiyItem(R.drawable.camera);
        ItemList.add(item7);
        DiyItem item8 = new DiyItem(R.drawable.cart);
        ItemList.add(item8);
        DiyItem item9 = new DiyItem(R.drawable.chat);
        ItemList.add(item9);
        DiyItem item10 = new DiyItem(R.drawable.clothes);
        ItemList.add(item10);
        DiyItem item11 = new DiyItem(R.drawable.cloud);
        ItemList.add(item11);
        DiyItem item12 = new DiyItem(R.drawable.mail);
        ItemList.add(item12);
        DiyItem item13 = new DiyItem(R.drawable.computer);
        ItemList.add(item13);
        DiyItem item14 = new DiyItem(R.drawable.wallet);
        ItemList.add(item14);
        DiyItem item15 = new DiyItem(R.drawable.earth);
        ItemList.add(item15);
        DiyItem item16 = new DiyItem(R.drawable.file);
        ItemList.add(item16);
        DiyItem item17 = new DiyItem(R.drawable.flower);
        ItemList.add(item17);
        DiyItem item18 = new DiyItem(R.drawable.fork);
        ItemList.add(item18);
        DiyItem item19 = new DiyItem(R.drawable.game);
        ItemList.add(item19);
        DiyItem item20 = new DiyItem(R.drawable.lock);
        ItemList.add(item20);
        DiyItem item21 = new DiyItem(R.drawable.phone);
        ItemList.add(item21);
        DiyItem item22 = new DiyItem(R.drawable.picture);
        ItemList.add(item22);
        DiyItem item23 = new DiyItem(R.drawable.purchase);
        ItemList.add(item23);
        DiyItem item24 = new DiyItem(R.drawable.radio);
        ItemList.add(item24);
        DiyItem item25 = new DiyItem(R.drawable.router);
        ItemList.add(item25);
        DiyItem item26 = new DiyItem(R.drawable.shop);
        ItemList.add(item26);
        DiyItem item27 = new DiyItem(R.drawable.switcher);
        ItemList.add(item27);
        DiyItem item28 = new DiyItem(R.drawable.umbrella);
        ItemList.add(item28);
        DiyItem item29 = new DiyItem(R.drawable.vedio);
        ItemList.add(item29);
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
                intent.putExtra("fontColor", "#FFFFFF");
                intent.putExtra("background", backgroundcolor);
                if (diy_name.getText().toString().equals("")) {
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setTitle("填写信息")//简单易懂的设置title
                            .setMessage("请填写订阅名称后可确认")
                            // 确定按钮
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                    dialog.show();
                } else if (DiyItemAdapter.flag == false) {
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setTitle("填写信息")//简单易懂的设置title
                            .setMessage("请选择订阅图标后可确认")
                            // 确定按钮
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                    dialog.show();
                } else {
                    startActivity(intent);
                }
                break;
            case R.id.color1:
                backgroundcolor = "#000000";
                diy_icon.setBackgroundColor(Color.parseColor(backgroundcolor));
                break;
            case R.id.color2:
                backgroundcolor = "#343434";
                diy_icon.setBackgroundColor(Color.parseColor(backgroundcolor));
                break;
            case R.id.color3:
                backgroundcolor = "#383a48";
                diy_icon.setBackgroundColor(Color.parseColor(backgroundcolor));
                break;
            case R.id.color4:
                backgroundcolor = "#373541";
                diy_icon.setBackgroundColor(Color.parseColor(backgroundcolor));
                break;
            case R.id.color5:
                backgroundcolor = "#373f42";
                diy_icon.setBackgroundColor(Color.parseColor(backgroundcolor));
            case R.id.color6:
                backgroundcolor = "#28373c";
                diy_icon.setBackgroundColor(Color.parseColor(backgroundcolor));
                break;
            case R.id.color7:
                backgroundcolor = "#423747";
                diy_icon.setBackgroundColor(Color.parseColor(backgroundcolor));
                break;
            case R.id.color8:
                backgroundcolor = "#0f4c81";
                diy_icon.setBackgroundColor(Color.parseColor(backgroundcolor));
                break;
            case R.id.color9:
                backgroundcolor = "#586cff";
                diy_icon.setBackgroundColor(Color.parseColor(backgroundcolor));
                break;
            case R.id.color10:
                backgroundcolor = "#fb518b";
                diy_icon.setBackgroundColor(Color.parseColor(backgroundcolor));
                break;
            case R.id.color11:
                backgroundcolor = "#f1908c";
                diy_icon.setBackgroundColor(Color.parseColor(backgroundcolor));
                break;
            case R.id.color12:
                backgroundcolor = "#fea41d";
                diy_icon.setBackgroundColor(Color.parseColor(backgroundcolor));
                break;
        }
    }
}
