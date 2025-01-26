package com.horbal.feature.image.details

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.horbal.common.android.stringArgument
import com.horbal.common.glide.onLoadFailed
import com.horbal.common.glide.onResourceReady
import com.horbal.common.image.domain.ImageDetails
import com.horbal.feature.image.details.databinding.FragmentImageDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImageDetailsFragment : Fragment(R.layout.fragment_image_details) {

    private val viewBinding: FragmentImageDetailsBinding by viewBinding()

    private val viewModel: ImageDetailsViewModel by viewModels(
        extrasProducer = {
            defaultViewModelCreationExtras.withCreationCallback<ImageDetailsViewModel.Factory> { factory ->
                factory.create(requireNotNull(imageId))
            }
        }
    )

    private var imageId by stringArgument()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        applyWindowInsets()

        viewBinding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        setInitialState()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is ImageDetailsState.Loading -> setLoadingState()
                    is ImageDetailsState.Loaded -> setLoadedState(it.details)
                    is ImageDetailsState.Failure -> setFailureState()
                }
            }
        }
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.root) { v, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
                        or WindowInsetsCompat.Type.displayCutout()
            )
            applyWindowInsetsToBackButton(bars)
            applyWindowInsetsToNameView(bars)
            applyWindowInsetsToDescriptionView(bars)
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun applyWindowInsetsToBackButton(bars: Insets) {
        (viewBinding.btnBack.layoutParams as ViewGroup.MarginLayoutParams).apply {
            leftMargin = bars.left
            topMargin = bars.top
        }
    }

    private fun applyWindowInsetsToNameView(bars: Insets) {
        (viewBinding.tvName.layoutParams as ViewGroup.MarginLayoutParams).apply {
            leftMargin = bars.left
            rightMargin = bars.right
        }
    }

    private fun applyWindowInsetsToDescriptionView(bars: Insets) {
        (viewBinding.tvDescription.layoutParams as ViewGroup.MarginLayoutParams).apply {
            leftMargin = bars.left
            rightMargin = bars.right
            bottomMargin = bars.bottom
        }
    }

    private fun setInitialState() {
        setProgressVisible(false)
        setContentVisible(false)
    }

    private fun setLoadingState() {
        setProgressVisible(true)
        setContentVisible(false)
    }

    private fun setLoadedState(details: ImageDetails) {
        Glide.with(this@ImageDetailsFragment)
            .load(details.url)
            .onResourceReady {
                setProgressVisible(false)
                setContentVisible(true)
            }
            .onLoadFailed {
                setProgressVisible(false)
                setContentVisible(true)
            }
            .into(viewBinding.ivImage)
        viewBinding.tvName.text = details.user.name
        viewBinding.tvDescription.text = details.description
    }

    private fun setFailureState() {
        setProgressVisible(false)
        setContentVisible(false)
    }

    private fun setProgressVisible(isVisible: Boolean) {
        viewBinding.progressBar.isInvisible = isVisible.not()
    }

    private fun setContentVisible(isVisible: Boolean) {
        viewBinding.ivImage.isInvisible = isVisible.not()
        viewBinding.tvName.isInvisible = isVisible.not()
        viewBinding.tvDescription.isInvisible = isVisible.not()
    }

    companion object {

        operator fun invoke(imageId: String): ImageDetailsFragment =
            ImageDetailsFragment().apply {
                arguments = Bundle()
                this.imageId = imageId
            }
    }
}