package h.shafoot.datastoredemo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import h.shafoot.datastoredemo.data.preferences.prefsstore.PrefsStore
import h.shafoot.datastoredemo.data.preferences.protostore.ProtoStore
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val prefsStore : PrefsStore,private val protoStore : ProtoStore) : ViewModel() {

    val name = prefsStore.getName().asLiveData()

    val note = prefsStore.getNote().asLiveData()

    fun setName(text : String) {
        viewModelScope.launch {
            prefsStore.setName(text)
        }
    }

    fun setNote(text : String) {
        viewModelScope.launch {
            prefsStore.setNote(text)
        }
    }

    val nameProto = protoStore.userPreferences.asLiveData()

}