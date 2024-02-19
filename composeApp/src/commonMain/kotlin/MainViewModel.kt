import data.Client.getPhotos
import dev.icerock.moko.mvvm.viewmodel.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.PhotoItem

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    init {
        loadPhotos()
    }

    private fun loadPhotos() {
        viewModelScope.launch {
            val photos = getPhotos()
            if (photos.isNotEmpty()) {
                _uiState.update {
                    it.copy(photos = photos)
                }
            }
        }
    }

}

data class UiState(
    val loading: Boolean = false,
    val photos: List<PhotoItem> = emptyList(),
    val error: String? = null
)