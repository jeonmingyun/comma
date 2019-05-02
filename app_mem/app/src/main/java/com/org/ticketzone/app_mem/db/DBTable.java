package com.org.ticketzone.app_mem.db;

import android.provider.BaseColumns;

public final class DBTable {

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

}
