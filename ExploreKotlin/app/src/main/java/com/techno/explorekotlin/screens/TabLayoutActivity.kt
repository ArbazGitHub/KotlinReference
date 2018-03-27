package com.techno.explorekotlin.screens

import android.graphics.Color
import android.graphics.LightingColorFilter
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.techno.explorekotlin.R
import com.techno.explorekotlin.fragments.common.BaseFragment
import com.techno.explorekotlin.fragments.tabs.TabOneFragment
import com.techno.explorekotlin.utils.FragmentNav.FragNavController

class TabLayoutActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener, FragNavController.TransactionListener, FragNavController.RootFragmentListener,TabOneFragment.OnFragmentInteractionListener, BaseFragment.FragmentNavigation {

    private var tabLayout: TabLayout? = null
    private var tabContainer: FrameLayout? = null

    private var mNavController: FragNavController? = null
    private val INDEX_ONE = FragNavController.TAB1
    private val INDEX_TWO = FragNavController.TAB2
    private val INDEX_THREE = FragNavController.TAB3

    private var mTabsTitle: Array<String>? = null

    private val mTabsIcons = intArrayOf(R.drawable.tab_selector, R.drawable.tab_selector, R.drawable.tab_selector)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)
        try {
            initViews();
            tabLayout!!.addOnTabSelectedListener(this)

            mNavController = FragNavController.newBuilder(savedInstanceState, supportFragmentManager, R.id.flContainer)
                    .transactionListener(this)
                    .rootFragmentListener(this, 3)
                    .build()
            mTabsTitle = arrayOf(getString(R.string.tab_title_one), getString(R.string.tab_title_two), getString(R.string.tab_title_three))
            //set tabs here
            setTabs()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun initViews() {
        try {
            tabLayout = findViewById(R.id.tabLayout)
            tabContainer = findViewById(R.id.flContainer)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    // Function to set Tabs
    private fun setTabs() {
        tabLayout!!.addTab(tabLayout!!.newTab().setCustomView(getTabView(0)))
        tabLayout!!.addTab(tabLayout!!.newTab().setCustomView(getTabView(1)))
        tabLayout!!.addTab(tabLayout!!.newTab().setCustomView(getTabView(2)))
        //Default tab selected
        tabLayout!!.getTabAt(INDEX_ONE)!!.customView!!.isSelected = true
    }

    // Get Custom Tab
    fun getTabView(position: Int): View {
        val view = LayoutInflater.from(this@TabLayoutActivity).inflate(R.layout.custom_tab, null)
        val title = view.findViewById<View>(R.id.title) as TextView
        if (Build.VERSION.SDK_INT > 23)
            title.setTextColor(resources.getColorStateList(R.drawable.tab_text_selector, null))
        else
            title.setTextColor(resources.getColorStateList(R.drawable.tab_text_selector))
        title.text = mTabsTitle!![position]
        val icon = view.findViewById<View>(R.id.icon) as ImageView
        icon.setImageResource(mTabsIcons[position])
        return view
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        when (tab.position) {
            0 -> mNavController!!.switchTab(INDEX_ONE)
            1 -> mNavController!!.switchTab(INDEX_TWO)
            2 -> mNavController!!.switchTab(INDEX_THREE)
            else -> {
            }
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {
        when (tab.position) {

        }
    }

    override fun onTabReselected(tab: TabLayout.Tab) {
        when (tab.position) {

        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (mNavController != null) {
            if (outState != null) {
                mNavController!!.onSaveInstanceState(outState)
            }
        }
    }



    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        if (supportActionBar != null && mNavController != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(!mNavController!!.isRootFragment)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }


    override fun onFragmentTransaction(fragment: Fragment, transactionType: FragNavController.TransactionType) {
        if (supportActionBar != null && mNavController != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(!mNavController!!.isRootFragment)
            val arrowBack = ContextCompat.getDrawable(this@TabLayoutActivity, R.drawable.ic_arrow_back)
            //            if (fragment instanceof SettingsFragment) {
            //                ColorFilter filter = new LightingColorFilter(Color.WHITE, Color.WHITE);
            //                arrowBack.setColorFilter(filter);
            //                getSupportActionBar().setHomeAsUpIndicator(arrowBack);
            //            } else {
            val filter = LightingColorFilter(Color.BLACK, Color.BLACK)
            arrowBack.colorFilter = filter
            supportActionBar!!.setHomeAsUpIndicator(arrowBack)
            //            }


        }
    }

    override fun getRootFragment(index: Int): Fragment {
        when (index) {
            INDEX_ONE -> return TabOneFragment.newInstance("", "")
            INDEX_TWO -> return TabOneFragment.newInstance("", "")
            INDEX_THREE -> return TabOneFragment.newInstance("", "")
            else -> {
            }
        }
        throw IllegalStateException("Need to send an index that we know")
    }

    override fun onBackPressed() {
        if (!mNavController!!.isRootFragment) {
            mNavController!!.popFragment()
        } else {
            super.onBackPressed()
        }
    }

    override fun pushFragment(fragment: Fragment) {
        if (mNavController != null) {
            mNavController!!.pushFragment(fragment)
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
