package h.shafoot.datastoredemo.data.preferences.protostore

import androidx.datastore.core.DataStore
import h.shafoot.datastoredemo.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject

class ProtoStoreImpl @Inject constructor(private val dataStore : DataStore<AppPreferences>) : ProtoStore {

    override val appPreferences : Flow<AppPreferences>
        get() = dataStore.data.catch { exception ->
            if (exception is IOException) {
                exception.printStackTrace()
                emit(AppPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }

    override suspend fun setDidParkingOnBoardingShown(value : Boolean) {
        dataStore.updateData { currentUserPreferences ->
            currentUserPreferences.toBuilder().setDidParkingOnBoardingShown(value).build()
        }
    }

    override suspend fun setBranches(value : List<String>) {
        dataStore.updateData { currentUserPreferences ->
            currentUserPreferences.toBuilder().addAllBranches(value).build()
        }
    }

    override suspend fun setPreferredBranchId(value : String) {
        dataStore.updateData { currentUserPreferences ->
            currentUserPreferences.toBuilder().setPreferredBranchId(value).build()
        }
    }

    override suspend fun setConfiguration(value : AppPreferences.Configuration) {
        dataStore.updateData { currentUserPreferences ->
            currentUserPreferences.toBuilder().setConfiguration(value).build()
        }
    }

}