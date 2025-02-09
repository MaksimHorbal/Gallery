package com.horbal.feature.image.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class ImageListAdapter(
    private val onClick: (ImageItem) -> Unit,
) : PagingDataAdapter<ImageItem, ImageViewHolder>(ImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder.inflate(parent, onClick)

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        if (image != null) {
            holder.bind(image)
        }
    }
}

private class ImageDiffCallback : DiffUtil.ItemCallback<ImageItem>() {

    override fun areItemsTheSame(
        oldItem: ImageItem,
        newItem: ImageItem,
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ImageItem,
        newItem: ImageItem,
    ) = oldItem == newItem
}