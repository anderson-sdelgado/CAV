package br.com.usinasantafe.cav.external.room.datasource.variable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.variable.PassengerExternalDao
import br.com.usinasantafe.cav.infra.models.room.variable.PassengerExternalRoomModel
import br.com.usinasantafe.cav.lib.State
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class IPassengerPeopleExternalRoomDatasourceTest {

    private lateinit var passengerExternalDao: PassengerExternalDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: IPassengerExternalRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        passengerExternalDao = db.passengerExternalDao()
        datasource = IPassengerExternalRoomDatasource(passengerExternalDao)
    }

    @After
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `add - Check success if row is inserted correctly`() = runTest {
        val modelBefore = PassengerExternalRoomModel(
            idVehicle = 1,
            document = "654321",
            name = "Passenger Name",
            phone = "123456789",
            address = "Address",
            state = State.INJURED,
            detail = "Passenger Detail"
        )

        val result = datasource.add(modelBefore)

        assertTrue(result.isSuccess)
        val id = result.getOrNull()!!
        assertTrue(id > 0)

        val list = passengerExternalDao.all()
        assertEquals(1, list.size)
        val savedModel = list[0]
        val modelAfter = PassengerExternalRoomModel(
            id = 1,
            idVehicle = 1,
            document = "654321",
            name = "Passenger Name",
            phone = "123456789",
            address = "Address",
            state = State.INJURED,
            detail = "Passenger Detail"
        )
        assertEquals(savedModel, modelAfter)
    }

    @Test
    fun `deleteByIdVehicleList - Check execution correct`() = runTest {
        val model1 = PassengerExternalRoomModel(idVehicle = 1, name = "N1", phone = "P1", address = null, state = State.UNHARMED, detail = null, document = null)
        val model2 = PassengerExternalRoomModel(idVehicle = 1, name = "N2", phone = "P2", address = null, state = State.UNHARMED, detail = null, document = null)
        val model3 = PassengerExternalRoomModel(idVehicle = 2, name = "N3", phone = "P3", address = null, state = State.UNHARMED, detail = null, document = null)

        passengerExternalDao.insert(model1)
        passengerExternalDao.insert(model2)
        passengerExternalDao.insert(model3)

        assertEquals(3, passengerExternalDao.all().size)

        val result = datasource.deleteByIdVehicleList(listOf(1))
        assertTrue(result.isSuccess)
        assertEquals(1, passengerExternalDao.all().size)
    }

}
