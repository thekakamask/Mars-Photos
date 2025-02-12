package com.dcac.marsphotos.data

import com.dcac.marsphotos.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


/*The DefaultAppContainer class serves as a dependency injection
container for managing and providing application-wide dependencies
in a structured manner.

It implements the AppContainer interface, which defines a contract
for dependency management, ensuring that any class implementing
it provides an instance of MarsPhotosRepository.

1️⃣ Define AppContainer : Ensures that any implementing class provides
a MarsPhotosRepository.
2️⃣ Implement AppContainer via DefaultAppContainer : Creates the
actual dependencies.
3️⃣ Initialize Retrofit : Prepares for network communication.
4️⃣ Create MarsApiService : Defines API request methods.
5️⃣ Provide MarsPhotosRepository	Makes API calls via
NetworkMarsPhotosRepository.*/

interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }
}