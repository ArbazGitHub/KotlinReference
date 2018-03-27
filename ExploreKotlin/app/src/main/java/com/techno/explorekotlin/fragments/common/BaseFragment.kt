package com.techno.explorekotlin.fragments.common

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Button

/**
 * Created by Arbaz.
 * Date: 29/1/18
 * Time: 3:45 PM
 */
class BaseFragment : Fragment(),
ActivityCompat.OnRequestPermissionsResultCallback {

    internal var mButton: Button? = null
    lateinit var mFragmentNavigation: FragmentNavigation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FragmentNavigation) {
            mFragmentNavigation = (context as FragmentNavigation?)!!
        }
    }

    interface FragmentNavigation {
        fun pushFragment(fragment: android.support.v4.app.Fragment)
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onResume() {
        super.onResume()


    }


}

