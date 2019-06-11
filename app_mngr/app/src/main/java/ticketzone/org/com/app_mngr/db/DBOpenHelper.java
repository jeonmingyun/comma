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

    private static final int DB_VERSION = 15;
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
        db.execSQL(DBTable.NumberTicket.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBTable.StoreMenu.DROP_QUERY);
        db.execSQL(DBTable.Store.DROP_QUERY);
        db.execSQL(DBTable.Categorie.DROP_QUERY);
        db.execSQL(DBTable.Owner.DROP_QUERY);
        db.execSQL(DBTable.NumberTicket.DROP_QUERY);
        onCreate(db);
    }
    public Cursor selectAllOwner() {
        mngrdb = this.getWritableDatabase();
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
                values.put("owner_password", jobj.getString("owner_password"));
                values.put("owner_name", jobj.getString("owner_name"));
                values.put("owner_tel", jobj.getString("owner_tel"));
                values.put("email", jobj.getString("email"));

                mngrdb.insert(DBTable.Owner.TABLENAME, null, values);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    // Store
    public Cursor selectAllStore() {
        mngrdb = this.getWritableDatabase();
        String sql = "select * from store";
        Cursor member_list = mngrdb.rawQuery(sql, null);

        return member_list;
    }

    public Cursor selectStore(String store_name){
        mngrdb = this.getWritableDatabase();
        Cursor store_list = mngrdb.rawQuery("select * from store where store_name = ?", new String[] {store_name});

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
                values.put("store_tel", jobj.getString("store_tel"));
                values.put("store_time", jobj.getString("store_time"));
                values.put("store_name", jobj.getString("store_name"));
                values.put("store_intro", jobj.getString("store_intro"));
                values.put("img_uuid", jobj.getString("img_uuid"));
                values.put("img_uploadpath", jobj.getString("img_uploadpath"));
                values.put("img_filename", jobj.getString("img_filename"));
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

    public void updateStore_time(String license_number, String store_time, String store_time2){
        mngrdb = this.getWritableDatabase();
        String sqlUpdate = "update store set store_time = ? || '-' || ? where license_number = ?";
        mngrdb.execSQL(sqlUpdate, new String[] {store_time, store_time2, license_number});
    }

    public void updateStore_maxnum(String license_number, String max_num){
        mngrdb = this.getWritableDatabase();
        String sqlUpdate = "update store set max_number = ? where license_number = ?";
        mngrdb.execSQL(sqlUpdate, new String[] {max_num, license_number});
    }



    // StoreMenu
    public Cursor selectAllStoreMenu() {
        mngrdb = this.getWritableDatabase();
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
                values.put("menu_cate", jobj.getString("menu_cate"));
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
        mngrdb = this.getWritableDatabase();
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
        mngrdb = this.getWritableDatabase();
        String sql = "select * from numberticket";
        Cursor member_list = mngrdb.rawQuery(sql, null);

        return member_list;
    }

    public Cursor selectWating(String license_number){
         mngrdb = this.getWritableDatabase();
        Cursor member_list = mngrdb.rawQuery("select max(substr(ticket_code,19)) as ticket_code from numberticket where ticket_code like strftime('%Y%m%d', 'now') || '%' and license_number = ?", new String[] {license_number});
        return member_list;
    }

    public Cursor ticketStatus(String license_number){
        mngrdb = this.getWritableDatabase();
        Cursor member_list = mngrdb.rawQuery("WITH\n" +
                "wait as\n" +
                "(select count(string_status) as wait from numberticket where string_status = 'wait' and ticket_code like strftime('%Y%m%d', 'now') || '%' and license_number = ?),\n" +
                "success as (select count(string_status) as success from numberticket where string_status = 'success' and ticket_code like strftime('%Y%m%d', 'now') || '%' and license_number = ?),\n" +
                "cancel as (select count(string_status) as cancel from numberticket where string_status = 'cancel' and ticket_code like strftime('%Y%m%d', 'now') || '%' and license_number = ?),\n" +
                "absence as (select count(string_status) as absence from numberticket where string_status = 'absence' and ticket_code like strftime('%Y%m%d', 'now') || '%' and license_number = ?)\n" +
                "select a.*,b.*,c.*,d.*\n" +
                "from wait a, success b, cancel c, absence d", new String[] {license_number});
        return  member_list;
    }

    public void insertTicket(JSONArray NumberTicketList) {
        mngrdb = this.getWritableDatabase();

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
                values.put("ticket_reg", jobj.getString("ticket_reg"));
                values.put("string_status",jobj.getString("string_status"));
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