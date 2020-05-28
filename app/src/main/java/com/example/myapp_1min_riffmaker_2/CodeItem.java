package com.example.myapp_1min_riffmaker_2;

import android.os.Parcel;
import android.os.Parcelable;

public class CodeItem implements Parcelable {
    private int mImagesource;
    String mCodeview1;
    String mCodeview2;

    public CodeItem(int mImagesource, String mCodeview1, String mCodeview2) {
        this.mImagesource = mImagesource;
        this.mCodeview1 = mCodeview1;
        this.mCodeview2 = mCodeview2;
    }

    protected CodeItem(Parcel in) {
        mImagesource = in.readInt();
        mCodeview1 = in.readString();
        mCodeview2 = in.readString();
    }

    public static final Creator<CodeItem> CREATOR = new Creator<CodeItem>() {
        @Override
        public CodeItem createFromParcel(Parcel in) {
            return new CodeItem(in);
        }

        @Override
        public CodeItem[] newArray(int size) {
            return new CodeItem[size];
        }
    };

    public int getmImagesource() {
        return mImagesource;
    }
    public String getmCodeview1() {
        return mCodeview1;
    }
    public String getmCodeview2() {
        return mCodeview2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mImagesource);
        dest.writeString(mCodeview1);
        dest.writeString(mCodeview2);
    }
}
