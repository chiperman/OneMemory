package com.example.onememory.exportOrImport;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.onememory.MainActivity;
import com.example.onememory.R;
import com.example.onememory.apps.App;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExportOrImport extends Activity {

    byte[] buffer = null; //定义保存数据的数组
    ArrayList<String> list = new ArrayList<>();
    File file;
    String path;
    // 获取文件的真实路径
    boolean dialogFlag = false;
    FileInputStream fis = null;//文件输入流对象

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            // 用户未选择任何文件，直接返回
            return;
        }
        Uri uri = data.getData(); // 获取用户选择文件的URI
        path = uri.getPath();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("确认选择？")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (path != null) {
                            file = new File(path, "");
                        } else {
                            Toast.makeText(ExportOrImport.this, "请先获取文件", Toast.LENGTH_LONG).show();
                            return;
                        }
                        try {
                            fis = new FileInputStream(file);//获取文件输入流对象
                            buffer = new byte[fis.available()]; //实例化字节数组
                            fis.read(buffer);//从输入流中读取数据
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (fis != null) {
                                try {
                                    fis.close(); //关闭输入流对象
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (buffer == null) {
                            Toast.makeText(ExportOrImport.this, "暂无数据", Toast.LENGTH_LONG).show();
                            return;
                        }
                        String s = "";
                        String result = "";
                        String data = new String(buffer); //把字节数组中的数据转化为字符串
                        int flag = 0;
                        App app = new App();
                        for (int j = 0; j < data.length(); j++) {
                            if (data.charAt(j) == '_') {
                                flag++;
                                if (s == null || s == "") {
                                    continue;
                                }
                                switch (flag) {
                                    case 1:
                                        app.setId(Integer.parseInt(s));
                                        break;
                                    case 2:
                                        app.setName(s);
                                        break;
                                    case 3:
                                        app.setIconId(Integer.parseInt(s));
                                        break;
                                    case 4:
                                        app.setDescription(s);
                                        break;
                                    case 5:
                                        app.setMoney(Float.parseFloat(s));
                                        break;
                                    case 6:
                                        app.setBgColor(s);
                                        break;
                                    case 7:
                                        app.setTextColor(s);
                                        break;
                                    case 8:
                                        app.setSubTime(s);
                                        break;
                                    case 9:
                                        app.setSubPeriod(s);
                                        break;
                                    case 10:
                                        app.setPayMethod(s);
                                        break;
                                    case 11:
                                        app.setExpired(Integer.parseInt(s));
                                        break;
                                    case 12:
                                        app.setState(Integer.parseInt(s));

                                }
                                s = "";
                            } else if (data.charAt(j) == '@') {
                                flag = 0;
                                MainActivity.apps.add(app);
                                MainActivity.addApp(app);
                                app = new App();
                            } else if (data.charAt(j) != '[' && data.charAt(j) != ']' && data.charAt(j) != ',' && data.charAt(j) != ' ') {
                                s += data.charAt(j);
                            }

                        }
                        if (path != "")
                            Toast.makeText(ExportOrImport.this, "已成功导入数据", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogFlag = true;
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        if (dialogFlag) return;
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_or_import);

        setPlaceUp();

        TextView btn_export = findViewById(R.id.btn_export);
        TextView btn_import = findViewById(R.id.btn_import);
        file = new File(Environment.getExternalStorageDirectory(), "OneMemory.txt");
        btn_export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存填写的备忘信息
                FileOutputStream fos = null;//声明文件输出流
                ArrayList<App> apps = MainActivity.apps;
                for (int i = 0; i < apps.size(); i++) {
                    list.add(apps.get(i).getId() + "_");
                    list.add(apps.get(i).getName() + "_");
                    list.add(apps.get(i).getIconId() + "_");
                    list.add(apps.get(i).getDescription() + "_");
                    list.add(apps.get(i).getMoney() + "_");
                    list.add(apps.get(i).getBgColor() + "_");
                    list.add(apps.get(i).getTextColor() + "_");
                    list.add(apps.get(i).getSubTime() + "_");
                    list.add(apps.get(i).getSubPeriod() + "_");
                    list.add(apps.get(i).getPayMethod() + "_");
                    list.add(apps.get(i).getExpired() + "_");
                    list.add(apps.get(i).getState() + "_");
                    list.add("@");
                }
                try {
                    fos = new FileOutputStream(file);//获取文件输出流对象
                    fos.write(list.toString().getBytes());//保存备忘录信息
                    fos.flush();//清除缓存
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();//关闭输出流
                            Toast.makeText(ExportOrImport.this, "保存成功", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(ExportOrImport.this, "null", Toast.LENGTH_LONG).show();
                    }

                }
            }

        });

        btn_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.setType(“image/*”);//选择图片
                //intent.setType(“audio/*”); //选择音频
                //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
                //intent.setType(“video/*;image/*”);//同时选择视频和图片
                intent.setType("*/*");//无类型限制
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Log.e("path:",path);
//                if (path == null)return;


            }
        });

    }

    //设置状态栏和导航栏
    public void setPlaceUp() {
        // 3.顶部状态栏透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        // 4.设置状态栏文字为暗色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
