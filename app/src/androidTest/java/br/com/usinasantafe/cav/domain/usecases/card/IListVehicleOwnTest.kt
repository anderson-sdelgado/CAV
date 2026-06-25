package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.room.dao.stable.ColabDao
import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.ColabRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import br.com.usinasantafe.cav.presenter.model.VehicleScreenModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class IListVehicleOwnTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: ListVehicleOwn

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var colabDao: ColabDao

    @Inject
    lateinit var equipDao: EquipDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_list_vehicle_own() = runTest {
        colabDao.insertAll(listOf(ColabRoomModel(reg = 100, name = "RONALDO")))
        equipDao.insertAll(listOf(EquipRoomModel(id = 10, nro = 200, description = "GOL")))
        
        val data = CardSharedPreferencesModel(
            vehicleOwnList = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1,
                    equip = EquipSharedPreferencesModel(idEquip = 10),
                    colab = ColabSharedPreferencesModel(reg = 100)
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase()
        val list = result.getOrNull()!!
        assertEquals(list.size, 1)
        assertEquals(list[0], VehicleScreenModel(id = 1, vehicle = "200 - GOL", driver = "100 - RONALDO"))
    }
}
