package com.dcac.marsphotos.data

import com.dcac.marsphotos.network.MarsApi
import com.dcac.marsphotos.network.MarsPhoto

interface MarsPhotosRepository {

    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotoRepository() : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return MarsApi.retrofitService.getPhotos()
    }
}