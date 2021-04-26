package com.commit451.aloy.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.commit451.aloy.AloyAdapter
import com.commit451.aloy.DynamicGridLayoutManager
import com.commit451.aloy.sample.databinding.ActivityDynamicGridLayoutManagerBinding

class DynamicGridLayoutManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDynamicGridLayoutManagerBinding
    private lateinit var adapter: AloyAdapter<Cheese, DynamicCheeseViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDynamicGridLayoutManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Aloy"

        adapter = DynamicGridLayoutAdapter()
        val layoutManager = DynamicGridLayoutManager(this)
        layoutManager.setMinimumSpanSize(resources.getDimensionPixelSize(R.dimen.cheese_item_width))
        binding.list.layoutManager =layoutManager
        binding.list.adapter = adapter

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
