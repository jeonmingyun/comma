package ticketzone.org.com.app_mngr.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 5;
    private static final String DB_NAME = "SQLite.db";
    public static SQLiteDatabase mngrdb;

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBTable.Owner.CREATE_QUERY);
        db.execSQL(DBTable.Categorie.CREATE_QUERY);
        db.execSQL(DBTable.Member.CREATE_QUERY);
        db.execSQL(DBTable.Store.CREATE_QUERY);
        db.execSQL(DBTable.StoreMenu.CREATE_QUERY);
        db.execSQL(DBTable.Number_ticket.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBTable.Owner.DROP_QUERY);
        db.execSQL(DBTable.Categorie.DROP_QUERY);
        db.execSQL(DBTable.Member.DROP_QUERY);
        db.execSQL(DBTable.Store.DROP_QUERY);
        db.execSQL(DBTable.StoreMenu.DROP_QUERY);
        db.execSQL(DBTable.Number_ticket.DROP_QUERY);
        onCreate(db);
    }
    // Owner
    public Cursor selectAllOwner(){
        mngrdb = this.getReadableDatabase();
        String sql = "select * from owner";
        Cursor owner_list = mngrdb.rawQuery(sql, null);

        return owner_list;
    }
    public boolean updateOwner(String owner_id, String owner_password, String owner_tel, String email){
        mngrdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("owner_password", owner_password);
        values.put("owner_tel", owner_tel);
        values.put("email", email);

        int result = mngrdb.update(DBTable.Owner.TABLENAME, values, "owner_id=?", new String[] {owner_id} );

        if( result == 0)
            return false; // error
        else
            return true; // success
    }

    public boolean deleteOwner(String owner_id){
        mngrdb = this.getWritableDatabase();

        int result = mngrdb.delete(DBTable.Owner.TABLENAME, "owner_id=?", new String[] {owner_id} );
        if( result == 0)
            return false; // error
        else
            return true; // success
    }

    //Categorie
    public Cursor selectAllCategorie() {
        mngrdb = this.getReadableDatabase();
        String sql = "select * from categorie";
        Cursor cate_list = mngrdb.rawQuery(sql, null);

        return cate_list;
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

    //Store
    public Cursor selectAllStore(){
        mngrdb = this.getReadableDatabase();
        String sql = "select * from store";
        Cursor store_list = mngrdb.rawQuery(sql, null);

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
    //매장 전체수정
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
    //매장 번호표 설정(최대 발급인원 설정)
    public boolean updateMaxNumber(String license_number, String max_number){
        mngrdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("max_number", max_number);

        int result = mngrdb.update(DBTable.Store.TABLENAME, values, "license_number=?",new String[] {license_number});

        if( result == 0)
            return false; // error
        else
            return true; // success
    }
    //매장 상태 설정(번호표 활성화 비활성화)
    public boolean updateStatus(String license_number, String store_status){
        mngrdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("store_status", store_status);
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
        Cursor menu_list = mngrdb.rawQuery(sql, null);

        return menu_list;
    }
    //메뉴의 카테고리 가져오기
    public Cursor selectMenuCate(){
        mngrdb = this.getReadableDatabase();
        String sql = ""; // 수정해야함
        Cursor cate_list = mngrdb.rawQuery(sql,null);

        return cate_list;
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

    public boolean deleteStoreMenu(String menu_code){
        mngrdb = this.getWritableDatabase();

        int result = mngrdb.delete(DBTable.Store.TABLENAME, "menu_code=?", new String[] {menu_code} );

        if( result == 0)
            return false; // error
        else
            return true; // success
    }
    //메뉴수정
    public boolean updateMenu(String menu_code, String menu_cate, String menu_name, String menu_price, String store_note){
        mngrdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("menu_cate", menu_cate);
        values.put("menu_name", menu_name);
        values.put("menu_price", menu_price);
        values.put("store_note", store_note);

        int result = mngrdb.update(DBTable.StoreMenu.TABLENAME, values, "menu_code=?", new String[] {menu_code});

        if(result == 0)
            return false;
        else
            return true;
    }


}
