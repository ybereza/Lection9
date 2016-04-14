package ru.mail.techotrack.lection9;

import android.app.Application;

/**
 * Created by vlad on 14/04/16.
 */
public class MyApplication extends Application{

    private static DBHelper db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = new DBHelper(getApplicationContext());
    }

    static public synchronized DBHelper getDb() {
        return db;
    }
}
