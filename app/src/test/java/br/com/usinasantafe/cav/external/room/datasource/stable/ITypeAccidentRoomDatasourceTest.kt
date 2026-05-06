package br.com.usinasantafe.cav.external.room.datasource.stable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.stable.TypeAccidentDao
import br.com.usinasantafe.cav.infra.models.room.stable.TypeAccidentRoomModel
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
class ITypeAccidentRoomDatasourceTest {

    private lateinit var typeAccidentDao: TypeAccidentDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: ITypeAccidentRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        typeAccidentDao = db.typeAccidentDao()
        datasource = ITypeAccidentRoomDatasource(typeAccidentDao)
    }

    @After
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `addAll - Check failure if have row repeated`() =
        runTest {
            val listBefore = typeAccidentDao.all()
            assertEquals(
                listBefore.size,
                0
            )
            val result = datasource.addAll(
                listOf(
                    TypeAccidentRoomModel(
                        id = 1,
                        desc = "TEST"
                    ),
                    TypeAccidentRoomModel(
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
                "ITypeAccidentRoomDatasource.addAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "android.database.sqlite.SQLiteConstraintException: DB[1] step() [INSERT OR ABORT INTO `tb_type_accident` (`id`,`desc`) VALUES (?,?)]DB[1][C] [UNIQUE constraint failed: tb_type_accident.id] (code 1555 SQLITE_CONSTRAINT_PRIMARYKEY)"
            )
            val listAfter = typeAccidentDao.all()
            assertEquals(
                listAfter.size,
                0
            )
        }

    @Test
    fun `addAll - Check success if have row is correct`() =
        runTest {
            val listBefore = typeAccidentDao.all()
            assertEquals(
                listBefore.size,
                0
            )
            val result = datasource.addAll(
                listOf(
                    TypeAccidentRoomModel(
                        id = 1,
                        desc = "TEST"
                    ),
                    TypeAccidentRoomModel(
                        id = 2,
                        desc = "TEST2"
                    ),
                )
            )
            assertEquals(
                result.isSuccess,
                true
            )
            val listAfter = typeAccidentDao.all()
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
            typeAccidentDao.insertAll(
                listOf(
                    TypeAccidentRoomModel(
                        id = 1,
                        desc = "TEST"
                    )
                )
            )
            val listBefore = typeAccidentDao.all()
            assertEquals(
                listBefore.size,
                1
            )
            val result = datasource.deleteAll()
            assertEquals(
                result.isSuccess,
                true
            )
            val listAfter = typeAccidentDao.all()
            assertEquals(
                listAfter.size,
                0
            )
        }

}