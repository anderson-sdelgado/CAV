package br.com.usinasantafe.cav.external.room.datasource.variable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.variable.VehicleOwnDao
import br.com.usinasantafe.cav.infra.models.room.variable.VehicleOwnRoomModel
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
class IVehicleOwnRoomDatasourceTest {

    private lateinit var vehicleOwnDao: VehicleOwnDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: IVehicleOwnRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        vehicleOwnDao = db.vehicleOwnDao()
        datasource = IVehicleOwnRoomDatasource(vehicleOwnDao)
    }

    @After
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `add - Check success if row is inserted correctly`() = runTest {
        val modelBefore = VehicleOwnRoomModel(
            idCard = 1,
            idEquip = 500,
            detailEquip = "Equip Detail",
            reg = 12345L,
            state = State.UNHARMED,
            detailColab = "Colab Detail"
        )

        val result = datasource.add(modelBefore)

        assertTrue(result.isSuccess)
        val id = result.getOrNull()!!
        assertTrue(id > 0)

        val list = vehicleOwnDao.all()
        assertEquals(1, list.size)
        val savedModel = list[0]
        val modelAfter = VehicleOwnRoomModel(
            id = 1,
            idCard = 1,
            idEquip = 500,
            detailEquip = "Equip Detail",
            reg = 12345L,
            state = State.UNHARMED,
            detailColab = "Colab Detail"
        )
        assertEquals(savedModel, modelAfter)
    }

    @Test
    fun `listByIdCard - Check return list correct`() = runTest {
        val model1 = VehicleOwnRoomModel(idCard = 1, idEquip = 1, reg = 1L, state = State.UNHARMED, detailEquip = null, detailColab = null)
        val model2 = VehicleOwnRoomModel(idCard = 1, idEquip = 2, reg = 2L, state = State.UNHARMED, detailEquip = null, detailColab = null)
        val model3 = VehicleOwnRoomModel(idCard = 2, idEquip = 3, reg = 3L, state = State.UNHARMED, detailEquip = null, detailColab = null)

        vehicleOwnDao.insert(model1)
        vehicleOwnDao.insert(model2)
        vehicleOwnDao.insert(model3)

        val result = datasource.listByIdCard(1)
        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrNull()!!.size)
    }

    @Test
    fun `deleteByIdCard - Check execution correct`() = runTest {
        val model1 = VehicleOwnRoomModel(idCard = 1, idEquip = 1, reg = 1L, state = State.UNHARMED, detailEquip = null, detailColab = null)
        val model2 = VehicleOwnRoomModel(idCard = 2, idEquip = 2, reg = 2L, state = State.UNHARMED, detailEquip = null, detailColab = null)

        vehicleOwnDao.insert(model1)
        vehicleOwnDao.insert(model2)

        assertEquals(2, vehicleOwnDao.all().size)

        val result = datasource.deleteByIdCard(1)
        assertTrue(result.isSuccess)
        assertEquals(1, vehicleOwnDao.all().size)
    }

}
