package com.techno.explorekotlin.model

import com.techno.explorekotlin.inteface.CRUDInterface
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by Arbaz.
 * Date: 18/1/18
 * Time: 4:37 PM
 */
class RecordModel : CRUDInterface {

    override fun addRecord(realm: Realm, recordMain: RecordMain): Boolean {
        try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(recordMain)
            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    override fun updateRecord(realm: Realm, id: Int, name: String, mobile: String): Boolean {
        try {
            realm!!.beginTransaction()
            var records = realm.where(RecordMain::class.java).equalTo("_ID", id).findAll()
            records.forEach { data ->
                data.rName = name
                data.rMobile = mobile

            }
            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    override fun deleteRecord(realm: Realm, id: Int): Boolean {
        try {
            realm.beginTransaction()
            var records = realm.where(RecordMain::class.java).equalTo("_ID", id).findAll()
            records.forEach { data -> data.deleteFromRealm() }
            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    override fun viewRecord(realm: Realm): RealmResults<RecordMain> {
        return realm!!.where(RecordMain::class.java).findAll()
    }

}