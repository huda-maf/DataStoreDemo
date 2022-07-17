package h.shafoot.datastoredemo.data.preferences.protostore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import h.shafoot.datastoredemo.AppPreferences
import java.io.InputStream
import java.io.OutputStream

object AppPreferencesSerializer : Serializer<AppPreferences> {

    override val defaultValue : AppPreferences = AppPreferences.getDefaultInstance()
    override suspend fun readFrom(input : InputStream) : AppPreferences {
        try {
            return AppPreferences.parseFrom(input)
        } catch (exception : InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t : AppPreferences, output : OutputStream) = t.writeTo(output)
}
