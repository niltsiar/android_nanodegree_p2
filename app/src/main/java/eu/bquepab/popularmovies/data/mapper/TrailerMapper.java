package eu.bquepab.popularmovies.data.mapper;

import eu.bquepab.popularmovies.data.model.RealmTrailer;
import eu.bquepab.popularmovies.model.Trailer;

public class TrailerMapper {

    //No instances
    private TrailerMapper() {
    }

    public static RealmTrailer map(final int movieId, final Trailer trailer) {
        return RealmTrailer.builder()
                           .id(trailer.id())
                           .movieId(movieId)
                           .key(trailer.key())
                           .name(trailer.name())
                           .site(trailer.site())
                           .size(trailer.size())
                           .type(trailer.type())
                           .build();
    }

    public static Trailer map(final RealmTrailer trailer) {
        return Trailer.builder()
                      .id(trailer.getId())
                      .key(trailer.getKey())
                      .name(trailer.getName())
                      .site(trailer.getSite())
                      .size(trailer.getSize())
                      .type(trailer.getType())
                      .build();
    }
}
