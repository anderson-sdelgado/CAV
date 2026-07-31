package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IDeleteCardRepositoryTest {

    private val cardSharedPreferencesDatasource = mock<CardSharedPreferencesDatasource>()
    private val repository = IDeleteCardRepository(
        cardSharedPreferencesDatasource = cardSharedPreferencesDatasource
    )

    @Test
    fun `deleteVehicleOwn - Check return failure if have error in CardSharedPreferencesDatasource deleteVehicleOwn`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.deleteVehicleOwn(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.deleteVehicleOwn",
                    "-",
                    Exception()
                )
            )
            val result = repository.deleteVehicleOwn(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDeleteCardRepository.deleteVehicleOwn -> ICardSharedPreferencesDatasource.deleteVehicleOwn"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `deleteVehicleOwn - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.deleteVehicleOwn(1)
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .deleteVehicleOwn(1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `deleteEquipSecondary - Check return failure if have error in CardSharedPreferencesDatasource deleteEquipSecondary`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.deleteEquipSecondary(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.deleteEquipSecondary",
                    "-",
                    Exception()
                )
            )
            val result = repository.deleteEquipSecondary(1, 2)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDeleteCardRepository.deleteEquipSecondary -> ICardSharedPreferencesDatasource.deleteEquipSecondary"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `deleteEquipSecondary - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.deleteEquipSecondary(1, 2)
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .deleteEquipSecondary(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
        }
    
    @Test
    fun `deleteVehicleInvolved - Check return failure if have error in CardSharedPreferencesDatasource deleteVehicleInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.deleteVehicleExternal(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.deleteVehicleInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.deleteVehicleExternal(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDeleteCardRepository.deleteVehicleInvolved -> ICardSharedPreferencesDatasource.deleteVehicleInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `deleteVehicleInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.deleteVehicleExternal(1)
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .deleteVehicleExternal(1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `deleteInvolved - Check return failure if have error in CardSharedPreferencesDatasource deleteInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.deleteInvolvedExternal(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.deleteInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.deleteInvolvedExternal(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDeleteCardRepository.deleteInvolved -> ICardSharedPreferencesDatasource.deleteInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `deleteInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.deleteInvolvedExternal(1)
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .deleteInvolvedExternal(1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `deleteWitness - Check return failure if have error in CardSharedPreferencesDatasource deleteWitness`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.deleteWitnessExternal(1)
                ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.deleteWitness",
                    "-",
                    Exception()
                )
            )
            val result = repository.deleteWitnessExternal(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDeleteCardRepository.deleteWitness -> ICardSharedPreferencesDatasource.deleteWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `deleteWitness - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.deleteWitnessExternal(1)
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .deleteWitnessExternal(1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `deletePassengerColab - Check return failure if have error in CardSharedPreferencesDatasource deletePassengerColab`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.deletePassengerColab(1, 2)
            ).thenReturn(
            resultFailure(
                "ICardSharedPreferencesDatasource.deletePassengerColab",
                "-",
                Exception()
                )
            )
            val result = repository.deletePassengerColab(1, 2)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDeleteCardRepository.deletePassengerColab -> ICardSharedPreferencesDatasource.deletePassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `deletePassengerColab - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.deletePassengerColab(1, 2)
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .deletePassengerColab(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `deletePassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource deletePassengerInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.deletePassengerInvolved(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.deletePassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.deletePassengerExternal(1, 2)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDeleteCardRepository.deletePassengerInvolved -> ICardSharedPreferencesDatasource.deletePassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `deletePassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.deletePassengerExternal(1, 2)
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .deletePassengerInvolved(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `deletePhoto - Check return failure if have error in CardSharedPreferencesDatasource deletePhoto`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.deletePhoto("test")
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.deletePhoto",
                    "-",
                    Exception()
                )
            )
            val result = repository.deletePhoto("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDeleteCardRepository.deletePhoto -> ICardSharedPreferencesDatasource.deletePhoto"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `deletePhoto - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.deletePhoto("test")
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .deletePhoto("test")
            assertEquals(
                result.isSuccess,
                true
            )
        }

}