package com.example.onememory.settings;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onememory.R;

public class Settings extends Activity implements View.OnClickListener {

    private ImageView iv_back;
    private String name;
    private String feedback_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setPlaceUp();

        iv_back = findViewById(R.id.st_back);
        iv_back.setOnClickListener(this);
        feedback_email = getString(R.string.feedback);
    }

    //设置状态栏和导航栏
    public void setPlaceUp() {
        // 3.顶部状态栏透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        // 4.设置状态栏文字为暗色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    //意见反馈
    public void feedback(View view) {
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:onememory.549app@gmail.com"));
        data.putExtra(Intent.EXTRA_SUBJECT, "[OneMemory - 意见反馈]");
        data.putExtra(Intent.EXTRA_TEXT, feedback_email);
        startActivity(data);
    }

    //好评鼓励
    public void encouragement(View view) {
        try {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(Settings.this, "设备可能未安装应用商店类应用", Toast.LENGTH_SHORT).show();
            //Utils.showToast(this, “设备可能未安装应用商店类应用”);
        }
    }

    //推荐好友
    public void Recommend(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "主题");
        intent.putExtra(Intent.EXTRA_TEXT, "这个软件很好");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "分享给好友"));
    }

    //关于作者
    public void AboutAuthor(View view) {
        Intent intent = new Intent(this, Aboutus.class);
        startActivity(intent);
    }

    //隐私协议
    public void Agreement(View view) {
        Intent intent = new Intent(this, Privacy.class);
        startActivity(intent);
    }

    //检查更新:(我们升级版本的原理就是，请求后台，拿到当前程序的版本号，也就是VersionCode,去和请求到的数据作比对，如果自己的服务器返回的版本号大于当前的就可以提示更新了。)
    public void update(View view) {
        int version = 0;
        try {
            PackageManager packageManager = Settings.this.getPackageManager();
            //getPackageName()是你当前程序的包名
            PackageInfo packInfo = packageManager.getPackageInfo(Settings.this.getPackageName(), 0);
            version = packInfo.versionCode;
            name = packInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(Settings.this, "已经是最新版本：" + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
