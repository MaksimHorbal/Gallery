package com.horbal.feature.image.list

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.horbal.feature.image.list.databinding.FragmentImageListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImageListFragment : Fragment(R.layout.fragment_image_list) {

    private val viewBinding: FragmentImageListBinding by viewBinding()

    private val viewModel: ImageListViewModel by viewModels()

    private val imageAdapter = ImageListAdapter {
        viewModel.imageClicked(it.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        applyWindowInsetsToImageList()

        with(viewBinding.imageList) {
            adapter = imageAdapter

            val spanCount = resources.getInteger(R.integer.image_list_span_count)
            layoutManager = GridLayoutManager(context, spanCount)

            addItemDecoration(
                ImageItemMarginDecoration(
                    spanCount = spanCount,
                    horizontalMargin = resources.getDimensionPixelSize(R.dimen.image_item_horizontal_margin),
                    verticalMargin = resources.getDimensionPixelSize(R.dimen.image_item_vertical_margin),
                )
            )
            addItemDecoration(
                ImageItemCornerDecoration(
                    cornerRadius = resources.getDimensionPixelSize(R.dimen.image_item_corner_radius)
                        .toFloat(),
                )
            )
        }

        observeImageData()
    }

    private fun applyWindowInsetsToImageList() {
        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.imageList) { v, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
                        or WindowInsetsCompat.Type.displayCutout()
            )
            v.updatePadding(
                left = bars.left,
                top = bars.top,
                right = bars.right,
                bottom = bars.bottom,
            )
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun observeImageData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.images
                .map { pagingData ->
                    pagingData.map { image ->
                        image.toUiModel(blurHashScaleFactor = 0.05f)
                    }
                }
                .collectLatest(imageAdapter::submitData)
        }
    }
}