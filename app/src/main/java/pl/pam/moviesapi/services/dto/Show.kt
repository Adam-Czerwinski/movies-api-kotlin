package pl.pam.moviesapi.services.dto

import com.google.gson.annotations.SerializedName

data class Show(@SerializedName("original_title") val originalTitle: String,@SerializedName("title") val title: String,@SerializedName("overview") val description:String,
                @SerializedName("release_date") val releaseDate: String, @SerializedName("vote_average") val rate:Float, @SerializedName("popularity") val popularity: Double,
                @SerializedName("poster_path") val posterPath: String)
