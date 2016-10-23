package com.example.oubeika.tsumegonomori;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            schema.create("GoData")
                    .addField("qNum", String.class)
                    .addField("teban", String.class)
                    .addField("level", String.class)
                    .addField("id", int.class, FieldAttribute.PRIMARY_KEY)
                    .addField("colP", int.class)
                    .addField("rowP", int.class)
                    .addField("stoneColorP", int.class)
                    .addField("colA", int.class)
                    .addField("rowA", int.class)
                    .addField("stoneColorA", int.class);

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

