package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.room.dao.stable.ColabDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.ColabRoomModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleInvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import br.com.usinasantafe.cav.lib.FlowNote
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
class IListPassengerTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: ListPassenger

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var colabDao: ColabDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_list_passenger_colab() = runTest {
        colabDao.insertAll(listOf(ColabRoomModel(reg = 123, name = "P1")))
        val data = CardSharedPreferencesModel(
            vehicleOwnList = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1,
                    passengerColabList = listOf(
                        ColabSharedPreferencesModel(id = 100, reg = 123)
                    )
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(FlowNote.PASSENGER_COLAB, 1)
        val list = result.getOrNull()!!
        assertEquals(list.size, 1)
        assertEquals(list[0], ItemListScreenModel(id = 100, desc = "123 - P1"))
    }

    @Test
    fun check_list_passenger_involved() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleInvolvedList = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        InvolvedSharedPreferencesModel(id = 200, document = "456", name = "P2")
                    )
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(FlowNote.PASSENGER_INVOLVED, 1)
        val list = result.getOrNull()!!
        assertEquals(list.size, 1)
        assertEquals(list[0], ItemListScreenModel(id = 200, desc = "456 - P2"))
    }
}
