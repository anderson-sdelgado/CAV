package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class IGetSetObsIntegrationTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getObs: GetObs

    @Inject
    lateinit var setObs: SetObs

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_set_and_get_obs() = runTest {
        cardSharedPreferencesDatasource.clean()

        val resultSet = setObs("TESTE OBS INTEGRATION")
        assertEquals(resultSet.isSuccess, true)

        val resultGet = getObs()
        assertEquals(resultGet.isSuccess, true)
        assertEquals(resultGet.getOrNull(), "TESTE OBS INTEGRATION")

        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.obs, "TESTE OBS INTEGRATION")
    }

    @Test
    fun check_get_obs_empty() = runTest {
        cardSharedPreferencesDatasource.clean()

        val resultGet = getObs()
        assertEquals(resultGet.isSuccess, true)
        assertEquals(resultGet.getOrNull(), "")
    }

}
