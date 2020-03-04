package pl.pam.moviesapi.services.api.show

import okhttp3.OkHttpClient
import pl.pam.moviesapi.utils.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApiServiceGenerator {
    companion object {
        private var builder: Retrofit.Builder
        private var retrofit: Retrofit
        private var httpClient: OkHttpClient.Builder
        private var moviesApiService: MoviesApiService? = null

        init {
            val baseUrl: String = Utils.getInstance().getProperty("api.url") as String
            builder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())

            retrofit = builder.build()

            httpClient = OkHttpClient.Builder()
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return if (moviesApiService != null) {
                moviesApiService as S
            } else {
                moviesApiService = retrofit.create(serviceClass) as MoviesApiService
                moviesApiService as S
            }
        }
    }
}