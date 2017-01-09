package eu.bquepab.popularmovies.data.api;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import eu.bquepab.popularmovies.model.Movie;
import java.util.List;

@AutoValue
public abstract class MovieListResponse {

    public static JsonAdapter<MovieListResponse> jsonAdapter(final Moshi moshi) {
        return new AutoValue_MovieListResponse.MoshiJsonAdapter(moshi);
    }

    public abstract int page();

    public abstract List<Movie> results();

    @Json(name = "total_results")
    public abstract int totalResults();

    @Json(name = "total_pages")
    public abstract int totalPages();
}
