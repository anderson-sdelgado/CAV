package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
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
class IGetDetailTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: GetDetail

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var equipSharedPreferencesDatasource: EquipSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_get_detail_insert_equip() = runTest {
        equipSharedPreferencesDatasource.clean()
        val result = usecase(Option.INSERT, FlowNote.EQUIP, 0, 0)
        assertEquals(result.getOrNull(), "")
        
        equipSharedPreferencesDatasource.setDetail("DETAIL EQUIP")
        val result2 = usecase(Option.INSERT, FlowNote.EQUIP, 0, 0)
        assertEquals(result2.getOrNull(), "DETAIL EQUIP")
    }

    @Test
    fun check_get_detail_edit_equip_sec() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleOwnList = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1,
                    equipSecList = listOf(
                        EquipCardSharedPreferencesModel(id = 10, detail = "DETAIL SEC")
                    )
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(Option.EDIT, FlowNote.EQUIP_SEC, 1, 10)
        assertEquals(result.getOrNull(), "DETAIL SEC")
    }
}
