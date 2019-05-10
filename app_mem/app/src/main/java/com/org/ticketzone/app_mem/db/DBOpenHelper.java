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
        mdb = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBTable.Member.CREATE_QUERY);
        db.execSQL(DBTable.Owner.CREATE_QUERY);
        db.execSQL(DBTable.Categorie.CREATE_QUERY);
        db.execSQL(DBTable.Coordinates.CREATE_QUERY);
        db.execSQL(DBTable.Store.CREATE_QUERY);
        db.execSQL(DBTable.StoreMenu.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBTable.Member.DROP_QUERY);
        db.execSQL(DBTable.Owner.DROP_QUERY);
        db.execSQL(DBTable.Categorie.DROP_QUERY);
        db.execSQL(DBTable.Coordinates.DROP_QUERY);
        db.execSQL(DBTable.Store.DROP_QUERY);
        db.execSQL(DBTable.StoreMenu.DROP_QUERY);
        onCreate(db);
    }

    /**
     * SQL
     */
    // Member
    public Cursor selectAllMember() {
        mdb = this.getReadableDatabase();
        String sql = "select * from member";
        Cursor member_list = mdb.rawQuery(sql, null);

        return member_list;
    }

    public boolean insertMember(String member_id, String member_nickname, String member_birth, String member_gender, String member_age_range) {
        mdb = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("member_id", member_id);
        values.put("member_nickname", member_nickname);
        values.put("member_birth", member_birth);
        values.put("member_gender", member_gender);
        values.put("member_age_range", member_age_range);

        long result = mdb.insert(DBTable.Member.TABLENAME, null, values);

        if(result == -1)
            return false; // error
        else
            return true; // success
    }

    public boolean updateMember(String member_id, String member_nickname, String member_birth, String member_gender, String member_age_range) {
        mdb = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("member_id", member_id);
        values.put("member_nickname", member_nickname);
        values.put("member_birth", member_birth);
        values.put("member_gender", member_gender);
        values.put("member_age_range", member_age_range);

        int result = mdb.update(DBTable.Member.TABLENAME, values, "member_id=?",new String[] {member_id});

        if( result == 0)
            return false; // error
        else
            return true; // success
    }

    public boolean deleteMember(String member_tel) {
        mdb = this.getWritableDatabase();

        int result = mdb.delete(DBTable.Member.TABLENAME, "member_tel=?", new String[] {member_tel} );

        if( result == 0)
            return false; // error
        else
            return true; // success
    }

    // Owner
    public Cursor selectAllOwner() {
        mdb = this.getReadableDatabase();
        String sql = "select * from owner";
        Cursor member_list = mdb.rawQuery(sql, null);

        return member_list;
    }

    public boolean insertOwner(String owner_id) {
        mdb = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("owner_id", owner_id);

        long result = mdb.insert(DBTable.Owner.TABLENAME, null, values);

        if(result == -1)
            return false; // error
        else
            return true; // success
    }

    // Store
    public Cursor selectAllStore() {
        mdb = this.getReadableDatabase();
        String sql = "select * from store";
        Cursor member_list = mdb.rawQuery(sql, null);

        return member_list;
    }

    public boolean insertStore(String license_number, String r_name, String max_number, String store_status, String cate_code, String owner_id, String store_tel, String store_time, String store_intro, String address_name) {
        mdb = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("license_number", license_number);
        values.put("r_name", r_name);
        values.put("max_number", max_number);
        values.put("store_status", store_status);
        values.put("cate_code", cate_code);
        values.put("owner_id", owner_id);
        values.put("store_tel", store_tel);
        values.put("store_time", store_time);
        values.put("store_intro", store_intro);
        values.put("address_name", address_name);

        long result = mdb.insert(DBTable.Store.TABLENAME, null, values);

        if(result == -1)
            return false; // error
        else
            return true; // success
    }
//    public boolean deleteStore(String License_number) {
//        mdb = this.getWritableDatabase();
//
//        int result = mdb.delete(DBTable.Store.TABLENAME, "license_number=?", new String[] {license_number} );
//
//        if( result == 0)
//            return false; // error
//        else
//            return true; // success
//    }
//    public boolean updateStore(String max_number, String store_status, String cate_code, String store_tel, String store_time, String store_intro, String address_name) {
//        mdb = this.getWritableDatabase();
//        ContentValues values =  new ContentValues();
//        values.put("max_number", max_number);
//        values.put("store_status", store_status);
//        values.put("cate_code", cate_code);
//        values.put("store_tel", store_tel);
//        values.put("store_time", store_time);
//        values.put("store_intro", store_intro);
//        values.put("address_number", address_name);
//
//        int result = mdb.update(DBTable.Store.TABLENAME, values, "license_number=?",new String[] {license_number});
//
//        if( result == 0)
//            return false; // error
//        else
//            return true; // success
//    }
    // Menu


    // Categorie


    // Coordinates

}