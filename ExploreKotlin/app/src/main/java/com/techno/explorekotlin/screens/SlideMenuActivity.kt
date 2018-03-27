package com.techno.explorekotlin.screens

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.techno.explorekotlin.R
import com.techno.explorekotlin.adapter.LeftMenuAdapter
import com.techno.explorekotlin.fragments.Menu1
import com.techno.explorekotlin.listener.RecyclerItemClickListener
 import kotlinx.android.synthetic.main.activity_slide_menu.*
import kotlinx.android.synthetic.main.app_bar_slide_menu.*

class SlideMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    internal lateinit var leftMenuList: ArrayList<String>
    lateinit var menuName: String
    lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_menu)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        try {

            //set Default Fragment Here
            fragment = Menu1()
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction()
                    .add(R.id.FLContainer, fragment)
                    .commit()


            rvLeftMenu.layoutManager = LinearLayoutManager(this)
            leftMenuList = ArrayList()
            for (i in 1..50) {
                leftMenuList.add("Menu" + "" + i)
            }
            rvLeftMenu.adapter = LeftMenuAdapter(this, leftMenuList)

            rvLeftMenu.addOnItemTouchListener(RecyclerItemClickListener(this, rvLeftMenu, object : RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    menuName = leftMenuList.get(position)
                    Toast.makeText(applicationContext,menuName,Toast.LENGTH_SHORT).show()
                    changeFragment(Menu1())
                    val drawer_home = findViewById(R.id.drawer_layout) as DrawerLayout
                    drawer_home.closeDrawer(GravityCompat.START)

                }

                override fun onItemLongClick(view: View?, position: Int) {
                }
            }))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.slide_menu_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    internal fun changeFragment(fragment: Fragment) {
        try {
            val backStateName = fragment.javaClass.name
            val manager = supportFragmentManager
            val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
            if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
                val fragmentTransaction = manager.beginTransaction()
                fragmentTransaction.replace(R.id.FLContainer, fragment, backStateName)
                fragmentTransaction.addToBackStack(backStateName)
                fragmentTransaction.commit()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
