package com.org.ticketzone.app_mem.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBOpenHelper {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase sqLite;
    private DataBaseHelper mDBHelper;
    private Context context;

    private class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        /**
         * Database가 존재하지 않을 때, 딱 한번 실행된다.
         * DB를 만드는 역할을 한다.
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DBTable.Member.CREATE_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DBTable.Member.DROP_QUERY);
            onCreate(db);
        }
    }

    public DBOpenHelper(Context context) {
        this.context = context;
    }

    public DBOpenHelper open() throws SQLException {
        mDBHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLite = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(sqLite);
    }

    public void close(){
        sqLite.close();
    }

}