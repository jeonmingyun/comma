package com.org.ticketzone.app_mem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBOpenHelper {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mSQLite;
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

        /** version update 되었을 때 DB 다시 만들어 준다.*/
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
        mSQLite = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mSQLite);
    }

    public void close(){
        mSQLite.close();
    }

    /**
     *  SQL
     */
    // select
    public Cursor selectColumns(){
        return mSQLite.query(DBTable.Member.TABLENAME, null, null, null, null, null, null);
    }

    //order by
    public Cursor orderBy(String table_name, String sort) {
        Cursor c = mSQLite.rawQuery(
                "select * from "
                        + "table_name"
                        + "order by " + sort + ";", null);

        return c;
    }

    // insert
    public long insertColumn(String member_tel, String member_name, String member_birth) {
        ContentValues values = new ContentValues();
        values.put(DBTable.Member.MEMBER_TEL, member_tel);
        values.put(DBTable.Member.MEMBER_NAME, member_name);
        values.put(DBTable.Member.MEMBER_BIRTH, member_birth);

        return mSQLite.insert(DBTable.Member.TABLENAME,null, values);
    }

    // update
    public boolean updateColumn(String member_tel, String member_name, String member_birth) {
//        ContentValues values = new ContentValues();
//        values.put(DBTable.)

        return false;
    }
}