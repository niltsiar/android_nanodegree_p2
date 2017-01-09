package eu.bquepab.popularmovies.data;

import eu.bquepab.popularmovies.BuildConfig;
import eu.bquepab.popularmovies.api.MovieListResponse;
import eu.bquepab.popularmovies.api.TmdbService;
import eu.bquepab.popularmovies.model.Movie;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CloudDataStore {

    private TmdbService service;

    @Inject
    public CloudDataStore(final TmdbService service) {
        this.service = service;
    }

    public Maybe<List<Movie>> getPopularMovies() {
        return service.getPopularMovies(BuildConfig.THE_MOVIE_DATABASE_API_KEY)
                      .subscribeOn(Schedulers.io())
                      .map(MovieListResponse::results)
                      .firstElement();
    }

    public Maybe<List<Movie>> getTopRatedMovies() {
        return service.getTopRatedMovies(BuildConfig.THE_MOVIE_DATABASE_API_KEY)
                      .subscribeOn(Schedulers.io())
                      .map(MovieListResponse::results)
                      .firstElement();
    }
}
