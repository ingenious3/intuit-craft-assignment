package com.example.catlist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catlist.R
import com.example.catlist.data.CatListItem
import kotlinx.android.synthetic.main.cat_list_item.view.*

class CatListItemAdapter : RecyclerView.Adapter<CatListItemViewHolder>() {

    private var items : ArrayList<CatListItem> = ArrayList<CatListItem>()
    var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(catListItem: CatListItem)
    }

    public fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListItemViewHolder {
        return CatListItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cat_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: CatListItemViewHolder, position: Int) {
        val  item = items.get(position)
        with(holder.itemView) {
            val breedName = item.originalFilename?.split(".")?.get(0)?.replace("-"," ")?.replace("_", " ")
            tv_breed.text = breedName
            setOnClickListener {
                itemClickListener?.onItemClick(item)
            }
            Glide.with(context).load(item.url).centerCrop().into(img_cat)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(list: List<CatListItem>) {

        val diffUtilCallBack = DiffUtilCallBack(items, list)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
        items.clear()
        items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }
}

class CatListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
