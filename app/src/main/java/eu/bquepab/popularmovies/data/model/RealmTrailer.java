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
public class RealmTrailer extends RealmObject {

    @PrimaryKey
    @Getter
    private String id;

    @Nullable
    @Getter
    private String key;

    @Nullable
    @Getter
    private String name;

    @Nullable
    @Getter
    private String site;

    @Nullable
    @Getter
    private Integer size;

    @Nullable
    @Getter
    private String type;
}
