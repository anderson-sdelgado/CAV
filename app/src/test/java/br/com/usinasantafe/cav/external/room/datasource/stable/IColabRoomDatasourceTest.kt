package br.com.usinasantafe.cav.external.room.datasource.stable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.stable.ColabDao
import br.com.usinasantafe.cav.infra.models.room.stable.ColabRoomModel
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
class IColabRoomDatasourceTest {

    private lateinit var colabDao: ColabDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: IColabRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        colabDao = db.colabDao()
        datasource = IColabRoomDatasource(colabDao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `addAll - Check failure if have row repeated`() =
        runTest {
            val list = colabDao.all()
            assertEquals(
                list.size,
                0
            )
            val result = datasource.addAll(
                listOf(
                    ColabRoomModel(
                        reg = 1,
                        name = "TESTE",
                    ),
                    ColabRoomModel(
                        reg = 1,
                        name = "TESTE",
                    ),
                )
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IColabRoomDatasource.addAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "android.database.sqlite.SQLiteConstraintException: DB[1] step() [INSERT OR ABORT INTO `tb_colab` (`regColab`,`nameColab`) VALUES (?,?)]DB[1][C] [UNIQUE constraint failed: tb_colab.regColab] (code 1555 SQLITE_CONSTRAINT_PRIMARYKEY)"
            )
            val qtdAfter = colabDao.all().size
            assertEquals(
                qtdAfter,
                0
            )
        }

    @Test
    fun `addAll - Check success if have row is correct`() =
        runTest {
            val qtdBefore = colabDao.all().size
            assertEquals(
                qtdBefore,
                0
            )
            val result = datasource.addAll(
                listOf(
                    ColabRoomModel(
                        reg = 1,
                        name = "TESTE",
                    ),
                    ColabRoomModel(
                        reg = 2,
                        name = "TESTE2",
                    ),
                )
            )
            assertEquals(
                result.isSuccess,
                true
            )
            val qtdAfter = colabDao.all().size
            assertEquals(
                qtdAfter,
                2
            )
            val list = colabDao.all()
            assertEquals(
                list.size,
                2
            )
            val model1 = list[0]
            assertEquals(
                model1.reg,
                1
            )
            assertEquals(
                model1.name,
                "TESTE"
            )
            val model2 = list[1]
            assertEquals(
                model2.reg,
                2
            )
            assertEquals(
                model2.name,
                "TESTE2"
            )
        }

    @Test
    fun `deleteAll - Check execution correct`() =
        runTest {
            colabDao.insertAll(
                listOf(
                    ColabRoomModel(
                        reg = 1,
                        name = "TESTE",
                    )
                )
            )
            val listBefore = colabDao.all()
            assertEquals(
                listBefore.size,
                1
            )
            val result = datasource.deleteAll()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                Unit
            )
            val listAfter = colabDao.all()
            assertEquals(
                listAfter.size,
                0
            )
        }

    @Test
    fun `hasReg - Check return false if table is empty`() =
        runTest {
            val result = datasource.hasReg(19759)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                false
            )
        }

    @Test
    fun `hasReg - Check return false if not have data fielded`() =
        runTest {
            colabDao.insertAll(
                listOf(
                    ColabRoomModel(
                        reg = 18078,
                        name = "RONALDO"
                    )
                )
            )
            val result = datasource.hasReg(19759)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                false
            )
        }

    @Test
    fun `hasReg - Check return true if have data fielded`() =
        runTest {
            colabDao.insertAll(
                listOf(
                    ColabRoomModel(
                        reg = 18078,
                        name = "RONALDO"
                    )
                )
            )
            val result = datasource.hasReg(18078)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
               true
            )
        }
}