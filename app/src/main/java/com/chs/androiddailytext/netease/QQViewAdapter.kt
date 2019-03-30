package com.chs.androiddailytext.netease

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chs.androiddailytext.R

/**
 *  作者：chs
 *  时间：2019-03-26 10:25
 *  描述：
 */
class QQViewAdapter(var data :MutableList<String>, var context: Context) : RecyclerView.Adapter<QQViewAdapter.QQViewHolder>() {

    lateinit var onItemClickListenter:OnItemClickListenter
    interface OnItemClickListenter{
        fun onItemClick(view: View,position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QQViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_qq,parent,false)
        return QQViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnItemClickListener(listenter: OnItemClickListenter){
        this.onItemClickListenter = listenter
    }

    override fun onBindViewHolder(holder: QQViewHolder, position: Int) {
        holder.textView.text = data.get(holder.adapterPosition)
        holder.qqTextView.text = holder.adapterPosition.toString()
        holder.textView.setOnClickListener {

//            onItemClickListenter.onItemClick(holder.textView,holder.adapterPosition)

        }
        holder.qqTextView.setOnTouchListener(QQViewListenter(context))
    }
    class QQViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView: TextView
        var qqTextView: TextView
        init {
            textView = itemView.findViewById(R.id.tv_name)
            qqTextView = itemView.findViewById(R.id.tv_qq)
        }

    }
}