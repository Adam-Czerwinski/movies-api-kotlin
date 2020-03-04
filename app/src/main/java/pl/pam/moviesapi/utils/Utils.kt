package pl.pam.moviesapi.utils


import java.io.InputStream
import java.util.*

public final class Utils private constructor() {
    companion object {
        private var properties: Properties? = null;
        fun initialize(fis: InputStream) {

            properties = Properties()
            properties!!.load(fis)

        }

        private var instance: Utils? = null;
        fun getInstance(): Utils {
            if (this.instance == null)
                this.instance = Utils();

            return this.instance!!;
        }
    }


    fun getProperty(propertyName: String): Any? {
        return properties!![propertyName]
    }
}