package com.kydah.happystarday

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder, parent, false))
    }
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        when(position){
            // bad coding practices!!! :DDD
            0 -> {
                holder.setImage(R.drawable.approval, "Full endorsement and approval to utilise this image.")
            }
            2 -> {
                holder.setImage(R.drawable.celebration, "A party popper is a pyrotechnic device commonly used at parties.\nIt emits a loud popping noise by means of a small friction-actuated explosive charge that is activated by pulling a string.")
            }
            1 -> {
                holder.setImage(R.drawable.android, "Top 10 Android Development Teachers")
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}