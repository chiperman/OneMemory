package com.example.onememory;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onememory.Rylist.AddListActivity;
import com.example.onememory.apps.App;
import com.example.onememory.database.OneDatabaseHelper;
import com.example.onememory.mainActivity.SubscribeAdapter;
import com.example.onememory.settings.Settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends Activity implements View.OnClickListener, Serializable {
    public static ArrayList<App> apps = new ArrayList<>();
    public static int itemNum = 0;
    public static float totalCost = 0;
    public static float monthCost = 0;
    private static SQLiteDatabase database;
    private TextView tv_itemNum, tv_totalCost, tv_monthCost;
    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private ImageView iv_setting;
    private ImageView iv_add;

    public static SQLiteDatabase getDatabase() {
        return database;
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "onResume");
        super.onResume();
//        initAppList();
//        initAppInfo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate");
        super.onCreate(savedInstanceState);
//        smoothSwitchScreen(); 解决页面下移
        setContentView(R.layout.activity_main);
        tv_itemNum = findViewById(R.id.itemNumber);
        tv_monthCost = findViewById(R.id.monthCost);
        tv_totalCost = findViewById(R.id.totalCost);

        OneDatabaseHelper oneDatabaseHelper = new OneDatabaseHelper(this, "One", null, 3);
        database = oneDatabaseHelper.getWritableDatabase();


        iv_setting = findViewById(R.id.iv_setting);
        iv_setting.setOnClickListener(this);
        iv_add = findViewById(R.id.iv_add);
        iv_add.setOnClickListener(this);


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

        initAppInfo();

        initAppList();
        tv_itemNum.setText(itemNum + "\n个项目");
        tv_totalCost.setText(totalCost + "\n总花费（元）");

    }

    // 在主页面按下 Back 按钮后，重新打开 App 直接打开 Mainactivity，不经过 Splash 页面
    public void onBackPressed() {
        // Do NOT call super.onBackPressed() 不要调用父类的方法
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    // 解决页面下移
//    private void smoothSwitchScreen() {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            ViewGroup rootView = ((ViewGroup) this.findViewById(android.R.id.content));
//            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//            int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
//            rootView.setPadding(0, statusBarHeight, 0, 0);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }
//
//    }

    private void initAppList() {
        SubscribeAdapter adapter = new SubscribeAdapter(this, apps);
        recyclerView = findViewById(R.id.rv_sub_app);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

    private void initAppInfo() {
        Intent intent = getIntent();
        int AppIcon;
        String AppName, bg_color, text_color, add_describe, tv_date, show_select, method_select;
        float app_money;
        AppIcon = intent.getIntExtra("AppIcon", 0);
        AppName = intent.getStringExtra("AppName");
        bg_color = intent.getStringExtra("bg_color");
        text_color = intent.getStringExtra("text_color");
        add_describe = intent.getStringExtra("add_describe");
        tv_date = intent.getStringExtra("tv_date");
        show_select = intent.getStringExtra("show_select");
        method_select = intent.getStringExtra("method_select");
        String money = intent.getStringExtra("app_money") != null ? intent.getStringExtra("app_money") : "0";
        app_money = Float.parseFloat(money);
        if (AppName != null) {
            App app = new App();
            app.setId(intent.getIntExtra("id", -1));
            app.setIconId(AppIcon);
            app.setName(AppName);
            app.setMoney(app_money);
            app.setBgColor(bg_color);
            app.setTextColor(text_color);
            app.setDescription(add_describe);
            app.setSubTime(tv_date);
            app.setSubPeriod(show_select);
            app.setPayMethod(method_select);
            totalCost += app_money;
            ++itemNum;
            apps.add(app);
        }

        // 从数据库读取
        if (!apps.isEmpty()) {
            return;
        }
        String[] columns = new String[]{"id", "name", "iconId", "description", "money", "sub_time", "sub_period", "pay_method", "bg_color", "text_color"};
        Cursor cursor = database.query("apps", columns, null, null, null, null, null);
        int i = 0;
        while (cursor.moveToNext()) {
            Log.e(TAG, "从数据库读取");
            App app = new App();
            app.setName(cursor.getString(cursor.getColumnIndex("name")));
            app.setIconId(cursor.getInt(cursor.getColumnIndex("iconId")));
            app.setMoney(cursor.getFloat(cursor.getColumnIndex("money")));
            app.setBgColor(cursor.getString(cursor.getColumnIndex("bg_color")));
            app.setTextColor(cursor.getString(cursor.getColumnIndex("text_color")));
            app.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            app.setSubTime(cursor.getString(cursor.getColumnIndex("sub_time")));
            app.setSubPeriod(cursor.getString(cursor.getColumnIndex("sub_period")));
            app.setPayMethod(cursor.getString(cursor.getColumnIndex("pay_method")));
            switch (app.getSubPeriod()) {
                case "按周订阅":
                case "按月订阅":
                    monthCost += app.getMoney();
                    break;
                case "按季订阅":
                    monthCost += app.getMoney() / 3;
                    break;
                case "按年订阅":
                    monthCost += app.getMoney() / 12;
                    break;
                case "一次性买断":
                    break;
                case "":
                    break;
            }
            totalCost += app.getMoney();
            ++itemNum;
            apps.add(app);
            //获取系统的日期
            Calendar calendar = Calendar.getInstance();

            //年
            int year = calendar.get(Calendar.YEAR);
            //月
            int month = calendar.get(Calendar.MONTH) + 1;
            //日
            int day = calendar.get(Calendar.DAY_OF_MONTH);


            String[] time0 = app.getSubTime().split("-");
            Log.e("长度", String.valueOf(time0.length));
            Integer[] time = new Integer[time0.length];
            //字符型数组转换为整形数组
            for (int j = 0; j < time0.length; j++) {
                if (time0[j] != "")
                    time[j] = Integer.parseInt(time0[j]);
                else time[j] = 0;
            }
            switch (app.getSubPeriod()) {
                case "按周订阅":
                    time[2] = time[2] + 7;
                    //判断该时间是否为下一个月，暂时按照一个月30天算
                    if (time[2] > 30) {
                        time[1]++;
                        if (time[1] == 13) {
                            time[1] = 1;
                            time[0]++;
                        }
                    }
                    break;
                case "按月订阅":
                    time[1]++;
                    //判断该月是否为最后一个月
                    if (time[1] == 13) {
                        time[1] = 1;
                        time[0]++;
                    }
                    break;
                case "按季订阅":
                    time[1] = time[1] + 3;
                    //判断该月是否为最后一个月
                    if (time[1] > 12) {
                        time[1] = time[1] % 12;
                        time[0]++;
                    }
                    break;
                case "按年订阅":
                    time[0]++;
                    break;
                case "一次性买断":
                    time[0] = time[0] + 100;
                    break;
                case "":
                    break;
            }
            //Log.e("时间2", String.valueOf(time[0]));
            if (year == time[0] && month == time[1] && time[0] != 0 && day < time[2]) {
                Log.e("时间2", String.valueOf(time[0]));
                Log.e("时间3", String.valueOf(time[1]));
                Log.e("时间4", String.valueOf(time[2]));
                //发出通知
                notification(app.getName(), app.getIconId(), i++, time[2] - day);
            }
        }


        //取一位小数
        //monthCost = (float) (Integer.parseInt(String.valueOf(monthCost*10))/10.0);
        tv_itemNum.setText(itemNum + "\n个项目");
        tv_monthCost.setText(String.format("%.1f", monthCost) + "\n每月花费（元）");
        tv_totalCost.setText(totalCost + "\n总花费（元）");

    }

    //通知函数
    private void notification(String appname, int icon, int num, int days) {
        //1.通知管理器
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //2.建立一个通知
        Notification notification = null;

        String id = "mchannel";
        String name = "通道1";
        //判断当前安卓版本为8.0后
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            Log.i("ooo:", mChannel.toString());

            notificationManager.createNotificationChannel(mChannel);

            notification = new Notification.Builder(this, id)
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), icon))
                    .setSmallIcon(R.drawable.add_black)
                    .setContentTitle(appname + "会员")  //标题
                    .setContentText("你的会员服务还有" + days + "天到期，请注意！")   //内容
                    //.setSubText("会员到期提醒")     //内容下面的一小段文字
                    .setTicker("收到一条会员订阅提醒~~")      //收到信息后状态栏显示的文字信息
                    .setWhen(System.currentTimeMillis())    //系统显示时间
                    .setAutoCancel(true)       //设置点击后取消Notificatio
                    //3.绑定一个通知显示界面
                    .setContentIntent(PendingIntent.getActivity(this, num, new Intent(this, Settings.class), PendingIntent.FLAG_CANCEL_CURRENT))
                    .build();
        }
        //8.0之前
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Log.e("当前机型版本号", String.valueOf(Build.VERSION.SDK_INT));
            notification = new Notification.Builder(this)
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), icon))
                    .setSmallIcon(R.drawable.add_black)
                    .setContentTitle(appname + "会员")  //标题
                    .setContentText("你的会员服务还有" + days + "天到期，请注意！")   //内容
                    //.setSubText("会员到期提醒")     //内容下面的一小段文字
                    .setTicker("收到一条会员订阅提醒~~")      //收到信息后状态栏显示的文字信息
                    .setWhen(System.currentTimeMillis())    //系统显示时间
                    .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)    //设置默认的三色灯与振动器
                    .setDefaults(Notification.DEFAULT_SOUND)    //设置系统的提示音
                    //.setOngoing(true)    //设置为系统不能清除通知
                    .setAutoCancel(true)       //设置点击后取消Notificatio
                    //3.绑定一个通知显示界面
                    .setContentIntent(PendingIntent.getActivity(this, num, new Intent(this, Settings.class), PendingIntent.FLAG_CANCEL_CURRENT))
                    .build();
        }

        //4.发出通知
        notificationManager.notify(num, notification);

    }

    @Override
    public void onClick(View v) {
        // 填写
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_setting:
                intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                break;
            case R.id.iv_add:
                intent = new Intent(MainActivity.this, AddListActivity.class);
                startActivity(intent);
//                finish();
                break;
            default:
                break;
        }
    }
}
