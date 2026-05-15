package br.com.usinasantafe.cav.external.room.datasource.stable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.stable.NatureDao
import br.com.usinasantafe.cav.infra.models.room.stable.NatureRoomModel
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
class INatureRoomDatasourceTest {

    private lateinit var natureDao: NatureDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: INatureRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        natureDao = db.natureDao()
        datasource = INatureRoomDatasource(natureDao)
    }

    @After
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `addAll - Check failure if have row repeated`() =
        runTest {
            val listBefore = natureDao.all()
            assertEquals(
                listBefore.size,
                0
            )
            val result = datasource.addAll(
                listOf(
                    NatureRoomModel(
                        id = 1,
                        description = "TEST"
                    ),
                    NatureRoomModel(
                        id = 1,
                        description = "TEST"
                    )
                )
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "INatureRoomDatasource.addAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "android.database.sqlite.SQLiteConstraintException: DB[1] step() [INSERT OR ABORT INTO `tb_nature` (`id`,`desc`) VALUES (?,?)]DB[1][C] [UNIQUE constraint failed: tb_nature.id] (code 1555 SQLITE_CONSTRAINT_PRIMARYKEY)"
            )
            val listAfter = natureDao.all()
            assertEquals(
                listAfter.size,
                0
            )
        }

    @Test
    fun `addAll - Check success if have row is correct`() =
        runTest {
            val listBefore = natureDao.all()
            assertEquals(
                listBefore.size,
                0
            )
            val result = datasource.addAll(
                listOf(
                    NatureRoomModel(
                        id = 1,
                        description = "TEST"
                    ),
                    NatureRoomModel(
                        id = 2,
                        description = "TEST2"
                    ),
                )
            )
            assertEquals(
                result.isSuccess,
                true
            )
            val listAfter = natureDao.all()
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
                model1.description,
                "TEST"
            )
            val model2 = listAfter[1]
            assertEquals(
                model2.id,
                2
            )
            assertEquals(
                model2.description,
                "TEST2"
            )
        }

    @Test
    fun `deleteAll - Check execution correct`() =
        runTest {
            natureDao.insertAll(
                listOf(
                    NatureRoomModel(
                        id = 1,
                        description = "TEST"
                    )
                )
            )
            val listBefore = natureDao.all()
            assertEquals(
                listBefore.size,
                1
            )
            val result = datasource.deleteAll()
            assertEquals(
                result.isSuccess,
                true
            )
            val listAfter = natureDao.all()
            assertEquals(
                listAfter.size,
                0
            )
        }

    @Test
    fun `listAll - Check return list if have data`() =
        runTest {
            natureDao.insertAll(
                listOf(
                    NatureRoomModel(
                        id = 1,
                        description = "TEST"
                    )
                )
            )
            val result = datasource.listAll()
            assertEquals(
                result.isSuccess,
                true
            )
            val list = result.getOrNull()!!
            assertEquals(
                list,
                listOf(
                    NatureRoomModel(
                        id = 1,
                        description = "TEST"
                    )
                )
            )
        }

    @Test
    fun `listByIdList - Check return emptyList if not have data`() =
        runTest {
            val result = datasource.listByIdList(listOf(1, 2))
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                emptyList()
            )
        }

    @Test
    fun `listByIdList - Check return list if have data fielded`() =
        runTest {
            natureDao.insertAll(
                listOf(
                    NatureRoomModel(
                        id = 1,
                        description = "Item 1"
                    ),
                    NatureRoomModel(
                        id = 2,
                        description = "Item 2"
                    ),
                    NatureRoomModel(
                        id = 3,
                        description = "Item 3"
                    ),
                    NatureRoomModel(
                        id = 4,
                        description = "Item 4"
                    )
                )
            )
            val result = datasource.listByIdList(listOf(2, 3))
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(
                    NatureRoomModel(
                        id = 2,
                        description = "Item 2"
                    ),
                    NatureRoomModel(
                        id = 3,
                        description = "Item 3"
                    ),
                )
            )
        }
}