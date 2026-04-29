package br.com.usinasantafe.cav.domain.usecases.common

import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class IHasNroEquipTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: HasNroEquip

    @Inject
    lateinit var equipDao: EquipDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_return_failure_if_value_of_field_is_incorrect() =
        runTest {
            val result = usecase("1df85a")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IHasNroEquip -> toLong"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NumberFormatException: For input string: \"1df85a\""
            )
        }

    @Test
    fun check_return_false_if_not_have_data_fielded() =
        runTest {
            val result = usecase("200")
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
    fun check_return_true_if_have_data_fielded() =
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
            val result = usecase("200")
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