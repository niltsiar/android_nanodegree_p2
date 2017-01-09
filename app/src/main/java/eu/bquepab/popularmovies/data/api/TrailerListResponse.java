package eu.bquepab.popularmovies.data.api;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import eu.bquepab.popularmovies.model.Trailer;
import java.util.List;

@AutoValue
public abstract class TrailerListResponse {

    public static JsonAdapter<TrailerListResponse> jsonAdapter(final Moshi moshi) {
        return new AutoValue_TrailerListResponse.MoshiJsonAdapter(moshi);
    }

    public abstract int id();

    public abstract List<Trailer> results();
}
