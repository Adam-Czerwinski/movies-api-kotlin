package pl.pam.moviesapi.services.api.poster;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PosterApiService {
    @GET("w300/{src}")
    Call<Object> getPoster(@Path("src") String source);
}
