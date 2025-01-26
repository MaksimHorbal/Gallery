package com.horbal.feature.image.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.horbal.feature.image.list.databinding.ItemImageBinding

class ImageViewHolder(
    private val viewBinding: ItemImageBinding,
    private val onClick: (ImageItem) -> Unit,
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(image: ImageItem) {
        Glide.with(viewBinding.root)
            .load(image.url)
            .thumbnail(
                Glide.with(viewBinding.root)
                    .load(image.blurHash)
            )
            .into(viewBinding.imageView)

        viewBinding.root.setOnClickListener {
            onClick(image)
        }
    }

    companion object {

        fun inflate(
            parent: ViewGroup,
            onClick: (ImageItem) -> Unit,
        ): ImageViewHolder {
            val binding = ItemImageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return ImageViewHolder(binding, onClick)
        }
    }
}