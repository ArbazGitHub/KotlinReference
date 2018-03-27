package com.techno.explorekotlin.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Arbaz.
 * Date: 18/1/18
 * Time: 4:22 PM
 */
open class RecordMain(@PrimaryKey
                      open var _ID: Int = 0,
                      open var rName: String = "",
                      open var rMobile: String = "") : RealmObject() {
}