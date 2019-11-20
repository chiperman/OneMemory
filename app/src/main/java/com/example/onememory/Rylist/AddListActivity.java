package com.example.onememory.Rylist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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


        name.add("Apple Music");
        picName.add(R.drawable.apple_music);
        white.add(R.drawable.apple_music_w);
        background_color.add("#ff2f56");
        text_color.add("#FFFFFF");

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