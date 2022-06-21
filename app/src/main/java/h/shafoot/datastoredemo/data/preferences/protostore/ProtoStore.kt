package h.shafoot.datastoredemo.data.preferences.protostore

import h.shafoot.datastoredemo.UserPreferences
import kotlinx.coroutines.flow.Flow

interface ProtoStore {

    val userPreferences : Flow<UserPreferences>

    suspend fun setName(value:String)

    suspend fun setNote(value:String)

}