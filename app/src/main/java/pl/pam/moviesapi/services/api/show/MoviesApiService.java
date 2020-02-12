package pl.pam.moviesapi.services.api.show;

import pl.pam.moviesapi.services.dto.MoviesListResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApiService {

    @GET("discover/movie?")
    Call<MoviesListResponseDTO> getMovies(@Query("api_key") String key, @Query("page") String page);

    @GET("search/movie?")
    Call<MoviesListResponseDTO> findMovies(@Query("api_key") String key,@Query("query") String input, @Query("page") String page);

    @GET("discover/tv?")
    Call<MoviesListResponseDTO> getTvShows(@Query("api_key") String key, @Query("page") String page);

    @GET("search/tv?")
    Call<MoviesListResponseDTO> findTvShows(@Query("api_key") String key,@Query("query") String input, @Query("page") String page);
}
