package eu.bquepab.popularmovies.data;

import io.realm.Realm;
import io.realm.RealmObject;
import java.util.ArrayList;
import java.util.List;

public class RealmFacade {

    public static <T extends RealmObject> void insert(final T entity) {
        final List<T> result = new ArrayList<>();
        final Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(realm1 -> result.add(realm1.copyToRealmOrUpdate(entity)));

        realm.close();
    }

    public static <T extends RealmObject> List<T> getAll(final Class<T> entityClass) {
        final Realm realm = Realm.getDefaultInstance();

        final List<T> result = new ArrayList<>(realm.copyFromRealm(realm.where(entityClass)
                                                                        .findAll()));

        realm.close();

        return result;
    }
}
