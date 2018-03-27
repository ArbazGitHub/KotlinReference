package com.techno.kotlindemo.screens

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.SyncStateContract
import android.view.Menu
import com.techno.kotlindemo.MainActivity
import com.techno.kotlindemo.R
import com.techno.kotlindemo.global.Constant

class SplashActivity : AppCompatActivity() {
    /** Duration of wait  */
    var SPLASH_DISPLAY_LENGTH: Long = 1000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val intent = Intent()
            intent.setClass(this, MainActivity::class.java);
            intent.putExtra("SplashData", "Kotlin Example")
            startActivity(intent)
        }, SPLASH_DISPLAY_LENGTH)
    }
}
