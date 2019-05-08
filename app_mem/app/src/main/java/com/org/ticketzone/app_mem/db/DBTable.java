package com.org.ticketzone.app_mem.db;

import android.provider.BaseColumns;

public final class DBTable {

    /*회원*/
    public static final class Member implements BaseColumns {
        public static final String MEMBER_TEL = "member_tel";
        public static final String MEMBER_NAME = "member_name";
        public static final String MEMBER_BIRTH= "member_birth";
        public static final String TABLENAME= "member";
        public static final String CREATE_QUERY= "create table "+ TABLENAME + "("
                + MEMBER_TEL + " text primary key,"
                + MEMBER_NAME + " text not null,"
                + MEMBER_BIRTH + " text not null );";
        public static final String DROP_QUERY= "drop table if exists "+TABLENAME;
    }

    /*점주*/
    public static final class Owner implements  BaseColumns{
        public static final String OWNER_ID = "owner_id";
        public static final String TABLENAME = "owner";
        public static final String CREATE_QUERY = "create table " + TABLENAME + "("
                + OWNER_ID + "text primary key);";
        public static final String DROP_QUERY = "drop table if exists " + TABLENAME;
    }

    /*카테고리*/
    public static final class Categorie implements BaseColumns{
        public static final String CATE_CODE = "cate_code";
        public static final String CATE_NAME = "cate_name";
        public static final String TABLENAME = "categorie";
        public static final String CREATE_QUERY = "create table " + TABLENAME + "("
                + CATE_CODE + "text primary key,"
                + CATE_NAME + "text not null);";
        public static final String DROP_QUERY = "drop table if exists "+ TABLENAME;
    }

    /*좌표*/
    public static final class Coordinates implements BaseColumns{
        public static final String COOR_X = "coor_x";
        public static final String COOR_Y = "coor_y";
        public static final String LICENSE_NUMBER = "license_number";
        public static final String TABLENAME = "coordinates";
        public static final String CREATE_QUERY = "create table " + TABLENAME + "("
                + COOR_X + "text not null,"
                + COOR_Y + "text not null,"
                + LICENSE_NUMBER + " foreign key("+LICENSE_NUMBER+") references STORE("+LICENSE_NUMBER+");";
        public static final String DROP_QUERY = "drop table if exists " + TABLENAME;
    }

    /*매장*/
    public static final class Store implements BaseColumns{
        public static final String LICENSE_NUMBER = "license_number";
        public static final String R_NAME = "r_name";
        public static final String MAX_NUMBER = "max_number";
        public static final String STORE_STATUS = "store_stats";
        public static final String CATE_CODE = "cate_code";
        public static final String OWNER_ID = "owner_id";
        public static final String STORE_TEL = "store_tel";
        public static final String STORE_TIME = "store_time";
        public static final String STORE_INTRO = "store_intro";
        public static final String ADDRESS_NAME = "address_name";
        public static final String TABLENAME = "store";
        public static final String CREATE_QUERY = "create table " + TABLENAME + "("
                + LICENSE_NUMBER + " text primary key,"
                + R_NAME + " text not null,"
                + MAX_NUMBER + " text,"
                + STORE_STATUS + " integer default 0,"
                + CATE_CODE + " foreign key("+CATE_CODE+") references CATEGORIE("+CATE_CODE+"),"
                + OWNER_ID + " foreign key("+OWNER_ID+") references OWNER("+OWNER_ID+"),"
                + STORE_TEL + " text not null unique,"
                + STORE_TIME + " text not null,"
                + STORE_INTRO + " text not null,"
                + ADDRESS_NAME + " text not null);";
        public static final String DROP_QUERY= "drop table if exists " + TABLENAME;
    }

}
