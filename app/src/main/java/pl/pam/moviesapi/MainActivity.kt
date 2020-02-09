package pl.pam.moviesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.pam.moviesapi.utils.Utils
import android.R.string.cancel
import android.content.Context.NOTIFICATION_SERVICE
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



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
