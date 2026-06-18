package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.room.dao.stable.DataLocalDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.DataLocalRoomModel
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
class ISetDataLocalListTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetDataLocalList

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var dataLocalDao: DataLocalDao


    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_empty_list_if_not_have_data() =
        runTest {
            val resultGetBefore = cardSharedPreferencesDatasource.get()
            assertEquals(
                resultGetBefore.isSuccess,
                true
            )
            val modelBefore = resultGetBefore.getOrNull()!!
            assertEquals(
                modelBefore.idDataLocalList,
                emptyList()
            )
            val result = usecase(
                idOption = 10,
                list = emptyList()
            )
            assertEquals(
                result.isSuccess,
                true
            )
            val resultGetAfter = cardSharedPreferencesDatasource.get()
            assertEquals(
                resultGetAfter.isSuccess,
                true
            )
            val modelAfter = resultGetAfter.getOrNull()!!
            assertEquals(
                modelAfter.idDataLocalList,
                emptyList()
            )
        }

    @Test
    fun check_alter_data_if_process_execute_successfully() =
        runTest {
            dataLocalDao.insertAll(
                listOf(
                    DataLocalRoomModel(
                        id = 1,
                        idOption = 1,
                        idItem = 1
                    ),
                    DataLocalRoomModel(
                        id = 2,
                        idOption = 1,
                        idItem = 2
                    ),
                    DataLocalRoomModel(
                        id = 3,
                        idOption = 2,
                        idItem = 10
                    ),
                    DataLocalRoomModel(
                        id = 4,
                        idOption = 5,
                        idItem = 10
                    ),
                    DataLocalRoomModel(
                        id = 5,
                        idOption = 5,
                        idItem = 30
                    ),
                    DataLocalRoomModel(
                        id = 6,
                        idOption = 5,
                        idItem = 60
                    ),
                    DataLocalRoomModel(
                        id = 7,
                        idOption = 10,
                        idItem = 100
                    ),
                    DataLocalRoomModel(
                        id = 8,
                        idOption = 10,
                        idItem = 200
                    )
                )
            )
            cardSharedPreferencesDatasource.save(
                CardSharedPreferencesModel(
                    idDataLocalList = listOf(1, 3, 5, 6, 8, 12)
                )
            )
            val resultGetBefore = cardSharedPreferencesDatasource.get()
            assertEquals(
                resultGetBefore.isSuccess,
                true
            )
            val modelBefore = resultGetBefore.getOrNull()!!
            assertEquals(
                modelBefore.idDataLocalList,
                listOf(1, 3, 5, 6, 8, 12)
            )
            val result = usecase(
                idOption = 5,
                list = listOf(
                    ItemCheckBoxScreenModel(
                        id = 10,
                        desc = "ITEM 1",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 20,
                        desc = "ITEM 2",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 30,
                        desc = "ITEM 3",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 40,
                        desc = "ITEM 4",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 50,
                        desc = "ITEM 5",
                        flag = true
                    )
                )
            )
            assertEquals(
                result.isSuccess,
                true
            )
            val resultGetAfter = cardSharedPreferencesDatasource.get()
            assertEquals(
                resultGetAfter.isSuccess,
                true
            )
            val modelAfter = resultGetAfter.getOrNull()!!
            assertEquals(
                modelAfter.idDataLocalList,
                listOf(1, 3, 8, 12, 20, 30, 50)
            )
        }

}