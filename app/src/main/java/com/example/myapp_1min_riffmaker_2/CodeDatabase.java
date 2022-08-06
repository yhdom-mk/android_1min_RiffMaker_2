package com.example.myapp_1min_riffmaker_2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = { Code.class }, version = 1, exportSchema = false)
public abstract class CodeDatabase extends RoomDatabase {
    public abstract CodeDao codeDao();
}
