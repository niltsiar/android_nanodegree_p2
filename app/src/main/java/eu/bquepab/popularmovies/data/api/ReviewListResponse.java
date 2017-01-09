package eu.bquepab.popularmovies.data.api;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import eu.bquepab.popularmovies.model.Review;
import java.util.List;

@AutoValue
public abstract class ReviewListResponse {

    public static JsonAdapter<ReviewListResponse> jsonAdapter(final Moshi moshi) {
        return new AutoValue_ReviewListResponse.MoshiJsonAdapter(moshi);
    }

    public abstract int id();

    public abstract int page();

    public abstract List<Review> results();

    @Json(name = "total_results")
    public abstract int totalResults();

    @Json(name = "total_pages")
    public abstract int totalPages();
}
