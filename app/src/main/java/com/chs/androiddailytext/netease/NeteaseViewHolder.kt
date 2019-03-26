package com.chs.androiddailytext.netease

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.chs.androiddailytext.R

/**
 *  作者：chs
 *  时间：2019-03-26 10:26
 *  描述：
 */
class NeteaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var textView:TextView
    init {
        textView = itemView.findViewById(R.id.tv_name)
    }

}