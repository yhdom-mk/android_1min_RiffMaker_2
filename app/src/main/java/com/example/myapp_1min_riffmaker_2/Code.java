package com.example.myapp_1min_riffmaker_2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//import androidx.room.annotation.NonNull;
@Entity(tableName = "codes")
public class Code {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "code")
    private String code;

    public Code(String code){
        this.code = code;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }
}
