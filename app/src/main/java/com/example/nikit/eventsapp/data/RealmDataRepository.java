package com.example.nikit.eventsapp.data;

import com.example.nikit.eventsapp.arch.FilterableRealmLiveData;
import com.example.nikit.eventsapp.arch.LiveRealmData;
import com.example.nikit.eventsapp.arch.LiveRealmDataObject;
import com.example.nikit.eventsapp.model.Event;
import com.example.nikit.eventsapp.model.User;

import java.util.HashMap;

import io.reactivex.Completable;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import timber.log.Timber;

public class RealmDataRepository {

    private Realm realm;

    private static RealmDataRepository realmDataRepository;

    private static HashMap<Realm, RealmDataRepository> repoCache = new HashMap<>();

    public static RealmDataRepository getDefaultInstance() {
        if(realmDataRepository == null)
            realmDataRepository = new RealmDataRepository(Realm.getDefaultInstance());

        return realmDataRepository;
    }

    /**
     * For threaded operation, a separate Realm instance is needed, not the default
     * instance, and thus all Realm objects can not pass through threads, extra care
     * must be taken to close the Realm instance after use or else app will crash
     * onDestroy of MainActivity. This is to ensure the database remains compact and
     * application remains free of silent bugs
     * @param realmInstance Separate Realm instance to be used
     * @return Realm Data Repository
     */
    public static RealmDataRepository getInstance(Realm realmInstance) {
        if(!repoCache.containsKey(realmInstance)) {
            repoCache.put(realmInstance, new RealmDataRepository(realmInstance));
        }
        return repoCache.get(realmInstance);
    }

    private RealmDataRepository(Realm realm) {
        this.realm = realm;
    }

    public Realm getRealmInstance() {
        return realm;
    }

    //User Section

    private void saveUserInRealm(User user) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(user);
        realm.commitTransaction();
        realm.close();
    }

    /**
     * Saves the User object in database and returns Completable
     * object for tracking the state of operation
     *
     * @param user User which is to be stored
     * @return Completable object to be subscribed by caller
     */
    public Completable saveUser(final User user) {
        return Completable.fromAction(() -> {
            saveUserInRealm(user);
            Timber.d("Saved User");
        });
    }

    /**
     * Returns Future style User which is null
     * To get the contents of User, add an OnRealmChangeListener
     * which notifies about the object state asynchronously
     *
     * @return User Returns User Future
     */
    public User getUser() {
        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirstAsync();
        realm.close();
        return user;
    }

    /**
     * Returns User synchronously
     *
     * @return User
     */
    public User getUserSync() {
        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        realm.close();
        return user;
    }

    public void clearUserData() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(User.class);
            }
        });
        realm.close();
    }


    // Events Section

    private void saveEventInRealm(Event event) {
        realm.beginTransaction();
        realm.insertOrUpdate(event);
        realm.commitTransaction();
    }

    /**
     * Saves the Event object in database and returns Completable
     * object for tracking the state of operation
     * @param event Event which is to be stored
     * @return Completable object to be subscribed by caller
     */
    public Completable saveEvent(final Event event) {
        return Completable.fromAction(() -> {
            saveEventInRealm(event);
            Timber.d("Saved Event");
        });
    }

    /**
     * Returns Future style Event which is null
     * To get the contents of Event, add an OnRealmChangeListener
     * which notifies about the object state asynchronously
     * @return Event Returns Event Future
     */
    public Event getEvent() {
        return realm.where(Event.class).findFirstAsync();
    }

    /**
     * Returns Event synchronously
     * @return Event
     */
    public Event getEventSync() {
        return realm.where(Event.class).findFirst();
    }


    /**
     * Convert RealmResults to LiveRealmData
     */
    public static <K extends RealmObject> LiveRealmData<K> asLiveData(RealmResults<K> data) {
        return new LiveRealmData<>(data);
    }

    /**
     * Convert RealmObject to LiveRealmDataObject
     */
    public static <K extends RealmObject> LiveRealmDataObject<K> asLiveDataForObject(K data) {
        return new LiveRealmDataObject<>(data);
    }

    /**
     * Convert RealmResults to FilterableRealmLiveData
     */
    public static <K extends RealmObject> FilterableRealmLiveData<K> asFilterableLiveData(RealmResults<K> data) {
        return new FilterableRealmLiveData<K>(data);
    }

    /**
     * Compacts the database to save space
     * Should be called when exiting application to ensure
     * all Realm instances are ready to be closed.
     *
     * Closing the repoCache instances is the responsibility
     * of caller
     */
    public static void compactDatabase() {
        Realm realm = realmDataRepository.getRealmInstance();

        Timber.d("Vacuuming the database");
        Realm.compactRealm(realm.getConfiguration());
    }

}
