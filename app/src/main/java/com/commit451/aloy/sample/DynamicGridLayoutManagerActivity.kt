package com.commit451.aloy.sample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import com.commit451.addendum.bindView
import com.commit451.aloy.AloyAdapter
import com.commit451.aloy.DynamicGridLayoutManager

class DynamicGridLayoutManagerActivity : AppCompatActivity() {

    val root: ViewGroup by bindView(R.id.root)
    val toolbar: Toolbar by bindView(R.id.toolbar)
    val list: RecyclerView by bindView(R.id.list)

    lateinit var adapter: AloyAdapter<Cheese, DynamicCheeseViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_grid_layout_manager)

        toolbar.title = "Aloy"

        adapter = AloyAdapter({ parent, viewType ->
            val holder = DynamicCheeseViewHolder.inflate(parent)
            holder.itemView.setOnClickListener {
                val cheese = adapter.items[holder.adapterPosition]
                Snackbar.make(root, "${cheese.name} clicked", Snackbar.LENGTH_SHORT)
                        .show()
            }
            holder
        }, { viewHolder, position, item ->
            viewHolder.bind(item)
        })
        val layoutManager = DynamicGridLayoutManager(this)
        layoutManager.setMinimumSpanSize(resources.getDimensionPixelSize(R.dimen.cheese_item_width))
        list.layoutManager =layoutManager
        list.adapter = adapter

        load()
    }

    fun load() {
        val cheeses = mutableListOf<Cheese>()
        for (index in 0..5) {
            cheeses.add(Cheeses.randomCheese)
        }
        adapter.clear()
        adapter.addAll(cheeses)
    }

}