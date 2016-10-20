package com.example.oubeika.tsumegonomori;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            // Migrate from v0 to v1
            oldVersion++;
        }

        if (oldVersion == 1) {
            // Migrate from v1 to v2
            oldVersion++;
        }

        if (oldVersion < newVersion) {
            throw new IllegalStateException(String.format("Migration missing from v%d to v%d", oldVersion, newVersion));
        }
    }
}

