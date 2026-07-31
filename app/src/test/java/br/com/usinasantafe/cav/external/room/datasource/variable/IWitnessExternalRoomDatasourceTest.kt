package br.com.usinasantafe.cav.external.room.datasource.variable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.variable.WitnessExternalDao
import br.com.usinasantafe.cav.infra.models.room.variable.WitnessExternalRoomModel
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
class IWitnessExternalRoomDatasourceTest {

    private lateinit var witnessExternalDao: WitnessExternalDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: IWitnessExternalRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        witnessExternalDao = db.witnessExternalDao()
        datasource = IWitnessExternalRoomDatasource(witnessExternalDao)
    }

    @After
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `add - Check success if row is inserted correctly`() = runTest {
        val modelBefore = WitnessExternalRoomModel(
            idCard = 1,
            name = "Witness Name",
            phone = "123456",
            detail = "Witness Detail"
        )

        val result = datasource.add(modelBefore)

        assertTrue(result.isSuccess)
        val id = result.getOrNull()!!
        assertTrue(id > 0)

        val list = witnessExternalDao.all()
        assertEquals(1, list.size)
        val savedModel = list[0]
        val modelAfter = WitnessExternalRoomModel(
            id = 1,
            idCard = 1,
            name = "Witness Name",
            phone = "123456",
            detail = "Witness Detail"
        )
        assertEquals(savedModel, modelAfter)
    }

    @Test
    fun `listByIdCard - Check return list correct`() = runTest {
        val model1 = WitnessExternalRoomModel(idCard = 1, name = "N1", phone = "P1", detail = null)
        val model2 = WitnessExternalRoomModel(idCard = 1, name = "N2", phone = "P2", detail = null)
        val model3 = WitnessExternalRoomModel(idCard = 2, name = "N3", phone = "P3", detail = null)

        witnessExternalDao.insert(model1)
        witnessExternalDao.insert(model2)
        witnessExternalDao.insert(model3)

        val result = datasource.listByIdCard(1)
        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrNull()!!.size)
    }

    @Test
    fun `deleteByIdCard - Check execution correct`() = runTest {
        val model1 = WitnessExternalRoomModel(idCard = 1, name = "N1", phone = "P1", detail = null)
        val model2 = WitnessExternalRoomModel(idCard = 2, name = "N2", phone = "P2", detail = null)

        witnessExternalDao.insert(model1)
        witnessExternalDao.insert(model2)

        assertEquals(2, witnessExternalDao.all().size)

        val result = datasource.deleteByIdCard(1)
        assertTrue(result.isSuccess)
        assertEquals(1, witnessExternalDao.all().size)
    }

}
