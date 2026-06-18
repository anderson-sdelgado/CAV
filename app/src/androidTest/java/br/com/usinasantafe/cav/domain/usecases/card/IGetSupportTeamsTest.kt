package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.room.dao.stable.SupportTeamsDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.card.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.SupportTeamsRoomModel
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
class IGetSupportTeamsTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: GetSupportTeams

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var supportTeamsDao: SupportTeamsDao

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
                    idSupportTeamsList = listOf(2, 3)
                )
            )
            supportTeamsDao.insertAll(
                listOf(
                    SupportTeamsRoomModel(
                        id = 1,
                        description = "ITEM 1"
                    ),
                    SupportTeamsRoomModel(
                        id = 2,
                        description = "ITEM 2"
                    ),
                    SupportTeamsRoomModel(
                        id = 3,
                        description = "ITEM 3"
                    ),
                    SupportTeamsRoomModel(
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