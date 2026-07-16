package br.com.usinasantafe.cav.external.room.datasource.variable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.variable.EquipSecDao
import br.com.usinasantafe.cav.infra.models.room.variable.EquipSecRoomModel
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
class IEquipSecRoomDatasourceTest {

    private lateinit var equipSecDao: EquipSecDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: IEquipSecRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        equipSecDao = db.equipCardDao()
        datasource = IEquipSecRoomDatasource(equipSecDao)
    }

    @After
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `add - Check success if row is inserted correctly`() = runTest {
        val modelBefore = EquipSecRoomModel(
            idVehicle = 1,
            idEquip = 100,
            detail = "Equip Detail"
        )

        val result = datasource.add(modelBefore)

        assertTrue(result.isSuccess)
        val id = result.getOrNull()!!
        assertTrue(id > 0)

        val list = equipSecDao.all()
        assertEquals(1, list.size)
        val savedModel = list[0]
        val modelAfter = EquipSecRoomModel(
            id = 1,
            idVehicle = 1,
            idEquip = 100,
            detail = "Equip Detail"
        )
        assertEquals(savedModel, modelAfter)
    }
}
