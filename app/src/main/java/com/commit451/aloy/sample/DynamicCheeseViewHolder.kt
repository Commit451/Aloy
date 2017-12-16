package com.commit451.aloy.sample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.commit451.aloy.sample.Cheese


/**
 * The view holder related to each Cheese item
 */
class DynamicCheeseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {

        fun inflate(parent: ViewGroup): DynamicCheeseViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_cheese_dynamic, parent, false)
            return DynamicCheeseViewHolder(view)
        }
    }

    var image: ImageView = view.findViewById(R.id.image)
    var title: TextView = view.findViewById(R.id.name)

    fun bind(cheese: Cheese) {
        image.setImageResource(cheese.drawable)
        title.text = cheese.name
    }
}
