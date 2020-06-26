package com.chs.app_jetpack.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chs.app_jetpack.R
import com.chs.module_wan.model.Article

/**
 * author：chs
 * date：2020/6/26
 * des：
 */
class ArticleAdapter : PagingDataAdapter<Article,ArticleViewHolder>(POST_COMPARATOR){

    companion object{
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                    oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
         holder.tvName.text = getItem(position)?.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))
    }

}
class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
   val tvName: TextView = itemView.findViewById(R.id.tvname)
}