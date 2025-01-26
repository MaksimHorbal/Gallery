package com.horbal.gallery.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.horbal.feature.image.details.ImageDetailsFragment
import com.horbal.feature.image.list.ImageListFragment
import com.horbal.gallery.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    var activity: FragmentActivity? = null

    fun showImageList() {
        runWithActivity {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, ImageListFragment())
            }
        }
    }

    fun showImageDetails(imageId: String) {
        runWithActivity {
            supportFragmentManager.commit {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                replace(R.id.fragment_container, ImageDetailsFragment(imageId))
                addToBackStack("ImageDetails")
            }
        }
    }

    private inline fun runWithActivity(block: FragmentActivity.() -> Unit) {
        activity?.run(block)
    }
}