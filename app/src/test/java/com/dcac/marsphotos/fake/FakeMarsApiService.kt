package com.dcac.marsphotos.fake

import com.dcac.marsphotos.model.MarsPhoto
import com.dcac.marsphotos.network.MarsApiService

class FakeMarsApiService : MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}