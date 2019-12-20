package com.example.onememory.Splash;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onememory.MainActivity;
import com.example.onememory.R;

import java.util.Calendar;

public class Splash extends AppCompatActivity {

    private TextView splash_slogon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash_slogon = findViewById(R.id.splash_slogon);

        // 获取系统的日期
        Calendar calendar = Calendar.getInstance();
        // 获取当前系统日期
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // 如果日期是12.25号，切换首页图片为圣诞节彩蛋
        if (month == 12 && day == 24 || day == 25 || day == 26) {
            splash_slogon.setText(R.string.christmas);

            Typeface type = Typeface.createFromAsset(getAssets(), "res/font/dancingscript.ttf");
            splash_slogon.setTypeface(type);
        }


        setPlaceUp();
        // 匿名Handler创建一个延时的调用
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent();
                intent.setClass(Splash.this, MainActivity.class);  //从启动动画ui跳转到主界面
                Splash.this.startActivity(intent);
                Splash.this.finish(); // 结束启动界面
            }
        }, 2000);  // 启动动画持续2秒钟
    }

    //设置状态栏和导航栏
    public void setPlaceUp() {
        // 全屏并且隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 隐藏标题栏 AppCompatActivity
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

}