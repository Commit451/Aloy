package com.commit451.aloy.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.commit451.aloy.AloyAdapter
import com.commit451.aloy.DynamicGridLayoutManager
import kotlinx.android.synthetic.main.activity_dynamic_grid_layout_manager.*

class DynamicGridLayoutManagerActivity : AppCompatActivity() {

    private lateinit var adapter: AloyAdapter<Cheese, DynamicCheeseViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_grid_layout_manager)

        toolbar.title = "Aloy"

        adapter = DynamicGridLayoutAdapter()
        val layoutManager = DynamicGridLayoutManager(this)
        layoutManager.setMinimumSpanSize(resources.getDimensionPixelSize(R.dimen.cheese_item_width))
        list.layoutManager =layoutManager
        list.adapter = adapter

        load()
    }

    private fun load() {
        val cheeses = mutableListOf<Cheese>()
        for (index in 0..5) {
            cheeses.add(Cheeses.randomCheese)
        }
        adapter.clear()
        adapter.addAll(cheeses)
    }
}
