package com.ferrysaptawan.sqllitenew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper2 extends SQLiteOpenHelper {
    public static final String DB_NAME = "mid_202102277.db";

    public DBHelper2(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE biodata(nim TEXT PRIMARY KEY, nama TEXT, jeniskelamin TEXT, alamat TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS biodata");
        onCreate(db);
    }

    public boolean insertData(String nim, String nama, String jeniskelamin, String alamat, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nim", nim);
        values.put("nama", nama);
        values.put("jeniskelamin", jeniskelamin);
        values.put("alamat", alamat);
        values.put("email", email);
        long result = db.insert("biodata", null, values);
        db.close();
        return result != -1;
    }

    public Cursor tampildata() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM biodata", null);
    }

    public boolean checknim(String nim) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM biodata WHERE nim=?", new String[]{nim});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean deleteData(String nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("biodata", "nim=?", new String[]{nim});
        db.close();
        return result > 0;
    }

    public boolean updateData(String nim, String nama, String jeniskelamin, String alamat, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nama", nama);
        values.put("jeniskelamin", jeniskelamin);
        values.put("alamat", alamat);
        values.put("email", email);

        int result = db.update("biodata", values, "nim=?", new String[]{nim});
        db.close();
        return result > 0;
    }

    public boolean checkusernamepassword(String isinim, String isinama) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM biodata WHERE nim=? AND nama=?", new String[]{isinim, isinama});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}