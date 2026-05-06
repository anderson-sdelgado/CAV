package br.com.usinasantafe.cav.external.room.datasource.stable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.stable.SupportTeamsDao
import br.com.usinasantafe.cav.infra.models.room.stable.SupportTeamsRoomModel
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.intArrayOf
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ISupportTeamsRoomDatasourceTest {

    private lateinit var supportTeamsDao: SupportTeamsDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: ISupportTeamsRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        supportTeamsDao = db.supportTeamsDao()
        datasource = ISupportTeamsRoomDatasource(supportTeamsDao)
    }

    @After
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `addAll - Check failure if have row repeated`() =
        runTest {
            val listBefore = supportTeamsDao.all()
            assertEquals(
                listBefore.size,
                0
            )
            val result = datasource.addAll(
                listOf(
                    SupportTeamsRoomModel(
                        id = 1,
                        desc = "TEST"
                    ),
                    SupportTeamsRoomModel(
                        id = 1,
                        desc = "TEST"
                    )
                )
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISupportTeamsRoomDatasource.addAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "android.database.sqlite.SQLiteConstraintException: DB[1] step() [INSERT OR ABORT INTO `tb_support_teams` (`id`,`desc`) VALUES (?,?)]DB[1][C] [UNIQUE constraint failed: tb_support_teams.id] (code 1555 SQLITE_CONSTRAINT_PRIMARYKEY)"
            )
            val listAfter = supportTeamsDao.all()
            assertEquals(
                listAfter.size,
                0
            )
        }

    @Test
    fun `addAll - Check success if have row is correct`() =
        runTest {
            val listBefore = supportTeamsDao.all()
            assertEquals(
                listBefore.size,
                0
            )
            val result = datasource.addAll(
                listOf(
                    SupportTeamsRoomModel(
                        id = 1,
                        desc = "TEST"
                    ),
                    SupportTeamsRoomModel(
                        id = 2,
                        desc = "TEST2"
                    ),
                )
            )
            assertEquals(
                result.isSuccess,
                true
            )
            val listAfter = supportTeamsDao.all()
            assertEquals(
                listAfter.size,
                2
            )
            assertEquals(
                listAfter.size,
                2
            )
            val model1 = listAfter[0]
            assertEquals(
                model1.id,
                1
            )
            assertEquals(
                model1.desc,
                "TEST"
            )
            val model2 = listAfter[1]
            assertEquals(
                model2.id,
                2
            )
            assertEquals(
                model2.desc,
                "TEST2"
            )
        }

    @Test
    fun `deleteAll - Check execution correct`() =
        runTest {
            supportTeamsDao.insertAll(
                listOf(
                    SupportTeamsRoomModel(
                        id = 1,
                        desc = "TEST"
                    )
                )
            )
            val listBefore = supportTeamsDao.all()
            assertEquals(
                listBefore.size,
                1
            )
            val result = datasource.deleteAll()
            assertEquals(
                result.isSuccess,
                true
            )
            val listAfter = supportTeamsDao.all()
            assertEquals(
                listAfter.size,
                0
            )
        }

}