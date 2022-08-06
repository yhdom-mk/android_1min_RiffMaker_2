package com.example.myapp_1min_riffmaker_2;

import android.content.Context;
import android.provider.DocumentsContract;

import androidx.room.Room;

public class AppDatabaseSingleton {
    private static CodeDatabase instance = null;
    public static CodeDatabase getInstance(Context context) {
        if(instance != null) {
            return instance;
        }
        instance = Room.databaseBuilder(context, CodeDatabase.class,
                "database-name").build();
        return instance;
    }
}
