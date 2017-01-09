package eu.bquepab.popularmovies.data;

import io.realm.Realm;
import io.realm.RealmObject;
import java.util.ArrayList;
import java.util.List;

public class RealmFacade {

    public static <T extends RealmObject> void insert(final T entity) {
        final Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(entity));

        realm.close();
    }

    public static <T extends RealmObject> List<T> getAll(final Class<T> entityClass) {
        final Realm realm = Realm.getDefaultInstance();

        final List<T> result = new ArrayList<>(realm.copyFromRealm(realm.where(entityClass)
                                                                        .findAll()));

        realm.close();

        return result;
    }

    public static <T extends RealmObject> boolean isStored(final Class<T> entityClass, final String id) {
        final Realm realm = Realm.getDefaultInstance();

        T value = realm.where(entityClass)
                       .contains("id", id)
                       .findFirst();

        final boolean result = null != value;

        realm.close();

        return result;
    }

    public static <T extends RealmObject> boolean isStored(final Class<T> entityClass, final int id) {
        final Realm realm = Realm.getDefaultInstance();

        T value = realm.where(entityClass)
                       .equalTo("id", id)
                       .findFirst();

        final boolean result = null != value;

        realm.close();

        return result;
    }
}
