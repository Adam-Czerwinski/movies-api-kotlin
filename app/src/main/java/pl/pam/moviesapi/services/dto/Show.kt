package pl.pam.moviesapi.services.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Show(@SerializedName("original_title") val originalTitle: String?,@SerializedName("original_name") val originalName: String, @SerializedName("title") val title: String,@SerializedName("overview") val description:String,
                @SerializedName("release_date") val releaseDate: String, @SerializedName("popularity") val popularity: Double,
                @SerializedName("poster_path") val posterPath: String,@SerializedName("vote_count") val voteCount: Long,
                @SerializedName("adult") val isForAdults: Boolean, @SerializedName("vote_average") val voteAverage: Float,
                @SerializedName("first_air_date") val firstAirDate: String) : Serializable
