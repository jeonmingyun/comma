package ticketzone.org.com.app_mngr.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 7;
    private static final String DB_NAME = "SQLite.db";
    public static SQLiteDatabase mngrdb;

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("create table", "create table");
        db.execSQL(DBTable.Owner.CREATE_QUERY);
        db.execSQL(DBTable.Categorie.CREATE_QUERY);
        db.execSQL(DBTable.Store.CREATE_QUERY);
        db.execSQL(DBTable.StoreMenu.CREATE_QUERY);
//        db.execSQL(DBTable.NumberTicket.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBTable.StoreMenu.DROP_QUERY);
        db.execSQL(DBTable.Store.DROP_QUERY);
        db.execSQL(DBTable.Categorie.DROP_QUERY);
        db.execSQL(DBTable.Owner.DROP_QUERY);
//        db.execSQL(DBTable.NumberTicket.DROP_QUERY);
        onCreate(db);
    }
    public Cursor selectAllOwner() {
        mngrdb = this.getReadableDatabase();
        String sql = "select * from owner";
        Cursor member_list = mngrdb.rawQuery(sql, null);

        return member_list;
    }

    public void insertOwner(JSONArray ownerList) {
        mngrdb = this.getWritableDatabase();
        for(int i = 0; i < ownerList.length(); i++) {
            try {
                JSONObject jobj = new JSONObject(ownerList.get(i).toString());
                ContentValues values = new ContentValues();
                values.put("owner_id", jobj.getString("owner_id"));

                mngrdb.insert(DBTable.Owner.TABLENAME, null, values);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    // Store
    public Cursor selectAllStore() {
        mngrdb = this.getReadableDatabase();
        String sql = "select * from store";
        Cursor store_list = mngrdb.rawQuery(sql, null);

        Log.e("storeList", store_list.getCount()+"");
        return store_list;
    }


    public void insertStore(JSONArray storeList) {
        mngrdb = this.getWritableDatabase();

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
                values.put("store_name", jobj.getString("store_name"));
                values.put("store_tel", jobj.getString("store_tel"));
                values.put("store_time", jobj.getString("store_time"));
                values.put("store_intro", jobj.getString("store_intro"));
                values.put("address_name", jobj.getString("address_name"));

                mngrdb.insert(DBTable.Store.TABLENAME, null, values);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean deleteStore(String license_number) {
        mngrdb = this.getWritableDatabase();

        int result = mngrdb.delete(DBTable.Store.TABLENAME, "license_number=?", new String[] {license_number} );

        if( result == 0)
            return false; // error
        else
            return true; // success
    }

    public boolean updateStore(String license_number, String max_number, String store_status, String cate_code, String store_tel, String store_time, String store_intro, String address_name) {
        mngrdb = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("max_number", max_number);
        values.put("store_status", store_status);
        values.put("cate_code", cate_code);
        values.put("store_tel", store_tel);
        values.put("store_time", store_time);
        values.put("store_intro", store_intro);
        values.put("address_number", address_name);

        int result = mngrdb.update(DBTable.Store.TABLENAME, values, "license_number=?",new String[] {license_number});

        if( result == 0)
            return false; // error
        else
            return true; // success
    }

    // StoreMenu
    public Cursor selectAllStoreMenu() {
        mngrdb = this.getReadableDatabase();
        String sql = "select * from store_menu";
        Cursor member_list = mngrdb.rawQuery(sql, null);

        return member_list;
    }


    public void insertStoreMenu(JSONArray menuList) {
        mngrdb = this.getWritableDatabase();

        for ( int i = 0; i < menuList.length(); i++) {
            try {
                JSONObject jobj = new JSONObject(menuList.get(i).toString());
                ContentValues values = new ContentValues();
                values.put("menu_code", jobj.getString("menu_code"));
                values.put("menu_name", jobj.getString("menu_name"));
                values.put("menu_price", jobj.getString("menu_price"));
                values.put("store_note", jobj.getString("store_note"));

                mngrdb.insert(DBTable.StoreMenu.TABLENAME, null, values);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // Categorie
    public Cursor selectAllCategorie() {
        mngrdb = this.getReadableDatabase();
        String sql = "select * from categorie";
        Cursor member_list = mngrdb.rawQuery(sql, null);

        return member_list;
    }

    public void insertCategorie(JSONArray categorieList) {
        mngrdb = this.getWritableDatabase();

        for ( int i = 0; i < categorieList.length(); i++) {
            try {
                JSONObject jobj = new JSONObject(categorieList.get(i).toString());
                ContentValues values = new ContentValues();
                values.put("cate_code", jobj.getString("cate_code"));
                values.put("cate_name", jobj.getString("cate_name"));

                mngrdb.insert(DBTable.Categorie.TABLENAME, null, values);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    // NumberTicket
    public Cursor selectAllTicket() {
        mngrdb = this.getReadableDatabase();
        String sql = "select * from numberticket";
        Cursor member_list = mngrdb.rawQuery(sql, null);

        return member_list;
    }

    public void insertTicket(JSONArray categorieList) {
        mngrdb = this.getWritableDatabase();

        for ( int i = 0; i < categorieList.length(); i++) {
            try {
                JSONObject jobj = new JSONObject(categorieList.get(i).toString());
                ContentValues values = new ContentValues();
                values.put("ticket_code", jobj.getString("ticket_code"));
                values.put("wait_number", jobj.getString("wait_number"));
                values.put("the_number", jobj.getString("the_number"));
                values.put("license_number", jobj.getString("license_number"));
                values.put("member_id", jobj.getString("member_id"));
                values.put("ticket_status", jobj.getString("ticket_status"));
                values.put("string_status", jobj.getString("string_status"));
                mngrdb.insert(DBTable.NumberTicket.TABLENAME, null, values);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteAllTable(){
        mngrdb = this.getWritableDatabase();
        mngrdb.delete(DBTable.Store.TABLENAME,null,null);
        mngrdb.delete(DBTable.StoreMenu.TABLENAME,null,null);
        mngrdb.delete(DBTable.Categorie.TABLENAME,null,null);
        mngrdb.delete(DBTable.Owner.TABLENAME,null,null);
        mngrdb.delete(DBTable.NumberTicket.TABLENAME, null,null);

    }

}
