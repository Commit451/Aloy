package com.commit451.aloy.sample

import android.widget.Toast
import com.commit451.aloy.AloyAdapter

class DynamicGridLayoutAdapter : AloyAdapter<Cheese, DynamicCheeseViewHolder>(onBindViewHolder = { holder, _, item ->
    holder.bind(item)
}) {
    init {
        onCreateViewHolder = { parent, _ ->
            val holder = DynamicCheeseViewHolder.inflate(parent)
            holder.itemView.setOnClickListener {
                val cheese = items[holder.adapterPosition]
                Toast.makeText(holder.itemView.context, "${cheese.name} clicked", Toast.LENGTH_SHORT)
                        .show()
            }
            holder
        }
    }
}