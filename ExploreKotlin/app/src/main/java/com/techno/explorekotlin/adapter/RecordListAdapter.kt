package com.techno.explorekotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techno.explorekotlin.R
import com.techno.explorekotlin.model.RecordMain
import kotlinx.android.synthetic.main.row_list_row.view.*

/**
 * Created by thetaubuntu5 on 22/11/17.
 */
class RecordListAdapter(var context: Context, private var recordList: ArrayList<RecordMain>) : RecyclerView.Adapter<RecordListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            holder.bindHolders(recordList[position], position, context)
        } catch (e: Exception) {
            e.printStackTrace()

        }

    }

    override fun getItemCount(): Int {
        return recordList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.row_list_row, parent, false);
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindHolders(recodMain: RecordMain, pos: Int, context: Context) {
            if (recodMain != null) {
                itemView.tvName.setText("Name :" + recodMain.rName)
                itemView.tvMobile.setText("Phone:" + recodMain.rMobile)

            }
        }
    }

}