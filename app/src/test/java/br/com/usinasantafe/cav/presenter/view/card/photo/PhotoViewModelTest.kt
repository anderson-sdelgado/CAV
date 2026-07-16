package br.com.usinasantafe.cav.presenter.view.card.photo

import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.DeletePhoto
import br.com.usinasantafe.cav.domain.usecases.card.HasDataLocal
import br.com.usinasantafe.cav.domain.usecases.card.HasLocal
import br.com.usinasantafe.cav.domain.usecases.card.HasNature
import br.com.usinasantafe.cav.domain.usecases.card.HasTypeAccident
import br.com.usinasantafe.cav.domain.usecases.card.HasVehicleOwn
import br.com.usinasantafe.cav.domain.usecases.card.ListPhoto
import br.com.usinasantafe.cav.domain.usecases.card.SaveCard
import br.com.usinasantafe.cav.domain.usecases.card.SetPhoto
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class PhotoViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val listPhoto = mock<ListPhoto>()
    private val setPhoto = mock<SetPhoto>()
    private val deletePhoto = mock<DeletePhoto>()
    private val hasLocal = mock<HasLocal>()
    private val hasNature = mock<HasNature>()
    private val hasTypeAccident = mock<HasTypeAccident>()
    private val hasDataLocal = mock<HasDataLocal>()
    private val hasVehicleOwn = mock<HasVehicleOwn>()
    private val saveCard = mock<SaveCard>()

    private val viewModel = PhotoViewModel(
        listPhoto = listPhoto,
        setPhoto = setPhoto,
        deletePhoto = deletePhoto,
        hasLocal = hasLocal,
        hasNature = hasNature,
        hasTypeAccident = hasTypeAccident,
        hasDataLocal = hasDataLocal,
        hasVehicleOwn = hasVehicleOwn,
        saveCard = saveCard
    )

    @Test
    fun `recoverData - Check return failure if have error in ListPhoto`() =
        runTest {
            whenever(
                listPhoto()
            ).thenReturn(
                resultFailure(
                    "ListPhoto",
                    "-",
                    Exception()
                )
            )
            viewModel.recoverData()
            advanceUntilIdle()
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "PhotoViewModel.recoverData -> ListPhoto -> java.lang.Exception"
            )
        }

    @Test
    fun `recoverData - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                listPhoto()
            ).thenReturn(
                Result.success(listOf("test1", "test2"))
            )
            viewModel.recoverData()
            advanceUntilIdle()
            assertEquals(
                viewModel.uiState.value.photos,
                listOf("test1", "test2")
            )
        }

    @Test
    fun `addPhoto - Check return failure if have error in SetPhoto`() =
        runTest {
            whenever(
                setPhoto("test")
            ).thenReturn(
                resultFailure(
                    "SetPhoto",
                    "-",
                    Exception()
                )
            )
            viewModel.addPhoto("test")
            advanceUntilIdle()
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "PhotoViewModel.save -> SetPhoto -> java.lang.Exception"
            )
        }

    @Test
    fun `addPhoto - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                setPhoto("test")
            ).thenReturn(
                Result.success(Unit)
            )
            viewModel.addPhoto("test")
            advanceUntilIdle()
            verify(setPhoto, atLeastOnce()).invoke("test")
            assertEquals(
                viewModel.uiState.value.photos,
                listOf("test")
            )
        }

    @Test
    fun `removePhoto - Check return failure if have error in DeletePhoto`() =
        runTest {
            whenever(
                listPhoto()
            ).thenReturn(
                Result.success(listOf("test"))
            )
            viewModel.recoverData()
            advanceUntilIdle()

            whenever(
                deletePhoto("test")
            ).thenReturn(
                resultFailure(
                    "DeletePhoto",
                    "-",
                    Exception()
                )
            )
            viewModel.removePhoto("test")
            advanceUntilIdle()
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "PhotoViewModel.delete -> DeletePhoto -> java.lang.Exception"
            )
        }

    @Test
    fun `removePhoto - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                listPhoto()
            ).thenReturn(
                Result.success(listOf("test"))
            )
            viewModel.recoverData()
            advanceUntilIdle()

            whenever(
                deletePhoto("test")
            ).thenReturn(
                Result.success(Unit)
            )
            viewModel.removePhoto("test")
            advanceUntilIdle()
            verify(deletePhoto, atLeastOnce()).invoke("test")
            assertEquals(
                viewModel.uiState.value.photos.size,
                0
            )
        }

    @Test
    fun `setNewPhoto - Check update state`() {
        viewModel.setNewPhoto("test")
        assertEquals(
            viewModel.uiState.value.newPhoto,
            "test"
        )
    }

}
