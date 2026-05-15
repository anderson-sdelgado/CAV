package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.room.dao.stable.OptionDataLocalDao
import br.com.usinasantafe.cav.infra.models.room.stable.OptionDataLocalRoomModel
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class IListOptionDataLocalTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: ListOptionDataLocal

    @Inject
    lateinit var optionDataLocalDao: OptionDataLocalDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_return_emptyList_if_not_have_data() =
        runTest {
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrThrow(),
                emptyList()
            )
        }

    @Test
    fun check_return_list_if_have_data() =
        runTest {
            optionDataLocalDao.insertAll(
                listOf(
                    OptionDataLocalRoomModel(
                        id = 1,
                        description = "Test"
                    )
                )
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrThrow(),
                listOf(
                    ItemListScreenModel(
                        id = 1,
                        description = "Test"
                    )
                )
            )
        }

}