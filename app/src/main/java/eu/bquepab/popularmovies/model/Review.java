package eu.bquepab.popularmovies.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Review implements Parcelable {

    public static JsonAdapter<Review> jsonAdapter(final Moshi moshi) {
        return new AutoValue_Review.MoshiJsonAdapter(moshi);
    }

    public static Builder builder() {
        return new AutoValue_Review.Builder();
    }

    public abstract String id();

    @Nullable
    public abstract String author();

    @Nullable
    public abstract String content();

    @Nullable
    public abstract String url();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(String id);

        public abstract Builder author(String author);

        public abstract Builder content(String content);

        public abstract Builder url(String url);

        public abstract Review build();
    }
}
