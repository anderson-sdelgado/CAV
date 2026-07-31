package br.com.usinasantafe.cav.external.room.datasource.variable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.variable.InvolvedExternalDao
import br.com.usinasantafe.cav.infra.models.room.variable.InvolvedExternalRoomModel
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
class IPeopleExternalRoomDatasourceTest {

    private lateinit var involvedExternalDao: InvolvedExternalDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: IInvolvedExternalRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        involvedExternalDao = db.involvedExternalDao()
        datasource = IInvolvedExternalRoomDatasource(involvedExternalDao)
    }

    @After
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `add - Check success if row is inserted correctly`() = runTest {
        val modelBefore = InvolvedExternalRoomModel(
            idCard = 1,
            document = "123456",
            name = "Involved Name",
            phone = "987654321",
            address = "Test Address",
            state = State.UNHARMED,
            detail = "Involved Detail"
        )

        val result = datasource.add(modelBefore)

        assertTrue(result.isSuccess)
        val id = result.getOrNull()!!
        assertTrue(id > 0)

        val list = involvedExternalDao.all()
        assertEquals(1, list.size)
        val savedModel = list[0]
        val modelAfter = InvolvedExternalRoomModel(
            id = 1,
            idCard = 1,
            document = "123456",
            name = "Involved Name",
            phone = "987654321",
            address = "Test Address",
            state = State.UNHARMED,
            detail = "Involved Detail"
        )
        assertEquals(savedModel, modelAfter)
    }

    @Test
    fun `listByIdCard - Check return list correct`() = runTest {
        val model1 = InvolvedExternalRoomModel(idCard = 1, name = "N1", phone = "P1", address = null, state = State.UNHARMED, detail = null, document = null)
        val model2 = InvolvedExternalRoomModel(idCard = 1, name = "N2", phone = "P2", address = null, state = State.UNHARMED, detail = null, document = null)
        val model3 = InvolvedExternalRoomModel(idCard = 2, name = "N3", phone = "P3", address = null, state = State.UNHARMED, detail = null, document = null)

        involvedExternalDao.insert(model1)
        involvedExternalDao.insert(model2)
        involvedExternalDao.insert(model3)

        val result = datasource.listByIdCard(1)
        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrNull()!!.size)
    }

    @Test
    fun `deleteByIdCard - Check execution correct`() = runTest {
        val model1 = InvolvedExternalRoomModel(idCard = 1, name = "N1", phone = "P1", address = null, state = State.UNHARMED, detail = null, document = null)
        val model2 = InvolvedExternalRoomModel(idCard = 2, name = "N2", phone = "P2", address = null, state = State.UNHARMED, detail = null, document = null)

        involvedExternalDao.insert(model1)
        involvedExternalDao.insert(model2)

        assertEquals(2, involvedExternalDao.all().size)

        val result = datasource.deleteByIdCard(1)
        assertTrue(result.isSuccess)
        assertEquals(1, involvedExternalDao.all().size)
    }

}
