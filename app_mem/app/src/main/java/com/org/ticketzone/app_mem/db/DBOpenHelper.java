package com.org.ticketzone.app_mem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "SQLite.db";
    public static SQLiteDatabase mdb;

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase mdb = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBTable.Member.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBTable.Member.DROP_QUERY);
        onCreate(db);
    }

    /**
     * SQL
     */
    public Cursor selectAllMember() {
        mdb = this.getReadableDatabase();
        String sql = "select * from member";
        Cursor member_list = mdb.rawQuery(sql, null);

        return member_list;
    }

    public boolean insertMember(String member_tel, String member_name, String member_birth) {
        mdb = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("member_tel", member_tel);
        values.put("member_name", member_name);
        values.put("member_birth", member_birth);

        long result = mdb.insert(DBTable.Member.TABLENAME, null, values);

        if(result == -1)
            return false; // error
        else
            return true; // success
    }

    public boolean updateMember(String member_tel, String member_name, String member_birth) {
        mdb = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("member_tel", member_tel);
        values.put("member_name", member_name);
        values.put("member_birth", member_birth);

        int result = mdb.update(DBTable.Member.TABLENAME, values, "member_tel=?",new String[] {member_tel});

        if( result == 0)
            return false; // error
        else
            return true; // success
    }

    public boolean deleteMember(String member_tel) {
        mdb = this.getReadableDatabase();

        int result = mdb.delete(DBTable.Member.TABLENAME, "member_tel=?", new String[] {member_tel} );

        if( result == 0)
            return false; // error
        else
            return true; // success
    }
}