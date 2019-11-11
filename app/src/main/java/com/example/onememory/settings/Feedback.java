package com.example.onememory.settings;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onememory.R;

public class Feedback extends Activity implements View.OnClickListener {

    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

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

        iv_back = findViewById(R.id.fd_back);
        iv_back.setOnClickListener(this);
    }

    public void back(View view) {
        onBackPressed();
    }

    public void submit(View view) {
        EditText editText = findViewById(R.id.help_feedback);
        Toast.makeText(Feedback.this, "提交反馈：" + editText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
