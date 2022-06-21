package h.shafoot.datastoredemo.data.preferences.protostore

import androidx.datastore.core.DataStore
import h.shafoot.datastoredemo.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject

class ProtoStoreImpl @Inject constructor(private val dataStore : DataStore<UserPreferences>) : ProtoStore {

    override val userPreferences : Flow<UserPreferences>
        get() = dataStore.data.catch { exception ->
            if (exception is IOException) {
                exception.printStackTrace()
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }

    override suspend fun setName(value : String) {
        dataStore.updateData { currentUserPreferences ->
            currentUserPreferences.toBuilder().setName(value).build()
        }
    }

    override suspend fun setNote(value : String) {
        dataStore.updateData { currentUserPreferences ->
            currentUserPreferences.toBuilder().setNote(value).build()
        }
    }


}