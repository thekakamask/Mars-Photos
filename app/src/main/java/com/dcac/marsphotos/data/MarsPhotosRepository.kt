package com.dcac.marsphotos.data

import com.dcac.marsphotos.network.MarsApiService
import com.dcac.marsphotos.model.MarsPhoto

/*This file defines an interface MarsPhotosRepository and its
network-based implementation NetworkMarsPhotosRepository.
It follows the Repository Pattern, which abstracts the data source,
allowing the app to retrieve Mars photos without depending
directly on Retrofit.

1️⃣ Defines an interface MarsPhotosRepository :
This defines a contract that any data source must follow.
It does not specify where the data comes from (network, database,
etc.).
Uses a suspend function (suspend fun getMarsPhotos()) to allow
asynchronous execution with coroutines.

2️⃣ Implements MarsPhotosRepository in NetworkMarsPhotosRepository :
This fetches Mars photos from the network using MarsApiService.
Calls the Retrofit API function getPhotos().
Runs asynchronously (since suspend functions must be called inside
coroutines).*/

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()

}