package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.room.dao.stable.ItemDataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.DataLocalDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.ItemDataLocalRoomModel
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
class IListItemDataLocalTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: ListItemDataLocal

    @Inject
    lateinit var dataLocalDao: DataLocalDao

    @Inject
    lateinit var itemDataLocalDao: ItemDataLocalDao

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_return_emptyList_if_not_have_data() =
        runTest {
            val result = usecase(1)
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
    fun check_return_failure_if_not_have_data_in_item_room() =
        runTest {
            dataLocalDao.insertAll(
                listOf(
                    DataLocalRoomModel(
                        id = 1,
                        idOption = 10,
                        idItem = 100
                    ),
                    DataLocalRoomModel(
                        id = 2,
                        idOption = 10,
                        idItem = 200
                    ),
                    DataLocalRoomModel(
                        id = 3,
                        idOption = 10,
                        idItem = 300
                    ),
                    DataLocalRoomModel(
                        id = 4,
                        idOption = 20,
                        idItem = 400
                    ),
                    DataLocalRoomModel(
                        id = 5,
                        idOption = 20,
                        idItem = 100
                    ),
                    DataLocalRoomModel(
                        id = 6,
                        idOption = 30,
                        idItem = 500
                    )
                )
            )
            val result = usecase(20)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IListItemDataLocal -> Collection contains no element matching the predicate."
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "null"
            )
        }

    @Test
    fun check_return_list_all_flag_false_if_have_all_data_except_in_card_room() =
        runTest {
            dataLocalDao.insertAll(
                listOf(
                    DataLocalRoomModel(
                        id = 1,
                        idOption = 10,
                        idItem = 100
                    ),
                    DataLocalRoomModel(
                        id = 2,
                        idOption = 10,
                        idItem = 200
                    ),
                    DataLocalRoomModel(
                        id = 3,
                        idOption = 10,
                        idItem = 300
                    ),
                    DataLocalRoomModel(
                        id = 4,
                        idOption = 20,
                        idItem = 400
                    ),
                    DataLocalRoomModel(
                        id = 5,
                        idOption = 20,
                        idItem = 100
                    ),
                    DataLocalRoomModel(
                        id = 6,
                        idOption = 20,
                        idItem = 300
                    ),
                    DataLocalRoomModel(
                        id = 7,
                        idOption = 30,
                        idItem = 500
                    )
                )
            )
            itemDataLocalDao.insertAll(
                listOf(
                    ItemDataLocalRoomModel(
                        id = 100,
                        description = "ITEM 100"
                    ),
                    ItemDataLocalRoomModel(
                        id = 200,
                        description = "ITEM 200"
                    ),
                    ItemDataLocalRoomModel(
                        id = 300,
                        description = "ITEM 300"
                    ),
                    ItemDataLocalRoomModel(
                        id = 400,
                        description = "ITEM 400"
                    ),
                    ItemDataLocalRoomModel(
                        id = 500,
                        description = "ITEM 500"
                    )
                )
            )
            val result = usecase(20)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(
                    ItemCheckBoxScreenModel(
                        id = 4,
                        desc = "ITEM 400",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 5,
                        desc = "ITEM 100",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 6,
                        desc = "ITEM 300",
                        flag = false
                    )
                )
            )
        }

    @Test
    fun check_return_correct_list_if_have_all_data() =
        runTest {
            dataLocalDao.insertAll(
                listOf(
                    DataLocalRoomModel(
                        id = 1,
                        idOption = 10,
                        idItem = 100
                    ),
                    DataLocalRoomModel(
                        id = 2,
                        idOption = 10,
                        idItem = 200
                    ),
                    DataLocalRoomModel(
                        id = 3,
                        idOption = 10,
                        idItem = 300
                    ),
                    DataLocalRoomModel(
                        id = 4,
                        idOption = 20,
                        idItem = 400
                    ),
                    DataLocalRoomModel(
                        id = 5,
                        idOption = 20,
                        idItem = 100
                    ),
                    DataLocalRoomModel(
                        id = 6,
                        idOption = 20,
                        idItem = 300
                    ),
                    DataLocalRoomModel(
                        id = 7,
                        idOption = 30,
                        idItem = 500
                    )
                )
            )
            itemDataLocalDao.insertAll(
                listOf(
                    ItemDataLocalRoomModel(
                        id = 100,
                        description = "ITEM 100"
                    ),
                    ItemDataLocalRoomModel(
                        id = 200,
                        description = "ITEM 200"
                    ),
                    ItemDataLocalRoomModel(
                        id = 300,
                        description = "ITEM 300"
                    ),
                    ItemDataLocalRoomModel(
                        id = 400,
                        description = "ITEM 400"
                    ),
                    ItemDataLocalRoomModel(
                        id = 500,
                        description = "ITEM 500"
                    )
                )
            )
            cardSharedPreferencesDatasource.save(
                CardSharedPreferencesModel(
                    idDataLocalList = listOf(4, 6)
                )
            )
            val result = usecase(20)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(
                    ItemCheckBoxScreenModel(
                        id = 4,
                        desc = "ITEM 400",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 5,
                        desc = "ITEM 100",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 6,
                        desc = "ITEM 300",
                        flag = true
                    )
                )
            )
        }

}