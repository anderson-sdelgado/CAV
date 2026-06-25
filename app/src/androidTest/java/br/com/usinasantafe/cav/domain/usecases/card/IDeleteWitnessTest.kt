package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class IDeleteWitnessTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: DeleteWitness

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_delete_witness() = runTest {
        val data = CardSharedPreferencesModel(
            witnessList = listOf(
                InvolvedSharedPreferencesModel(id = 1)
            )
        )
        cardSharedPreferencesDatasource.save(data)
        
        val result = usecase(1)
        assertEquals(result.isSuccess, true)
        
        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.witnessList.size, 0)
    }
}
