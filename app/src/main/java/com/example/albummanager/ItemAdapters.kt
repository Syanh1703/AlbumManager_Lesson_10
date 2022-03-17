package com.example.albummanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapters(val context: Context, val items: ArrayList<MyDataItem>)
    : RecyclerView.Adapter<ItemAdapters.ViewHolder>(){

        class ViewHolder(view : View):RecyclerView.ViewHolder(view)
        {
            var photoID :TextView = view.findViewById(R.id.tvId)
            var photoTitle :TextView = view.findViewById(R.id.tvTitle)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =LayoutInflater.from(context).inflate(R.layout.photo_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items[position]
        if(holder is ViewHolder)
        {
            holder.photoID.text = item.id.toString()
            holder.photoTitle.text = item.title
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}