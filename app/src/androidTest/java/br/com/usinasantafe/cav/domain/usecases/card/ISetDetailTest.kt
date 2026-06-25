package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InvolvedSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleInvolvedSharedPreferencesModel
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
class ISetDetailTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetDetail

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var equipSharedPreferencesDatasource: EquipSharedPreferencesDatasource

    @Inject
    lateinit var involvedSharedPreferencesDatasource: InvolvedSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_set_detail_insert_equip() = runTest {
        equipSharedPreferencesDatasource.clean()
        val result = usecase("DETAIL", Option.INSERT, FlowNote.EQUIP, 0, 0)
        assertEquals(result.isSuccess, true)
        
        val resultGet = equipSharedPreferencesDatasource.get()
        assertEquals(resultGet.getOrNull()?.detail, "DETAIL")
    }

    @Test
    fun check_update_detail_involved() = runTest {
        val data = CardSharedPreferencesModel(
            involvedList = listOf(
                InvolvedSharedPreferencesModel(id = 1, detail = "OLD")
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase("NEW", Option.EDIT, FlowNote.INVOLVED, 1, 0)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.involvedList[0].detail, "NEW")
    }
}
