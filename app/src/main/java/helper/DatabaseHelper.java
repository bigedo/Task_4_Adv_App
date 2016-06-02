package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bigedo on 5/24/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "accounting";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "accounting";
    public static final String COL_TYPE = "type";
    public static final String COL_ITEM = "item";
    public static final String COL_AMOUNT = "amount";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = getWritableDatabase();
    }

    //cek the db status to make sure, we can user the db.
    public String getDbStatus() {
        return "";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (item string, type string, amount integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertNewData(String item, int amount, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+TABLE_NAME);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ITEM, item);
        contentValues.put(COL_AMOUNT, amount);
        contentValues.put(COL_TYPE, type);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
