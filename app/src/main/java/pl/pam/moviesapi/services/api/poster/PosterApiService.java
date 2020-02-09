package pl.pam.moviesapi.services.api.poster;

import pl.pam.moviesapi.services.dto.MoviesListResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PosterApiService {
    @GET("w300/{src}")
    Call<Object> getPoster(@Path("src") String source);
}
