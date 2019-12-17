package com.example.onememory.Rylist;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onememory.R;

import java.util.ArrayList;
import java.util.List;

public class AddListActivity extends Activity implements View.OnClickListener {

    private List<Fruit> fruitList = new ArrayList<>();
    private ImageView iv_back;
    private ImageView iv_search;
    private TextView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        //为输入框设置动态监听
        EditText editText = findViewById(R.id.search_et_input);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Toast.makeText(getApplication(),s.toString(),Toast.LENGTH_SHORT).show();

                fruitList.clear();
                initFruits(s.toString());
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                LinearLayoutManager layoutManager = new LinearLayoutManager(AddListActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                FruitAdapter adapter = new FruitAdapter(fruitList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
        //页面的初始数据
        initFruits("");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        iv_back = findViewById(R.id.list_back);
        iv_back.setOnClickListener(this);
        iv_search = findViewById(R.id.list_search);
        iv_search.setOnClickListener(this);
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
    }

    //菜单数量的设置 SelectName为搜索参数
    private void initFruits(String SelectName) {
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<Integer> picName = new ArrayList<Integer>();
        ArrayList<Integer> white = new ArrayList<>();
        ArrayList<String> background_color = new ArrayList<>();
        ArrayList<String> text_color = new ArrayList<>();
        ArrayList<String> text_hintcolor = new ArrayList<>();
        ArrayList<String> url = new ArrayList<>();


        name.add("App Store");
        picName.add(R.drawable.app_store_ios);
        white.add(R.drawable.app_store_ios_w);
        background_color.add("#1F74F9");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");


        name.add("哔哩哔哩");
        picName.add(R.drawable.bilibili_fill);
        white.add(R.drawable.bilibili_fill_w);
        background_color.add("#00a1d6");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("https://www.bilibili.com/");

        name.add("QQ音乐");
        picName.add(R.drawable.qq_music);
        white.add(R.drawable.qq_music_w);
        background_color.add("#CCffdc00");
        text_color.add("#000000");
        text_hintcolor.add("#000000");
        url.add("https://y.qq.com/portal/vipportal/index.html");

        name.add("iCloud");
        picName.add(R.drawable.icloud);
        white.add(R.drawable.icloud_w);
        background_color.add("#3c78e7");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("https://support.apple.com/zh-cn/HT201318");

        name.add("网易云音乐");
        picName.add(R.drawable.netease_cloud_music);
        white.add(R.drawable.netease_cloud_music_w);
        background_color.add("#dd001b");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("https://music.163.com/#/member");

        name.add("Netflix");
        picName.add(R.drawable.netflix);
        white.add(R.drawable.netflix_w);
        background_color.add("#e50914");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("https://www.netflix.com/");

        name.add("Nintendo Switch");
        picName.add(R.drawable.nintendo_switch);
        white.add(R.drawable.nintendo_switch_w);
        background_color.add("#d81e06");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("坚果云");
        picName.add(R.drawable.nut);
        white.add(R.drawable.nut_w);
        background_color.add("#f4ca76");
        text_color.add("#000000");
        text_hintcolor.add("#000000");
        url.add("https://www.jianguoyun.com/s/pricing");

        name.add("Office 365");
        picName.add(R.drawable.office365);
        white.add(R.drawable.office365_w);
        background_color.add("#d83b01");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("https://products.office.com/zh-cn/compare-all-microsoft-office-products?&activetab=tab:primaryr1");


        name.add("Pocket");
        picName.add(R.drawable.pocket);
        white.add(R.drawable.pocket_w);
        background_color.add("#ef4056");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("https://getpocket.com/premium?ep=1");

        name.add("PS4");
        picName.add(R.drawable.ps4);
        white.add(R.drawable.ps4_w);
        background_color.add("#0070d1");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("https://store.playstation.com/zh-hans-hk/grid/STORE-MSF86012-PSPLUSCONTENTS/1?smcid=hk-chs_ps%3Acom_psplus-about");

        name.add("500px");
        picName.add(R.drawable.px);
        white.add(R.drawable.px_w);
        background_color.add("#222222");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("https://web.500px.com/upgrade");

        name.add("Slack");
        picName.add(R.drawable.slack);
        white.add(R.drawable.slack_w);
        background_color.add("#008de1");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("https://slack.com/pricing");

        name.add("Spotify");
        picName.add(R.drawable.spotify);
        white.add(R.drawable.spotify_w);
        background_color.add("#1db954");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("https://www.spotify.com/hk-en/premium/?checkout=false");

        name.add("滴滴出行");
        picName.add(R.drawable.didi);
        white.add(R.drawable.didi_w);
        background_color.add("#ff7d41");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("VSCO");
        picName.add(R.drawable.vsco);
        white.add(R.drawable.vsco_w);
        background_color.add("#333333");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("WPS");
        picName.add(R.drawable.wps);
        white.add(R.drawable.wps_w);
        background_color.add("#db2900");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("https://www.docer.com/");

        name.add("Xbox");
        picName.add(R.drawable.xbox_360);
        white.add(R.drawable.xbox_360_w);
        background_color.add("#107c10");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("https://www.xbox.com/zh-CN/live/");

        name.add("Youtube");
        picName.add(R.drawable.youtube);
        white.add(R.drawable.youtube_w);
        background_color.add("#CCff0000");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("爱奇艺");
        picName.add(R.drawable.qiy);
        white.add(R.drawable.qiy_w);
        background_color.add("#BF00be06");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("百度云");
        picName.add(R.drawable.baidupan);
        white.add(R.drawable.baidupan_w);
        background_color.add("#06a7ff");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("饿了么");
        picName.add(R.drawable.elm);
        white.add(R.drawable.elm_w);
        background_color.add("#008de1");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("考拉");
        picName.add(R.drawable.kaola);
        white.add(R.drawable.kaola_w);
        background_color.add("#CCff224a");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("腾讯QQ");
        picName.add(R.drawable.qq);
        white.add(R.drawable.qq_w);
        background_color.add("#CC12B7F5");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("淘宝");
        picName.add(R.drawable.taobao);
        white.add(R.drawable.taobao_w);
        background_color.add("#ff3f00");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("腾讯视频");
        picName.add(R.drawable.tengxunvideo);
        white.add(R.drawable.tengxunvideo_w);
        background_color.add("#ff9103");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("印象笔记");
        picName.add(R.drawable.yinxiang);
        white.add(R.drawable.yinxiang_w);
        background_color.add("#35bd64");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("亚马逊");
        picName.add(R.drawable.amazon);
        white.add(R.drawable.amazon_w);
        background_color.add("#146cb1");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("Apple Music");
        picName.add(R.drawable.applemusic);
        white.add(R.drawable.applemusic_w);
        background_color.add("#EF6182");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("美团");
        picName.add(R.drawable.meituan);
        white.add(R.drawable.meituan_w);
        background_color.add("#CCFECE00");
        text_color.add("#000000");
        text_hintcolor.add("#000000");
        url.add("");

        name.add("当当网");
        picName.add(R.drawable.dangdang);
        white.add(R.drawable.dangdang_w);
        background_color.add("#E83227");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("滴答清单");
        picName.add(R.drawable.didaqingdan);
        white.add(R.drawable.didaqingdan_w);
        background_color.add("#617FDE");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("京东");
        picName.add(R.drawable.jd);
        white.add(R.drawable.jd_w);
        background_color.add("#FD3C13");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("Keep");
        picName.add(R.drawable.keep);
        white.add(R.drawable.keep_w);
        background_color.add("#584F60");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("芒果TV");
        picName.add(R.drawable.mangguotv);
        white.add(R.drawable.mangguotv_w);
        background_color.add("#FA6503");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("得到");
        picName.add(R.drawable.dedao);
        white.add(R.drawable.dedao_w);
        background_color.add("#FF6B00");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("墨刀");
        picName.add(R.drawable.modao);
        white.add(R.drawable.modao_w);
        background_color.add("#FF473E");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("Moo 音乐");
        picName.add(R.drawable.moo);
        white.add(R.drawable.moo_w);
        background_color.add("#CCFFE131");
        text_color.add("#000000");
        text_hintcolor.add("#000000");
        url.add("");

        name.add("幕布");
        picName.add(R.drawable.mubu);
        white.add(R.drawable.mubu_w);
        background_color.add("#393A3F");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("Notion");
        picName.add(R.drawable.notion);
        white.add(R.drawable.notion_w);
        background_color.add("#000000");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("拼多多");
        picName.add(R.drawable.pdd);
        white.add(R.drawable.pdd_w);
        background_color.add("#CCF40009");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("少数派");
        picName.add(R.drawable.shaoshupai);
        white.add(R.drawable.shaoshupai_w);
        background_color.add("#D51719");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("石墨");
        picName.add(R.drawable.shimo);
        white.add(R.drawable.shimo_w);
        background_color.add("#383D41");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("小睡眠");
        picName.add(R.drawable.xiaoshuimian);
        white.add(R.drawable.xiaoshuimian_w);
        background_color.add("#0f709E");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("白描");
        picName.add(R.drawable.baimiao);
        white.add(R.drawable.baimiao_w);
        background_color.add("#448c57");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("淘票票");
        picName.add(R.drawable.taopiaopiao);
        white.add(R.drawable.taopiaopiao_w);
        background_color.add("#BFFF0000");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("网易考拉");
        picName.add(R.drawable.wangyikaola);
        white.add(R.drawable.wangyikaola_w);
        background_color.add("#BFFF0509");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("微博");
        picName.add(R.drawable.sinaweibo);
        white.add(R.drawable.sinaweibo_w);
        background_color.add("#BFF0131C");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("微信读书");
        picName.add(R.drawable.weixinread);
        white.add(R.drawable.weixinread_w);
        background_color.add("#30A5FF");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("Xmind");
        picName.add(R.drawable.xmind);
        white.add(R.drawable.xmind_w);
        background_color.add("#FE6404");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("每日瑜伽");
        picName.add(R.drawable.yujia);
        white.add(R.drawable.yujia_w);
        background_color.add("#8CA5FF");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        name.add("知乎");
        picName.add(R.drawable.zhihu);
        white.add(R.drawable.zhihu_w);
        background_color.add("#0e88EB");
        text_color.add("#FFFFFF");
        text_hintcolor.add("#FFFFFF");
        url.add("");

        for (int i = 0; i < name.size(); i++) {
            //字符串的匹配
            if (SelectName == "" || name.get(i).trim().toLowerCase().contains(SelectName.toString().trim().toLowerCase()))
                fruitList.add(new Fruit(name.get(i), picName.get(i), white.get(i), background_color.get(i), text_color.get(i), text_hintcolor.get(i), url.get(i)));
        }
    }

    private void search() {
        //添加订阅组件
        TextView textView = findViewById(R.id.add);
        //搜索图标组件1
        ImageView imageView1 = findViewById(R.id.list_search);
        //返回图标组件
        ImageView imageback = findViewById(R.id.list_back);
        //搜索图标组件2
        ImageView imageView2 = findViewById(R.id.searchIcon);
        //输入框组件
        EditText editText = findViewById(R.id.search_et_input);
        //取消组件
        TextView cancel = findViewById(R.id.cancel);

        editText.setVisibility(View.VISIBLE);
        imageView2.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
        imageView1.setVisibility(View.INVISIBLE);
        imageback.setVisibility(View.INVISIBLE);

        //呼出软键盘,并聚焦输入框
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    private void cancel() {
        //添加订阅组件
        TextView textView = findViewById(R.id.add);
        //搜索图标组件1
        ImageView imageView1 = findViewById(R.id.list_search);
        //返回图标组件
        ImageView imageback = findViewById(R.id.list_back);
        //搜索图标组件2
        ImageView imageView2 = findViewById(R.id.searchIcon);
        //输入框组件
        EditText editText = findViewById(R.id.search_et_input);
        //取消组件
        TextView cancel = findViewById(R.id.cancel);

        //将输入框内容清空
        editText.setText("");
        editText.setVisibility(View.INVISIBLE);
        imageView2.setVisibility(View.INVISIBLE);
        cancel.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.VISIBLE);
        imageView1.setVisibility(View.VISIBLE);
        imageback.setVisibility(View.VISIBLE);

        //隐藏软件盘功能
        InputMethodManager imm = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_back:
                onBackPressed();
                break;
            case R.id.list_search:
                search();
                break;
            case R.id.cancel:
                cancel();
                break;
        }
    }
}