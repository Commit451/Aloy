package com.commit451.aloy.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.commit451.aloy.AloyAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val ID_REPLACE = 4
    }

    private lateinit var adapter: AloyAdapter<Cheese, CheeseViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Aloy"
        toolbar.inflateMenu(R.menu.add)
        toolbar.inflateMenu(R.menu.remove)
        toolbar.menu.add(0, ID_REPLACE, 3, "Replace Items")
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_add -> {
                    val cheese = Cheeses.randomCheese
                    adapter.add(cheese)
                    return@setOnMenuItemClickListener true
                }
                R.id.action_remove -> {
                    val cheese = adapter.items[adapter.itemCount - 1]
                    adapter.remove(cheese)
                    return@setOnMenuItemClickListener true
                }
                ID_REPLACE -> {
                    load()
                    return@setOnMenuItemClickListener true
                }
            }
            false
        }

        adapter = AloyAdapter({ parent, viewType ->
            val holder = CheeseViewHolder.inflate(parent)
            holder.itemView.setOnClickListener {
                val cheese = adapter.items[holder.adapterPosition]
                Snackbar.make(root, "${cheese.name} clicked", Snackbar.LENGTH_SHORT)
                        .show()
            }
            holder
        }, { viewHolder, position, item ->
            viewHolder.bind(item)
        })
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        findViewById<View>(R.id.button_dynamic).setOnClickListener {
            val intent = Intent(this, DynamicGridLayoutManagerActivity::class.java)
            startActivity(intent)
        }
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
