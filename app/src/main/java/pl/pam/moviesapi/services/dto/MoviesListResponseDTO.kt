package pl.pam.moviesapi.services.dto

import com.google.gson.annotations.SerializedName

data class MoviesListResponseDTO(@SerializedName("page") val page: Int, @SerializedName("total_results") val totalResults: Long, @SerializedName("total_pages") val totalPages: Int,
                                 @SerializedName("results") val movies: MutableList<Show>)