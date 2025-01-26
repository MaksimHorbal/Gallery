package com.horbal.gallery

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.horbal.gallery.navigation.ImagesFlowCoordinator
import com.horbal.gallery.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var coordinator: ImagesFlowCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        navigator.activity = this
        if (savedInstanceState == null) {
            coordinator.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.activity = null
    }
}