package eu.bquepab.popularmovies.data.mapper;

import eu.bquepab.popularmovies.data.model.RealmMovie;
import eu.bquepab.popularmovies.model.Movie;

public class MovieMapper {

    //No instances
    private MovieMapper() {
    }

    public static RealmMovie map(final Movie movie) {
        return RealmMovie.builder()
                         .id(movie.id())
                         .title(movie.title())
                         .posterPath(movie.posterPath())
                         .synopsis(movie.synopsis())
                         .userRating(movie.userRating())
                         .releaseDate(movie.releaseDate())
                         .build();
    }

    public static Movie map(final RealmMovie movie) {
        return Movie.builder()
                    .id(movie.getId())
                    .title(movie.getTitle())
                    .posterPath(movie.getPosterPath())
                    .synopsis(movie.getSynopsis())
                    .userRating(movie.getUserRating())
                    .releaseDate(movie.getReleaseDate())
                    .build();
    }
}
