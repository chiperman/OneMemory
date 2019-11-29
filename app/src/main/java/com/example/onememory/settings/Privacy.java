package com.example.onememory.settings;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onememory.R;

public class Privacy extends Activity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        setPlaceUp();

        init();
    }

    public void setPlaceUp() {
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
    }


    public void init() {
        tv_text = findViewById(R.id.github);
        tv_text.setOnClickListener(this);
        iv_back = findViewById(R.id.privacy_back);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.github:
                Uri uri = Uri.parse("https://github.com/chiperman/OneMemory");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.privacy_back:
                onBackPressed();
                break;
        }
    }
}
