package com.techno.explorekotlin

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by Arbaz.
 * Date: 18/1/18
 * Time: 4:23 PM
 */
class ExploreKotlinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        try {
            Realm.init(this)
            val config = RealmConfiguration.Builder()
                    .name("record.realm")
                    .deleteRealmIfMigrationNeeded()
                    .build()
            Realm.setDefaultConfiguration(config)
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }

}