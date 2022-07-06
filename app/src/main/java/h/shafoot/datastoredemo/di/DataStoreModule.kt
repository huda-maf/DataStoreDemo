package h.shafoot.datastoredemo.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import h.shafoot.datastoredemo.UserPreferences
import h.shafoot.datastoredemo.data.preferences.prefsstore.PrefsStore
import h.shafoot.datastoredemo.data.preferences.prefsstore.PrefsStoreImpl
import h.shafoot.datastoredemo.data.preferences.protostore.ProtoStore
import h.shafoot.datastoredemo.data.preferences.protostore.ProtoStoreImpl
import h.shafoot.datastoredemo.data.preferences.protostore.UserPreferencesSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataStoreModule {

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
                ReplaceFileCorruptionHandler { emptyPreferences() },
                //migrations = listOf(SharedPreferencesMigration(context, "shafoot.h.myevents_prefernces")),
                scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
                produceFile = { context.preferencesDataStoreFile("demo_store_prefs.pb") })
    }

    @Provides
    @Singleton
    fun provideProtoDataStore(@ApplicationContext context : Context) : DataStore<UserPreferences> {
        return DataStoreFactory.create(
                serializer = UserPreferencesSerializer, produceFile = { context.dataStoreFile("") },/* migrations = listOf(SharedPreferencesMigration(context, "prefs name") {
            sharedPrefs : SharedPreferencesView, currentData : UserPreferences -> // mapping shared prefs to userprefs
            if (currentData.sortOrder ==)
        })*/
        )
    }

    @Provides
    @Singleton
    fun providePrefsStore(dataStore: DataStore<Preferences>): PrefsStore {
        return PrefsStoreImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideProtoStore(dataStore: DataStore<UserPreferences>): ProtoStore {
        return ProtoStoreImpl(dataStore)
    }

}