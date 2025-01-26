package com.horbal.feature.image.list

import android.graphics.Canvas
import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class ImageItemCornerDecoration(cornerRadius: Float) : RecyclerView.ItemDecoration() {

    private val outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setRoundRect(0, 0, view.width, view.height, cornerRadius)
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.children.forEach {
            it.clipToOutline = true
            it.outlineProvider = outlineProvider
        }
    }
}