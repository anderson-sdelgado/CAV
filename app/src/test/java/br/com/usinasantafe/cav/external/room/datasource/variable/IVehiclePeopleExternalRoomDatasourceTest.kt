package br.com.usinasantafe.cav.external.room.datasource.variable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.variable.VehicleExternalDao
import br.com.usinasantafe.cav.infra.models.room.variable.VehicleExternalRoomModel
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
class IVehiclePeopleExternalRoomDatasourceTest {

    private lateinit var vehicleExternalDao: VehicleExternalDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: IVehicleExternalRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        vehicleExternalDao = db.vehicleExternalDao()
        datasource = IVehicleExternalRoomDatasource(vehicleExternalDao)
    }

    @After
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `add - Check success if row is inserted correctly`() = runTest {
        val modelBefore = VehicleExternalRoomModel(
            idCard = 1,
            document = "123",
            name = "Driver Name",
            phone = "456",
            address = "Address",
            state = State.UNHARMED,
            detailDriver = "Driver Detail",
            plate = "ABC1234",
            brand = "Brand",
            detailVehicle = "Vehicle Detail"
        )

        val result = datasource.add(modelBefore)

        assertTrue(result.isSuccess)
        val id = result.getOrNull()!!
        assertTrue(id > 0)

        val list = vehicleExternalDao.all()
        assertEquals(1, list.size)
        val savedModel = list[0]
        val modelAfter = VehicleExternalRoomModel(
            id = 1,
            idCard = 1,
            document = "123",
            name = "Driver Name",
            phone = "456",
            address = "Address",
            state = State.UNHARMED,
            detailDriver = "Driver Detail",
            plate = "ABC1234",
            brand = "Brand",
            detailVehicle = "Vehicle Detail"
        )
        assertEquals(savedModel, modelAfter)
    }

    @Test
    fun `listByIdCard - Check return list correct`() = runTest {
        val model1 = VehicleExternalRoomModel(idCard = 1, plate = "P1", brand = "B1", name = "N1", phone = "Ph1", state = State.UNHARMED, address = null, detailDriver = null, detailVehicle = null, document = null)
        val model2 = VehicleExternalRoomModel(idCard = 1, plate = "P2", brand = "B2", name = "N2", phone = "Ph2", state = State.UNHARMED, address = null, detailDriver = null, detailVehicle = null, document = null)
        val model3 = VehicleExternalRoomModel(idCard = 2, plate = "P3", brand = "B3", name = "N3", phone = "Ph3", state = State.UNHARMED, address = null, detailDriver = null, detailVehicle = null, document = null)

        vehicleExternalDao.insert(model1)
        vehicleExternalDao.insert(model2)
        vehicleExternalDao.insert(model3)

        val result = datasource.listByIdCard(1)
        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrNull()!!.size)
    }

    @Test
    fun `deleteByIdCard - Check execution correct`() = runTest {
        val model1 = VehicleExternalRoomModel(idCard = 1, plate = "P1", brand = "B1", name = "N1", phone = "Ph1", state = State.UNHARMED, address = null, detailDriver = null, detailVehicle = null, document = null)
        val model2 = VehicleExternalRoomModel(idCard = 2, plate = "P2", brand = "B2", name = "N2", phone = "Ph2", state = State.UNHARMED, address = null, detailDriver = null, detailVehicle = null, document = null)

        vehicleExternalDao.insert(model1)
        vehicleExternalDao.insert(model2)

        assertEquals(2, vehicleExternalDao.all().size)

        val result = datasource.deleteByIdCard(1)
        assertTrue(result.isSuccess)
        assertEquals(1, vehicleExternalDao.all().size)
    }

}
