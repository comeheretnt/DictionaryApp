package com.example.dictionaryappv1.databaseUtils;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelperVietAnh extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "viet_anh.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelperVietAnh(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
