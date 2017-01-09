package eu.bquepab.popularmovies.data;

import eu.bquepab.popularmovies.data.mapper.MovieMapper;
import eu.bquepab.popularmovies.data.model.RealmMovie;
import eu.bquepab.popularmovies.model.Movie;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

public class LocalDataStore {

    @Inject
    public LocalDataStore() {
    }

    public void saveMovie(final Movie movie) {
        RealmFacade.insert(MovieMapper.map(movie));
    }

    public void saveMovies(final List<Movie> movies) {
        Observable.fromIterable(movies)
                  .map(MovieMapper::map)
                  .subscribe(RealmFacade::insert);
    }

    public Maybe<List<Movie>> getMovies() {
        return Observable.fromIterable(RealmFacade.getAll(RealmMovie.class))
                         .map(MovieMapper::map)
                         .toList()
                         .toMaybe();
    }
}
