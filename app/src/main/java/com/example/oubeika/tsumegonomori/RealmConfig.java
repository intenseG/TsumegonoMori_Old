package com.example.oubeika.tsumegonomori;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmConfig extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(this).schemaVersion(42).migration(new Migration()).build();
        Realm.setDefaultConfiguration(config);
    }
}
