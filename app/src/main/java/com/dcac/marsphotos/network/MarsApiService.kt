package com.dcac.marsphotos.network

import com.dcac.marsphotos.model.MarsPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

/*The MarsApiService interface is responsible for defining how the
application interacts with the Mars API to retrieve a list of photos.
it's an interface that follows the Retrofit convention to define
API endpoints.

When getPhotos() is called, Retrofit sends a GET request to the
"photos" endpoint.
The response is automatically deserialized into a list of MarsPhoto
objects (assuming correct JSON mapping).
Since the function is marked as suspend, it runs asynchronously
within a coroutine, ensuring efficient network operations.
The calling function (likely inside a ViewModel or Repository)
processes the returned data and updates the UI accordingly.
This interface is used inside a repository class
(NetworkMarsPhotosRepository), which in turn is used by
the ViewModel to fetch and manage Mars photos.*/

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>

}