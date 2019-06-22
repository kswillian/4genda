package com.example.nelso.a4genda;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ConfigurationRealm extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
