package eu.bquepab.popularmovies.data;

import eu.bquepab.popularmovies.model.Movie;
import eu.bquepab.popularmovies.model.Review;
import eu.bquepab.popularmovies.model.Trailer;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

public class DataRepository {

    private CloudDataStore cloudDataStorage;
    private LocalDataStore localDataStore;

    @Inject
    public DataRepository(final CloudDataStore cloudDataStorage, final LocalDataStore realmLocalDataStorage) {
        this.cloudDataStorage = cloudDataStorage;
        this.localDataStore = realmLocalDataStorage;
    }

    public Maybe<List<Movie>> getPopularMovies() {
        return cloudDataStorage.getPopularMovies();
    }

    public Maybe<List<Movie>> getTopRatedMovies() {
        return cloudDataStorage.getTopRatedMovies();
    }

    public Maybe<List<Movie>> getStarredMovies() {
        return localDataStore.getMovies();
    }

    public void saveMovie(final Movie movie, final List<Review> reviews) {
        localDataStore.saveMovie(movie);
        localDataStore.saveReviews(movie, reviews);
    }

    public void saveMovies(final List<Movie> movies) {
        localDataStore.saveMovies(movies);
    }

    public void deleteMovie(final Movie movie) {
        localDataStore.deleteMovie(movie);
        localDataStore.deleteReviews(movie);
    }

    public Single<Boolean> isFavoriteMovie(final Movie movie) {
        return localDataStore.isFavoriteMovie(movie);
    }

    public Maybe<List<Review>> getReviews(final Movie movie, final boolean isFavorite) {
        if (isFavorite) {
            return localDataStore.getReviews(movie);
        } else {
            return cloudDataStorage.getReviews(movie.id());
        }
    }

    public Maybe<List<Trailer>> getTrailers(final Movie movie) {
        return cloudDataStorage.getTrailers(movie.id());
    }
}
