package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.infra.datasource.room.variable.*
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.*
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ISaveCardRepositoryTest {

    private val cardSharedPreferencesDatasource = mock<CardSharedPreferencesDatasource>()
    private val involvedRoomDatasource = mock<InvolvedRoomDatasource>()
    private val vehicleInvolvedRoomDatasource = mock<VehicleInvolvedRoomDatasource>()
    private val passengerColabRoomDatasource = mock<PassengerColabRoomDatasource>()
    private val passengerInvolvedRoomDatasource = mock<PassengerInvolvedRoomDatasource>()
    private val equipSecRoomDatasource = mock<EquipSecRoomDatasource>()
    private val vehicleOwnRoomDatasource = mock<VehicleOwnRoomDatasource>()
    private val witnessRoomDatasource = mock<WitnessRoomDatasource>()
    private val cardRoomDatasource = mock<CardRoomDatasource>()

    private val repository = ISaveAndSendCardRepository(
        cardSharedPreferencesDatasource,
        involvedRoomDatasource,
        vehicleInvolvedRoomDatasource,
        passengerColabRoomDatasource,
        passengerInvolvedRoomDatasource,
        equipSecRoomDatasource,
        vehicleOwnRoomDatasource,
        witnessRoomDatasource,
        cardRoomDatasource
    )

    @Test
    fun `save - Check return failure if have error in CardSharedPreferencesDatasource get`() = runTest {
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(
            resultFailure("CardSharedPreferencesDatasource.get", Exception())
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISaveCardRepository.save -> CardSharedPreferencesDatasource.get", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if regAttendant is null`() = runTest {
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(
            Result.success(CardSharedPreferencesModel(regAttendant = null))
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISaveCardRepository.save -> regAttendant is required", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if idCar is null`() = runTest {
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(
            Result.success(CardSharedPreferencesModel(regAttendant = 1L, idCar = null))
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISaveCardRepository.save -> idCar is required", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if local is null`() = runTest {
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(
            Result.success(CardSharedPreferencesModel(regAttendant = 1L, idCar = 1, local = null))
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISaveCardRepository.save -> local is required", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if have error in CardRoomDatasource add`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test")
        )
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(Result.success(model))
        whenever(cardRoomDatasource.add(any())).thenReturn(
            resultFailure("CardRoomDatasource.add", Exception())
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISaveCardRepository.save -> CardRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if have error in VehicleOwnRoomDatasource add`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(
                equip = EquipCardSharedPreferencesModel(idEquip = 1),
                colab = ColabCardSharedPreferencesModel(reg = 1, state = State.UNHARMED)
            ))
        )
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(Result.success(model))
        whenever(cardRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(vehicleOwnRoomDatasource.add(any())).thenReturn(
            resultFailure("VehicleOwnRoomDatasource.add", Exception())
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISaveCardRepository.save -> VehicleOwnRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if have error in PassengerColabRoomDatasource add`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(
                equip = EquipCardSharedPreferencesModel(idEquip = 1),
                colab = ColabCardSharedPreferencesModel(reg = 1, state = State.UNHARMED),
                passengerColabList = listOf(ColabCardSharedPreferencesModel(reg = 2, state = State.UNHARMED))
            ))
        )
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(Result.success(model))
        whenever(cardRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(vehicleOwnRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(passengerColabRoomDatasource.add(any())).thenReturn(
            resultFailure("PassengerColabRoomDatasource.add", Exception())
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISaveCardRepository.save -> PassengerColabRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if have error in EquipSecRoomDatasource add`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(
                equip = EquipCardSharedPreferencesModel(idEquip = 1),
                colab = ColabCardSharedPreferencesModel(reg = 1, state = State.UNHARMED),
                equipSecList = listOf(EquipCardSharedPreferencesModel(idEquip = 2))
            ))
        )
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(Result.success(model))
        whenever(cardRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(vehicleOwnRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(equipSecRoomDatasource.add(any())).thenReturn(
            resultFailure("EquipSecRoomDatasource.add", Exception())
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISaveCardRepository.save -> EquipSecRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if have error in VehicleInvolvedRoomDatasource add`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            vehicleInvolvedList = listOf(VehicleInvolvedSharedPreferencesModel(
                vehicle = VehicleSharedPreferencesModel(plate = "ABC1234", brand = "Test"),
                driver = InvolvedSharedPreferencesModel(name = "Test", phone = "123", state = State.UNHARMED)
            ))
        )
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(Result.success(model))
        whenever(cardRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(vehicleInvolvedRoomDatasource.add(any())).thenReturn(
            resultFailure("VehicleInvolvedRoomDatasource.add", Exception())
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISaveCardRepository.save -> VehicleInvolvedRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if have error in PassengerInvolvedRoomDatasource add`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            vehicleInvolvedList = listOf(VehicleInvolvedSharedPreferencesModel(
                vehicle = VehicleSharedPreferencesModel(plate = "ABC1234", brand = "Test"),
                driver = InvolvedSharedPreferencesModel(name = "Test", phone = "123", state = State.UNHARMED),
                passengerInvolvedList = listOf(InvolvedSharedPreferencesModel(name = "Pass", phone = "456"))
            ))
        )
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(Result.success(model))
        whenever(cardRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(vehicleInvolvedRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(passengerInvolvedRoomDatasource.add(any())).thenReturn(
            resultFailure("PassengerInvolvedRoomDatasource.add", Exception())
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISaveCardRepository.save -> PassengerInvolvedRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if have error in InvolvedRoomDatasource add`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            involvedList = listOf(InvolvedSharedPreferencesModel(name = "Inv", phone = "789"))
        )
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(Result.success(model))
        whenever(cardRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(involvedRoomDatasource.add(any())).thenReturn(
            resultFailure("InvolvedRoomDatasource.add", Exception())
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISaveCardRepository.save -> InvolvedRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if have error in WitnessRoomDatasource add`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            witnessList = listOf(InvolvedSharedPreferencesModel(name = "Wit", phone = "000"))
        )
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(Result.success(model))
        whenever(cardRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(witnessRoomDatasource.add(any())).thenReturn(
            resultFailure("WitnessRoomDatasource.add", Exception())
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISaveCardRepository.save -> WitnessRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return success if all data saved successfully`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(
                equip = EquipCardSharedPreferencesModel(idEquip = 1),
                colab = ColabCardSharedPreferencesModel(reg = 1, state = State.UNHARMED),
                passengerColabList = listOf(ColabCardSharedPreferencesModel(reg = 2, state = State.UNHARMED)),
                equipSecList = listOf(EquipCardSharedPreferencesModel(idEquip = 2))
            )),
            vehicleInvolvedList = listOf(VehicleInvolvedSharedPreferencesModel(
                vehicle = VehicleSharedPreferencesModel(plate = "ABC1234", brand = "Test"),
                driver = InvolvedSharedPreferencesModel(name = "Driver", phone = "123", state = State.UNHARMED),
                passengerInvolvedList = listOf(InvolvedSharedPreferencesModel(name = "passenger", phone = "456"))
            )),
            involvedList = listOf(InvolvedSharedPreferencesModel(name = "Inv", phone = "789")),
            witnessList = listOf(InvolvedSharedPreferencesModel(name = "Wit", phone = "000"))
        )

        whenever(cardSharedPreferencesDatasource.get()).thenReturn(Result.success(model))
        whenever(cardRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(vehicleOwnRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(passengerColabRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(equipSecRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(vehicleInvolvedRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(passengerInvolvedRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(involvedRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(witnessRoomDatasource.add(any())).thenReturn(Result.success(1))

        val result = repository.save()

        assertTrue(result.isSuccess)
        verify(cardRoomDatasource).add(any())
        verify(vehicleOwnRoomDatasource).add(any())
        verify(passengerColabRoomDatasource).add(any())
        verify(equipSecRoomDatasource).add(any())
        verify(vehicleInvolvedRoomDatasource).add(any())
        verify(passengerInvolvedRoomDatasource).add(any())
        verify(involvedRoomDatasource).add(any())
        verify(witnessRoomDatasource).add(any())
    }
}
