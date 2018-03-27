package com.techno.kotlindemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_main.*;

var strName: String? = "I am in main activity"

class MainActivity : AppCompatActivity(), TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        tvText.setText("Text goes here!")
        btnClick.setOnClickListener {
            if (etText != null && !TextUtils.isEmpty(etText.text))
                tvText.setText(etText.text)
            else
                Toast.makeText(applicationContext, "Enter value", Toast.LENGTH_LONG).show()
        }
        etText!!.addTextChangedListener(this)
    }

}
