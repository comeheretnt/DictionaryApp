package com.example.dictionaryappv1.databaseUtils;


import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelperAnhViet extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "anh_viet.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelperAnhViet(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
