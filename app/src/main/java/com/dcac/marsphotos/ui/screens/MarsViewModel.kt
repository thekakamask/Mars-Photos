package com.dcac.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.dcac.marsphotos.MarsPhotosApplication
import com.dcac.marsphotos.data.MarsPhotosRepository
import com.dcac.marsphotos.model.MarsPhoto
import kotlinx.coroutines.launch
import java.io.IOException

/*The MarsViewModel class is responsible for handling UI-related
data and managing the state for the Mars photos screen. It
communicates with the repository to fetch data from the network
and updates the UI accordingly.

1️⃣ Initialization :
When an instance of MarsViewModel is created, it immediately
calls getMarsPhotos() to start fetching Mars photos.
This ensures that data is available as soon as the ViewModel
is initialized.
2️⃣ getMarsPhotos() function :
The function is a coroutine (viewModelScope.launch), meaning it runs
 asynchronously without blocking the UI thread.
It tries to fetch the list of Mars photos from
marsPhotosRepository.getMarsPhotos().
If the data is successfully retrieved, the state is updated with
MarsUiState.Success(), which holds the number of photos retrieved.
If an error occurs (e.g., no internet connection), it updates
the state with MarsUiState.Error.
3️⃣ MarsUiState sealed interface :
It defines three states:
Success(photos: String): Holds data when the request is successful.
Error: Represents a failure state.
Loading: Indicates that the data is currently being fetched.
4️⃣ Factory companion object :
The Factory object is responsible for creating instances of
MarsViewModel.
It ensures dependency injection by retrieving the
MarsPhotosRepository from the MarsPhotosApplication.container.*/

class MarsViewModel (private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set
    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */

    private fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = try {
                //val listResult = marsPhotosRepository.getMarsPhotos()
                val result = marsPhotosRepository.getMarsPhotos()[0]
                /*MarsUiState.Success(
                    "Success: ${listResult.size} Mars photos retrieved")*/
                MarsUiState.Success(marsPhotosRepository.getMarsPhotos())
            } catch (e: IOException) {
                MarsUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }

}

sealed interface MarsUiState {
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    data object Error : MarsUiState
    data object Loading : MarsUiState
}