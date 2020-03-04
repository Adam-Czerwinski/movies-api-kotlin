package pl.pam.moviesapi.services.api.poster

import okhttp3.OkHttpClient
import pl.pam.moviesapi.utils.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PosterApiServiceGenerator {

    companion object {
        private var builder: Retrofit.Builder
        private var retrofit: Retrofit
        private var httpClient: OkHttpClient.Builder
        private var posterApiService: PosterApiService? = null

        init {
            val baseUrl: String = Utils.getInstance().getProperty("api.img.url") as String
            builder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())

            retrofit = builder.build()

            httpClient = OkHttpClient.Builder()
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return if (posterApiService != null) {
                posterApiService as S
            } else {
                posterApiService = retrofit.create(serviceClass) as PosterApiService
                posterApiService as S
            }
        }
    }
}