package pl.pam.moviesapi.utils


import java.io.FileInputStream
import java.util.*

class Utils {
    companion object{
        private var properties: Properties? = null;

        fun getProperty (propertyName: String): Any? {

            if(properties==null)
            {
                val fis = FileInputStream("file:///android_asset/application.properties")
                properties = Properties()
                properties!!.load(fis)
            }

            return properties!![propertyName]
        }
    }
}