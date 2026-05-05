package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.entities.variable.Local
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.LocalSharedPreferencesModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ICardRepositoryTest {

    private val cardSharedPreferencesDatasource = mock<CardSharedPreferencesDatasource>()
    private val repository = ICardRepository(
        cardSharedPreferencesDatasource = cardSharedPreferencesDatasource
    )

    @Test
    fun `setRegAttendant - Check return failure if have error in CardSharedPreferencesDatasource setRegAttendant`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setRegAttendant(19759)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setRegAttendant",
                    "-",
                    Exception()
                )
            )
            val result = repository.setRegAttendant(19759)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.setRegAttendant -> ICardSharedPreferencesDatasource.setRegAttendant"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setRegAttendant - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setRegAttendant(19759)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).setRegAttendant(19759)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setIdCar - Check return failure if have error in CardSharedPreferencesDatasource setIdCar`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setIdCar(200)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setIdCar",
                    "-",
                    Exception()
                )
            )
            val result = repository.setIdCar(200)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.setIdCar -> ICardSharedPreferencesDatasource.setIdCar"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setIdCar - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setIdCar(200)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).setIdCar(200)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setLocal - Check return failure if have error in CardSharedPreferencesDatasource setLocal`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setLocal(
                    LocalSharedPreferencesModel(
                        address = "Test",
                        latitude = 0.0,
                        longitude = 0.0
                    )
                )
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setLocal",
                    "-",
                    Exception()
                )
            )
            val result = repository.setLocal(
                Local(
                    address = "Test",
                    latitude = 0.0,
                    longitude = 0.0
                )
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.setLocal -> ICardSharedPreferencesDatasource.setLocal"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setLocal - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setLocal(
                Local(
                    address = "Test",
                    latitude = 0.0,
                    longitude = 0.0
                )
            )
            verify(cardSharedPreferencesDatasource, atLeastOnce()).setLocal(
                LocalSharedPreferencesModel(
                    address = "Test",
                    latitude = 0.0,
                    longitude = 0.0
                )
            )
            assertEquals(
                result.isSuccess,
                true
            )
        }

}