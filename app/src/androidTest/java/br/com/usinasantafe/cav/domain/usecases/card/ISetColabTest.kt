package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabSharedPreferencesModel
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
class ISetColabTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetColab

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Inject
    lateinit var colabSharedPreferencesDatasource: ColabSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_set_colab_insert() = runTest {
        colabSharedPreferencesDatasource.clean()
        val result = usecase("123456", Option.INSERT, FlowNote.COLAB, 0, 0)
        assertEquals(result.isSuccess, true)
        
        val resultGet = colabSharedPreferencesDatasource.get()
        assertEquals(resultGet.getOrNull()?.reg, 123456L)
    }

    @Test
    fun check_update_colab_edit() = runTest {
        val data = CardSharedPreferencesModel(
            vehicleOwnList = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1,
                    colab = ColabSharedPreferencesModel(reg = 10L)
                )
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase("20", Option.EDIT, FlowNote.COLAB, 1, 0)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.vehicleOwnList[0].colab.reg, 20L)
    }
}
