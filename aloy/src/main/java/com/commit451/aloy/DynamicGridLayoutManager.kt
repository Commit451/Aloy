@file:Suppress("unused")

package com.commit451.aloy

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

/**
 * [GridLayoutManager] which dynamically sizes its number of columns based on
 * [.setMinimumWidth] and the orientation
 */
class DynamicGridLayoutManager : GridLayoutManager {

    private var minSize = -1

    constructor(context: Context) : super(context, 2)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(context, 2, orientation, reverseLayout)

    /**
     * Set the minimum pixel size a view needs to be to have it be its own column. Size is the width if
     * the view orientation is vertical, and is the height if the orientation is horizontal.
     *
     * For example, if we set minimumSize to 200dp and the screen width is 800dp and the orientation
     * is vertical, there will be a spanCount of 4 (columns)
     *
     * It is recommended you use [android.content.res.Resources.getDimensionPixelSize] or something
     * comparable to pass here, as passing raw pixel values is typically wrong
     */
    fun setMinimumSpanSize(@Px minimumSize: Int) {
        minSize = minimumSize
        requestLayout()
    }

    override fun onMeasure(recycler: RecyclerView.Recycler, state: RecyclerView.State, widthSpec: Int, heightSpec: Int) {
        if (minSize != -1) {
            val widthOrHeight = if (orientation == LinearLayoutManager.VERTICAL) View.MeasureSpec.getSize(widthSpec) else View.MeasureSpec.getSize(heightSpec)
            val span = max(1, widthOrHeight / minSize)
            spanCount = span
        }
        super.onMeasure(recycler, state, widthSpec, heightSpec)
    }
}
