package com.example.onememory.exportOrImport;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.onememory.R;

public class CourseActivity extends Activity implements View.OnClickListener {

    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        back = findViewById(R.id.course_back);
        back.setOnClickListener(this);

        setPlaceUp();
    }

    //设置状态栏和导航栏
    public void setPlaceUp() {
        // 3.顶部状态栏透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        // 4.设置状态栏文字为暗色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }
}
