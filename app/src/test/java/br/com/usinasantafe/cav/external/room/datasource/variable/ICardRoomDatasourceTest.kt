package br.com.usinasantafe.cav.external.room.datasource.variable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.variable.CardDao
import br.com.usinasantafe.cav.infra.models.room.variable.CardRoomModel
import br.com.usinasantafe.cav.lib.StatusSend
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.Date
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ICardRoomDatasourceTest {

    private lateinit var cardDao: CardDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: ICardRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        cardDao = db.cardDao()
        datasource = ICardRoomDatasource(cardDao)
    }

    @After
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `add - Check success if row is inserted correctly`() = runTest {
        val modelBefore = CardRoomModel(
            regAttendant = 1L,
            idCar = 1,
            address = "Test Address",
            latitude = -20.0,
            longitude = -48.0,
            idNatureList = listOf(1, 2),
            idTypeAccidentList = listOf(3),
            idDataLocalList = listOf(4),
            idSupportTeamsList = listOf(5),
            urlPhotoList = listOf("url1"),
            obs = "Observation"
        )

        val result = datasource.add(modelBefore)

        assertTrue(result.isSuccess)
        val id = result.getOrNull()!!
        assertTrue(id > 0)

        val list = cardDao.all()
        assertEquals(1, list.size)
        val savedModel = list[0]
        val modelAfter = CardRoomModel(
            id = 1,
            regAttendant = 1L,
            idCar = 1,
            address = "Test Address",
            latitude = -20.0,
            longitude = -48.0,
            idNatureList = listOf(1, 2),
            idTypeAccidentList = listOf(3),
            idDataLocalList = listOf(4),
            idSupportTeamsList = listOf(5),
            urlPhotoList = listOf("url1"),
            obs = "Observation"
        )
        assertEquals(savedModel, modelAfter)
    }
    
    @Test
    fun `hasSend - Check return false if has not send`() =
        runTest {
            val result = datasource.hasSend()
            assertTrue(result.isSuccess)
            assertEquals(false, result.getOrNull()!!)
        }

    @Test
    fun `hasSend - Check return true if has send`() =
        runTest {
            val model = CardRoomModel(
                regAttendant = 1L,
                idCar = 1,
                address = "Test Address",
                latitude = -20.0,
                longitude = -48.0,
                idNatureList = listOf(1, 2),
                idTypeAccidentList = listOf(3),
                idDataLocalList = listOf(4),
                idSupportTeamsList = listOf(5),
                urlPhotoList = listOf("url1"),
                obs = "Observation"
            )
            cardDao.insert(model)
            val result = datasource.hasSend()
            assertTrue(result.isSuccess)
            assertEquals(true, result.getOrNull()!!)
        }

    @Test
    fun `getSend - Check return null if has not send`() = runTest {
        val result = datasource.getSend()
        assertTrue(result.isSuccess)
        assertEquals(null, result.getOrNull())
    }

    @Test
    fun `getSend - Check return correct if has send`() = runTest {
        val model1 = CardRoomModel(
            regAttendant = 1L,
            idCar = 1,
            address = "Test Address",
            latitude = -20.0,
            longitude = -48.0,
            idNatureList = listOf(1, 2),
            idTypeAccidentList = listOf(3),
            idDataLocalList = listOf(4),
            idSupportTeamsList = listOf(5),
            urlPhotoList = listOf("url1"),
            obs = "Observation",
            dateHour = Date(1784224761)
        )
        val model2 = CardRoomModel(
            regAttendant = 2L,
            idCar = 2,
            address = "Test Address",
            latitude = -02.0,
            longitude = -38.0,
            idNatureList = listOf(2, 3),
            idTypeAccidentList = listOf(1, 3),
            idDataLocalList = listOf(2),
            idSupportTeamsList = listOf(5),
            urlPhotoList = listOf("url2"),
            obs = "Observation2",
            dateHour = Date(1784073600)
        )
        cardDao.insert(model1)
        cardDao.insert(model2)
        val result = datasource.getSend()
        assertTrue(result.isSuccess)
        val modelAfter = CardRoomModel(
            id = 2,
            regAttendant = 2L,
            idCar = 2,
            address = "Test Address",
            latitude = -02.0,
            longitude = -38.0,
            idNatureList = listOf(2, 3),
            idTypeAccidentList = listOf(1, 3),
            idDataLocalList = listOf(2),
            idSupportTeamsList = listOf(5),
            urlPhotoList = listOf("url2"),
            obs = "Observation2",
            dateHour = Date(1784073600)
        )
        assertEquals(modelAfter, result.getOrNull()!!)
    }

    @Test
    fun `listDelete - Check return list correct`() = runTest {
        val now = Date()
        val twoWeeksAgo = Date(now.time - (14 * 24 * 60 * 60 * 1000L))
        
        val model1 = CardRoomModel(
            regAttendant = 1L, idCar = 1, address = "A1", latitude = 0.0, longitude = 0.0,
            idNatureList = emptyList(), idTypeAccidentList = emptyList(), idDataLocalList = emptyList(), idSupportTeamsList = emptyList(),
            urlPhotoList = emptyList(), obs = "O1", dateHour = twoWeeksAgo, statusSend = StatusSend.SENT
        )
        val model2 = CardRoomModel(
            regAttendant = 2L, idCar = 2, address = "A2", latitude = 0.0, longitude = 0.0,
            idNatureList = emptyList(), idTypeAccidentList = emptyList(), idDataLocalList = emptyList(), idSupportTeamsList = emptyList(),
            urlPhotoList = emptyList(), obs = "O2", dateHour = now, statusSend = StatusSend.SENT
        )
        val model3 = CardRoomModel(
            regAttendant = 3L, idCar = 3, address = "A3", latitude = 0.0, longitude = 0.0,
            idNatureList = emptyList(), idTypeAccidentList = emptyList(), idDataLocalList = emptyList(), idSupportTeamsList = emptyList(),
            urlPhotoList = emptyList(), obs = "O3", dateHour = twoWeeksAgo, statusSend = StatusSend.SEND
        )

        cardDao.insert(model1)
        cardDao.insert(model2)
        cardDao.insert(model3)

        val result = datasource.listDelete()
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()!!.size)
        assertEquals(1L, result.getOrNull()!![0].regAttendant)
    }

    @Test
    fun `deleteById - Check execution correct`() = runTest {
        val model = CardRoomModel(
            regAttendant = 1L, idCar = 1, address = "A1", latitude = 0.0, longitude = 0.0,
            idNatureList = emptyList(), idTypeAccidentList = emptyList(), idDataLocalList = emptyList(), idSupportTeamsList = emptyList(),
            urlPhotoList = emptyList(), obs = "O1"
        )
        val id = cardDao.insert(model).toInt()
        
        assertEquals(1, cardDao.all().size)
        
        val result = datasource.deleteById(id)
        assertTrue(result.isSuccess)
        assertEquals(0, cardDao.all().size)
    }

}
