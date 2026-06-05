package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetDetailTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetDetail(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getDetailEquip - flowNote is EQUIP`() =
        runTest {
            whenever(
                cardRepository.getDetailEquip(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailEquip",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDetail -> ICardRepository.getDetailEquip"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null - flowNote is EQUIP`() =
        runTest {
            whenever(
                cardRepository.getDetailEquip(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - flowNote is EQUIP`() =
        runTest {
            whenever(
                cardRepository.getDetailEquip(1)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Test"
            )
        }

    //////////////////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository getDetailEquipSecondary - flowNote is EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.getDetailEquipSecondary(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailEquipSecondary",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDetail -> ICardRepository.getDetailEquipSecondary"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null - flowNote is EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.getDetailEquipSecondary(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - flowNote is EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.getDetailEquipSecondary(1, 2)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Test"
            )
        }

    //////////////////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository getDetailColab - flowNote is COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailColab(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.COLAB,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDetail -> ICardRepository.getDetailColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null - flowNote is COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailColab(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                flowNote = FlowNote.COLAB,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - flowNote is COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailColab(1)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                flowNote = FlowNote.COLAB,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Test"
            )
        }

    //////////////////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository getDetailPassengerColab - flowNote is PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailPassengerColab(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailPassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDetail -> ICardRepository.getDetailPassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null - flowNote is PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailPassengerColab(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - flowNote is PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailPassengerColab(1, 2)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Test"
            )
        }

    //////////////////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository getDetailVehicle - flowNote is VEHICLE`() =
        runTest {
            whenever(
                cardRepository.getDetailVehicle(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailVehicle",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.VEHICLE,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDetail -> ICardRepository.getDetailVehicle"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null - flowNote is VEHICLE`() =
        runTest {
            whenever(
                cardRepository.getDetailVehicle(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                flowNote = FlowNote.VEHICLE,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - flowNote is VEHICLE`() =
        runTest {
            whenever(
                cardRepository.getDetailVehicle(1)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                flowNote = FlowNote.VEHICLE,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Test"
            )
        }

    //////////////////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository getDetailDriver - flowNote is DRIVER`() =
        runTest {
            whenever(
                cardRepository.getDetailDriver(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailDriver",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.DRIVER,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDetail -> ICardRepository.getDetailDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null - flowNote is DRIVER`() =
        runTest {
            whenever(
                cardRepository.getDetailDriver(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                flowNote = FlowNote.DRIVER,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - flowNote is DRIVER`() =
        runTest {
            whenever(
                cardRepository.getDetailDriver(1)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                flowNote = FlowNote.DRIVER,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Test"
            )
        }

    //////////////////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository getDetailDriver - flowNote is PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailPassengerInvolved(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailPassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDetail -> ICardRepository.getDetailPassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null - flowNote is PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailPassengerInvolved(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - flowNote is PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailPassengerInvolved(1, 2)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Test"
            )
        }

    //////////////////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository getDetailInvolved - flowNote is INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.INVOLVED,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDetail -> ICardRepository.getDetailInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null - flowNote is INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                flowNote = FlowNote.INVOLVED,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - flowNote is INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved(1)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                flowNote = FlowNote.INVOLVED,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Test"
            )
        }

    //////////////////////////////////////////////////////////////

    @Test
    fun `Check return failure if have error in CardRepository getDetailInvolved - flowNote is WITNESS`() =
        runTest {
            whenever(
                cardRepository.getDetailWitness(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailWitness",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.WITNESS,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDetail -> ICardRepository.getDetailWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null - flowNote is WITNESS`() =
        runTest {
            whenever(
                cardRepository.getDetailWitness(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                flowNote = FlowNote.WITNESS,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully - flowNote is WITNESS`() =
        runTest {
            whenever(
                cardRepository.getDetailWitness(1)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                flowNote = FlowNote.WITNESS,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Test"
            )
        }

}