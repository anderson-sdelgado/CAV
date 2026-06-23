package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
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
class IGetCarTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: GetCar

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var equipDao: EquipDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_return_failure_if_not_have_data_in_card_shared_preferences() =
        runTest {
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetCar -> ICardRepository.getIdCar -> ICardSharedPreferencesDatasource.getIdCar"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NullPointerException: idCar is required"
            )
        }

    @Test
    fun check_return_failure_if_not_have_data() =
        runTest {
            cardSharedPreferencesDatasource.save(
                CardSharedPreferencesModel(
                    idCar = 1
                )
            )
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetCar -> IEquipRepository.getById -> IEquipRoomDatasource.getById"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NullPointerException: model is required"
            )
        }

    @Test
    fun check_return_correct_if_have_all_data() =
        runTest {
            cardSharedPreferencesDatasource.save(
                CardSharedPreferencesModel(
                    idCar = 1
                )
            )
            equipDao.insertAll(
                listOf(
                    EquipRoomModel(
                        id = 1,
                        nro = 2200,
                        description = "TRATOR"
                    ),
                    EquipRoomModel(
                        id = 2,
                        nro = 2202,
                        description = "TRATOR PESADO"
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
                "2200 - TRATOR"
            )
        }

}