package com.tech.kotlinexample.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tech.kotlinexample.R

/**
 * Created by arbaz on 5/7/17.
 */
class CustomAdapter(private var context: Context, private var strList: ArrayList<String>) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false);
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var Str: String = strList.get(position)
        if (Str != null) {
            holder.tvTitle.setText(Str)
        }
    }

    override fun getItemCount(): Int {

        return strList.size;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle) as TextView
        }

    }
}