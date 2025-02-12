package com.dcac.marsphotos

import com.dcac.marsphotos.data.NetworkMarsPhotosRepository
import com.dcac.marsphotos.fake.FakeDataSource
import com.dcac.marsphotos.fake.FakeMarsApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkMarsRepositoryTest {

    @Test
    fun networkMarsPhotosRepository_getMars_Photos_verifyPhotosList() =
        runTest {
            val repository = NetworkMarsPhotosRepository(
                marsApiService = FakeMarsApiService()
            )
            assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
        }
}