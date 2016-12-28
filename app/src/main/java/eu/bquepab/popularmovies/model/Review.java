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

    public abstract String id();

    @Nullable
    public abstract String author();

    @Nullable
    public abstract String content();

    @Nullable
    public abstract String url();
}
