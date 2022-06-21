package h.shafoot.datastoredemo.data.preferences.prefsstore

import kotlinx.coroutines.flow.Flow

interface PrefsStore {

    suspend fun setName(value : String)

    fun getName() : Flow<String>

    suspend fun setNote(value : String)

    fun getNote() : Flow<String>
}