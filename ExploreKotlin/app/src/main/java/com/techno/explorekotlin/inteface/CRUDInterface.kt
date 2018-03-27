package com.techno.explorekotlin.inteface

import com.techno.explorekotlin.model.RecordMain
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by Arbaz.
 * Date: 18/1/18
 * Time: 4:30 PM
 */
interface CRUDInterface {
    fun addRecord(realm: Realm, recordMain: RecordMain): Boolean
    fun updateRecord(realm: Realm, id: Int,name:String,mobile:String): Boolean
    fun deleteRecord(realm: Realm, id: Int): Boolean
    fun viewRecord(realm: Realm): RealmResults<RecordMain>
}