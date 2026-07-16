package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class IGetNroEquipTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: GetNroEquip

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var equipSharedPreferencesDatasource: EquipSharedPreferencesDatasource

    @Inject
    lateinit var equipDao: EquipDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_get_nro_equip_insert() = runTest {
        equipDao.insertAll(listOf(EquipRoomModel(id = 10, nro = 100, description = "TEST")))
        equipSharedPreferencesDatasource.clean()
        val result = usecase(Option.INSERT, FlowNote.EQUIP, 0, 0)
        assertEquals(result.getOrNull(), "")
        
        equipSharedPreferencesDatasource.setIdEquip(10)
        val result2 = usecase(Option.INSERT, FlowNote.EQUIP, 0, 0)
        assertEquals(result2.getOrNull(), "100")
    }

    @Test
    fun check_get_nro_equip_edit() = runTest {
        equipDao.insertAll(listOf(EquipRoomModel(id = 20, nro = 200, description = "TEST 2")))
        val data = CardSharedPreferencesModel(
            vehicleOwnList = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1,
                    equip = EquipCardSharedPreferencesModel(idEquip = 20)
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(Option.EDIT, FlowNote.EQUIP, 1, 0)
        assertEquals(result.getOrNull(), "200")
    }
}
