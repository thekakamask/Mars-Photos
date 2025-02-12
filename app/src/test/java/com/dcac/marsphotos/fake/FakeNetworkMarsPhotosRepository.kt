package com.dcac.marsphotos.fake

import com.dcac.marsphotos.data.MarsPhotosRepository
import com.dcac.marsphotos.model.MarsPhoto

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}