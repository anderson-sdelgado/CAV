package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
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
class IListEquipSecTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: ListEquipSec

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var equipDao: EquipDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_list_equip_sec() = runTest {
        equipDao.insertAll(listOf(
            EquipRoomModel(id = 10, nro = 100, description = "E1"),
            EquipRoomModel(id = 20, nro = 200, description = "E2")
        ))
        val data = CardSharedPreferencesModel(
            vehicleOwnList = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1,
                    equipSecList = listOf(
                        EquipCardSharedPreferencesModel(id = 1000, idEquip = 10),
                        EquipCardSharedPreferencesModel(id = 2000, idEquip = 20)
                    )
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(1)
        val list = result.getOrNull()!!
        assertEquals(list.size, 2)
        assertEquals(list[0], ItemListScreenModel(id = 1000, desc = "100 - E1"))
        assertEquals(list[1], ItemListScreenModel(id = 2000, desc = "200 - E2"))
    }
}
