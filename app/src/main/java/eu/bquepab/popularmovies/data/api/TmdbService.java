package eu.bquepab.popularmovies.data.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbService {

    @GET("movie/popular")
    Observable<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<MovieListResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Observable<ReviewListResponse> getReviews(@Path("id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Observable<TrailerListResponse> getTrailers(@Path("id") int movieId, @Query("api_key") String apiKey);
}
