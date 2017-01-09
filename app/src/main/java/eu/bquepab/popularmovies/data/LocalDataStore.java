package eu.bquepab.popularmovies.data;

import eu.bquepab.popularmovies.data.mapper.MovieMapper;
import eu.bquepab.popularmovies.data.mapper.ReviewMapper;
import eu.bquepab.popularmovies.data.mapper.TrailerMapper;
import eu.bquepab.popularmovies.data.model.RealmMovie;
import eu.bquepab.popularmovies.data.model.RealmReview;
import eu.bquepab.popularmovies.data.model.RealmTrailer;
import eu.bquepab.popularmovies.model.Movie;
import eu.bquepab.popularmovies.model.Review;
import eu.bquepab.popularmovies.model.Trailer;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
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

    public void saveReviews(final Movie movie, final List<Review> reviews) {
        Observable.fromIterable(reviews)
                  .map(review -> ReviewMapper.map(movie.id(), review))
                  .subscribe(RealmFacade::insert);
    }

    public void saveTrailers(final Movie movie, final List<Trailer> trailers) {
        Observable.fromIterable(trailers)
                  .map(trailer -> TrailerMapper.map(movie.id(), trailer))
                  .subscribe(RealmFacade::insert);
    }

    public Maybe<List<Movie>> getMovies() {
        return Observable.fromIterable(RealmFacade.getAll(RealmMovie.class))
                         .map(MovieMapper::map)
                         .toList()
                         .toMaybe();
    }

    public Maybe<List<Review>> getReviews(final Movie movie) {
        return Observable.fromIterable(RealmFacade.getAll(RealmReview.class))
                         .filter(review -> review.getMovieId() == movie.id())
                         .map(ReviewMapper::map)
                         .toList()
                         .toMaybe();
    }

    public Maybe<List<Trailer>> getTrailers(final Movie movie) {
        return Observable.fromIterable(RealmFacade.getAll(RealmTrailer.class))
                         .filter(trailer -> trailer.getMovieId() == movie.id())
                         .map(TrailerMapper::map)
                         .toList()
                         .toMaybe();
    }

    public Single<Boolean> isFavoriteMovie(final Movie movie) {
        return Single.just(RealmFacade.isStored(RealmMovie.class, movie.id()));
    }

    public void deleteMovie(final Movie movie) {
        RealmFacade.delete(MovieMapper.map(movie), movie.id());
    }

    public void deleteReviews(final Movie movie) {
        RealmFacade.delete(RealmReview.class, "movieId", movie.id());
    }

    public void deleteTrailers(final Movie movie) {
        RealmFacade.delete(RealmTrailer.class, "movieId", movie.id());
    }
}
