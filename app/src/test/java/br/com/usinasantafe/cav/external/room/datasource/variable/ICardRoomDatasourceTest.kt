package br.com.usinasantafe.cav.external.room.datasource.variable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.variable.CardDao
import br.com.usinasantafe.cav.infra.models.room.variable.CardRoomModel
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
}
