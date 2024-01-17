package com.example.myapplication.SQList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.myapplication.Bean.User;

import java.util.ArrayList;
import java.util.List;

public class userSQ extends SQLiteOpenHelper {
    private static Context mContext;

    public userSQ(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public userSQ(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public userSQ(@Nullable Context context) {
        super(context, "Users", null, 1);
        mContext = context;
    }

    public static Context getmContext() {
        return mContext;
    }

    public static void setmContext(Context mContext) {
        userSQ.mContext = mContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT,"
                + "password TEXT,"
                + "sex TEXT"
                + ")";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("sex", user.getSex());
        db.insert("users", null, values);
        db.close();
    }

    public void insertUser(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("users", null, values);
        db.close();
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM users";
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String username = cursor.getString(1);
                String password = cursor.getString(2);
                String sex = cursor.getString(3);
                User user = new User(username, password, sex);
                user.setId(id);
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }


    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("sex", user.getSex());
        int rowsAffected = db.update("users", values, "id=?", new String[]{String.valueOf(user.getId())});
        db.close();
        return rowsAffected;
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("users", "id=?", new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public User getUserById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM users WHERE id=?";
        Cursor cursor = db.rawQuery(select, new String[]{String.valueOf(id)});
        User user = null;
        if (cursor.moveToFirst()) {
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            String sex = cursor.getString(3);
            user = new User(username, password, sex);
            user.setId(id);
        }
        cursor.close();
        db.close();
        return user;
    }

    public User getUserByName(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM users WHERE username=?";
        Cursor cursor = db.rawQuery(select, new String[]{username});
        User user = null;
        if (cursor.moveToFirst()) {
            int id = Integer.parseInt(cursor.getString(0));
            String password = cursor.getString(2);
            String sex = cursor.getString(3);
            user = new User(username, password, sex);
            user.setId(id);
        }
        cursor.close();
        db.close();
        return user;
    }

    public boolean containsById(int id) {
        return getUserById(id) != null;
    }

    public boolean containsByName(String name) {
        return getUserByName(name) != null;
    }

    public List<User> selectlike(String keyword) {
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM users WHERE username LIKE ? OR password LIKE ? OR sex LIKE ?";
        Cursor cursor = db.rawQuery(select, new String[]{"%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%"});
        List<User> userList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String username = cursor.getString(1);
                String password = cursor.getString(2);
                String sex = cursor.getString(3);
                User user = new User(username, password, sex);
                user.setId(id);
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }
}
