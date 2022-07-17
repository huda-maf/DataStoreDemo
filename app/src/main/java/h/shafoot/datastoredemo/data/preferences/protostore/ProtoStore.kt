package h.shafoot.datastoredemo.data.preferences.protostore

import h.shafoot.datastoredemo.AppPreferences
import kotlinx.coroutines.flow.Flow

interface ProtoStore {

    val appPreferences : Flow<AppPreferences>

    suspend fun setDidParkingOnBoardingShown(value:Boolean)

    suspend fun setBranches(value:List<String>)

    suspend fun setPreferredBranchId(value:String)

    suspend fun setConfiguration(value:AppPreferences.Configuration)

}