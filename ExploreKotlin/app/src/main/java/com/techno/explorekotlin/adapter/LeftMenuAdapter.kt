package com.techno.explorekotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techno.explorekotlin.R
import com.techno.explorekotlin.model.RecordMain
import kotlinx.android.synthetic.main.left_menu_list_row.view.*
import kotlinx.android.synthetic.main.row_list_row.view.*

/**
 * Created by thetaubuntu5 on 22/11/17.
 */
class LeftMenuAdapter(var context: Context, private var leftMenuList: ArrayList<String>) : RecyclerView.Adapter<LeftMenuAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            holder.bindHolders(leftMenuList[position], position, context)
        } catch (e: Exception) {
            e.printStackTrace()

        }

    }

    override fun getItemCount(): Int {
        return leftMenuList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.left_menu_list_row, parent, false);
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindHolders(leftMenu: String, pos: Int, context: Context) {
            if (leftMenu!= null) {
                itemView.tvMenu.setText(leftMenu)
            }
        }
    }

}