package com.org.ticketzone.app_mem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBOpenHelper extends SQLiteOpenHelper{

    private static final int DB_VERSION = 9;
    private static final String DB_NAME = "SQLite.db";
    public static SQLiteDatabase mdb;

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBTable.Member.CREATE_QUERY);
        db.execSQL(DBTable.Owner.CREATE_QUERY);
        db.execSQL(DBTable.Categorie.CREATE_QUERY);
        db.execSQL(DBTable.StoreMenu.CREATE_QUERY);
        db.execSQL(DBTable.Store.CREATE_QUERY);
        db.execSQL(DBTable.Coordinates.CREATE_QUERY);
        db.execSQL(DBTable.NumberTicket.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBTable.Coordinates.DROP_QUERY);
        db.execSQL(DBTable.Store.DROP_QUERY);
        db.execSQL(DBTable.Member.DROP_QUERY);
        db.execSQL(DBTable.Owner.DROP_QUERY);
        db.execSQL(DBTable.Categorie.DROP_QUERY);
        db.execSQL(DBTable.StoreMenu.DROP_QUERY);
        db.execSQL(DBTable.NumberTicket.DROP_QUERY);
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

    public void insertOwner(JSONArray ownerList) {
        mdb = this.getWritableDatabase();
        for(int i = 0; i < ownerList.length(); i++) {
            try {
                JSONObject jobj = new JSONObject(ownerList.get(i).toString());
                ContentValues values = new ContentValues();
                values.put("owner_id", jobj.getString("owner_id"));

                mdb.insert(DBTable.Owner.TABLENAME, null, values);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // Store
    public Cursor selectAllStore() {
        mdb = this.getWritableDatabase();
        String sql = "select * from store";
        Cursor member_list = mdb.rawQuery(sql, null);

        return member_list;
    }

    public void insertStore(JSONArray storeList) {
        mdb = this.getWritableDatabase();

        for(int i = 0; i < storeList.length(); i++) {
            try {
                JSONObject jobj = new JSONObject(storeList.get(i).toString());
                ContentValues values =  new ContentValues();
                values.put("license_number", jobj.getString("license_number"));
                values.put("r_name", jobj.getString("r_name"));
                values.put("max_number", jobj.getString("max_number"));
                values.put("store_status", jobj.getString("store_status"));
                values.put("cate_code", jobj.getString("cate_code"));
                values.put("owner_id", jobj.getString("owner_id"));
                values.put("store_tel", jobj.getString("store_tel"));
                values.put("store_time", jobj.getString("store_time"));
                values.put("store_name", jobj.getString("store_name"));
                values.put("store_intro", jobj.getString("store_intro"));
                values.put("address_name", jobj.getString("address_name"));

                mdb.insert(DBTable.Store.TABLENAME, null, values);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean deleteStore(String license_number) {
        mdb = this.getWritableDatabase();

        int result = mdb.delete(DBTable.Store.TABLENAME, "license_number=?", new String[] {license_number} );

        if( result == 0)
            return false; // error
        else
            return true; // success
    }

    public boolean updateStore(String license_number, String max_number, String store_status, String cate_code, String store_tel, String store_time, String store_intro, String address_name) {
        mdb = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("max_number", max_number);
        values.put("store_status", store_status);
        values.put("cate_code", cate_code);
        values.put("store_tel", store_tel);
        values.put("store_time", store_time);
        values.put("store_intro", store_intro);
        values.put("address_number", address_name);

        int result = mdb.update(DBTable.Store.TABLENAME, values, "license_number=?",new String[] {license_number});

        if( result == 0)
            return false; // error
        else
            return true; // success
    }

    // StoreMenu
    public Cursor selectAllStoreMenu() {
        mdb = this.getReadableDatabase();
        String sql = "select * from store_menu";
        Cursor member_list = mdb.rawQuery(sql, null);

        return member_list;
    }

    public void insertStoreMenu(JSONArray menuList) {
        mdb = this.getWritableDatabase();

        for ( int i = 0; i < menuList.length(); i++) {
            try {
                JSONObject jobj = new JSONObject(menuList.get(i).toString());
                ContentValues values = new ContentValues();
                values.put("menu_code", jobj.getString("menu_code"));
                values.put("menu_name", jobj.getString("menu_name"));
                values.put("menu_price", jobj.getString("menu_price"));
                values.put("store_note", jobj.getString("store_note"));

                mdb.insert(DBTable.StoreMenu.TABLENAME, null, values);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // Categorie
    public Cursor selectAllCategorie() {
        mdb = this.getReadableDatabase();
        String sql = "select * from categorie";
        Cursor member_list = mdb.rawQuery(sql, null);

        return member_list;
    }

    public void insertCategorie(JSONArray categorieList) {
        mdb = this.getWritableDatabase();

        for ( int i = 0; i < categorieList.length(); i++) {
            try {
                JSONObject jobj = new JSONObject(categorieList.get(i).toString());
                ContentValues values = new ContentValues();
                values.put("cate_code", jobj.getString("cate_code"));
                values.put("cate_name", jobj.getString("cate_name"));

                mdb.insert(DBTable.Categorie.TABLENAME, null, values);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // Coordinates
    public Cursor selectAllCoordinates() {
        mdb = this.getReadableDatabase();
        String sql = "select * from coordinates";
        Cursor member_list = mdb.rawQuery(sql, null);

        return member_list;
    }

    public void insertCoordinates(JSONArray coordinatesList) {
        mdb = this.getWritableDatabase();

        for ( int i = 0; i < coordinatesList.length(); i++) {
            try {
                JSONObject jobj = new JSONObject(coordinatesList.get(i).toString());
                ContentValues values = new ContentValues();
                values.put("coor_x", jobj.getString("coor_x"));
                values.put("coor_y", jobj.getString("coor_y"));
                values.put("license_number", jobj.getString("license_number"));

                mdb.insert(DBTable.Coordinates.TABLENAME, null, values);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Cursor selectAllTicket() {
        mdb = this.getReadableDatabase();
        String sql = "select * from numberticket";
        Cursor member_list = mdb.rawQuery(sql, null);

        return member_list;
    }

    public Cursor countTeam(String license_number) { //select 에 매개변수 받아서 쓰는SQL
        mdb = this.getReadableDatabase();
        Cursor member_list = mdb.rawQuery("select count(*) from numberticket where license_number=? and ticket_status = 0", new String[] {license_number});

        return member_list;
    }

    public void insertTicket(JSONArray NumberTicketList) {
        mdb = this.getWritableDatabase();

        for ( int i = 0; i < NumberTicketList.length(); i++) {
            try {
                JSONObject jobj = new JSONObject(NumberTicketList.get(i).toString());
                ContentValues values = new ContentValues();
                values.put("ticket_code", jobj.getString("ticket_code"));
                values.put("wait_number", jobj.getString("wait_number"));
                values.put("the_number", jobj.getString("the_number"));
                values.put("license_number", jobj.getString("license_number"));
                values.put("member_id", jobj.getString("member_id"));
                values.put("ticket_status", jobj.getString("ticket_status"));
                mdb.insert(DBTable.NumberTicket.TABLENAME, null, values);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}