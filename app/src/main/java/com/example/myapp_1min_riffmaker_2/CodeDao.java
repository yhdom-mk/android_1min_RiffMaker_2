package com.example.myapp_1min_riffmaker_2;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CodeDao {
    @Query("SELECT * FROM codes")
    List<Code> getAll();

    @Query("SELECT * FROM codes WHERE id IN (:ids)")
    List<Code> loadAllByIds(int[] ids);

    @Insert
    void insertAll(Code... code);
    @Insert
    void insert(Code id);
    @Delete
    void delete(Code code);
}
