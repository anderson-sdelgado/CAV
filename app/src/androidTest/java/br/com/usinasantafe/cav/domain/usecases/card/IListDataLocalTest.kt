package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.room.dao.stable.ItemDataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.OptionDataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.DataLocalDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.ItemDataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.OptionDataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.DataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class IListDataLocalTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetDataLocalList

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var dataLocalDao: DataLocalDao

    @Inject
    lateinit var optionDataLocalDao: OptionDataLocalDao

    @Inject
    lateinit var itemDataLocalDao: ItemDataLocalDao

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
                result.getOrNull()!!,
                emptyList()
            )
        }

    @Test
    fun check_return_failure_if_not_have_data_in_ROptionItemDataLocal_room() =
        runTest {
            cardSharedPreferencesDatasource.save(
                CardSharedPreferencesModel(
                    idDataLocalList = listOf(1, 2)
                )
            )
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IListDataLocal -> IDataLocalRepository.getROptionItemById -> IROptionItemDataLocalRoomDatasource.getById"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NullPointerException: model is required"
            )
        }

    @Test
    fun check_return_failure_if_not_have_data_in_Option_room() =
        runTest {
            cardSharedPreferencesDatasource.save(
                CardSharedPreferencesModel(
                    idDataLocalList = listOf(1, 2)
                )
            )
            dataLocalDao.insertAll(
                listOf(
                    DataLocalRoomModel(
                        id = 1,
                        idOption = 1,
                        idItem = 2,
                    ),DataLocalRoomModel(
                        id = 2,
                        idOption = 3,
                        idItem = 4,
                    ),
                )
            )
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IListDataLocal -> IDataLocalRepository.getDescOptionById -> IOptionDataLocalRoomDatasource.getDescById"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NullPointerException: desc is required"
            )
        }

    @Test
    fun check_return_failure_if_not_have_data_in_Item_room() =
        runTest {
            cardSharedPreferencesDatasource.save(
                CardSharedPreferencesModel(
                    idDataLocalList = listOf(1, 2)
                )
            )
            dataLocalDao.insertAll(
                listOf(
                    DataLocalRoomModel(
                        id = 1,
                        idOption = 1,
                        idItem = 2,
                    ),DataLocalRoomModel(
                        id = 2,
                        idOption = 3,
                        idItem = 4,
                    ),
                )
            )
            optionDataLocalDao.insertAll(
                listOf(
                    OptionDataLocalRoomModel(
                        id = 1,
                        description = "Option 1"
                    ),
                    OptionDataLocalRoomModel(
                        id = 3,
                        description = "Option 3"
                    )
                )
            )
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IListDataLocal -> IDataLocalRepository.getDescItemById -> IItemDataLocalRoomDatasource.getDescById"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NullPointerException: desc is required"
            )
        }

    @Test
    fun check_return_correct_if_have_all_data() =
        runTest {
            cardSharedPreferencesDatasource.save(
                CardSharedPreferencesModel(
                    idDataLocalList = listOf(1, 2)
                )
            )
            dataLocalDao.insertAll(
                listOf(
                    DataLocalRoomModel(
                        id = 1,
                        idOption = 1,
                        idItem = 2,
                    ),DataLocalRoomModel(
                        id = 2,
                        idOption = 3,
                        idItem = 4,
                    ),
                )
            )
            optionDataLocalDao.insertAll(
                listOf(
                    OptionDataLocalRoomModel(
                        id = 1,
                        description = "Option 1"
                    ),
                    OptionDataLocalRoomModel(
                        id = 3,
                        description = "Option 3"
                    )
                )
            )
            itemDataLocalDao.insertAll(
                listOf(
                    ItemDataLocalRoomModel(
                        id = 2,
                        description = "Item 2"
                    ),
                    ItemDataLocalRoomModel(
                        id = 4,
                        description = "Item 4"
                    ),
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
                    "Option 1" to "Item 2",
                    "Option 3" to "Item 4",
                )
            )
        }

}