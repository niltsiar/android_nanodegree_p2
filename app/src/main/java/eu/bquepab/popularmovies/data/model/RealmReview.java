package eu.bquepab.popularmovies.data.model;

import android.support.annotation.Nullable;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RealmReview extends RealmObject {

    @PrimaryKey
    @Getter
    private String id;

    @Getter
    private int movieId;

    @Nullable
    @Getter
    private String author;

    @Nullable
    @Getter
    private String content;

    @Nullable
    @Getter
    private String url;
}
