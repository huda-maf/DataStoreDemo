package h.shafoot.datastoredemo.data.preferences.prefsstore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import h.shafoot.datastoredemo.data.preferences.prefsstore.PrefsStoreKeys.NAME
import h.shafoot.datastoredemo.data.preferences.prefsstore.PrefsStoreKeys.NOTE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class PrefsStoreImpl @Inject constructor(private val dataStore : DataStore<Preferences>) : PrefsStore {

    override suspend fun setName(value : String) {
        dataStore.edit {
            it[NAME] = value
        }
    }

    override fun getName() : Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { it[NAME] ?: "" }
    }

    override suspend fun setNote(value : String) {
        dataStore.edit {
            it[NOTE] = value
        }
    }

    override fun getNote() : Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { it[NOTE] ?: "" }
    }

}