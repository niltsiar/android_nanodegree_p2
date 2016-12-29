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
}
