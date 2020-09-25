package com.example.kotlinassignment.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinassignment.R
import com.example.kotlinassignment.model.Rows
import com.example.kotlinassignment.viewmodel.RowClickListener

/* Adapter class to populate the data to list view from fragment*/

class UserContentAdapter(val userContentList : List<Rows>, val rowClickListener: RowClickListener, val context: Context): RecyclerView.Adapter<UserContentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userContentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title_desc_notNull = let {
            it.userContentList.get(position).title != null && it.userContentList.get(position).description != null
        }
        if(title_desc_notNull){
            holder.titleText.setText(userContentList.get(position).title)
            holder.descriptionText.setText(userContentList.get(position).description)

            Glide.with(holder.itemView.context)
                .load(userContentList.get(position).imageHref)
                .placeholder(R.drawable.nonetwork).fitCenter()
                .into(holder.image)
        }
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val titleText: TextView = view.findViewById(R.id.titleText)
        val descriptionText: TextView = view.findViewById(R.id.descText)
        val image: ImageView = view.findViewById(R.id.imageView)

        override fun onClick(itemView: View?) {
            rowClickListener.onRowClick(adapterPosition, userContentList.get(adapterPosition).title)
        }
    }

}