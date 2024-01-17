package com.example.myapplication.Bean;

public class Food {
    private int mId;
    private int mImageResource;
    private String mName;
    private String mRating;
    private String mSales;
    private String mPrice;

    private String mInfo;

    private String mTime;

    public Food(int imageResource, String name, String rating, String sales, String info, String time, String price) {
        mImageResource = imageResource;
        mName = name;
        mRating = rating;
        mSales = sales;
        mInfo = info;
        mTime = time;
        mPrice = price;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getName() {
        return mName;
    }

    public String getRating() {
        return mRating;
    }

    public String getSales() {
        return mSales;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getInfo() {
        return mInfo;
    }

    public String getTime() {
        return mTime;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }

    public void setmSales(String mSales) {
        this.mSales = mSales;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public void setmInfo(String mInfo) {
        this.mInfo = mInfo;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}

