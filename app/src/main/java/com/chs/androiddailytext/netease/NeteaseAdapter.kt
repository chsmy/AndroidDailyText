package com.chs.androiddailytext.netease

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chs.androiddailytext.R

/**
 *  作者：chs
 *  时间：2019-03-26 10:25
 *  描述：
 */
class NeteaseAdapter(var data :MutableList<String>,var context: Context) : RecyclerView.Adapter<NeteaseViewHolder>() {

    lateinit var onItemClickListenter:OnItemClickListenter
    interface OnItemClickListenter{
        fun onItemClick(view: View,position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeteaseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_netease,parent,false)
        return NeteaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnItemClickListener(listenter: OnItemClickListenter){
        this.onItemClickListenter = listenter
    }

    override fun onBindViewHolder(holder: NeteaseViewHolder, position: Int) {
        holder.textView.text = data.get(holder.adapterPosition)
        holder.textView.setOnClickListener {

            onItemClickListenter.onItemClick(holder.textView,holder.adapterPosition)

        }
    }

}