package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.room.dao.stable.NatureDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.NatureRoomModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class IListNatureTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: ListNature

    @Inject
    lateinit var natureDao: NatureDao

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_return_failure_if_not_have_data() =
        runTest {
            val result = usecase()
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
    fun check_return_correct_if_have_data_nature_room_and_not_have_data_in_card() =
        runTest {
            natureDao.insertAll(
                listOf(
                    NatureRoomModel(
                        id = 1,
                        description = "TEST"
                    ),
                    NatureRoomModel(
                        id = 2,
                        description = "TEST2"
                    )
                )
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(
                    ItemCheckBoxScreenModel(
                        id = 1,
                        desc = "TEST",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 2,
                        desc = "TEST2",
                        flag = false
                    )
                )
            )
        }

    @Test
    fun check_return_correct_if_have_data() =
        runTest {
            natureDao.insertAll(
                listOf(
                    NatureRoomModel(
                        id = 1,
                        description = "TEST"
                    ),
                    NatureRoomModel(
                        id = 2,
                        description = "TEST2"
                    )
                )
            )
            cardSharedPreferencesDatasource.save(
                CardSharedPreferencesModel(
                    idNatureList = listOf(1)
                )
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(
                    ItemCheckBoxScreenModel(
                        id = 1,
                        desc = "TEST",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 2,
                        desc = "TEST2",
                        flag = false
                    )
                )
            )
        }

}