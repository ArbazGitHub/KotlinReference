package com.tech.kotlinexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.tech.kotlinexample.adapter.CustomAdapter

class MainActivity : AppCompatActivity(), TextWatcher, View.OnClickListener {

    private var edWrite: android.widget.EditText? = null
    private var btnSubmit: android.widget.Button? = null
    private var rvRecordList: RecyclerView? = null
    var etVal1: EditText? = null

    var etVal2: EditText? = null
    var tvAnswer: TextView? = null
    var isFirst: Boolean? = false;
    var isSecond: Boolean? = false;
    var isUpdatedFirst: Boolean? = false;

    var tvEmpty: TextView? = null
    var tvClearList: TextView? = null
    var strList = ArrayList<String>();
    var adapter: CustomAdapter? = null

    var x: Int? = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btnSubmit = findViewById(R.id.btnSubmit) as Button
        this.edWrite = findViewById(R.id.edWrite) as EditText
        this.etVal1 = findViewById(R.id.etVal1) as EditText
        this.etVal2 = findViewById(R.id.etVal2) as EditText
        this.tvAnswer = findViewById(R.id.tvAnswer) as TextView
        this.tvEmpty = findViewById(R.id.tvEmpty) as TextView
        this.tvClearList = findViewById(R.id.tvClearList) as TextView

        this.rvRecordList = findViewById(R.id.rvRecordList) as RecyclerView
        rvRecordList!!.layoutManager = LinearLayoutManager(this)


        /*OnClick Method Here*/
//        btnSubmit!!.setOnClickListener {
//            if (!TextUtils.isEmpty(edWrite!!.text.toString())) {
//                rvRecordList!!.visibility = View.VISIBLE
//                tvEmpty!!.visibility = View.GONE
//                strList.add(edWrite!!.text.toString())
//                adapter.notifyDataSetChanged()
//                edWrite!!.setText("")
//            }
//        }
        /*Click Event Here*/
        btnSubmit!!.setOnClickListener(this)
        tvClearList!!.setOnClickListener(this)


        /*Get RecyclerView Item Click Here*/
        rvRecordList!!.addOnItemTouchListener(RecyclerItemClickListener
        (this, RecyclerItemClickListener.OnItemClickListener { view, position ->
            var firstVal: String = etVal1!!.text.toString()
            var secondVal: String = etVal2!!.text.toString()
            var str: String = strList.get(position);

            /*Custom Toast Here*/
//            var toast: Toast = Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT)
//            toast.setGravity(Gravity.CENTER, 0, 0)
//            toast.show()

            /*Kotlin(IF...ELSE) if..else condition Here*/
            if (isFirst == false || isSecond == false) {
                if (firstVal.isEmpty()) {
                    etVal1!!.setText(str)
                    isFirst = true
                } else if (secondVal.isEmpty()) {
                    etVal2!!.setText(str)
                    isSecond = true
                }
            } else {
                if (!firstVal.isEmpty() && isUpdatedFirst == false) {
                    etVal1!!.setText(str)
                    isUpdatedFirst = true;

                } else if (!secondVal.isEmpty() && isUpdatedFirst == true) {
                    etVal2!!.setText(str)
                    isUpdatedFirst = false
                }
            }

        }))

        /*Edit text Change event*/
        etVal1!!.addTextChangedListener(this)
        etVal2!!.addTextChangedListener(this)



    }


    /*Edit Text Method Here*/
    override fun afterTextChanged(s: Editable?) {
        var firstVal: String = etVal1!!.text.toString()
        var secondVal: String = etVal2!!.text.toString()
        var ans: Int? = null
        var maxAns: Int? = null
        if (!TextUtils.isEmpty(firstVal) && !TextUtils.isEmpty(secondVal)) {
            /*Add function Call Here*/
            ans = addVal(Integer.parseInt(firstVal), Integer.parseInt(secondVal)) as Int
            /*Max function Call Here*/
            maxAns = maxVAl(Integer.parseInt(firstVal), Integer.parseInt(secondVal))
            tvAnswer!!.setText("Answer Is=  " + ans.toString() + "  MaxVal= " + maxAns.toString())

        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    /*Find Max Value using Kotlin (IF..Else) if..else condition function*/
    private fun maxVAl(a: Int, b: Int): Int? {
        var max: Int
        if (a > b)
            max = a
        else
            max = b
        return max
    }

    /*sum of two value kotlin function Here*/
    private fun addVal(a: Int, b: Int): Int {
        return a + b
    }



    /*kotlin (WHEN)  click method Here Like Switch Case*/
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSubmit -> {
                if (!TextUtils.isEmpty(edWrite!!.text.toString())) {
                    rvRecordList!!.visibility = View.VISIBLE
                    tvClearList!!.visibility = View.VISIBLE
                    tvEmpty!!.visibility = View.GONE

                    strList.add(edWrite!!.text.toString())
                    edWrite!!.setText("")

                    adapter = CustomAdapter(this, strList) as CustomAdapter
                    rvRecordList!!.adapter = adapter
                    adapter!!.notifyDataSetChanged()

                }
            }
            R.id.tvClearList -> {
                //Clear Array List
                for (i in strList.indices
                ){
                    Log.e("ArrayString",strList[i])
                }
                strList.clear()
                if (strList.isEmpty() && strList.size > 0) {
                    rvRecordList!!.visibility = View.VISIBLE
                    tvClearList!!.visibility = View.VISIBLE
                    tvEmpty!!.visibility = View.GONE
                } else {
                    rvRecordList!!.visibility = View.GONE
                    tvClearList!!.visibility = View.GONE
                    tvEmpty!!.visibility = View.VISIBLE
                }
            }

            else -> {

            }
        }
    }
}






