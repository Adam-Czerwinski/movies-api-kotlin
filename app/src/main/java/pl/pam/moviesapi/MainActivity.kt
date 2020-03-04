package pl.pam.moviesapi

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MotionEventCompat
import androidx.navigation.findNavController
import pl.pam.moviesapi.services.view.ShowList
import pl.pam.moviesapi.services.view.ShowListAdapter
import pl.pam.moviesapi.services.view.details.ShowListDetails
import pl.pam.moviesapi.utils.Utils


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        this.initializeUtils()
        this.initializeApi();
    }

    private fun initializeUtils() {
        Utils.initialize(assets.open("application.properties"));
    }

    private fun initializeApi() {

    }

}
