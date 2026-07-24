package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.infra.datasource.retrofit.variable.CardRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.CardRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.EquipSecRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.InvolvedRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.PassengerColabRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.PassengerInvolvedRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.VehicleInvolvedRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.VehicleOwnRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.WitnessRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.variable.CardRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.VehicleInvolvedRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.VehicleOwnRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.WitnessRoomModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.LocalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleInvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleSharedPreferencesModel
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

class ISendCardRepositoryTest {

    private val cardSharedPreferencesDatasource = mock<CardSharedPreferencesDatasource>()
    private val involvedRoomDatasource = mock<InvolvedRoomDatasource>()
    private val vehicleInvolvedRoomDatasource = mock<VehicleInvolvedRoomDatasource>()
    private val passengerColabRoomDatasource = mock<PassengerColabRoomDatasource>()
    private val passengerInvolvedRoomDatasource = mock<PassengerInvolvedRoomDatasource>()
    private val equipSecRoomDatasource = mock<EquipSecRoomDatasource>()
    private val vehicleOwnRoomDatasource = mock<VehicleOwnRoomDatasource>()
    private val witnessRoomDatasource = mock<WitnessRoomDatasource>()
    private val cardRoomDatasource = mock<CardRoomDatasource>()
    private val cardRetrofitDatasource = mock<CardRetrofitDatasource>()

    private val repository = ISendCardRepository(
        cardSharedPreferencesDatasource = cardSharedPreferencesDatasource,
        cardRoomDatasource = cardRoomDatasource,
        cardRetrofitDatasource = cardRetrofitDatasource,
        vehicleOwnRoomDatasource = vehicleOwnRoomDatasource,
        passengerColabRoomDatasource = passengerColabRoomDatasource,
        equipSecRoomDatasource = equipSecRoomDatasource,
        vehicleInvolvedRoomDatasource = vehicleInvolvedRoomDatasource,
        passengerInvolvedRoomDatasource = passengerInvolvedRoomDatasource,
        involvedRoomDatasource = involvedRoomDatasource,
        witnessRoomDatasource = witnessRoomDatasource,
    )

    @Test
    fun `save - Check return failure if have error in CardSharedPreferencesDatasource get`() = runTest {
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(
            resultFailure("CardSharedPreferencesDatasource.get", Exception())
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISendCardRepository.save -> CardSharedPreferencesDatasource.get", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if regAttendant is null`() = runTest {
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(
            Result.success(CardSharedPreferencesModel(regAttendant = null))
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISendCardRepository.save -> regAttendant is required", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if idCar is null`() = runTest {
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(
            Result.success(CardSharedPreferencesModel(regAttendant = 1L, idCar = null))
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISendCardRepository.save -> idCar is required", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if local is null`() = runTest {
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(
            Result.success(CardSharedPreferencesModel(regAttendant = 1L, idCar = 1, local = null))
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISendCardRepository.save -> local is required", result.exceptionOrNull()!!.message)
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
        assertEquals("ISendCardRepository.save -> CardRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if have error in VehicleOwnRoomDatasource add`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(
                equip = EquipCardSharedPreferencesModel(idEquip = 1),
                colab = ColabCardSharedPreferencesModel(reg = 1, state = br.com.usinasantafe.cav.lib.State.UNHARMED)
            ))
        )
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(Result.success(model))
        whenever(cardRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(vehicleOwnRoomDatasource.add(any())).thenReturn(
            resultFailure("VehicleOwnRoomDatasource.add", Exception())
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISendCardRepository.save -> VehicleOwnRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if have error in PassengerColabRoomDatasource add`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(
                equip = EquipCardSharedPreferencesModel(idEquip = 1),
                colab = ColabCardSharedPreferencesModel(reg = 1, state = br.com.usinasantafe.cav.lib.State.UNHARMED),
                passengerColabList = listOf(ColabCardSharedPreferencesModel(reg = 2, state = br.com.usinasantafe.cav.lib.State.UNHARMED))
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
        assertEquals("ISendCardRepository.save -> PassengerColabRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if have error in EquipSecRoomDatasource add`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(
                equip = EquipCardSharedPreferencesModel(idEquip = 1),
                colab = ColabCardSharedPreferencesModel(reg = 1, state = br.com.usinasantafe.cav.lib.State.UNHARMED),
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
        assertEquals("ISendCardRepository.save -> EquipSecRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if have error in VehicleInvolvedRoomDatasource add`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            vehicleInvolvedList = listOf(VehicleInvolvedSharedPreferencesModel(
                vehicle = VehicleSharedPreferencesModel(plate = "ABC1234", brand = "Test"),
                driver = InvolvedSharedPreferencesModel(name = "Test", phone = "123", state = br.com.usinasantafe.cav.lib.State.UNHARMED)
            ))
        )
        whenever(cardSharedPreferencesDatasource.get()).thenReturn(Result.success(model))
        whenever(cardRoomDatasource.add(any())).thenReturn(Result.success(1))
        whenever(vehicleInvolvedRoomDatasource.add(any())).thenReturn(
            resultFailure("VehicleInvolvedRoomDatasource.add", Exception())
        )

        val result = repository.save()

        assertTrue(result.isFailure)
        assertEquals("ISendCardRepository.save -> VehicleInvolvedRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return failure if have error in PassengerInvolvedRoomDatasource add`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            vehicleInvolvedList = listOf(VehicleInvolvedSharedPreferencesModel(
                vehicle = VehicleSharedPreferencesModel(plate = "ABC1234", brand = "Test"),
                driver = InvolvedSharedPreferencesModel(name = "Test", phone = "123", state = br.com.usinasantafe.cav.lib.State.UNHARMED),
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
        assertEquals("ISendCardRepository.save -> PassengerInvolvedRoomDatasource.add", result.exceptionOrNull()!!.message)
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
        assertEquals("ISendCardRepository.save -> InvolvedRoomDatasource.add", result.exceptionOrNull()!!.message)
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
        assertEquals("ISendCardRepository.save -> WitnessRoomDatasource.add", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `save - Check return success if all data saved successfully`() = runTest {
        val model = CardSharedPreferencesModel(
            regAttendant = 1L,
            idCar = 1,
            local = LocalSharedPreferencesModel(address = "Test"),
            vehicleOwnList = listOf(VehicleOwnSharedPreferencesModel(
                equip = EquipCardSharedPreferencesModel(idEquip = 1),
                colab = ColabCardSharedPreferencesModel(reg = 1, state = br.com.usinasantafe.cav.lib.State.UNHARMED),
                passengerColabList = listOf(ColabCardSharedPreferencesModel(reg = 2, state = br.com.usinasantafe.cav.lib.State.UNHARMED)),
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

    @Test
    fun `hasSend - Check return failure if have error in CardRoomDatasource hasSend`() =
        runTest {
            whenever(
                cardRoomDatasource.hasSend()
            ).thenReturn(
                resultFailure(
                    "ICardRoomDatasource.hasSend",
                    "-",
                    Exception()
                )
            )
            val result = repository.hasSend()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISendCardRepository.hasSend -> ICardRoomDatasource.hasSend"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `hasSend - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardRoomDatasource.hasSend()
            ).thenReturn(
                Result.success(false)
            )
            val result = repository.hasSend()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                false
            )
        }

    @Test
    fun `send - Check return failure if have error in CardRoomDatasource getSend`() = runTest {
        whenever(cardRoomDatasource.getSend()).thenReturn(
            resultFailure("CardRoomDatasource.getSend", Exception())
        )

        val result = repository.send("token")

        assertTrue(result.isFailure)
        assertEquals("ISendCardRepository.send -> CardRoomDatasource.getSend", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `send - Check return failure if card id is null`() = runTest {
        whenever(cardRoomDatasource.getSend()).thenReturn(
            Result.success(CardRoomModel(id = null, regAttendant = 1L, idCar = 1, address = "Test", latitude = 0.0, longitude = 0.0, idNatureList = emptyList(), idTypeAccidentList = emptyList(), idDataLocalList = emptyList(), idSupportTeamsList = emptyList(), urlPhotoList = emptyList(), obs = "Test"))
        )

        val result = repository.send("token")

        assertTrue(result.isFailure)
        assertEquals("ISendCardRepository.send -> id is required", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `send - Check return failure if have error in CardRetrofitDatasource send`() = runTest {
        val cardModel = CardRoomModel(id = 1, regAttendant = 1L, idCar = 1, address = "Test", latitude = 0.0, longitude = 0.0, idNatureList = emptyList(), idTypeAccidentList = emptyList(), idDataLocalList = emptyList(), idSupportTeamsList = emptyList(), urlPhotoList = emptyList(), obs = "Test")
        whenever(cardRoomDatasource.getSend()).thenReturn(Result.success(cardModel))
        whenever(vehicleOwnRoomDatasource.listByIdCard(1)).thenReturn(Result.success(emptyList()))
        whenever(vehicleInvolvedRoomDatasource.listByIdCard(1)).thenReturn(Result.success(emptyList()))
        whenever(involvedRoomDatasource.listByIdCard(1)).thenReturn(Result.success(emptyList()))
        whenever(witnessRoomDatasource.listByIdCard(1)).thenReturn(Result.success(emptyList()))
        whenever(passengerColabRoomDatasource.listByIdVehicleList(any())).thenReturn(Result.success(emptyList()))
        whenever(passengerInvolvedRoomDatasource.listByIdVehicleList(any())).thenReturn(Result.success(emptyList()))
        whenever(equipSecRoomDatasource.listByIdVehicleList(any())).thenReturn(Result.success(emptyList()))

        whenever(cardRetrofitDatasource.send(any(), any())).thenReturn(
            resultFailure("CardRetrofitDatasource.send", Exception())
        )

        val result = repository.send("token")

        assertTrue(result.isFailure)
        assertEquals("ISendCardRepository.send -> CardRetrofitDatasource.send", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `send - Check return success if all data sent successfully`() = runTest {
        val cardModel = CardRoomModel(id = 1, regAttendant = 1L, idCar = 1, address = "Test", latitude = 0.0, longitude = 0.0, idNatureList = emptyList(), idTypeAccidentList = emptyList(), idDataLocalList = emptyList(), idSupportTeamsList = emptyList(), urlPhotoList = emptyList(), obs = "Test")
        val vehicleOwnModel = VehicleOwnRoomModel(id = 1, idCard = 1, idEquip = 1, detailEquip = "Test", reg = 1L, state = State.UNHARMED, detailColab = "Test")
        val vehicleInvolvedModel = VehicleInvolvedRoomModel(id = 2, idCard = 1, document = "123", name = "Test", phone = "456", address = "Test", state = State.UNHARMED, detailDriver = "Test", plate = "ABC", brand = "Test", detailVehicle = "Test")
        val involvedModel = InvolvedRoomModel(id = 3, idCard = 1, document = "123", name = "Test", phone = "456", address = "Test", state = State.UNHARMED, detail = "Test")
        val witnessModel = WitnessRoomModel(id = 4, idCard = 1, name = "Test", phone = "456", detail = "Test")

        whenever(cardRoomDatasource.getSend()).thenReturn(Result.success(cardModel))
        whenever(vehicleOwnRoomDatasource.listByIdCard(1)).thenReturn(Result.success(listOf(vehicleOwnModel)))
        whenever(vehicleInvolvedRoomDatasource.listByIdCard(1)).thenReturn(Result.success(listOf(vehicleInvolvedModel)))
        whenever(involvedRoomDatasource.listByIdCard(1)).thenReturn(Result.success(listOf(involvedModel)))
        whenever(witnessRoomDatasource.listByIdCard(1)).thenReturn(Result.success(listOf(witnessModel)))
        whenever(passengerColabRoomDatasource.listByIdVehicleList(listOf(1))).thenReturn(Result.success(emptyList()))
        whenever(passengerInvolvedRoomDatasource.listByIdVehicleList(listOf(2))).thenReturn(Result.success(emptyList()))
        whenever(equipSecRoomDatasource.listByIdVehicleList(listOf(1))).thenReturn(Result.success(emptyList()))

        whenever(cardRetrofitDatasource.send(any(), any())).thenReturn(Result.success(mock()))

        val result = repository.send("token")

        assertTrue(result.isSuccess)
        verify(cardRetrofitDatasource).send(any(), any())
    }

    @Test
    fun `delete - Check return failure if have error in CardRoomDatasource listDelete`() = runTest {
        whenever(cardRoomDatasource.listDelete()).thenReturn(
            resultFailure("CardRoomDatasource.listDelete", Exception())
        )

        val result = repository.delete()

        assertTrue(result.isFailure)
        assertEquals("ISendCardRepository.delete -> CardRoomDatasource.listDelete", result.exceptionOrNull()!!.message)
    }

    @Test
    fun `delete - Check return success if delete process execute successfully`() = runTest {
        val cardModel = CardRoomModel(id = 1, regAttendant = 1L, idCar = 1, address = "Test", latitude = 0.0, longitude = 0.0, idNatureList = emptyList(), idTypeAccidentList = emptyList(), idDataLocalList = emptyList(), idSupportTeamsList = emptyList(), urlPhotoList = listOf("path/to/photo.jpg"), obs = "Test")
        val vehicleOwnModel = VehicleOwnRoomModel(id = 10, idCard = 1, idEquip = 1, detailEquip = "Test", reg = 1L, state = State.UNHARMED, detailColab = "Test")
        val vehicleInvolvedModel = VehicleInvolvedRoomModel(id = 20, idCard = 1, document = "123", name = "Test", phone = "456", address = "Test", state = State.UNHARMED, detailDriver = "Test", plate = "ABC", brand = "Test", detailVehicle = "Test")

        whenever(cardRoomDatasource.listDelete()).thenReturn(Result.success(listOf(cardModel)))
        whenever(vehicleOwnRoomDatasource.listByIdCard(1)).thenReturn(Result.success(listOf(vehicleOwnModel)))
        whenever(vehicleInvolvedRoomDatasource.listByIdCard(1)).thenReturn(Result.success(listOf(vehicleInvolvedModel)))
        
        whenever(equipSecRoomDatasource.deleteByIdVehicleList(listOf(10))).thenReturn(Result.success(Unit))
        whenever(passengerColabRoomDatasource.deleteByIdVehicleList(listOf(10))).thenReturn(Result.success(Unit))
        whenever(passengerInvolvedRoomDatasource.deleteByIdVehicleList(listOf(20))).thenReturn(Result.success(Unit))
        whenever(involvedRoomDatasource.deleteByIdCard(1)).thenReturn(Result.success(Unit))
        whenever(witnessRoomDatasource.deleteByIdCard(1)).thenReturn(Result.success(Unit))
        whenever(vehicleInvolvedRoomDatasource.deleteByIdCard(1)).thenReturn(Result.success(Unit))
        whenever(vehicleOwnRoomDatasource.deleteByIdCard(1)).thenReturn(Result.success(Unit))
        whenever(cardRoomDatasource.deleteById(1)).thenReturn(Result.success(Unit))

        val result = repository.delete()

        assertTrue(result.isSuccess)
        verify(equipSecRoomDatasource).deleteByIdVehicleList(listOf(10))
        verify(passengerColabRoomDatasource).deleteByIdVehicleList(listOf(10))
        verify(passengerInvolvedRoomDatasource).deleteByIdVehicleList(listOf(20))
        verify(involvedRoomDatasource).deleteByIdCard(1)
        verify(witnessRoomDatasource).deleteByIdCard(1)
        verify(vehicleInvolvedRoomDatasource).deleteByIdCard(1)
        verify(vehicleOwnRoomDatasource).deleteByIdCard(1)
        verify(cardRoomDatasource).deleteById(1)
    }

}
