package br.com.usinasantafe.cav.external.room.datasource.stable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.stable.ROptionItemDataLocalDao
import br.com.usinasantafe.cav.infra.models.room.stable.ROptionItemDataLocalRoomModel
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
class IROptionItemDataLocalRoomDatasourceTest {

    private lateinit var rOptionItemDataLocalDao: ROptionItemDataLocalDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: IROptionItemDataLocalRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        rOptionItemDataLocalDao = db.rOptionItemDataLocalDao()
        datasource = IROptionItemDataLocalRoomDatasource(rOptionItemDataLocalDao)
    }

    @After
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `addAll - Check failure if have row repeated`() =
        runTest {
            val listBefore = rOptionItemDataLocalDao.all()
            assertEquals(
                listBefore.size,
                0
            )
            val result = datasource.addAll(
                listOf(
                    ROptionItemDataLocalRoomModel(
                        id = 1,
                        idItem = 1,
                        idOption = 1
                    ),
                    ROptionItemDataLocalRoomModel(
                        id = 1,
                        idItem = 1,
                        idOption = 1
                    )
                )
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IROptionItemDataLocalRoomDatasource.addAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "android.database.sqlite.SQLiteConstraintException: DB[1] step() [INSERT OR ABORT INTO `tb_r_option_item_data_local` (`id`,`idOption`,`idItem`) VALUES (?,?,?)]DB[1][C] [UNIQUE constraint failed: tb_r_option_item_data_local.id] (code 1555 SQLITE_CONSTRAINT_PRIMARYKEY)"
            )
            val listAfter = rOptionItemDataLocalDao.all()
            assertEquals(
                listAfter.size,
                0
            )
        }

    @Test
    fun `addAll - Check success if have row is correct`() =
        runTest {
            val listBefore = rOptionItemDataLocalDao.all()
            assertEquals(
                listBefore.size,
                0
            )
            val result = datasource.addAll(
                listOf(
                    ROptionItemDataLocalRoomModel(
                        id = 1,
                        idItem = 1,
                        idOption = 1
                    ),
                    ROptionItemDataLocalRoomModel(
                        id = 2,
                        idItem = 2,
                        idOption = 2
                    ),
                )
            )
            assertEquals(
                result.isSuccess,
                true
            )
            val listAfter = rOptionItemDataLocalDao.all()
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
                model1.idOption,
                1
            )
            assertEquals(
                model1.idItem,
                1
            )
            val model2 = listAfter[1]
            assertEquals(
                model2.id,
                2
            )
            assertEquals(
                model2.idOption,
                2
            )
            assertEquals(
                model2.idItem,
                2
            )
        }

    @Test
    fun `deleteAll - Check execution correct`() =
        runTest {
            rOptionItemDataLocalDao.insertAll(
                listOf(
                    ROptionItemDataLocalRoomModel(
                        id = 1,
                        idItem = 1,
                        idOption = 1
                    )
                )
            )
            val listBefore = rOptionItemDataLocalDao.all()
            assertEquals(
                listBefore.size,
                1
            )
            val result = datasource.deleteAll()
            assertEquals(
                result.isSuccess,
                true
            )
            val listAfter = rOptionItemDataLocalDao.all()
            assertEquals(
                listAfter.size,
                0
            )
        }

}