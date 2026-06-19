package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.domain.entities.variable.Involved
import br.com.usinasantafe.cav.domain.entities.variable.Vehicle
import br.com.usinasantafe.cav.domain.entities.variable.VehicleInvolved
import br.com.usinasantafe.cav.domain.entities.variable.VehicleOwn
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InvolvedSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
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
    private val equipSharedPreferencesDatasource = mock<EquipSharedPreferencesDatasource>()
    private val involvedSharedPreferencesDatasource = mock<InvolvedSharedPreferencesDatasource>()
    private val vehicleSharedPreferencesDatasource = mock<VehicleSharedPreferencesDatasource>()
    private val repository = IRecoverDataCardRepository(
        cardSharedPreferencesDatasource = cardSharedPreferencesDatasource,
        colabSharedPreferencesDatasource = colabSharedPreferencesDatasource,
        equipSharedPreferencesDatasource = equipSharedPreferencesDatasource,
        involvedSharedPreferencesDatasource = involvedSharedPreferencesDatasource,
        vehicleSharedPreferencesDatasource = vehicleSharedPreferencesDatasource
    )

    @Test
    fun `getIdEquip - Check return failure if have error in CardSharedPreferencesDatasource getIdEquip`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getIdEquip(1)).thenReturn(
                resultFailure("ICardSharedPrefencesDatasource.getIdEquip", "-", Exception())
            )
            val result = repository.getIdEquip(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getIdEquip -> ICardSharedPrefencesDatasource.getIdEquip")
        }

    @Test
    fun `getIdEquip - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getIdEquip(1)).thenReturn(Result.success(10))
            val result = repository.getIdEquip(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, 10)
        }

    @Test
    fun `getIdEquipSecondary - Check return failure if have error in CardSharedPreferencesDatasource getIdEquipSecondary`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getIdEquipSecondary(1, 2)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getIdEquipSecondary", "-", Exception())
            )
            val result = repository.getIdEquipSecondary(1, 2)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getIdEquipSecondary -> ICardSharedPreferencesDatasource.getIdEquipSecondary")
        }

    @Test
    fun `getIdEquipSecondary - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getIdEquipSecondary(1, 2)).thenReturn(Result.success(10))
            val result = repository.getIdEquipSecondary(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, 10)
        }

    @Test
    fun `getDetailEquip(id) - Check return failure if have error in CardSharedPreferencesDatasource getDetailEquip`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailEquip(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getDetailEquip", "-", Exception())
            )
            val result = repository.getDetailEquip(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailEquip -> ICardSharedPreferencesDatasource.getDetailEquip")
        }

    @Test
    fun `getDetailEquip(id) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailEquip(1)).thenReturn(Result.success("test"))
            val result = repository.getDetailEquip(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, "test")
        }

    @Test
    fun `getDetailEquip(id) - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailEquip(1)).thenReturn(Result.success(null))
            val result = repository.getDetailEquip(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), null)
        }

    @Test
    fun `getDetailEquipSecondary - Check return failure if have error in CardSharedPreferencesDatasource getDetailEquipSecondary`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailEquipSecondary(1, 2)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getDetailEquipSecondary", "-", Exception())
            )
            val result = repository.getDetailEquipSecondary(1, 2)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailEquipSecondary -> ICardSharedPreferencesDatasource.getDetailEquipSecondary")
        }

    @Test
    fun `getDetailEquipSecondary - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailEquipSecondary(1, 2)).thenReturn(Result.success("test"))
            val result = repository.getDetailEquipSecondary(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, "test")
        }

    @Test
    fun `getDetailEquipSecondary - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailEquipSecondary(1, 2)).thenReturn(Result.success(null))
            val result = repository.getDetailEquipSecondary(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), null)
        }

    @Test
    fun `getDetailColab(id) - Check return failure if have error in CardSharedPreferencesDatasource getDetailColab`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailColab(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getDetailColab", "-", Exception())
            )
            val result = repository.getDetailColab(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailColab -> ICardSharedPreferencesDatasource.getDetailColab")
        }

    @Test
    fun `getDetailColab(id) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailColab(1)).thenReturn(Result.success("test"))
            val result = repository.getDetailColab(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, "test")
        }

    @Test
    fun `getDetailColab(id) - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailColab(1)).thenReturn(Result.success(null))
            val result = repository.getDetailColab(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), null)
        }

    @Test
    fun `getDetailPassengerColab - Check return failure if have error in CardSharedPreferencesDatasource getDetailPassengerColab`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailPassengerColab(1, 2)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getDetailPassengerColab", "-", Exception())
            )
            val result = repository.getDetailPassengerColab(1, 2)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailPassengerColab -> ICardSharedPreferencesDatasource.getDetailPassengerColab")
        }

    @Test
    fun `getDetailPassengerColab - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailPassengerColab(1, 2)).thenReturn(Result.success("test"))
            val result = repository.getDetailPassengerColab(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, "test")
        }

    @Test
    fun `getDetailPassengerColab - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailPassengerColab(1, 2)).thenReturn(Result.success(null))
            val result = repository.getDetailPassengerColab(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), null)
        }

    @Test
    fun `getDetailVehicle(idMain) - Check return failure if have error in CardSharedPreferencesDatasource getDetailVehicle`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailVehicle(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getDetailVehicle", "-", Exception())
            )
            val result = repository.getDetailVehicle(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailVehicle -> ICardSharedPreferencesDatasource.getDetailVehicle")
        }

    @Test
    fun `getDetailVehicle(idMain) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailVehicle(1)).thenReturn(Result.success("test"))
            val result = repository.getDetailVehicle(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, "test")
        }

    @Test
    fun `getDetailVehicle(idMain) - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailVehicle(1)).thenReturn(Result.success(null))
            val result = repository.getDetailVehicle(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), null)
        }

    @Test
    fun `getDetailDriver - Check return failure if have error in CardSharedPreferencesDatasource getDetailDriver`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailDriver(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getDetailDriver", "-", Exception())
            )
            val result = repository.getDetailDriver(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailDriver -> ICardSharedPreferencesDatasource.getDetailDriver")
        }

    @Test
    fun `getDetailDriver - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailDriver(1)).thenReturn(Result.success("test"))
            val result = repository.getDetailDriver(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, "test")
        }

    @Test
    fun `getDetailDriver - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailDriver(1)).thenReturn(Result.success(null))
            val result = repository.getDetailDriver(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), null)
        }

    @Test
    fun `getDetailPassengerInvolved(idMain, idSecondary) - Check return failure if have error in CardSharedPreferencesDatasource getDetailPassengerInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailPassengerInvolved(1, 2)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getDetailPassengerInvolved", "-", Exception())
            )
            val result = repository.getDetailPassengerInvolved(1, 2)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailPassengerInvolved -> ICardSharedPreferencesDatasource.getDetailPassengerInvolved")
        }

    @Test
    fun `getDetailPassengerInvolved(idMain, idSecondary) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailPassengerInvolved(1, 2)).thenReturn(Result.success("test"))
            val result = repository.getDetailPassengerInvolved(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, "test")
        }

    @Test
    fun `getDetailPassengerInvolved(idMain, idSecondary) - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailPassengerInvolved(1, 2)).thenReturn(Result.success(null))
            val result = repository.getDetailPassengerInvolved(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), null)
        }

    @Test
    fun `getDetailInvolved(idMain) - Check return failure if have error in CardSharedPreferencesDatasource getDetailInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailInvolved(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getDetailInvolved", "-", Exception())
            )
            val result = repository.getDetailInvolved(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailInvolved -> ICardSharedPreferencesDatasource.getDetailInvolved")
        }

    @Test
    fun `getDetailInvolved(idMain) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailInvolved(1)).thenReturn(Result.success("test"))
            val result = repository.getDetailInvolved(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, "test")
        }

    @Test
    fun `getDetailInvolved(idMain) - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailInvolved(1)).thenReturn(Result.success(null))
            val result = repository.getDetailInvolved(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), null)
        }

    @Test
    fun `getDetailWitness - Check return failure if have error in CardSharedPreferencesDatasource getDetailWitness`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailWitness(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getDetailWitness", "-", Exception())
            )
            val result = repository.getDetailWitness(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailWitness -> ICardSharedPreferencesDatasource.getDetailWitness")
        }

    @Test
    fun `getDetailWitness - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailWitness(1)).thenReturn(Result.success("test"))
            val result = repository.getDetailWitness(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, "test")
        }

    @Test
    fun `getDetailWitness - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDetailWitness(1)).thenReturn(Result.success(null))
            val result = repository.getDetailWitness(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), null)
        }

    @Test
    fun `getRegColab(id) - Check return failure if have error in CardSharedPreferencesDatasource getRegColab`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getRegColab(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getRegColab", "-", Exception())
            )
            val result = repository.getRegColab(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getRegColab -> ICardSharedPreferencesDatasource.getRegColab")
        }

    @Test
    fun `getRegColab(id) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getRegColab(1)).thenReturn(Result.success(123456L))
            val result = repository.getRegColab(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, 123456L)
        }

    @Test
    fun `getRegPassengerColab - Check return failure if have error in CardSharedPreferencesDatasource getRegPassengerColab`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getRegPassengerColab(1, 2)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getRegPassengerColab", "-", Exception())
            )
            val result = repository.getRegPassengerColab(1, 2)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getRegPassengerColab -> ICardSharedPreferencesDatasource.getRegPassengerColab")
        }

    @Test
    fun `getRegPassengerColab - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getRegPassengerColab(1, 2)).thenReturn(Result.success(123456L))
            val result = repository.getRegPassengerColab(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, 123456L)
        }

    @Test
    fun `getStateColab(id) - Check return failure if have error in CardSharedPreferencesDatasource getStateColab`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getStateColab(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getStateColab", "-", Exception())
            )
            val result = repository.getStateColab(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getStateColab -> ICardSharedPreferencesDatasource.getStateColab")
        }

    @Test
    fun `getStateColab(id) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getStateColab(1)).thenReturn(Result.success(State.UNHARMED))
            val result = repository.getStateColab(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, State.UNHARMED)
        }

    @Test
    fun `getStatePassengerColab - Check return failure if have error in CardSharedPreferencesDatasource getStatePassengerColab`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getStatePassengerColab(1, 2)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getStatePassengerColab", "-", Exception())
            )
            val result = repository.getStatePassengerColab(1, 2)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getStatePassengerColab -> ICardSharedPreferencesDatasource.getStatePassengerColab")
        }

    @Test
    fun `getStatePassengerColab - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getStatePassengerColab(1, 2)).thenReturn(Result.success(State.UNHARMED))
            val result = repository.getStatePassengerColab(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, State.UNHARMED)
        }

    @Test
    fun `getStateWitness(id) - Check return failure if have error in CardSharedPreferencesDatasource getStateWitness`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getStateWitness(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getStateWitness", "-", Exception())
            )
            val result = repository.getStateWitness(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getStateWitness -> ICardSharedPreferencesDatasource.getStateWitness")
        }

    @Test
    fun `getStateWitness(id) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getStateWitness(1)).thenReturn(Result.success(State.UNHARMED))
            val result = repository.getStateWitness(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, State.UNHARMED)
        }

    @Test
    fun `getStatePassengerInvolved(idMain, idSecondary) - Check return failure if have error in CardSharedPreferencesDatasource getStatePassengerInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getStatePassengerInvolved(1, 2)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getStatePassengerInvolved", "-", Exception())
            )
            val result = repository.getStatePassengerInvolved(1, 2)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getStatePassengerInvolved -> ICardSharedPreferencesDatasource.getStatePassengerInvolved")
        }

    @Test
    fun `getStatePassengerInvolved(idMain, idSecondary) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getStatePassengerInvolved(1, 2)).thenReturn(Result.success(State.UNHARMED))
            val result = repository.getStatePassengerInvolved(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, State.UNHARMED)
        }

    @Test
    fun `getStateInvolved(id) - Check return failure if have error in CardSharedPreferencesDatasource getStateInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getStateInvolved(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getStateInvolved", "-", Exception())
            )
            val result = repository.getStateInvolved(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getStateInvolved -> ICardSharedPreferencesDatasource.getStateInvolved")
        }

    @Test
    fun `getStateInvolved(id) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getStateInvolved(1)).thenReturn(Result.success(State.UNHARMED))
            val result = repository.getStateInvolved(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, State.UNHARMED)
        }

    @Test
    fun `getStateDriver(id) - Check return failure if have error in CardSharedPreferencesDatasource getStateDriver`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getStateDriver(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getStateDriver", "-", Exception())
            )
            val result = repository.getStateDriver(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getStateDriver -> ICardSharedPreferencesDatasource.getStateDriver")
        }

    @Test
    fun `getStateDriver(id) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getStateDriver(1)).thenReturn(Result.success(State.UNHARMED))
            val result = repository.getStateDriver(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull()!!, State.UNHARMED)
        }

    @Test
    fun `getAddressPassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource getAddressPassengerInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getAddressPassengerInvolved(1, 2)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getAddressPassengerInvolved", "-", Exception())
            )
            val result = repository.getAddressPassengerInvolved(1, 2)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getAddressPassengerInvolved -> ICardSharedPreferencesDatasource.getAddressPassengerInvolved")
        }

    @Test
    fun `getAddressPassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getAddressPassengerInvolved(1, 2)).thenReturn(Result.success("address"))
            val result = repository.getAddressPassengerInvolved(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "address")
        }

    @Test
    fun `getAddressDriver - Check return failure if have error in CardSharedPreferencesDatasource getAddressDriver`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getAddressDriver(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getAddressDriver", "-", Exception())
            )
            val result = repository.getAddressDriver(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getAddressDriver -> ICardSharedPreferencesDatasource.getAddressDriver")
        }

    @Test
    fun `getAddressDriver - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getAddressDriver(1)).thenReturn(Result.success("address"))
            val result = repository.getAddressDriver(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "address")
        }

    @Test
    fun `getAddressInvolved(idMain) - Check return failure if have error in CardSharedPreferencesDatasource getAddressInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getAddressInvolved(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getAddressInvolved", "-", Exception())
            )
            val result = repository.getAddressInvolved(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getAddressInvolved -> ICardSharedPreferencesDatasource.getAddressInvolved")
        }

    @Test
    fun `getAddressInvolved(idMain) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getAddressInvolved(1)).thenReturn(Result.success("address"))
            val result = repository.getAddressInvolved(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "address")
        }

    @Test
    fun `getBrand - Check return failure if have error in CardSharedPreferencesDatasource getBrand`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getBrand(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getBrand", "-", Exception())
            )
            val result = repository.getBrand(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getBrand -> ICardSharedPreferencesDatasource.getBrand")
        }

    @Test
    fun `getBrand - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getBrand(1)).thenReturn(Result.success("brand"))
            val result = repository.getBrand(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "brand")
        }

    @Test
    fun `getPlate - Check return failure if have error in CardSharedPreferencesDatasource getPlate`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getPlate(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getPlate", "-", Exception())
            )
            val result = repository.getPlate(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getPlate -> ICardSharedPreferencesDatasource.getPlate")
        }

    @Test
    fun `getPlate - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getPlate(1)).thenReturn(Result.success("plate"))
            val result = repository.getPlate(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "plate")
        }

    @Test
    fun `getDocumentDriver - Check return failure if have error in CardSharedPreferencesDatasource getDocumentDriver`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDocumentDriver(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getDocumentDriver", "-", Exception())
            )
            val result = repository.getDocumentDriver(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDocumentDriver -> ICardSharedPreferencesDatasource.getDocumentDriver")
        }

    @Test
    fun `getDocumentDriver - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDocumentDriver(1)).thenReturn(Result.success("document"))
            val result = repository.getDocumentDriver(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "document")
        }

    @Test
    fun `getDocumentPassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource getDocumentPassengerInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDocumentPassengerInvolved(1, 2)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getDocumentPassengerInvolved", "-", Exception())
            )
            val result = repository.getDocumentPassengerInvolved(1, 2)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDocumentPassengerInvolved -> ICardSharedPreferencesDatasource.getDocumentPassengerInvolved")
        }

    @Test
    fun `getDocumentPassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDocumentPassengerInvolved(1, 2)).thenReturn(Result.success("document"))
            val result = repository.getDocumentPassengerInvolved(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "document")
        }

    @Test
    fun `getNameDriver - Check return failure if have error in CardSharedPreferencesDatasource getNameDriver`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getNameDriver(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getNameDriver", "-", Exception())
            )
            val result = repository.getNameDriver(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getNameDriver -> ICardSharedPreferencesDatasource.getNameDriver")
        }

    @Test
    fun `getNameDriver - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getNameDriver(1)).thenReturn(Result.success("name"))
            val result = repository.getNameDriver(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "name")
        }

    @Test
    fun `getNamePassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource getNamePassengerInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getNamePassengerInvolved(1, 2)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getNamePassengerInvolved", "-", Exception())
            )
            val result = repository.getNamePassengerInvolved(1, 2)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getNamePassengerInvolved -> ICardSharedPreferencesDatasource.getNamePassengerInvolved")
        }

    @Test
    fun `getNamePassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getNamePassengerInvolved(1, 2)).thenReturn(Result.success("name"))
            val result = repository.getNamePassengerInvolved(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "name")
        }

    @Test
    fun `listIdEquipSecondary(idMain) - Check return failure if have error in CardSharedPreferencesDatasource listIdEquipSecondary`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.listIdEquipSecondary(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.listIdEquipSecondary", "-", Exception())
            )
            val result = repository.listIdEquipSecondary(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.listIdEquipSecondary -> ICardSharedPreferencesDatasource.listIdEquipSecondary")
        }

    @Test
    fun `listIdEquipSecondary(idMain) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.listIdEquipSecondary(1)).thenReturn(Result.success(listOf(1, 2)))
            val result = repository.listIdEquipSecondary(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), listOf(1, 2))
        }

    @Test
    fun `listPassengerColab - Check return failure if have error in CardSharedPreferencesDatasource listPassengerColab`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.listPassengerColab(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.listPassengerColab", "-", Exception())
            )
            val result = repository.listPassengerColab(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.listPassengerColab -> ICardSharedPreferencesDatasource.listPassengerColab")
        }

    @Test
    fun `listPassengerColab - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(ColabCard(reg = 123L))
            whenever(cardSharedPreferencesDatasource.listPassengerColab(1)).thenReturn(Result.success(list))
            val result = repository.listPassengerColab(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), list)
        }

    @Test
    fun `listPassengerInvolved - Check return failure if have error in CardSharedPreferencesDatasource listPassengerInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.listPassengerInvolved(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.listPassengerInvolved", "-", Exception())
            )
            val result = repository.listPassengerInvolved(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.listPassengerInvolved -> ICardSharedPreferencesDatasource.listPassengerInvolved")
        }

    @Test
    fun `listPassengerInvolved - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(Involved(name = "test"))
            whenever(cardSharedPreferencesDatasource.listPassengerInvolved(1)).thenReturn(Result.success(list))
            val result = repository.listPassengerInvolved(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), list)
        }

    @Test
    fun `listInvolved - Check return failure if have error in CardSharedPreferencesDatasource listInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.listInvolved()).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.listInvolved", "-", Exception())
            )
            val result = repository.listInvolved()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.listInvolved -> ICardSharedPreferencesDatasource.listInvolved")
        }

    @Test
    fun `listInvolved - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(Involved(name = "test"))
            whenever(cardSharedPreferencesDatasource.listInvolved()).thenReturn(Result.success(list))
            val result = repository.listInvolved()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), list)
        }

    @Test
    fun `listWitness - Check return failure if have error in CardSharedPreferencesDatasource listWitness`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.listWitness()).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.listWitness", "-", Exception())
            )
            val result = repository.listWitness()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.listWitness -> ICardSharedPreferencesDatasource.listWitness")
        }

    @Test
    fun `listWitness - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(Involved(name = "test"))
            whenever(cardSharedPreferencesDatasource.listWitness()).thenReturn(Result.success(list))
            val result = repository.listWitness()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), list)
        }

    @Test
    fun `getDocumentInvolved(idMain) - Check return failure if have error in CardSharedPreferencesDatasource getDocumentInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDocumentInvolved(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getDocumentInvolved", "-", Exception())
            )
            val result = repository.getDocumentInvolved(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDocumentInvolved -> ICardSharedPreferencesDatasource.getDocumentInvolved")
        }

    @Test
    fun `getDocumentInvolved(idMain) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDocumentInvolved(1)).thenReturn(Result.success("document"))
            val result = repository.getDocumentInvolved(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "document")
        }

    @Test
    fun `getDocumentWitness(idMain) - Check return failure if have error in CardSharedPreferencesDatasource getDocumentWitness`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDocumentWitness(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getDocumentWitness", "-", Exception())
            )
            val result = repository.getDocumentWitness(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDocumentWitness -> ICardSharedPreferencesDatasource.getDocumentWitness")
        }

    @Test
    fun `getDocumentWitness(idMain) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getDocumentWitness(1)).thenReturn(Result.success("document"))
            val result = repository.getDocumentWitness(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "document")
        }

    @Test
    fun `getNameInvolved(idMain) - Check return failure if have error in CardSharedPreferencesDatasource getNameInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getNameInvolved(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getNameInvolved", "-", Exception())
            )
            val result = repository.getNameInvolved(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getNameInvolved -> ICardSharedPreferencesDatasource.getNameInvolved")
        }

    @Test
    fun `getNameInvolved(idMain) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getNameInvolved(1)).thenReturn(Result.success("name"))
            val result = repository.getNameInvolved(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "name")
        }

    @Test
    fun `getNameWitness(idMain) - Check return failure if have error in CardSharedPreferencesDatasource getNameWitness`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getNameWitness(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getNameWitness", "-", Exception())
            )
            val result = repository.getNameWitness(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getNameWitness -> ICardSharedPreferencesDatasource.getNameWitness")
        }

    @Test
    fun `getNameWitness(idMain) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getNameWitness(1)).thenReturn(Result.success("name"))
            val result = repository.getNameWitness(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "name")
        }

    @Test
    fun `getPhoneDriver(idMain) - Check return failure if have error in CardSharedPreferencesDatasource getPhoneDriver`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getPhoneDriver(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getPhoneDriver", "-", Exception())
            )
            val result = repository.getPhoneDriver(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getPhoneDriver -> ICardSharedPreferencesDatasource.getPhoneDriver")
        }

    @Test
    fun `getPhoneDriver(idMain) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getPhoneDriver(1)).thenReturn(Result.success("phone"))
            val result = repository.getPhoneDriver(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "phone")
        }

    @Test
    fun `getPhoneInvolved(idMain) - Check return failure if have error in CardSharedPreferencesDatasource getPhoneInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getPhoneInvolved(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getPhoneInvolved", "-", Exception())
            )
            val result = repository.getPhoneInvolved(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getPhoneInvolved -> ICardSharedPreferencesDatasource.getPhoneInvolved")
        }

    @Test
    fun `getPhoneInvolved(idMain) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getPhoneInvolved(1)).thenReturn(Result.success("phone"))
            val result = repository.getPhoneInvolved(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "phone")
        }

    @Test
    fun `getPhoneWitness(idMain) - Check return failure if have error in CardSharedPreferencesDatasource getPhoneWitness`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getPhoneWitness(1)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getPhoneWitness", "-", Exception())
            )
            val result = repository.getPhoneWitness(1)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getPhoneWitness -> ICardSharedPreferencesDatasource.getPhoneWitness")
        }

    @Test
    fun `getPhoneWitness(idMain) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getPhoneWitness(1)).thenReturn(Result.success("phone"))
            val result = repository.getPhoneWitness(1)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "phone")
        }

    @Test
    fun `getPhonePassengerInvolved(idMain, idSecondary) - Check return failure if have error in CardSharedPreferencesDatasource getPhonePassengerInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getPhonePassengerInvolved(1, 2)).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.getPhonePassengerInvolved", "-", Exception())
            )
            val result = repository.getPhonePassengerInvolved(1, 2)
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getPhonePassengerInvolved -> ICardSharedPreferencesDatasource.getPhonePassengerInvolved")
        }

    @Test
    fun `getPhonePassengerInvolved(idMain, idSecondary) - Check return correct if function execute successfully`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.getPhonePassengerInvolved(1, 2)).thenReturn(Result.success("phone"))
            val result = repository.getPhonePassengerInvolved(1, 2)
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "phone")
        }

    @Test
    fun `listVehicleOwn - Check return failure if have error in CardSharedPreferencesDatasource listVehicleOwn`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.listVehicleOwn()).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.listVehicleOwn", "-", Exception())
            )
            val result = repository.listVehicleOwn()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.listVehicleOwn -> ICardSharedPreferencesDatasource.listVehicleOwn")
        }

    @Test
    fun `listVehicleOwn - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(VehicleOwn(id = 1))
            whenever(cardSharedPreferencesDatasource.listVehicleOwn()).thenReturn(Result.success(list))
            val result = repository.listVehicleOwn()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), list)
        }

    @Test
    fun `listVehicleInvolved - Check return failure if have error in CardSharedPreferencesDatasource listVehicleInvolved`() =
        runTest {
            whenever(cardSharedPreferencesDatasource.listVehicleInvolved()).thenReturn(
                resultFailure("ICardSharedPreferencesDatasource.listVehicleInvolved", "-", Exception())
            )
            val result = repository.listVehicleInvolved()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.listVehicleInvolved -> ICardSharedPreferencesDatasource.listVehicleInvolved")
        }

    @Test
    fun `listVehicleInvolved - Check return correct if function execute successfully`() =
        runTest {
            val list = listOf(VehicleInvolved(id = 1, vehicle = Vehicle(), driver = Involved(), passengerInvolvedList = emptyList()))
            whenever(cardSharedPreferencesDatasource.listVehicleInvolved()).thenReturn(Result.success(list))
            val result = repository.listVehicleInvolved()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), list)
        }

    @Test
    fun `getRegColab() - Check return failure if have error in ColabSharedPreferencesDatasource getRegColab`() =
        runTest {
            whenever(colabSharedPreferencesDatasource.getRegColab()).thenReturn(
                resultFailure("IColabSharedPreferencesDatasource.getRegColab", "-", Exception())
            )
            val result = repository.getRegColab()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getRegColab -> IColabSharedPreferencesDatasource.getRegColab")
        }

    @Test
    fun `getRegColab() - Check return correct if function execute successfully`() =
        runTest {
            whenever(colabSharedPreferencesDatasource.getRegColab()).thenReturn(Result.success(123456L))
            val result = repository.getRegColab()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), 123456L)
        }

    @Test
    fun `getStateColab() - Check return failure if have error in ColabSharedPreferencesDatasource getState`() =
        runTest {
            whenever(colabSharedPreferencesDatasource.getState()).thenReturn(
                resultFailure("IColabSharedPreferencesDatasource.getState", "-", Exception())
            )
            val result = repository.getStateColab()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getStateColab -> IColabSharedPreferencesDatasource.getState")
        }

    @Test
    fun `getStateColab() - Check return correct if function execute successfully`() =
        runTest {
            whenever(colabSharedPreferencesDatasource.getState()).thenReturn(Result.success(State.UNHARMED))
            val result = repository.getStateColab()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), State.UNHARMED)
        }

    @Test
    fun `getDetailColab() - Check return failure if have error in ColabSharedPreferencesDatasource getDetail`() =
        runTest {
            whenever(colabSharedPreferencesDatasource.getDetail()).thenReturn(
                resultFailure("IColabSharedPreferencesDatasource.getDetail", "-", Exception())
            )
            val result = repository.getDetailColab()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailColab -> IColabSharedPreferencesDatasource.getDetail")
        }

    @Test
    fun `getDetailColab() - Check return correct if function execute successfully`() =
        runTest {
            whenever(colabSharedPreferencesDatasource.getDetail()).thenReturn(Result.success("test"))
            val result = repository.getDetailColab()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "test")
        }

    @Test
    fun `getPhoneInvolved() - Check return failure if have error in InvolvedSharedPreferencesDatasource getPhone`() =
        runTest {
            whenever(involvedSharedPreferencesDatasource.getPhone()).thenReturn(
                resultFailure("IInvolvedSharedPreferencesDatasource.getPhone", "-", Exception())
            )
            val result = repository.getPhone()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getPhone -> IInvolvedSharedPreferencesDatasource.getPhone")
        }

    @Test
    fun `getPhoneInvolved() - Check return correct if function execute successfully`() =
        runTest {
            whenever(involvedSharedPreferencesDatasource.getPhone()).thenReturn(Result.success("phone"))
            val result = repository.getPhone()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "phone")
        }

    @Test
    fun `getStateInvolved() - Check return failure if have error in InvolvedSharedPreferencesDatasource getState`() =
        runTest {
            whenever(involvedSharedPreferencesDatasource.getState()).thenReturn(
                resultFailure("IInvolvedSharedPreferencesDatasource.getState", "-", Exception())
            )
            val result = repository.getStateInvolved()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getStateInvolved -> IInvolvedSharedPreferencesDatasource.getState")
        }

    @Test
    fun `getStateInvolved() - Check return correct if function execute successfully`() =
        runTest {
            whenever(involvedSharedPreferencesDatasource.getState()).thenReturn(Result.success(State.UNHARMED))
            val result = repository.getStateInvolved()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), State.UNHARMED)
        }

    @Test
    fun `getDetailEquip() - Check return failure if have error in EquipSharedPreferencesDatasource getDetail`() =
        runTest {
            whenever(equipSharedPreferencesDatasource.getDetail()).thenReturn(
                resultFailure("IEquipSharedPreferencesDatasource.getDetail", "-", Exception())
            )
            val result = repository.getDetailEquip()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailEquip -> IEquipSharedPreferencesDatasource.getDetail")
        }

    @Test
    fun `getDetailEquip() - Check return correct if function execute successfully`() =
        runTest {
            whenever(equipSharedPreferencesDatasource.getDetail()).thenReturn(Result.success("test"))
            val result = repository.getDetailEquip()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "test")
        }

    @Test
    fun `getDetailInvolved() - Check return failure if have error in InvolvedSharedPreferencesDatasource getDetail`() =
        runTest {
            whenever(involvedSharedPreferencesDatasource.getDetail()).thenReturn(
                resultFailure("IInvolvedSharedPreferencesDatasource.getDetail", "-", Exception())
            )
            val result = repository.getDetailInvolved()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailInvolved -> IInvolvedSharedPreferencesDatasource.getDetail")
        }

    @Test
    fun `getDetailInvolved() - Check return correct if function execute successfully`() =
        runTest {
            whenever(involvedSharedPreferencesDatasource.getDetail()).thenReturn(Result.success("test"))
            val result = repository.getDetailInvolved()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "test")
        }

    @Test
    fun `getDetailVehicle() - Check return failure if have error in VehicleSharedPreferencesDatasource getDetail`() =
        runTest {
            whenever(vehicleSharedPreferencesDatasource.getDetail()).thenReturn(
                resultFailure("IVehicleSharedPreferencesDatasource.getDetail", "-", Exception())
            )
            val result = repository.getDetailVehicle()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailVehicle -> IVehicleSharedPreferencesDatasource.getDetail")
        }

    @Test
    fun `getDetailVehicle() - Check return correct if function execute successfully`() =
        runTest {
            whenever(vehicleSharedPreferencesDatasource.getDetail()).thenReturn(Result.success("test"))
            val result = repository.getDetailVehicle()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "test")
        }

    @Test
    fun `getIdEquip() - Check return failure if have error in EquipSharedPreferencesDatasource getIdEquip`() =
        runTest {
            whenever(vehicleSharedPreferencesDatasource.getDetail()).thenReturn(
                resultFailure("IVehicleSharedPreferencesDatasource.getDetail", "-", Exception())
            )
            val result = repository.getDetailVehicle()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDetailVehicle -> IVehicleSharedPreferencesDatasource.getDetail")
        }

    @Test
    fun `getIdEquip() - Check return correct if function execute successfully and return null`() =
        runTest {
            whenever(equipSharedPreferencesDatasource.getIdEquip()).thenReturn(Result.success(null))
            val result = repository.getIdEquip()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), null)
        }

    @Test
    fun `getIdEquip() - Check return correct if function execute successfully`() =
        runTest {
            whenever(equipSharedPreferencesDatasource.getIdEquip()).thenReturn(Result.success(1))
            val result = repository.getIdEquip()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), 1)
        }

    @Test
    fun `getPlate() - Check return failure if have error in VehicleSharedPreferencesDatasource getPlate`() =
        runTest {
            whenever(vehicleSharedPreferencesDatasource.getPlate()).thenReturn(
                resultFailure("IVehicleSharedPreferencesDatasource.getPlate", "-", Exception())
            )
            val result = repository.getPlate()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getPlate -> IVehicleSharedPreferencesDatasource.getPlate")
        }

    @Test
    fun `getPlate() - Check return correct if function execute successfully`() =
        runTest {
            whenever(vehicleSharedPreferencesDatasource.getPlate()).thenReturn(Result.success("ABC1234"))
            val result = repository.getPlate()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "ABC1234")
        }

    @Test
    fun `getBrand() - Check return failure if have error in VehicleSharedPreferencesDatasource getBrand`() =
        runTest {
            whenever(vehicleSharedPreferencesDatasource.getBrand()).thenReturn(
                resultFailure("IVehicleSharedPreferencesDatasource.getBrand", "-", Exception())
            )
            val result = repository.getBrand()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getBrand -> IVehicleSharedPreferencesDatasource.getBrand")
        }

    @Test
    fun `getBrand() - Check return correct if function execute successfully`() =
        runTest {
            whenever(vehicleSharedPreferencesDatasource.getBrand()).thenReturn(Result.success("FORD"))
            val result = repository.getBrand()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "FORD")
        }

    @Test
    fun `getDocument() - Check return failure if have error in InvolvedSharedPreferencesDatasource getDocument`() =
        runTest {
            whenever(involvedSharedPreferencesDatasource.getDocument()).thenReturn(
                resultFailure("IInvolvedSharedPreferencesDatasource.getDocument", "-", Exception())
            )
            val result = repository.getDocument()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getDocument -> IInvolvedSharedPreferencesDatasource.getDocument")
        }

    @Test
    fun `getDocument() - Check return correct if function execute successfully`() =
        runTest {
            whenever(involvedSharedPreferencesDatasource.getDocument()).thenReturn(Result.success("123456789"))
            val result = repository.getDocument()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "123456789")
        }

    @Test
    fun `getName() - Check return failure if have error in InvolvedSharedPreferencesDatasource getName`() =
        runTest {
            whenever(involvedSharedPreferencesDatasource.getName()).thenReturn(
                resultFailure("IInvolvedSharedPreferencesDatasource.getName", "-", Exception())
            )
            val result = repository.getName()
            assertEquals(result.isFailure, true)
            assertEquals(result.exceptionOrNull()!!.message, "IRecoverDataCardRepository.getName -> IInvolvedSharedPreferencesDatasource.getName")
        }

    @Test
    fun `getName() - Check return correct if function execute successfully`() =
        runTest {
            whenever(involvedSharedPreferencesDatasource.getName()).thenReturn(Result.success("JOAO"))
            val result = repository.getName()
            assertEquals(result.isSuccess, true)
            assertEquals(result.getOrNull(), "JOAO")
        }

}
