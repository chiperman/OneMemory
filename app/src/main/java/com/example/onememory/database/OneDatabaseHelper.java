package com.example.onememory.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OneDatabaseHelper extends SQLiteOpenHelper {
    public OneDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public OneDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table if not exists apps(" +
                "id integer primary key autoincrement," +
                "name varchar(50) not null," +
                "iconId int not null," +
                "description text," +
                "money float ," +
                "bg_color varchar(50)," +
                "text_color varchar(50)," +
                "sub_time varchar(50) ," +
                "sub_period varchar(50) ," +
                "pay_method varchar(50), " +
                "expired int default 0)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
