package eu.bquepab.popularmovies.data.mapper;

import eu.bquepab.popularmovies.data.model.RealmReview;
import eu.bquepab.popularmovies.model.Review;

public class ReviewMapper {

    //No instances
    private ReviewMapper() {
    }

    public static RealmReview map(final int movieId, final Review review) {
        return RealmReview.builder()
                          .id(review.id())
                          .movieId(movieId)
                          .author(review.author())
                          .content(review.content())
                          .url(review.url())
                          .build();
    }

    public static Review map(final RealmReview review) {
        return Review.builder()
                     .id(review.getId())
                     .author(review.getAuthor())
                     .content(review.getContent())
                     .url(review.getUrl())
                     .build();
    }
}
