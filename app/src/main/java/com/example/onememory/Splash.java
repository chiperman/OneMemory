package com.example.onememory;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        // 全屏并且隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        // 隐藏标题栏 Activity
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        // 隐藏标题栏 AppCompatActivity
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        // 匿名Handler创建一个延时的调用
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent();
                intent.setClass(Splash.this, viewCard.class);  //从启动动画ui跳转到主界面
                Splash.this.startActivity(intent);
                Splash.this.finish(); // 结束启动界面
            }
        }, 2000);  // 启动动画持续2秒钟
    }
}