package br.com.usinasantafe.cav.external.room.datasource.variable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.variable.PassengerColabDao
import br.com.usinasantafe.cav.infra.models.room.variable.PassengerColabRoomModel
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
class IPassengerColabRoomDatasourceTest {

    private lateinit var passengerColabDao: PassengerColabDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: IPassengerColabRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        passengerColabDao = db.colabCardDao()
        datasource = IPassengerColabRoomDatasource(passengerColabDao)
    }

    @After
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `add - Check success if row is inserted correctly`() = runTest {
        val modelBefore = PassengerColabRoomModel(
            idVehicle = 1,
            reg = 12345L,
            state = State.UNHARMED,
            detail = "Passenger Detail"
        )

        val result = datasource.add(modelBefore)

        assertTrue(result.isSuccess)
        val id = result.getOrNull()!!
        assertTrue(id > 0)

        val list = passengerColabDao.all()
        assertEquals(1, list.size)
        val savedModel = list[0]
        val modelAfter = PassengerColabRoomModel(
            id = 1,
            idVehicle = 1,
            reg = 12345L,
            state = State.UNHARMED,
            detail = "Passenger Detail"
        )
        assertEquals(savedModel, modelAfter)
    }
}
