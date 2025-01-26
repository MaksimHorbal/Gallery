package com.horbal.feature.image.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ImageItemMarginDecoration(
    private val spanCount: Int,
    private val horizontalMargin: Int,
    private val verticalMargin: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        outRect.left = horizontalMargin - column * horizontalMargin / spanCount
        outRect.right = (column + 1) * horizontalMargin / spanCount

        if (position < spanCount) {
            outRect.top = verticalMargin
        }
        outRect.bottom = verticalMargin
    }
}