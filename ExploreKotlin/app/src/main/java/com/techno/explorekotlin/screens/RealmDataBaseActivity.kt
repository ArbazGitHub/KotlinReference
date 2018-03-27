package com.techno.explorekotlin.screens

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import com.techno.explorekotlin.R
import com.techno.explorekotlin.adapter.RecordListAdapter
import com.techno.explorekotlin.listener.RecyclerItemClickListener
import com.techno.explorekotlin.model.RecordMain
import com.techno.explorekotlin.model.RecordModel
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_realm_data_base.*

class RealmDataBaseActivity : AppCompatActivity(), View.OnClickListener {
    val realm = Realm.getDefaultInstance()
    var rID: Int = 0;

    var recordModel = RecordModel()
    lateinit var recordMain: RecordMain
    lateinit var recordList: ArrayList<RecordMain>
    var strName: String = ""
    var strMobile: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm_data_base)
        try {
            recordList = ArrayList()
            recordModel = RecordModel();
            rvRecord.layoutManager = LinearLayoutManager(this)

            btnRDAdd.setOnClickListener(this)
            btnRDUpdate.setOnClickListener(this)
            btnRDDelete.setOnClickListener(this)
            btnRDView.setOnClickListener(this)

            if (recordModel.viewRecord(realm).size>0){
                     btnRDView.performClick()
            }

            rvRecord.addOnItemTouchListener(RecyclerItemClickListener(this, rvRecord, object : RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    recordMain = recordList.get(position)
                    etName.setText("")
                    etMobile.setText("")
                    etName.setText(recordMain.rName)
                    etMobile.setText(recordMain.rMobile)
                }

                override fun onItemLongClick(view: View?, position: Int) {
                }
            }))
            strName = etName.text.toString()
            strMobile = etMobile.text.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnRDAdd -> {
                strName = etName.text.toString()
                strMobile = etMobile.text.toString()

                if (!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strMobile)) {
                    recordMain = RecordMain(rID, strName, strMobile)
                    recordModel.addRecord(realm, recordMain)
                    rID++
                    displayRecord(realm)
                    etName.setText("")
                    etMobile.setText("")
                } else {
                    Toast.makeText(this, resources.getString(R.string.add_record_field_empty_msg), Toast.LENGTH_LONG).show()
                }

            }
            R.id.btnRDUpdate -> {
                strName = etName.text.toString()
                strMobile = etMobile.text.toString()

                if (recordMain != null && !TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strMobile)) {
                    recordModel.updateRecord(realm, recordMain._ID, strName, strMobile)
                    displayRecord(realm)
                    etName.setText("")
                    etMobile.setText("")
                }

            }
            R.id.btnRDDelete -> {
                if (recordMain != null) {
                    recordModel.deleteRecord(realm, recordMain._ID)
                    displayRecord(realm)
                }
                etName.setText("")
                etMobile.setText("")
            }
            R.id.btnRDView -> {
                recordList = ArrayList()
                displayRecord(realm)
            }
        }
    }

    fun displayRecord(realm: Realm) {
        recordList = ArrayList()
        var results = recordModel.viewRecord(realm)
        if(results.size>0){
            rvRecord.visibility=View.VISIBLE
            tvEmpty.visibility=View.GONE
        results.forEach { results ->
            recordMain = RecordMain(results._ID, results.rName, results.rMobile)
            recordList.add(recordMain)
            rvRecord.adapter = RecordListAdapter(this, recordList)
        }
        }else{
            rvRecord.visibility=View.GONE
            tvEmpty.visibility=View.VISIBLE
        }
    }

}
