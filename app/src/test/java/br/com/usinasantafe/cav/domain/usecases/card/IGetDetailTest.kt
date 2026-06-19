package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
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
    fun `Check return failure if have error in CardRepository getDetailEquip - Option INSERT - FlowNote EQUIP`() =
        runTest {
            whenever(
                cardRepository.getDetailEquip()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailEquip",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP,
                idMain = 0,
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
    fun `Check return empty if function execute successfully and return is null - Option INSERT - FlowNote EQUIP`() =
        runTest {
            whenever(
                cardRepository.getDetailEquip()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP,
                idMain = 0,
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
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote EQUIP`() =
        runTest {
            whenever(
                cardRepository.getDetailEquip()
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP,
                idMain = 0,
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
    fun `Check return failure if have error in CardRepository getDetailEquip - Option INSERT - FlowNote EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.getDetailEquip()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailEquip",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 0,
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
    fun `Check return empty if function execute successfully and return is null - Option INSERT - FlowNote EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.getDetailEquip()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 0,
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
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.getDetailEquip()
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 0,
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
    fun `Check return failure if have error in CardRepository getDetailColab - Option INSERT - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailColab()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                idMain = 0,
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
    fun `Check return empty if function execute successfully and return is null - Option INSERT - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailColab()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                idMain = 0,
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
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailColab()
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                idMain = 0,
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
    fun `Check return failure if have error in CardRepository getDetailColab - Option INSERT - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailColab()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailColab",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 0,
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
    fun `Check return empty if function execute successfully and return is null - Option INSERT - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailColab()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_COLAB,
                idMain = 0,
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
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailColab()
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.COLAB,
                idMain = 0,
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
    fun `Check return failure if have error in CardRepository getDetailVehicle - Option INSERT - FlowNote VEHICLE`() =
        runTest {
            whenever(
                cardRepository.getDetailVehicle()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailVehicle",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.VEHICLE,
                idMain = 0,
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
    fun `Check return empty if function execute successfully and return is null - Option INSERT - FlowNote VEHICLE`() =
        runTest {
            whenever(
                cardRepository.getDetailVehicle()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.VEHICLE,
                idMain = 0,
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
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote VEHICLE`() =
        runTest {
            whenever(
                cardRepository.getDetailVehicle()
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.VEHICLE,
                idMain = 0,
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
    fun `Check return failure if have error in CardRepository getDetailInvolved - Option INSERT - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.DRIVER,
                idMain = 0,
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
    fun `Check return empty if function execute successfully and return is null - Option INSERT - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.DRIVER,
                idMain = 0,
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
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved()
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.DRIVER,
                idMain = 0,
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
    fun `Check return failure if have error in CardRepository getDetailInvolved - Option INSERT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 0,
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
    fun `Check return empty if function execute successfully and return is null - Option INSERT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 0,
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
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved()
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.PASSENGER_INVOLVED,
                idMain = 0,
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
    fun `Check return failure if have error in CardRepository getDetailInvolved - Option INSERT - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.INVOLVED,
                idMain = 0,
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
    fun `Check return empty if function execute successfully and return is null - Option INSERT - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.INVOLVED,
                idMain = 0,
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
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved()
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.INVOLVED,
                idMain = 0,
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
    fun `Check return failure if have error in CardRepository getDetailInvolved - Option INSERT - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDetailInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.WITNESS,
                idMain = 0,
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
    fun `Check return empty if function execute successfully and return is null - Option INSERT - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.WITNESS,
                idMain = 0,
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
    fun `Check return correct if function execute successfully - Option INSERT - FlowNote WITNESS`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved()
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.INSERT,
                flowNote = FlowNote.WITNESS,
                idMain = 0,
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
    fun `Check return failure if have error in CardRepository getDetailEquip - Option EDIT - FlowNote EQUIP`() =
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
                option = Option.EDIT,
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
    fun `Check return empty if function execute successfully and return is null - Option EDIT - FlowNote EQUIP`() =
        runTest {
            whenever(
                cardRepository.getDetailEquip(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote EQUIP`() =
        runTest {
            whenever(
                cardRepository.getDetailEquip(1)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return failure if have error in CardRepository getDetailEquipSecondary - Option EDIT - FlowNote EQUIP_SEC`() =
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
                option = Option.EDIT,
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
    fun `Check return empty if function execute successfully and return is null - Option EDIT - FlowNote EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.getDetailEquipSecondary(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.getDetailEquipSecondary(1, 2)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return failure if have error in CardRepository getDetailColab - Option EDIT - FlowNote COLAB`() =
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
                option = Option.EDIT,
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
    fun `Check return empty if function execute successfully and return is null - Option EDIT - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailColab(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailColab(1)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return failure if have error in CardRepository getDetailPassengerColab - Option EDIT - FlowNote PASSENGER_COLAB`() =
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
                option = Option.EDIT,
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
    fun `Check return empty if function execute successfully and return is null - Option EDIT - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailPassengerColab(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote PASSENGER_COLAB`() =
        runTest {
            whenever(
                cardRepository.getDetailPassengerColab(1, 2)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return failure if have error in CardRepository getDetailVehicle - Option EDIT - FlowNote VEHICLE`() =
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
                option = Option.EDIT,
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
    fun `Check return empty if function execute successfully and return is null - Option EDIT - FlowNote VEHICLE`() =
        runTest {
            whenever(
                cardRepository.getDetailVehicle(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote VEHICLE`() =
        runTest {
            whenever(
                cardRepository.getDetailVehicle(1)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return failure if have error in CardRepository getDetailDriver - Option EDIT - FlowNote DRIVER`() =
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
                option = Option.EDIT,
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
    fun `Check return empty if function execute successfully and return is null - Option EDIT - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.getDetailDriver(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote DRIVER`() =
        runTest {
            whenever(
                cardRepository.getDetailDriver(1)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return failure if have error in CardRepository getDetailDriver - Option EDIT - FlowNote PASSENGER_INVOLVED`() =
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
                option = Option.EDIT,
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
    fun `Check return empty if function execute successfully and return is null - Option EDIT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailPassengerInvolved(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote PASSENGER_INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailPassengerInvolved(1, 2)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return failure if have error in CardRepository getDetailInvolved - Option EDIT - FlowNote INVOLVED`() =
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
                option = Option.EDIT,
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
    fun `Check return empty if function execute successfully and return is null - Option EDIT - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return correct if function execute successfully - Option EDIT - FlowNote INVOLVED`() =
        runTest {
            whenever(
                cardRepository.getDetailInvolved(1)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return failure if have error in CardRepository getDetailInvolved - Option EDIT - flowNote is WITNESS`() =
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
                option = Option.EDIT,
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
    fun `Check return empty if function execute successfully and return is null - Option EDIT - flowNote is WITNESS`() =
        runTest {
            whenever(
                cardRepository.getDetailWitness(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.EDIT,
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
    fun `Check return correct if function execute successfully - Option EDIT - flowNote is WITNESS`() =
        runTest {
            whenever(
                cardRepository.getDetailWitness(1)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(
                option = Option.EDIT,
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