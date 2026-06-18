package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.domain.entities.variable.Involved
import br.com.usinasantafe.cav.domain.entities.variable.VehicleInvolved
import br.com.usinasantafe.cav.domain.entities.variable.VehicleOwn
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IRecoverDataCardRepositoryTest {

    private val cardSharedPreferencesDatasource = mock<CardSharedPreferencesDatasource>()
    private val colabSharedPreferencesDatasource = mock<ColabSharedPreferencesDatasource>()
    private val repository = IRecoverDataCardRepository(
        cardSharedPreferencesDatasource = cardSharedPreferencesDatasource,
        colabSharedPreferencesDatasource = colabSharedPreferencesDatasource
    )

    @Test
    fun `getIdEquip - Check return failure if have error in CardSharedPreferencesDatasource getIdEquip`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getIdEquip(
                    1
                )
            ).thenReturn(
                resultFailure(
                    "ICardSharedPrefencesDatasource.getIdEquip",
                    "-",
                    Exception()
                )
            )
            val result = repository.getIdEquip(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getIdEquip -> ICardSharedPrefencesDatasource.getIdEquip"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getIdEquip - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getIdEquip(1)
            ).thenReturn(
                Result.success(10)
            )
            val result = repository.getIdEquip(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                10
            )
        }

    @Test
    fun `getIdEquipSecondary - Check return failure if have error in CardSharedPreferencesDatasource getIdEquipSecondary`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getIdEquipSecondary(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getIdEquipSecondary",
                    "-",
                    Exception()
                )
            )
            val result = repository.getIdEquipSecondary(1, 2)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getIdEquipSecondary -> ICardSharedPreferencesDatasource.getIdEquipSecondary"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getIdEquipSecondary - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getIdEquipSecondary(1, 2)
            ).thenReturn(
                Result.success(10)
            )
            val result = repository.getIdEquipSecondary(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                10
            )
        }

    @Test
    fun `getDetailEquip - Check return failure if have error in CardSharedPreferencesDatasource getDetailEquip`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailEquip(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getDetailEquip",
                    "-",
                    Exception()
                )
            )
            val result = repository.getDetailEquip(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getDetailEquip -> ICardSharedPreferencesDatasource.getDetailEquip"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getDetailEquip - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailEquip(1)
            ).thenReturn(
                Result.success("test")
            )
            val result = repository.getDetailEquip(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "test"
            )
        }

    @Test
    fun `getDetailEquip - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailEquip(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getDetailEquip(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getDetailEquipSecondary - Check return failure if have error in CardSharedPreferencesDatasource getDetailEquipSecondary`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailEquipSecondary(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getDetailEquipSecondary",
                    "-",
                    Exception()
                )
            )
            val result = repository.getDetailEquipSecondary(1, 2)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getDetailEquipSecondary -> ICardSharedPreferencesDatasource.getDetailEquipSecondary"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getDetailEquipSecondary - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailEquipSecondary(1, 2)
            ).thenReturn(
                Result.success("test")
            )
            val result = repository.getDetailEquipSecondary(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "test"
            )
        }

    @Test
    fun `getDetailEquipSecondary - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailEquipSecondary(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getDetailEquipSecondary(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getDetailColab - Check return failure if have error in CardSharedPreferencesDatasource getDetailColab`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailColab(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getDetailColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.getDetailColab(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getDetailColab -> ICardSharedPreferencesDatasource.getDetailColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getDetailColab - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailColab(1)
            ).thenReturn(
                Result.success("test")
            )
            val result = repository.getDetailColab(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "test"
            )
        }

    @Test
    fun `getDetailColab - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailColab(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getDetailColab(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getDetailPassengerColab - Check return failure if have error in CardSharedPreferencesDatasource getDetailPassengerColab`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailPassengerColab(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getDetailPassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.getDetailPassengerColab(1, 2)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getDetailPassengerColab -> ICardSharedPreferencesDatasource.getDetailPassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getDetailPassengerColab - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailPassengerColab(1, 2)
            ).thenReturn(
                Result.success("test")
            )
            val result = repository.getDetailPassengerColab(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "test"
            )
        }

    @Test
    fun `getDetailPassengerColab - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailPassengerColab(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getDetailPassengerColab(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getDetailVehicle - Check return failure if have error in CardSharedPreferencesDatasource getDetailVehicle`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailVehicle(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getDetailVehicle",
                    "-",
                    Exception()
                )
            )
            val result = repository.getDetailVehicle(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getDetailVehicle -> ICardSharedPreferencesDatasource.getDetailVehicle"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getDetailVehicle - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailVehicle(1)
            ).thenReturn(
                Result.success("test")
            )
            val result = repository.getDetailVehicle(1)
            assertEquals(
                result,
                Result.success("test")
            )
        }

    @Test
    fun `getDetailVehicle - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailVehicle(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getDetailVehicle(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getDetailDriver - Check return failure if have error in CardSharedPreferencesDatasource getDetailDriver`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailDriver(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getDetailDriver",
                    "-",
                    Exception()
                )
            )
            val result = repository.getDetailDriver(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getDetailDriver -> ICardSharedPreferencesDatasource.getDetailDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getDetailDriver - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailDriver(1)
            ).thenReturn(
                Result.success("test")
            )
            val result = repository.getDetailDriver(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "test"
            )
        }

    @Test
    fun `getDetailDriver - Check return correct if function execute successfully and return null`() =
        runTest{
            whenever(
                cardSharedPreferencesDatasource.getDetailDriver(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getDetailDriver(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getDetailPassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource getDetailPassengerInvolved`() =
        runTest{
            whenever(
                cardSharedPreferencesDatasource.getDetailPassengerInvolved(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getDetailPassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.getDetailPassengerInvolved(1, 2)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getDetailPassengerInvolved -> ICardSharedPreferencesDatasource.getDetailPassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getDetailPassengerInvolved - Check return correct if function execute successfully`() =
        runTest{
            whenever(
                cardSharedPreferencesDatasource.getDetailPassengerInvolved(1, 2)
            ).thenReturn(
                Result.success("test")
            )
            val result = repository.getDetailPassengerInvolved(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "test"
            )
        }

    @Test
    fun `getDetailPassengerInvolved - Check return correct if function execute successfully and return null`() =
        runTest{
            whenever(
                cardSharedPreferencesDatasource.getDetailPassengerInvolved(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getDetailPassengerInvolved(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getDetailInvolved - Check return failure if have error in CardSharedPreferencesDatasource getDetailInvolved`() =
        runTest{
            whenever(
                cardSharedPreferencesDatasource.getDetailInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getDetailInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.getDetailInvolved(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getDetailInvolved -> ICardSharedPreferencesDatasource.getDetailInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getDetailInvolved - Check return correct if function execute successfully`() =
        runTest{
            whenever(
                cardSharedPreferencesDatasource.getDetailInvolved(1)
            ).thenReturn(
                Result.success("test")
            )
            val result = repository.getDetailInvolved(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "test"
            )
        }

    @Test
    fun `getDetailInvolved - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDetailInvolved(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getDetailInvolved(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getDetailWitness - Check return failure if have error in CardSharedPreferencesDatasource getDetailWitness`() =
        runTest{
            whenever(
                cardSharedPreferencesDatasource.getDetailWitness(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getDetailWitness",
                    "-",
                    Exception()
                )
            )
            val result = repository.getDetailWitness(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getDetailWitness -> ICardSharedPreferencesDatasource.getDetailWitness"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getDetailWitness - Check return correct if function execute successfully`() =
        runTest{
            whenever(
                cardSharedPreferencesDatasource.getDetailWitness(1)
            ).thenReturn(
                Result.success("test")
            )
            val result = repository.getDetailWitness(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "test"
            )
        }

    @Test
    fun `getDetailWitness - Check return correct if function execute successfully and return null`() =
        runTest{
            whenever(
                cardSharedPreferencesDatasource.getDetailWitness(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getDetailWitness(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getRegColab - Check return failure if have error in CardSharedPreferencesDatasource getRegColab`() =
        runTest{
            whenever(
                cardSharedPreferencesDatasource.getRegColab(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getRegColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.getRegColab(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getRegColab -> ICardSharedPreferencesDatasource.getRegColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getRegColab - Check return correct if function execute successfully - Card`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getRegColab(1)
            ).thenReturn(
                Result.success(123456)
            )
            val result = repository.getRegColab(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                123456
            )
        }

    @Test
    fun `getStateColab - Check return failure if have error in CardSharedPreferencesDatasource getStateColab`() =
        runTest{
            whenever(
                cardSharedPreferencesDatasource.getStateColab(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getStateColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.getStateColab(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getStateColab -> ICardSharedPreferencesDatasource.getStateColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getStateColab - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getStateColab(1)
            ).thenReturn(
                Result.success(State.UNHARMED)
            )
            val result = repository.getStateColab(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                State.UNHARMED
            )
        }

    @Test
    fun `getStatePassengerColab - Check return failure if have error in CardSharedPreferencesDatasource getStatePassengerColab`() =
        runTest{
            whenever(
                cardSharedPreferencesDatasource.getStatePassengerColab(1, 1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getStatePassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.getStatePassengerColab(1, 1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getStatePassengerColab -> ICardSharedPreferencesDatasource.getStatePassengerColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getStatePassengerColab - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getStatePassengerColab(1, 1)
            ).thenReturn(
                Result.success(State.UNHARMED)
            )
            val result = repository.getStatePassengerColab(1, 1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                State.UNHARMED
            )
        }

    @Test
    fun `getStateWitness - Check return failure if have error in CardSharedPreferencesDatasource getStateWitness`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getStateWitness(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getStateWitness",
                    "-",
                    Exception()
                )
            )
            val result = repository.getStateWitness(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getStateWitness -> ICardSharedPreferencesDatasource.getStateWitness"
            )
            assertEquals(
            result.exceptionOrNull()!!.cause.toString(),
            "java.lang.Exception"
            )
        }

    @Test
    fun `getStateWitness - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getStateWitness(1)
            ).thenReturn(
                Result.success(State.UNHARMED)
            )
            val result = repository.getStateWitness(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                State.UNHARMED
            )
        }

    @Test
    fun `getStatePassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource getStatePassengerInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getStatePassengerInvolved(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getStatePassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.getStatePassengerInvolved(1, 2)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getStatePassengerInvolved -> ICardSharedPreferencesDatasource.getStatePassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getStatePassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getStatePassengerInvolved(1, 2)
            ).thenReturn(
                Result.success(State.UNHARMED)
            )
            val result = repository.getStatePassengerInvolved(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                State.UNHARMED
            )
        }

    @Test
    fun `getStateInvolved - Check return failure if have error in CardSharedPreferencesDatasource getStateInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getStateInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getStateInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.getStateInvolved(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getStateInvolved -> ICardSharedPreferencesDatasource.getStateInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getStateInvolved - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getStateInvolved(1)
            ).thenReturn(
                Result.success(State.UNHARMED)
            )
            val result = repository.getStateInvolved(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                State.UNHARMED
            )
        }
    @Test
    fun `getRegPassengerColab - Check return failure if have error in CardSharedPreferencesDatasource getRegPassengerColab`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getRegPassengerColab(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getRegPassengerColab",
                    "-",
                    Exception()
                )
            )

            val result = repository.getRegPassengerColab(1, 2)

            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                "IRecoverDataCardRepository.getRegPassengerColab -> ICardSharedPreferencesDatasource.getRegPassengerColab",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getRegPassengerColab - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getRegPassengerColab(1, 2)
            ).thenReturn(
                Result.success(123456L)
            )
            val result = repository.getRegPassengerColab(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                123456L
            )
        }

    @Test
    fun `getStateDriver - Check return failure if have error in CardSharedPreferencesDatasource getStateDriver`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getStateDriver(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getStateDriver",
                    "-",
                    Exception()
                )
            )
            val result = repository.getStateDriver(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                "IRecoverDataCardRepository.getStateDriver -> ICardSharedPreferencesDatasource.getStateDriver",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getStateDriver - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getStateDriver(1)
            ).thenReturn(
                Result.success(State.UNHARMED)
            )

            val result = repository.getStateDriver(1)

            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                State.UNHARMED
            )
        }

    @Test
    fun `getAddressPassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource getAddressPassengerInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getAddressPassengerInvolved(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getAddressPassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.getAddressPassengerInvolved(1, 2)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getAddressPassengerInvolved -> ICardSharedPreferencesDatasource.getAddressPassengerInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getAddressPassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getAddressPassengerInvolved(1, 2)
            ).thenReturn(
                Result.success("Rua Teste")
            )
            val result = repository.getAddressPassengerInvolved(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Rua Teste"
            )
        }

    @Test
    fun `getAddressPassengerInvolved - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getAddressPassengerInvolved(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getAddressPassengerInvolved(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getAddressDriver - Check return failure if have error in CardSharedPreferencesDatasource getAddressDriver`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getAddressDriver(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getAddressDriver",
                    "-",
                    Exception()
                )
            )

            val result = repository.getAddressDriver(1)

            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getAddressDriver -> ICardSharedPreferencesDatasource.getAddressDriver",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getAddressDriver - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getAddressDriver(1)
            ).thenReturn(
                Result.success("Rua Teste")
            )
            val result = repository.getAddressDriver(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                "Rua Teste",
                result.getOrNull()!!
            )
        }

    @Test
    fun `getAddressDriver - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getAddressDriver(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getAddressDriver(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getAddressInvolved - Check return failure if have error in CardSharedPreferencesDatasource getAddressInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getAddressInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getAddressInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.getAddressInvolved(1)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getAddressInvolved -> ICardSharedPreferencesDatasource.getAddressInvolved",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getAddressInvolved - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getAddressInvolved(1)
            ).thenReturn(
                Result.success("Rua Teste")
            )
            val result = repository.getAddressInvolved(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                "Rua Teste",
                result.getOrNull()!!
            )
        }

    @Test
    fun `getAddressInvolved - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getAddressInvolved(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getAddressInvolved(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getBrand - Check return failure if have error in CardSharedPreferencesDatasource getBrand`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getBrand(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getBrand",
                    "-",
                    Exception()
                )
            )
            val result = repository.getBrand(1)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getBrand -> ICardSharedPreferencesDatasource.getBrand",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getBrand - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getBrand(1)
            ).thenReturn(
                Result.success("FORD")
            )
            val result = repository.getBrand(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                "FORD",
                result.getOrNull()
            )
        }

    @Test
    fun `getBrand - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getBrand(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getBrand(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                null,
                result.getOrNull()
            )
        }
    @Test
    fun `getPlate - Check return failure if have error in CardSharedPreferencesDatasource getPlate`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getPlate(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getPlate",
                    "-",
                    Exception()
                )
            )

            val result = repository.getPlate(1)

            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getPlate -> ICardSharedPreferencesDatasource.getPlate",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getPlate - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getPlate(1)
            ).thenReturn(
                Result.success("ABC1234")
            )

            val result = repository.getPlate(1)

            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                "ABC1234",
                result.getOrNull()!!
            )
        }

    @Test
    fun `getPlate - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getPlate(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getPlate(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                null,
                result.getOrNull()
            )
        }

    @Test
    fun `getDocumentDriver - Check return failure if have error in CardSharedPreferencesDatasource getDocumentDriver`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDocumentDriver(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getDocumentDriver",
                    "-",
                    Exception()
                )
            )

            val result = repository.getDocumentDriver(1)

            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getDocumentDriver -> ICardSharedPreferencesDatasource.getDocumentDriver",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getDocumentDriver - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDocumentDriver(1)
            ).thenReturn(
                Result.success("123456789")
            )
            val result = repository.getDocumentDriver(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                "123456789",
                result.getOrNull()
            )
        }

    @Test
    fun `getDocumentDriver - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDocumentDriver(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getDocumentDriver(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                null,
                result.getOrNull()
            )
        }

    @Test
    fun `getDocumentPassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource getDocumentPassengerInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDocumentPassengerInvolved(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getDocumentPassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.getDocumentPassengerInvolved(1, 2)
            assertEquals(
                true,
                result.isFailure)
            assertEquals(
                "IRecoverDataCardRepository.getDocumentPassengerInvolved -> ICardSharedPreferencesDatasource.getDocumentPassengerInvolved",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getDocumentPassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDocumentPassengerInvolved(1, 2)
            ).thenReturn(
                Result.success("123456789")
            )
            val result = repository.getDocumentPassengerInvolved(1, 2)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                "123456789",
                result.getOrNull()!!
            )
        }

    @Test
    fun `getDocumentPassengerInvolved - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDocumentPassengerInvolved(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getDocumentPassengerInvolved(1, 2)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                null,
                result.getOrNull()
            )
        }

    @Test
    fun `getNameDriver - Check return failure if have error in CardSharedPreferencesDatasource getNameDriver`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getNameDriver(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getNameDriver",
                    "-",
                    Exception()
                )
            )
            val result = repository.getNameDriver(1)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getNameDriver -> ICardSharedPreferencesDatasource.getNameDriver",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getNameDriver - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getNameDriver(1)
            ).thenReturn(
                Result.success("JOAO")
            )
            val result = repository.getNameDriver(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                "JOAO",
                result.getOrNull()!!
            )
        }

    @Test
    fun `getNameDriver - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getNameDriver(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getNameDriver(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                null,
                result.getOrNull()
            )
        }

    @Test
    fun `getNamePassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource getNamePassengerInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getNamePassengerInvolved(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getNamePassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.getNamePassengerInvolved(1, 2)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getNamePassengerInvolved -> ICardSharedPreferencesDatasource.getNamePassengerInvolved",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getNamePassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getNamePassengerInvolved(1, 2)
            ).thenReturn(
                Result.success("MARIA")
            )
            val result = repository.getNamePassengerInvolved(1, 2)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                "MARIA",
                result.getOrNull()!!
            )
        }

    @Test
    fun `getNamePassengerInvolved - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getNamePassengerInvolved(1, 2)
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getNamePassengerInvolved(1, 2)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                null,
                result.getOrNull()
            )
        }

    @Test
    fun `listIdEquipSecondary - Check return failure if have error in CardSharedPreferencesDatasource listIdEquipSecondary`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listIdEquipSecondary(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.listIdEquipSecondary",
                    "-",
                    Exception()
                )
            )
            val result = repository.listIdEquipSecondary(1)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.listIdEquipSecondary -> ICardSharedPreferencesDatasource.listIdEquipSecondary",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `listIdEquipSecondary - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(1, 2, 3)
            whenever(
                cardSharedPreferencesDatasource.listIdEquipSecondary(1)
            ).thenReturn(
                Result.success(list)
            )
            val result = repository.listIdEquipSecondary(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                list,
                result.getOrNull()!!
            )
        }
    @Test
    fun `listPassengerColab - Check return failure if have error in CardSharedPreferencesDatasource listPassengerColab`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listPassengerColab(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.listPassengerColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.listPassengerColab(1)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.listPassengerColab -> ICardSharedPreferencesDatasource.listPassengerColab",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `listPassengerColab - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(mock<ColabCard>())
            whenever(
                cardSharedPreferencesDatasource.listPassengerColab(1)
            ).thenReturn(
                Result.success(list)
            )
            val result = repository.listPassengerColab(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                list,
                result.getOrNull()!!
            )
        }

    @Test
    fun `listPassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource listPassengerInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listPassengerInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.listPassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.listPassengerInvolved(1)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.listPassengerInvolved -> ICardSharedPreferencesDatasource.listPassengerInvolved",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `listPassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(mock<Involved>())
            whenever(
                cardSharedPreferencesDatasource.listPassengerInvolved(1)
            ).thenReturn(
                Result.success(list)
            )
            val result = repository.listPassengerInvolved(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                list,
                result.getOrNull()!!
            )
        }

    @Test
    fun `listInvolved - Check return failure if have error in CardSharedPreferencesDatasource listInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listInvolved()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.listInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.listInvolved()
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.listInvolved -> ICardSharedPreferencesDatasource.listInvolved",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `listInvolved - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(mock<Involved>())
            whenever(
                cardSharedPreferencesDatasource.listInvolved()
            ).thenReturn(
                Result.success(list)
            )
            val result = repository.listInvolved()
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                list,
                result.getOrNull()!!
            )
        }

    @Test
    fun `listWitness - Check return failure if have error in CardSharedPreferencesDatasource listWitness`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listWitness()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.listWitness",
                    "-",
                    Exception()
                )
            )
            val result = repository.listWitness()
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.listWitness -> ICardSharedPreferencesDatasource.listWitness",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `listWitness - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(mock<Involved>())
            whenever(
                cardSharedPreferencesDatasource.listWitness()
            ).thenReturn(
                Result.success(list)
            )
            val result = repository.listWitness()
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                list,
                result.getOrNull()!!
            )
        }

    @Test
    fun `getDocumentInvolved - Check return failure if have error in CardSharedPreferencesDatasource getDocumentInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDocumentInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getDocumentInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.getDocumentInvolved(1)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getDocumentInvolved -> ICardSharedPreferencesDatasource.getDocumentInvolved",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getDocumentInvolved - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDocumentInvolved(1)
            ).thenReturn(
                Result.success("123456")
            )
            val result = repository.getDocumentInvolved(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                "123456",
                result.getOrNull()!!
            )
        }

    @Test
    fun `getDocumentWitness - Check return failure if have error in CardSharedPreferencesDatasource getDocumentWitness`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDocumentWitness(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getDocumentWitness",
                    "-",
                    Exception()
                )
            )
            val result = repository.getDocumentWitness(1)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getDocumentWitness -> ICardSharedPreferencesDatasource.getDocumentWitness",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getDocumentWitness - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getDocumentWitness(1)
            ).thenReturn(
                Result.success("123456")
            )
            val result = repository.getDocumentWitness(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                "123456",
                result.getOrNull()!!
            )
        }

    @Test
    fun `getNameInvolved - Check return failure if have error in CardSharedPreferencesDatasource getNameInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getNameInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getNameInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.getNameInvolved(1)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getNameInvolved -> ICardSharedPreferencesDatasource.getNameInvolved",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getNameInvolved - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getNameInvolved(1)
            ).thenReturn(
                Result.success("JOAO")
            )
            val result = repository.getNameInvolved(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                "JOAO",
                result.getOrNull()
            )
        }

    @Test
    fun `getNameWitness - Check return failure if have error in CardSharedPreferencesDatasource getNameWitness`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getNameWitness(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getNameWitness",
                    "-",
                    Exception()
                )
            )
            val result = repository.getNameWitness(1)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getNameWitness -> ICardSharedPreferencesDatasource.getNameWitness",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getNameWitness - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getNameWitness(1)
            ).thenReturn(
                Result.success("MARIA")
            )
            val result = repository.getNameWitness(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                "MARIA",
                result.getOrNull()!!
            )
        }

    @Test
    fun `getPhoneDriver - Check return failure if have error in CardSharedPreferencesDatasource getPhoneDriver`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getPhoneDriver(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getPhoneDriver",
                    "-",
                    Exception()
                )
            )
            val result = repository.getPhoneDriver(1)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getPhoneDriver -> ICardSharedPreferencesDatasource.getPhoneDriver",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getPhoneDriver - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getPhoneDriver(1)
            ).thenReturn(
                Result.success("999999999")
            )
            val result = repository.getPhoneDriver(1)
            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                "999999999",
                result.getOrNull()!!
            )
        }

    @Test
    fun `getPhoneInvolved - Check return failure if have error in CardSharedPreferencesDatasource getPhoneInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getPhoneInvolved(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getPhoneInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.getPhoneInvolved(1)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getPhoneInvolved -> ICardSharedPreferencesDatasource.getPhoneInvolved",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getPhoneInvolved - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getPhoneInvolved(1)
            ).thenReturn(
                Result.success("999999999")
            )
            val result = repository.getPhoneInvolved(1)
            assertEquals(
                "999999999",
                result.getOrNull()!!
            )
        }

    @Test
    fun `getPhoneWitness - Check return failure if have error in CardSharedPreferencesDatasource getPhoneWitness`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getPhoneWitness(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getPhoneWitness",
                    "-",
                    Exception()
                )
            )
            val result = repository.getPhoneWitness(1)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getPhoneWitness -> ICardSharedPreferencesDatasource.getPhoneWitness",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getPhoneWitness - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getPhoneWitness(1)
            ).thenReturn(
                Result.success("999999999")
            )
            val result = repository.getPhoneWitness(1)
            assertEquals(
                "999999999",
                result.getOrNull()!!
            )
        }

    @Test
    fun `getPhonePassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource getPhonePassengerInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getPhonePassengerInvolved(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getPhonePassengerInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.getPhonePassengerInvolved(1, 2)
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.getPhonePassengerInvolved -> ICardSharedPreferencesDatasource.getPhonePassengerInvolved",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `getPhonePassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getPhonePassengerInvolved(1, 2)
            ).thenReturn(
                Result.success("999999999")
            )
            val result = repository.getPhonePassengerInvolved(1, 2)
            assertEquals(
                "999999999",
                result.getOrNull()!!
            )
        }

    @Test
    fun `listVehicleOwn - Check return failure if have error in CardSharedPreferencesDatasource listVehicleOwn`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listVehicleOwn()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.listVehicleOwn",
                    "-",
                    Exception()
                )
            )
            val result = repository.listVehicleOwn()
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.listVehicleOwn -> ICardSharedPreferencesDatasource.listVehicleOwn",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `listVehicleOwn - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(mock<VehicleOwn>())
            whenever(
                cardSharedPreferencesDatasource.listVehicleOwn()
            ).thenReturn(
                Result.success(list)
            )
            val result = repository.listVehicleOwn()
            assertEquals(
                list,
                result.getOrNull()!!
            )
        }

    @Test
    fun `listVehicleInvolved - Check return failure if have error in CardSharedPreferencesDatasource listVehicleInvolved`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listVehicleInvolved()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.listVehicleInvolved",
                    "-",
                    Exception()
                )
            )
            val result = repository.listVehicleInvolved()
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IRecoverDataCardRepository.listVehicleInvolved -> ICardSharedPreferencesDatasource.listVehicleInvolved",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.Exception",
                result.exceptionOrNull()!!.cause.toString()
            )
        }

    @Test
    fun `listVehicleInvolved - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(mock<VehicleInvolved>())
            whenever(
                cardSharedPreferencesDatasource.listVehicleInvolved()
            )
                .thenReturn(
                    Result.success(list)
                )
            val result = repository.listVehicleInvolved()
            assertEquals(
                list,
                result.getOrNull()!!
            )
        }

    @Test
    fun `getRegColab - Check return failure if have error in ColabSharedPreferencesDatasource getRegColab`() =
        runTest {
            whenever(
                colabSharedPreferencesDatasource.getRegColab()
            ).thenReturn(
                resultFailure(
                    "IColabSharedPreferencesDatasource.getRegColab",
                    "-",
                    Exception()
                )
            )
            val result = repository.getRegColab()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getRegColab -> IColabSharedPreferencesDatasource.getRegColab"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getRegColab - Check return correct if function execute successfully - Colab`() =
        runTest {
            whenever(
                colabSharedPreferencesDatasource.getRegColab()
            ).thenReturn(
                Result.success(123456)
            )
            val result = repository.getRegColab()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                123456
            )
        }

    @Test
    fun `getState - Check return failure if have error in ColabSharedPreferencesDatasource getState`() =
        runTest {
            whenever(
                colabSharedPreferencesDatasource.getState()
            ).thenReturn(
                resultFailure(
                    "IColabSharedPreferencesDatasource.getState",
                    "-",
                    Exception()
                )
            )
            val result = repository.getStateColab()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getState -> IColabSharedPreferencesDatasource.getState"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getState - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                colabSharedPreferencesDatasource.getState()
            ).thenReturn(
                Result.success(State.UNHARMED)
            )
            val result = repository.getStateColab()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                State.UNHARMED
            )
        }

    @Test
    fun `getDetail - Check return failure if have error in ColabSharedPreferencesDatasource getDetail`() =
        runTest {
            whenever(
                colabSharedPreferencesDatasource.getDetail()
            ).thenReturn(
                resultFailure(
                    "IColabSharedPreferencesDatasource.getDetail",
                    "-",
                    Exception()
                )
            )
            val result = repository.getDetailColab()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IRecoverDataCardRepository.getDetail -> IColabSharedPreferencesDatasource.getDetail"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getDetail - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(
                colabSharedPreferencesDatasource.getDetail()
            ).thenReturn(
                Result.success(null)
            )
            val result = repository.getDetailColab()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                null
            )
        }

    @Test
    fun `getDetail - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                colabSharedPreferencesDatasource.getDetail()
            ).thenReturn(
                Result.success("test")
            )
            val result = repository.getDetailColab()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "test"
            )
        }

}