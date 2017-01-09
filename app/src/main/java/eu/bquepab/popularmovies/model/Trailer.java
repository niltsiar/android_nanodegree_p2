package eu.bquepab.popularmovies.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Trailer implements Parcelable {

    public static JsonAdapter<Trailer> jsonAdapter(final Moshi moshi) {
        return new AutoValue_Trailer.MoshiJsonAdapter(moshi);
    }

    public static Builder builder() {
        return new AutoValue_Trailer.Builder();
    }

    public abstract String id();

    @Nullable
    public abstract String key();

    @Nullable
    public abstract String name();

    @Nullable
    public abstract String site();

    @Nullable
    public abstract Integer size();

    @Nullable
    public abstract String type();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(String id);

        public abstract Builder key(String key);

        public abstract Builder name(String name);

        public abstract Builder site(String site);

        public abstract Builder size(Integer size);

        public abstract Builder type(String type);

        public abstract Trailer build();
    }
}
