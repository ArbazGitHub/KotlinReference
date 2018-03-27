package com.techno.explorekotlin.screens

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.techno.explorekotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatabase.setOnClickListener(this)
        btnNavigationBar.setOnClickListener(this)
        btnTabLayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        try {
            when (v!!.id) {
                R.id.btnDatabase -> {
                    val intent = Intent(this, RealmDataBaseActivity::class.java)
                    startActivity(intent)
                }
                R.id.btnNavigationBar -> {
                    val intent = Intent(this, SlideMenuActivity::class.java)
                    startActivity(intent)
                }
                R.id.btnTabLayout -> {
                    val intent = Intent(this, TabLayoutActivity::class.java)
                    startActivity(intent)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
