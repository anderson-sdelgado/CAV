package br.com.usinasantafe.cav.external.room.datasource.stable

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.room.dao.DatabaseRoom
import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
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
class IEquipRoomDatasourceTest {

    private lateinit var equipDao: EquipDao
    private lateinit var db: DatabaseRoom
    private lateinit var datasource: IEquipRoomDatasource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseRoom::class.java
        ).allowMainThreadQueries().build()
        equipDao = db.equipDao()
        datasource = IEquipRoomDatasource(equipDao)
    }

    @After
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `addAll - Check failure if have row repeated`() =
        runTest {
            val list = equipDao.all()
            assertEquals(
                list.size,
                0
            )
            val result = datasource.addAll(
                listOf(
                    EquipRoomModel(
                        id = 1,
                        nro = 10,
                        desc = "TESTE1"
                    ),
                    EquipRoomModel(
                        id = 1,
                        nro = 10,
                        desc = "TESTE1"
                    ),
                )
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IEquipRoomDatasource.addAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "android.database.sqlite.SQLiteConstraintException: DB[1] step() [INSERT OR ABORT INTO `tb_equip` (`id`,`nro`,`desc`) VALUES (?,?,?)]DB[1][C] [UNIQUE constraint failed: tb_equip.id] (code 1555 SQLITE_CONSTRAINT_PRIMARYKEY)"
            )
            val qtdAfter = equipDao.all().size
            assertEquals(
                qtdAfter,
                0
            )
        }

    @Test
    fun `addAll - Check success if have row is correct`() =
        runTest {
            val qtdBefore = equipDao.all().size
            assertEquals(
                qtdBefore,
                0
            )
            val result = datasource.addAll(
                listOf(
                    EquipRoomModel(
                        id = 1,
                        nro = 10,
                        desc = "TESTE1"
                    ),
                    EquipRoomModel(
                        id = 2,
                        nro = 20,
                        desc = "TESTE2"
                    ),
                )
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                Unit
            )
            val qtdAfter = equipDao.all().size
            assertEquals(
                qtdAfter,
                2
            )
            val list = equipDao.all()
            assertEquals(
                list.size,
                2
            )
            val model1 = list[0]
            assertEquals(
                model1.id,
                1
            )
            assertEquals(
                model1.nro,
                10
            )
            assertEquals(
                model1.desc,
                "TESTE1"
            )
            val model2 = list[1]
            assertEquals(
                model2.id,
                2
            )
            assertEquals(
                model2.nro,
                20
            )
            assertEquals(
                model2.desc,
                "TESTE2"
            )
        }

    @Test
    fun `deleteAll - Check execution correct`() =
        runTest {
            equipDao.insertAll(
                listOf(
                    EquipRoomModel(
                        id = 1,
                        nro = 10,
                        desc = "TESTE1"
                    )
                )
            )
            val listBefore = equipDao.all()
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
            val listAfter = equipDao.all()
            assertEquals(
                listAfter.size,
                0
            )
        }

    @Test
    fun `hasNro - Check return false if table is empty`() =
        runTest {
            val result = datasource.hasNro(200)
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
    fun `hasNro - Check return false if not have data fielded`() =
        runTest {
            equipDao.insertAll(
                listOf(
                    EquipRoomModel(
                        id = 1,
                        nro = 100,
                        desc = "TRATOR"
                    )
                )
            )
            val result = datasource.hasNro(200)
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
    fun `hasNro - Check return true if have data fielded`() =
        runTest {
            equipDao.insertAll(
                listOf(
                    EquipRoomModel(
                        id = 2,
                        nro = 200,
                        desc = "TRATOR"
                    )
                )
            )
            val result = datasource.hasNro(200)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                true
            )
        }

    @Test
    fun `getIdByNro - Check return failure if not have data fielded`() =
        runTest {
            val result = datasource.getIdByNro(200)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IEquipRoomDatasource.getIdByNro"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.util.NoSuchElementException: nro 200 not found"
            )
        }

    @Test
    fun `getIdByNro - Check return correct if have data fielded`() =
        runTest {
            equipDao.insertAll(
                listOf(
                    EquipRoomModel(
                        id = 2,
                        nro = 200,
                        desc = "TRATOR"
                    )
                )
            )
            val result = datasource.getIdByNro(200)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                2
            )
        }

}