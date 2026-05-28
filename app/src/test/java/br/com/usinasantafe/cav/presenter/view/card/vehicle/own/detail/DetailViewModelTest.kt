package br.com.usinasantafe.cav.presenter.view.card.vehicle.own.detail

import androidx.lifecycle.SavedStateHandle
import br.com.usinasantafe.cav.MainCoroutineRule
import br.com.usinasantafe.cav.domain.usecases.card.GetDetailVehicleOwn
import br.com.usinasantafe.cav.domain.usecases.card.SetDetailVehicleOwn
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.lib.TypeDetail
import br.com.usinasantafe.cav.presenter.Args.OPTION_ARG
import br.com.usinasantafe.cav.presenter.Args.TYPE_DETAIL_ARG
import br.com.usinasantafe.cav.presenter.view.card.detail.DetailViewModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getDetailVehicleOwn = mock<GetDetailVehicleOwn>()
    private val setDetailVehicleOwn = mock<SetDetailVehicleOwn>()
    private val viewModel = DetailViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                OPTION_ARG to Option.INSERT.ordinal,
                TYPE_DETAIL_ARG to TypeDetail.EQUIP.ordinal
            )
        ),
        getDetailVehicleOwn = getDetailVehicleOwn,
        setDetailVehicleOwn = setDetailVehicleOwn
    )

    @Test
    fun `recoverData - Check return failure if have error in GetDetailVehicleOwn`() =
        runTest {
            whenever(
                getDetailVehicleOwn(
                    option = Option.INSERT,
                    typeDetail = TypeDetail.EQUIP
                )
            ).thenReturn(
                resultFailure(
                    context = "GetDetailVehicleOwn",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "DetailVehicleOwnViewModel.recoverData -> GetDetailVehicleOwn -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
        }

    @Test
    fun `recoverData - Check return true if process execute successfully`() =
        runTest {
            whenever(
                getDetailVehicleOwn(
                    option = Option.INSERT,
                    typeDetail = TypeDetail.EQUIP
                )
            ).thenReturn(
                Result.success("Test")
            )
            viewModel.recoverData()
            assertEquals(
                viewModel.uiState.value.text,
                "Test"
            )
        }

    @Test
    fun `set - Check return failure if have error in SetDetailVehicleOwn`() =
        runTest {
            whenever(
                setDetailVehicleOwn(
                    option = Option.INSERT,
                    typeDetail = TypeDetail.EQUIP,
                    text = "Test"
                )
            ).thenReturn(
                resultFailure(
                    context = "SetDetailVehicleOwn",
                    message = "-",
                    cause = Exception()
                )
            )
            viewModel.onTextChanged("Test")
            viewModel.set()
            assertEquals(
                viewModel.uiState.value.status.flagDialog,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.failure,
                "DetailVehicleOwnViewModel.set -> SetDetailVehicleOwn -> java.lang.Exception"
            )
            assertEquals(
                viewModel.uiState.value.status.errors,
                Errors.EXCEPTION
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                false
            )
        }

    @Test
    fun `set - Check return true if process execute successfully`() =
        runTest {
            viewModel.onTextChanged("Test")
            viewModel.set()
            verify(
                setDetailVehicleOwn,
                atLeastOnce()
            ).invoke(
                option = Option.INSERT,
                typeDetail = TypeDetail.EQUIP,
                text = "Test"
            )
            assertEquals(
                viewModel.uiState.value.status.flagAccess,
                true
            )
            assertEquals(
                viewModel.uiState.value.status.flagFailure,
                false
            )
        }

}