package com.commit451.aloy

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Allows you to create a [RecyclerView.Adapter] without having to extend [RecyclerView.Adapter]
 */
open class AloyAdapter<T, VH : RecyclerView.ViewHolder>() : RecyclerView.Adapter<VH>() {

    var onCreateViewHolder:  ((parent: ViewGroup, viewType: Int) -> VH)? = null
    var onBindViewHolder: ((viewHolder: VH, position: Int, item: T) -> Unit)? = null
    var onGetItemViewType: ((position: Int) -> Int)? = null

    val items = mutableListOf<T>()

    constructor(onCreateViewHolder: ((parent: ViewGroup, viewType: Int) -> VH)? = null, onBindViewHolder: ((viewHolder: VH, position: Int, item: T) -> Unit)? = null) : this() {
        this.onCreateViewHolder = onCreateViewHolder
        this.onBindViewHolder = onBindViewHolder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = onCreateViewHolder!!.invoke(parent, viewType)

    override fun onBindViewHolder(viewHolder: VH, position: Int) {
        val item = items[position]
        onBindViewHolder!!.invoke(viewHolder, position, item)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        val onGetItemViewType = onGetItemViewType
        if (onGetItemViewType != null) {
            return onGetItemViewType.invoke(position)
        } else {
            return super.getItemViewType(position)
        }
    }

    fun set(collection: Collection<T>?) {
        items.clear()
        collection?.let {
            items.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun add(item: T, index: Int = items.size) {
        items.add(index, item)
        notifyItemInserted(index)
    }

    fun addAll(collection: Collection<T>, index: Int = items.size) {
        items.addAll(index, collection)
        notifyItemRangeInserted(index, items.size)
    }

    fun remove(item: T) {
        val index = items.indexOfFirst { it == item }
        if (index != -1) {
            items.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun removeAll(itemsToRemove: Collection<T>) {
        for (item in itemsToRemove) {
            remove(item)
        }
    }

    fun update(item: T) {
        val index = items.indexOfFirst { it == item }
        if (index != -1) {
            notifyItemChanged(index)
        }
    }

    fun updateAll(items: List<T>) {
        for (item in items) {
            update(item)
        }
    }

    fun clear() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }
}
