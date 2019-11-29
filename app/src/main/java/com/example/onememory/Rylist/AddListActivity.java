package com.example.onememory.Rylist;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onememory.R;

import java.util.ArrayList;
import java.util.List;

public class AddListActivity extends Activity implements View.OnClickListener {

    private List<Fruit> fruitList = new ArrayList<>();
    private ImageView iv_back;
    private ImageView iv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

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

        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        iv_back = findViewById(R.id.list_back);
        iv_back.setOnClickListener(this);
        iv_search = findViewById(R.id.list_search);
        iv_search.setOnClickListener(this);
    }


    private void initFruits() {
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<Integer> picName = new ArrayList<Integer>();
        ArrayList<Integer> white = new ArrayList<>();
        ArrayList<String> background_color = new ArrayList<>();
        ArrayList<String> text_color = new ArrayList<>();


        name.add("App Store");
        picName.add(R.drawable.app_store_ios);
        white.add(R.drawable.app_store_ios_w);
        background_color.add("#1f74f9");
        text_color.add("#FFFFFF");

        name.add("哔哩哔哩");
        picName.add(R.drawable.bilibili_fill);
        white.add(R.drawable.bilibili_fill_w);
        background_color.add("#00a1d6");
        text_color.add("#FFFFFF");

        name.add("QQ音乐");
        picName.add(R.drawable.qq_music);
        white.add(R.drawable.qq_music_w);
        background_color.add("#ffdc00");
        text_color.add("#000000");

        name.add("iCloud");
        picName.add(R.drawable.icloud);
        white.add(R.drawable.icloud_w);
        background_color.add("#3c78e7");
        text_color.add("#FFFFFF");

        name.add("网易云音乐");
        picName.add(R.drawable.netease_cloud_music);
        white.add(R.drawable.netease_cloud_music_w);
        background_color.add("#dd001b");
        text_color.add("#FFFFFF");

        name.add("Netflix");
        picName.add(R.drawable.netflix);
        white.add(R.drawable.netflix_w);
        background_color.add("#e50914");
        text_color.add("#FFFFFF");

        name.add("Nintendo Switch");
        picName.add(R.drawable.nintendo_switch);
        white.add(R.drawable.nintendo_switch_w);
        background_color.add("#d81e06");
        text_color.add("#FFFFFF");

        name.add("坚果云");
        picName.add(R.drawable.nut);
        white.add(R.drawable.nut_w);
        background_color.add("#f4ca76");
        text_color.add("#000000");

        name.add("Office");
        picName.add(R.drawable.office365);
        white.add(R.drawable.office365_w);
        background_color.add("#d83b01");
        text_color.add("#FFFFFF");

        name.add("Pocket");
        picName.add(R.drawable.pocket);
        white.add(R.drawable.pocket_w);
        background_color.add("#ef4056");
        text_color.add("#FFFFFF");

        name.add("PS4");
        picName.add(R.drawable.ps4);
        white.add(R.drawable.ps4_w);
        background_color.add("#0070d1");
        text_color.add("#FFFFFF");

        name.add("500px");
        picName.add(R.drawable.px);
        white.add(R.drawable.px_w);
        background_color.add("#222222");
        text_color.add("#FFFFFF");

        name.add("Slack");
        picName.add(R.drawable.slack);
        white.add(R.drawable.slack_w);
        background_color.add("#008de1");
        text_color.add("#FFFFFF");

        name.add("Spotify");
        picName.add(R.drawable.spotify);
        white.add(R.drawable.spotify_w);
        background_color.add("#1db954");
        text_color.add("#FFFFFF");

        name.add("滴滴出行");
        picName.add(R.drawable.didi);
        white.add(R.drawable.didi_w);
        background_color.add("#ff7d41");
        text_color.add("#FFFFFF");

        name.add("VSCO");
        picName.add(R.drawable.vsco);
        white.add(R.drawable.vsco_w);
        background_color.add("#333333");
        text_color.add("#FFFFFF");

        name.add("WPS");
        picName.add(R.drawable.wps);
        white.add(R.drawable.wps_w);
        background_color.add("#db2900");
        text_color.add("#FFFFFF");

        name.add("Xbox");
        picName.add(R.drawable.xbox_360);
        white.add(R.drawable.xbox_360_w);
        background_color.add("#107c10");
        text_color.add("#FFFFFF");

        name.add("Youtube");
        picName.add(R.drawable.youtube);
        white.add(R.drawable.youtube_w);
        background_color.add("#ff0000");
        text_color.add("#FFFFFF");

        name.add("爱奇艺");
        picName.add(R.drawable.qiy);
        white.add(R.drawable.qiy_w);
        background_color.add("#00be06");
        text_color.add("#FFFFFF");


        name.add("百度云");
        picName.add(R.drawable.baidupan);
        white.add(R.drawable.baidupan_w);
        background_color.add("#06a7ff");
        text_color.add("#FFFFFF");

        name.add("饿了么");
        picName.add(R.drawable.elm);
        white.add(R.drawable.elm_w);
        background_color.add("#008de1");
        text_color.add("#FFFFFF");

        name.add("考拉");
        picName.add(R.drawable.kaola);
        white.add(R.drawable.kaola_w);
        background_color.add("#ff224a");
        text_color.add("#FFFFFF");

        name.add("腾讯QQ");
        picName.add(R.drawable.qq);
        white.add(R.drawable.qq_w);
        background_color.add("#12B7F5");
        text_color.add("#FFFFFF");

        name.add("淘宝");
        picName.add(R.drawable.taobao);
        white.add(R.drawable.taobao_w);
        background_color.add("#ff3f00");
        text_color.add("#FFFFFF");

        name.add("腾讯视频");
        picName.add(R.drawable.tengxunvideo);
        white.add(R.drawable.tengxunvideo_w);
        background_color.add("#ff9103");
        text_color.add("#FFFFFF");

        name.add("印象笔记");
        picName.add(R.drawable.yinxiang);
        white.add(R.drawable.yinxiang_w);
        background_color.add("#35bd64");
        text_color.add("#FFFFFF");

        name.add("亚马逊");
        picName.add(R.drawable.amazon);
        white.add(R.drawable.amazon_w);
        background_color.add("#146cb1");
        text_color.add("#FFFFFF");

        name.add("Apple Music");
        picName.add(R.drawable.applemusic);
        white.add(R.drawable.applemusic_w);
        background_color.add("#EF6182");
        text_color.add("#FFFFFF");

        name.add("袋鼠");
        picName.add(R.drawable.daishu);
        white.add(R.drawable.daishu_w);
        background_color.add("#FECE00");
        text_color.add("#000000");

        name.add("当当网");
        picName.add(R.drawable.dangdang);
        white.add(R.drawable.dangdang_w);
        background_color.add("#E83227");
        text_color.add("#FFFFFF");

        name.add("滴答清单");
        picName.add(R.drawable.didaqingdan);
        white.add(R.drawable.didaqingdan_w);
        background_color.add("#617FDE");
        text_color.add("#FFFFFF");

        name.add("京东");
        picName.add(R.drawable.jd);
        white.add(R.drawable.jd_w);
        background_color.add("#FD3C13");
        text_color.add("#FFFFFF");

        name.add("Keep");
        picName.add(R.drawable.keep);
        white.add(R.drawable.keep_w);
        background_color.add("#584F60");
        text_color.add("#FFFFFF");

        name.add("芒果TV");
        picName.add(R.drawable.mangguotv);
        white.add(R.drawable.mangguotv_w);
        background_color.add("#FA6503");
        text_color.add("#FFFFFF");

        name.add("得到");
        picName.add(R.drawable.dedao);
        white.add(R.drawable.dedao_w);
        background_color.add("#FF6B00");
        text_color.add("#FFFFFF");

        name.add("墨刀");
        picName.add(R.drawable.modao);
        white.add(R.drawable.modao_w);
        background_color.add("#FF473E");
        text_color.add("#FFFFFF");

        name.add("Moo 音乐");
        picName.add(R.drawable.moo);
        white.add(R.drawable.moo_w);
        background_color.add("#FFE131");
        text_color.add("#000000");

        name.add("幕布");
        picName.add(R.drawable.mubu);
        white.add(R.drawable.mubu_w);
        background_color.add("#393A3F");
        text_color.add("#FFFFFF");

        name.add("Notion");
        picName.add(R.drawable.notion);
        white.add(R.drawable.notion_w);
        background_color.add("#000000");
        text_color.add("#FFFFFF");

        name.add("拼多多");
        picName.add(R.drawable.pdd);
        white.add(R.drawable.pdd_w);
        background_color.add("#F40009");
        text_color.add("#FFFFFF");

        name.add("少数派");
        picName.add(R.drawable.shaoshupai);
        white.add(R.drawable.shaoshupai_w);
        background_color.add("#D51719");
        text_color.add("#FFFFFF");

        name.add("石墨");
        picName.add(R.drawable.shimo);
        white.add(R.drawable.shimo_w);
        background_color.add("#383D41");
        text_color.add("#FFFFFF");

        name.add("小睡眠");
        picName.add(R.drawable.sleep);
        white.add(R.drawable.sleep_w);
        background_color.add("#0f709E");
        text_color.add("#FFFFFF");

        name.add("淘票票");
        picName.add(R.drawable.taopp);
        white.add(R.drawable.taopp_w);
        background_color.add("#FF0000");
        text_color.add("#FFFFFF");

        name.add("网易考拉");
        picName.add(R.drawable.wangyikaola);
        white.add(R.drawable.wangyikaola_w);
        background_color.add("#FF0509");
        text_color.add("#FFFFFF");

        name.add("微博");
        picName.add(R.drawable.sinaweibo);
        white.add(R.drawable.sinaweibo_w);
        background_color.add("#F0131C");
        text_color.add("#FFFFFF");

        name.add("微信读书");
        picName.add(R.drawable.weixinread);
        white.add(R.drawable.weixinread_w);
        background_color.add("#30A5FF");
        text_color.add("#FFFFFF");

        name.add("Xmind");
        picName.add(R.drawable.xmind);
        white.add(R.drawable.xmind_w);
        background_color.add("#FE6404");
        text_color.add("#FFFFFF");

        name.add("每日瑜伽");
        picName.add(R.drawable.yujia);
        white.add(R.drawable.yujia_w);
        background_color.add("#8CA5FF");
        text_color.add("#FFFFFF");

        name.add("知乎");
        picName.add(R.drawable.zhihu);
        white.add(R.drawable.zhihu_w);
        background_color.add("#0e88EB");
        text_color.add("#FFFFFF");

        for (int i = 0; i < name.size(); i++) {
            fruitList.add(new Fruit(name.get(i), picName.get(i), white.get(i), background_color.get(i), text_color.get(i)));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_back:
                onBackPressed();
                break;
            case R.id.list_search:
                break;
        }
    }
}