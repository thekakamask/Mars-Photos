package com.dcac.marsphotos

import com.dcac.marsphotos.fake.FakeDataSource
import com.dcac.marsphotos.fake.FakeNetworkMarsPhotosRepository
import com.dcac.marsphotos.rules.TestDispatcherRule
import com.dcac.marsphotos.ui.screens.MarsUiState
import com.dcac.marsphotos.ui.screens.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()
    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() =
        runTest {
            val marsViewModel = MarsViewModel(
                marsPhotosRepository = FakeNetworkMarsPhotosRepository()
            )
            assertEquals(
                MarsUiState.Success(FakeDataSource.photosList),
                marsViewModel.marsUiState
            )
        }
}