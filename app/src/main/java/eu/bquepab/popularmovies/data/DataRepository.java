package eu.bquepab.popularmovies.data;

import eu.bquepab.popularmovies.model.Movie;
import io.reactivex.Maybe;
import java.util.List;
import javax.inject.Inject;

public class DataRepository {

    private CloudDataStore cloudDataStorage;
    private LocalDataStore realmLocalDataStorage;

    @Inject
    public DataRepository(final CloudDataStore cloudDataStorage, final LocalDataStore realmLocalDataStorage) {
        this.cloudDataStorage = cloudDataStorage;
        this.realmLocalDataStorage = realmLocalDataStorage;
    }

    public Maybe<List<Movie>> getPopularMovies() {
        return cloudDataStorage.getPopularMovies();
    }

    public Maybe<List<Movie>> getTopRatedMovies() {
        return cloudDataStorage.getTopRatedMovies();
    }

    public Maybe<List<Movie>> getStarredMovies() {
        return realmLocalDataStorage.getMovies();
    }

    public void saveMovie(final Movie movie) {
        realmLocalDataStorage.saveMovie(movie);
    }

    public void saveMovies(final List<Movie> movies) {
        realmLocalDataStorage.saveMovies(movies);
    }
}
