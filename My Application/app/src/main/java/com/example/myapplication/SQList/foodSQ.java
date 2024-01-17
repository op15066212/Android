package com.example.myapplication.SQList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.myapplication.Bean.Food;

import java.util.ArrayList;
import java.util.List;

public class foodSQ extends SQLiteOpenHelper {
    private static Context mContext;

    public foodSQ(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public foodSQ(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public foodSQ(@Nullable Context context) {
        super(context, "Foods", null, 1);
        mContext = context;
    }

    public static Context getmContext() {
        return mContext;
    }

    public static void setmContext(Context mContext) {
        foodSQ.mContext = mContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE foods ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT,"
                + "image_resource INTEGER,"
                + "rating TEXT,"
                + "sales TEXT,"
                + "price TEXT,"
                + "info TEXT,"
                + "time TEXT"
                + ")";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", food.getName());
        values.put("image_resource", food.getImageResource());
        values.put("rating", food.getRating());
        values.put("sales", food.getSales());
        values.put("price", food.getPrice());
        values.put("info", food.getInfo());
        values.put("time", food.getTime());
        db.insert("foods", null, values);
        db.close();
    }

    public List<Food> getAllFoods() {
        List<Food> foods = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM foods";
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int imageResource = cursor.getInt(2);
                String name = cursor.getString(1);
                String rating = cursor.getString(3);
                String sales = cursor.getString(4);
                String price = cursor.getString(5);
                String info = cursor.getString(6);
                String time = cursor.getString(7);
                Food food = new Food(imageResource, name, rating, sales, info, time, price);
                food.setId(id);
                foods.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foods;
    }


    public int updateFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", food.getName());
        values.put("image_resource", food.getImageResource());
        values.put("rating", food.getRating());
        values.put("sales", food.getSales());
        values.put("price", food.getPrice());
        values.put("info", food.getInfo());
        values.put("time", food.getTime());
        int rowsAffected = db.update("foods", values, "id=?", new String[]{String.valueOf(food.getId())});
        db.close();
        return rowsAffected;
    }

    public void deleteFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("foods", "id=?", new String[]{String.valueOf(food.getId())});
        db.close();
    }

    public Food getFoodById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM foods WHERE id=?";
        Cursor cursor = db.rawQuery(select, new String[]{String.valueOf(id)});
        Food food = null;
        if (cursor.moveToFirst()) {
            int imageResource = cursor.getInt(2);
            String name = cursor.getString(1);
            String rating = cursor.getString(3);
            String sales = cursor.getString(4);
            String price = cursor.getString(5);
            String info = cursor.getString(6);
            String time = cursor.getString(7);
            food = new Food(imageResource, name, rating, sales, price, info, time);
            food.setId(id);
        }
        cursor.close();
        db.close();
        return food;
    }

}
