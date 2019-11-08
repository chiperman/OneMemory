package com.example.onememory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

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

    //意见反馈x
    public void feedback(View view) {
        Intent intent = new Intent(this, feedback.class);
        startActivity(intent);
    }

    //好评鼓励
    public void encouragement(View view) {
        try {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(settings.this, "设备可能未安装应用商店类应用", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(settings.this);
        builder.setTitle("关于作者");
        builder.setMessage("本产品由549小组独家制作，本小组人员非常帅气");
        builder.setPositiveButton("了解", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(settings.this, "点击了确定", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    //隐私协议
    public void Agreement(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(settings.this);
        builder.setTitle("隐私协议");
        builder.setMessage(R.string.agreement);
        builder.setPositiveButton("了解", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(settings.this, "已了解协议", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    //检查更新:(我们升级版本的原理就是，请求后台，拿到当前程序的版本号，也就是VersionCode,去和请求到的数据作比对，如果自己的服务器返回的版本号大于当前的就可以提示更新了。)
    public void update(View view) {
        int version = 0;
        try {
            PackageManager packageManager = settings.this.getPackageManager();
            //getPackageName()是你当前程序的包名
            PackageInfo packInfo = packageManager.getPackageInfo(settings.this.getPackageName(), 0);
            version = packInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(settings.this, "已经是最新版本：" + version, Toast.LENGTH_SHORT).show();
    }

    public void toMain(View view) {
        Intent intent = new Intent(this, viewCard.class);
        startActivity(intent);
    }
}
