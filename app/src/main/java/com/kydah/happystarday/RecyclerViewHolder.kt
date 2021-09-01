package com.kydah.happystarday

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image : ImageView = itemView.findViewById(R.id.imageView)
    private val subtitle : TextView = itemView.findViewById(R.id.imageSubtext)

    fun setImage(imageID : Int, imageSubtext : String){
        image.setImageResource(imageID)
        subtitle.text = imageSubtext
    }
}