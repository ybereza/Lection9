package ru.mail.techotrack.lection9;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vlad on 14/04/16.
 */
public class DBHelper  extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DB_NAME = "contacts";
    private Context context;

    private static String DT_NAME = "contacts";
    private static String FN_ID = "id";
    private static String FN_NAME = "full_name";
    private static String FN_PHONE1 = "phone1";
    private static String FN_PHONE2 = "phone2";
    private static String FN_EMAIL = "email";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + DT_NAME + " ("
                + FN_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FN_NAME + "  TEXT, "
                + FN_PHONE1 + "  TEXT, "
                + FN_PHONE2 + "  TEXT, "
                + FN_EMAIL + "  TEXT )";
        db.execSQL(query);
        // TODO: Load data from content provider
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: Drop all tables
        db.execSQL("DROP TABLE IF EXISTS `conctacts`");
        onCreate(db);
    }

    public void addContact(AppContact contact) {
        SQLiteDatabase db = getWritableDatabase();
        assert db != null;
        ContentValues v = new ContentValues();
        v.put(FN_NAME, contact.getName());
        v.put(FN_PHONE1, contact.getPhone1());
        v.put(FN_PHONE2, contact.getPhone2());
        v.put(FN_EMAIL, contact.getEmail());
        db.insert(DT_NAME, null, v);
    }

    public long getSize() {
        SQLiteDatabase db = getReadableDatabase();
        long cnt = DatabaseUtils.queryNumEntries(db, DT_NAME);
        return cnt;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(DT_NAME, null, null, null, null, null, null);
    }

}
