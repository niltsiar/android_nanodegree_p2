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
public class RealmMovie extends RealmObject {

    @PrimaryKey
    @Getter
    private int id;

    @Getter
    private String title;

    @Nullable
    @Getter
    private String posterPath;

    @Getter
    private String synopsis;

    @Getter
    private float userRating;

    @Getter
    private String releaseDate;
}
