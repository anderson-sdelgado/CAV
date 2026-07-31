package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IUpdateCardRepositoryTest {

    private val cardSharedPreferencesDatasource = mock<CardSharedPreferencesDatasource>()
    private val repository = IUpdateCardRepository(
        cardSharedPreferencesDatasource = cardSharedPreferencesDatasource
    )

    @Test
    fun `updateIdEquip - Check return failure if have error in CardSharedPreferencesDatasource updateIdEquip`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateIdEquip(1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateIdEquip",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateIdEquip(1, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateIdEquip -> ICardSharedPreferencesDatasource.updateIdEquip"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateIdEquip - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateIdEquip(1, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateIdEquip(1, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateIdEquipSecondary - Check return failure if have error in CardSharedPreferencesDatasource updateIdEquipSecondary`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateIdEquipSecondary(1, 1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateIdEquipSecondary",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateIdEquipSecondary(1, 1, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateIdEquipSecondary -> ICardSharedPreferencesDatasource.updateIdEquipSecondary"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateIdEquipSecondary - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateIdEquipSecondary(1, 1, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateIdEquipSecondary(1, 1, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateDetailEquip - Check return failure if have error in CardSharedPreferencesDatasource updateDetailEquip`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateDetailEquip("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateDetailEquip",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateDetailEquip("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateDetailEquip -> ICardSharedPreferencesDatasource.updateDetailEquip"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateDetailEquip - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateDetailEquip("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateDetailEquip("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateDetailEquipSecondary - Check return failure if have error in CardSharedPreferencesDatasource updateDetailEquipSecondary`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateDetailEquipSecondary("test", 1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateDetailEquipSecondary",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateDetailEquipSecondary("test", 1, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateDetailEquipSecondary -> ICardSharedPreferencesDatasource.updateDetailEquipSecondary"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateDetailEquipSecondary - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateDetailEquipSecondary("test", 1, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateDetailEquipSecondary("test", 1, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateDetailColab - Check return failure if have error in CardSharedPreferencesDatasource updateDetailColab`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateDetailColab("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateDetailColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateDetailColab("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateDetailColab -> ICardSharedPreferencesDatasource.updateDetailColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateDetailColab - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateDetailColab("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateDetailColab("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateDetailDriver - Check return failure if have error in CardSharedPreferencesDatasource updateDetailDriver`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateDetailDriver("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateDetailDriver",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateDetailDriver("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateDetailDriver -> ICardSharedPreferencesDatasource.updateDetailDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateDetailDriver - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateDetailDriver("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateDetailDriver("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateDetailPassengerColab - Check return failure if have error in CardSharedPreferencesDatasource updateDetailPassengerColab`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateDetailPassengerColab("test", 1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateDetailPassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateDetailPassengerColab("test", 1, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateDetailPassengerColab -> ICardSharedPreferencesDatasource.updateDetailPassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateDetailPassengerColab - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateDetailPassengerColab("test", 1, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateDetailPassengerColab("test", 1, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateDetailVehicle - Check return failure if have error in CardSharedPreferencesDatasource updateDetailVehicle`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateDetailVehicle("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateDetailVehicle",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateDetailVehicle("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateDetailVehicle -> ICardSharedPreferencesDatasource.updateDetailVehicle"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateDetailVehicle - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateDetailVehicle("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateDetailVehicle("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateDetailPassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource updateDetailPassengerInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateDetailPassengerExternal("test", 1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateDetailPassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateDetailPassengerExternal("test", 1, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateDetailPassengerInvolved -> ICardSharedPreferencesDatasource.updateDetailPassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateDetailPassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateDetailPassengerExternal("test", 1, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateDetailPassengerExternal("test", 1, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateDetailInvolved - Check return failure if have error in CardSharedPreferencesDatasource updateDetailInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateDetailInvolvedExternal("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateDetailInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateDetailInvolvedExternal("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateDetailInvolved -> ICardSharedPreferencesDatasource.updateDetailInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateDetailInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateDetailInvolvedExternal("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateDetailInvolvedExternal("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateDetailWitness - Check return failure if have error in CardSharedPreferencesDatasource updateDetailWitness`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateDetailWitnessExternal("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateDetailWitness",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateDetailWitnessExternal("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateDetailWitness -> ICardSharedPreferencesDatasource.updateDetailWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateDetailWitness - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateDetailWitnessExternal("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateDetailWitnessExternal("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateRegColab - Check return failure if have error in CardSharedPreferencesDatasource updateRegColab`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateRegColab(123456L, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateRegColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateRegColab(123456L, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateRegColab -> ICardSharedPreferencesDatasource.updateRegColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateRegColab - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateRegColab(123456L, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateRegColab(123456L, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateRegPassengerColab - Check return failure if have error in CardSharedPreferencesDatasource updateRegPassengerColab`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateRegPassengerColab(123456L, 1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateRegPassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateRegPassengerColab(123456L, 1, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateRegPassengerColab -> ICardSharedPreferencesDatasource.updateRegPassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateRegPassengerColab - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateRegPassengerColab(123456L, 1, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateRegPassengerColab(123456L, 1, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateStateColab - Check return failure if have error in CardSharedPreferencesDatasource updateStateColab`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateStateColab(State.UNHARMED, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateStateColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateStateColab(State.UNHARMED, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateStateColab -> ICardSharedPreferencesDatasource.updateStateColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateStateColab - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateStateColab(State.UNHARMED, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateStateColab(State.UNHARMED, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateStatePassengerColab - Check return failure if have error in CardSharedPreferencesDatasource updateStatePassengerColab`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateStatePassengerColab(State.UNHARMED, 1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateStatePassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateStatePassengerColab(State.UNHARMED, 1, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateStatePassengerColab -> ICardSharedPreferencesDatasource.updateStatePassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateStatePassengerColab - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateStatePassengerColab(State.UNHARMED, 1, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateStatePassengerColab(State.UNHARMED, 1, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateStateDriver - Check return failure if have error in CardSharedPreferencesDatasource updateStateDriver`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateStateDriver(State.UNHARMED, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateStateDriver",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateStateDriver(State.UNHARMED, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateStateDriver -> ICardSharedPreferencesDatasource.updateStateDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateStateDriver - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateStateDriver(State.UNHARMED, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateStateDriver(State.UNHARMED, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateStatePassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource updateStatePassengerInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateStatePassengerExternal(State.UNHARMED, 1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateStatePassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateStatePassengerExternal(State.UNHARMED, 1, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateStatePassengerInvolved -> ICardSharedPreferencesDatasource.updateStatePassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateStatePassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateStatePassengerExternal(State.UNHARMED, 1, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateStatePassengerExternal(State.UNHARMED, 1, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateStateInvolved - Check return failure if have error in CardSharedPreferencesDatasource updateStateInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateStateInvolvedExternal(State.UNHARMED, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateStateInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateStateInvolvedExternal(State.UNHARMED, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateStateInvolved -> ICardSharedPreferencesDatasource.updateStateInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateStateInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateStateInvolvedExternal(State.UNHARMED, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateStateInvolvedExternal(State.UNHARMED, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateStateWitness - Check return failure if have error in CardSharedPreferencesDatasource updateStateWitness`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateStateWitnessExternal(State.UNHARMED, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateStateWitness",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateStateWitnessExternal(State.UNHARMED, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateStateWitness -> ICardSharedPreferencesDatasource.updateStateWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateStateWitness - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateStateWitnessExternal(State.UNHARMED, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateStateWitnessExternal(State.UNHARMED, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateAddressPassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource updateAddressPassengerInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateAddressPassengerInvolved("test", 1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateAddressPassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateAddressPassengerInvolved("test", 1, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateAddressPassengerInvolved -> ICardSharedPreferencesDatasource.updateAddressPassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateAddressPassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateAddressPassengerInvolved("test", 1, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateAddressPassengerInvolved("test", 1, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateAddressInvolved - Check return failure if have error in CardSharedPreferencesDatasource updateAddressInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateAddressInvolved("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateAddressInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateAddressInvolved("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateAddressInvolved -> ICardSharedPreferencesDatasource.updateAddressInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateAddressInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateAddressInvolved("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateAddressInvolved("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateAddressDriver - Check return failure if have error in CardSharedPreferencesDatasource updateAddressDriver`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateAddressDriver("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateAddressDriver",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateAddressDriver("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateAddressDriver -> ICardSharedPreferencesDatasource.updateAddressDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateAddressDriver - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateAddressDriver("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateAddressDriver("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateBrand - Check return failure if have error in CardSharedPreferencesDatasource updateBrand`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateBrand("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateBrand",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateBrand("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateBrand -> ICardSharedPreferencesDatasource.updateBrand"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateBrand - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateBrand("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateBrand("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updatePlate - Check return failure if have error in CardSharedPreferencesDatasource updatePlate`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updatePlate("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updatePlate",
                    "-",
                    Exception()
                )
            )
            val result = repository.updatePlate("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updatePlate -> ICardSharedPreferencesDatasource.updatePlate"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updatePlate - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updatePlate("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updatePlate("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateDocumentDriver - Check return failure if have error in CardSharedPreferencesDatasource updateDocumentDriver`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateDocumentDriver("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateDocumentDriver",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateDocumentDriver("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateDocumentDriver -> ICardSharedPreferencesDatasource.updateDocumentDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateDocumentDriver - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateDocumentDriver("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateDocumentDriver("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateDocumentPassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource updateDocumentPassengerInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateDocumentPassengerInvolved("test", 1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateDocumentPassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateDocumentPassengerInvolved("test", 1, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateDocumentPassengerInvolved -> ICardSharedPreferencesDatasource.updateDocumentPassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateDocumentPassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateDocumentPassengerInvolved("test", 1, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateDocumentPassengerInvolved("test", 1, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateDocumentInvolved - Check return failure if have error in CardSharedPreferencesDatasource updateDocumentInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateDocumentInvolved("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateDocumentInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateDocumentInvolved("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateDocumentInvolved -> ICardSharedPreferencesDatasource.updateDocumentInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateDocumentInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateDocumentInvolved("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateDocumentInvolved("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateNameDriver - Check return failure if have error in CardSharedPreferencesDatasource updateNameDriver`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateNameDriver("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateNameDriver",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateNameDriver("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateNameDriver -> ICardSharedPreferencesDatasource.updateNameDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateNameDriver - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateNameDriver("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateNameDriver("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateNamePassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource updateNamePassengerInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateNamePassengerInvolved("test", 1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateNamePassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateNamePassengerInvolved("test", 1, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateNamePassengerInvolved -> ICardSharedPreferencesDatasource.updateNamePassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateNamePassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateNamePassengerInvolved("test", 1, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateNamePassengerInvolved("test", 1, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateNameInvolved - Check return failure if have error in CardSharedPreferencesDatasource updateNameInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateNameInvolved("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateNameInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateNameInvolved("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateNameInvolved -> ICardSharedPreferencesDatasource.updateNameInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateNameInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateNameInvolved("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateNameInvolved("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updateNameWitness - Check return failure if have error in CardSharedPreferencesDatasource updateNameWitness`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updateNameWitness("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updateNameWitness",
                    "-",
                    Exception()
                )
            )
            val result = repository.updateNameWitness("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updateNameWitness -> ICardSharedPreferencesDatasource.updateNameWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updateNameWitness - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updateNameWitness("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updateNameWitness("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updatePhoneDriver - Check return failure if have error in CardSharedPreferencesDatasource updatePhoneDriver`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updatePhoneDriver("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updatePhoneDriver",
                    "-",
                    Exception()
                )
            )
            val result = repository.updatePhoneDriver("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updatePhoneDriver -> ICardSharedPreferencesDatasource.updatePhoneDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updatePhoneDriver - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updatePhoneDriver("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updatePhoneDriver("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updatePhoneInvolved - Check return failure if have error in CardSharedPreferencesDatasource updatePhoneInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updatePhoneInvolved("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updatePhoneInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.updatePhoneInvolved("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updatePhoneInvolved -> ICardSharedPreferencesDatasource.updatePhoneInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updatePhoneInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updatePhoneInvolved("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updatePhoneInvolved("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updatePhoneWitness - Check return failure if have error in CardSharedPreferencesDatasource updatePhoneWitness`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updatePhoneWitness("test", 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updatePhoneWitness",
                    "-",
                    Exception()
                )
            )
            val result = repository.updatePhoneWitness("test", 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updatePhoneWitness -> ICardSharedPreferencesDatasource.updatePhoneWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updatePhoneWitness - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updatePhoneWitness("test", 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updatePhoneWitness("test", 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `updatePhonePassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource updatePhonePassengerInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.updatePhonePassengerInvolved("test", 1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.updatePhonePassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.updatePhonePassengerInvolved("test", 1, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IUpdateCardRepository.updatePhonePassengerInvolved -> ICardSharedPreferencesDatasource.updatePhonePassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `updatePhonePassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.updatePhonePassengerInvolved("test", 1, 1)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).updatePhonePassengerInvolved("test", 1, 1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

}
