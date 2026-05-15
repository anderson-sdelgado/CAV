package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.room.dao.stable.TypeAccidentDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.TypeAccidentRoomModel
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
class IGetTypeAccidentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: GetTypeAccident

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var typeAccidentDao: TypeAccidentDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_return_correct_if_not_have_data_in_card_shared_preferences() =
        runTest {
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "-"
            )
        }

    @Test
    fun check_return_correct_if_not_have_data_in_nature_room() =
        runTest {
            cardSharedPreferencesDatasource.save(
                CardSharedPreferencesModel(
                    idNatureList = listOf(1, 2)
                )
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "-"
            )
        }

    @Test
    fun check_return_correct_if_have_all_data() =
        runTest {
            cardSharedPreferencesDatasource.save(
                CardSharedPreferencesModel(
                    idTypeAccidentList = listOf(2, 3)
                )
            )
            typeAccidentDao.insertAll(
                listOf(
                    TypeAccidentRoomModel(
                        id = 1,
                        description = "ITEM 1"
                    ),
                    TypeAccidentRoomModel(
                        id = 2,
                        description = "ITEM 2"
                    ),
                    TypeAccidentRoomModel(
                        id = 3,
                        description = "ITEM 3"
                    ),
                    TypeAccidentRoomModel(
                        id = 4,
                        description = "ITEM 4"
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
                "ITEM 2 - ITEM 3"
            )
        }

}