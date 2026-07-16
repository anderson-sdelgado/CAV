package br.com.usinasantafe.cav.domain.usecases.card

import android.net.Uri
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
class ISetPhotoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SetPhoto

    @Inject
    lateinit var cardSharedPreferencesDatasource: ICardSharedPreferencesDatasource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_set_photo_list() = runTest {
        cardSharedPreferencesDatasource.clean()

        val photo = Uri.parse("file://test.jpg").toString()
        val result = usecase(photo)
        assertEquals(result.isSuccess, true)

        val modelAfter = cardSharedPreferencesDatasource.get().getOrThrow()
        assertEquals(modelAfter.urlPhotoList[0], Uri.parse("file://test.jpg").toString())
    }

}
