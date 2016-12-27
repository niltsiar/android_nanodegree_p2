package eu.bquepab.popularmovies.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbService {

    @GET("movie/popular")
    Observable<DiscoverResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<DiscoverResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
