package com.khoa.demomotionlayout;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    private String name;
    private String imgUrl;
    private String detail;

    public Item(String name, String detail, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.detail = detail;
    }

    protected Item(Parcel in) {
        name = in.readString();
        imgUrl = in.readString();
        detail = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imgUrl);
        dest.writeString(detail);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
