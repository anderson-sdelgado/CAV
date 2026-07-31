package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.PeopleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Provider
import kotlin.intArrayOf
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class IDeleteCardSharedPreferencesDatasourceTest {

    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var cardDatasource: CardSharedPreferencesDatasource
    private lateinit var datasource: IDeleteCardSharedPreferencesDatasource

    class TestProvider<T : Any> : Provider<T> {
        lateinit var value: T
        override fun get(): T = value
    }

    @Before
    fun setup() {

        context = ApplicationProvider.getApplicationContext()

        sharedPreferences =
            context.getSharedPreferences("test", Context.MODE_PRIVATE)

        sharedPreferences.edit().clear().commit()

        val provider = TestProvider<CardSharedPreferencesDatasource>()

        datasource = IDeleteCardSharedPreferencesDatasource(provider)

        cardDatasource =
            ICardSharedPreferencesDatasource(
                basicCardSharedPreferencesDatasource = mock(),
                insertCardSharedPreferencesDatasource = mock(),
                recoverDataCardSharedPreferencesDatasource = mock(),
                updateCardSharedPreferencesDatasource = mock(),
                deleteCardSharedPreferencesDatasource = datasource,
                sharedPreferences = sharedPreferences
            )

        provider.value = cardDatasource

    }

    @Test
    fun `deleteVehicleOwn - Check delete data correct in sharedPreferences internal`() =
        runTest {
            val list = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 2
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 3
                )
            )
            val data = CardSharedPreferencesModel(
                vehicleOwnList = list
            )
            cardDatasource.save(data)
            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.vehicleOwnList,
                list
            )
            val result =  datasource.deleteVehicleOwn(2)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(
                modelAfter.vehicleOwnList,
                listOf(
                    VehicleOwnSharedPreferencesModel(
                        id = 1
                    ),
                    VehicleOwnSharedPreferencesModel(
                        id = 3
                    )
                )
            )
        }

    @Test
    fun `deleteEquipSecondary - Check delete data correct in sharedPreferences internal`() =
        runTest {
            val listBefore = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1,
                    equipSecList = listOf(
                        EquipCardSharedPreferencesModel(
                            id = 1
                        ),
                        EquipCardSharedPreferencesModel(
                            id = 2
                        ),
                        EquipCardSharedPreferencesModel(
                            id = 3
                        )
                    )
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 2,
                    equipSecList = listOf(
                        EquipCardSharedPreferencesModel(
                            id = 1
                        ),
                        EquipCardSharedPreferencesModel(
                            id = 2
                        )
                    )
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 3
                )
            )
            val data = CardSharedPreferencesModel(
                vehicleOwnList = listBefore
            )
            cardDatasource.save(data)
            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.vehicleOwnList,
                listBefore
            )
            val result =  datasource.deleteEquipSecondary(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = cardDatasource.get().getOrThrow()
            val listAfter = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1,
                    equipSecList = listOf(
                        EquipCardSharedPreferencesModel(
                            id = 1
                        ),
                        EquipCardSharedPreferencesModel(
                            id = 3
                        )
                    )
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 2,
                    equipSecList = listOf(
                        EquipCardSharedPreferencesModel(
                            id = 1
                        ),
                        EquipCardSharedPreferencesModel(
                            id = 2
                        )
                    )
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 3
                )
            )
            assertEquals(
                modelAfter.vehicleOwnList,
                listAfter
            )
        }

    @Test
    fun `deleteVehicleInvolved - Check delete data correct in sharedPreferences internal`() =
        runTest {
            val list = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1
                ),
                VehicleExternalSharedPreferencesModel(
                    id = 2
                ),
                VehicleExternalSharedPreferencesModel(
                    id = 3
                )
            )
            val data = CardSharedPreferencesModel(
                vehicleExternalList = list
            )
            cardDatasource.save(data)
            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.vehicleExternalList,
                list
            )
            val result =  datasource.deleteVehicleExternal(2)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(
                modelAfter.vehicleExternalList,
                listOf(
                    VehicleExternalSharedPreferencesModel(
                        id = 1
                    ),
                    VehicleExternalSharedPreferencesModel(
                        id = 3
                    )
                )
            )
        }

    @Test
    fun `deleteInvolved - Check delete data correct in sharedPreferences internal`() =
        runTest {
            val list = listOf(
                PeopleExternalSharedPreferencesModel(
                    id = 1
                ),
                PeopleExternalSharedPreferencesModel(
                    id = 2
                ),
                PeopleExternalSharedPreferencesModel(
                    id = 3
                )
            )
            val data = CardSharedPreferencesModel(
                involvedExternalList = list
            )
            cardDatasource.save(data)
            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.involvedExternalList,
                list
            )
            val result =  datasource.deleteInvolvedExternal(2)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(
                modelAfter.involvedExternalList,
                listOf(
                    PeopleExternalSharedPreferencesModel(
                        id = 1
                    ),
                    PeopleExternalSharedPreferencesModel(
                        id = 3
                    )
                )
            )
        }

    @Test
    fun `deleteWitness - Check delete data correct in sharedPreferences internal`() =
        runTest {
            val list = listOf(
                PeopleExternalSharedPreferencesModel(
                    id = 1
                ),
                PeopleExternalSharedPreferencesModel(
                    id = 2
                ),
                PeopleExternalSharedPreferencesModel(
                    id = 3
                )
            )
            val data = CardSharedPreferencesModel(
                involvedExternalList = list
            )
            cardDatasource.save(data)
            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.involvedExternalList,
                list
            )
            val result =  datasource.deleteInvolvedExternal(2)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(
                modelAfter.involvedExternalList,
                listOf(
                    PeopleExternalSharedPreferencesModel(
                        id = 1
                    ),
                    PeopleExternalSharedPreferencesModel(
                        id = 3
                    )
                )
            )
        }

    @Test
    fun `deletePassengerColab - Check delete data correct in sharedPreferences internal`() =
        runTest {
            val listBefore = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1,
                    passengerColabList = listOf(
                        ColabCardSharedPreferencesModel(
                            id = 1
                        ),
                        ColabCardSharedPreferencesModel(
                            id = 2
                        ),
                        ColabCardSharedPreferencesModel(
                            id = 3
                        )
                    )
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 2,
                    passengerColabList = listOf(
                        ColabCardSharedPreferencesModel(
                            id = 1
                        ),
                        ColabCardSharedPreferencesModel(
                            id = 2
                        ),
                    )
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 3
                )
            )
            val data = CardSharedPreferencesModel(
                vehicleOwnList = listBefore
            )
            cardDatasource.save(data)
            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.vehicleOwnList,
                listBefore
            )
            val result =  datasource.deletePassengerColab(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = cardDatasource.get().getOrThrow()
            val listAfter = listOf(
                VehicleOwnSharedPreferencesModel(
                    id = 1,
                    passengerColabList = listOf(
                        ColabCardSharedPreferencesModel(
                            id = 1
                        ),
                        ColabCardSharedPreferencesModel(
                            id = 3
                        )
                    )
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 2,
                    passengerColabList = listOf(
                        ColabCardSharedPreferencesModel(
                            id = 1
                        ),
                        ColabCardSharedPreferencesModel(
                            id = 2
                        ),
                    )
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 3
                )
            )
            assertEquals(
                modelAfter.vehicleOwnList,
                listAfter
            )
        }

    @Test
    fun `deletePassengerInvolved - Check delete data correct in sharedPreferences internal`() =
        runTest {
            val listBefore = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        PeopleExternalSharedPreferencesModel(
                            id = 1
                        ),
                        PeopleExternalSharedPreferencesModel(
                            id = 2
                        ),
                        PeopleExternalSharedPreferencesModel(
                            id = 3
                        )
                    )
                ),
                VehicleExternalSharedPreferencesModel(
                    id = 2,
                    passengerInvolvedList = listOf(
                        PeopleExternalSharedPreferencesModel(
                            id = 1
                        ),
                        PeopleExternalSharedPreferencesModel(
                            id = 2
                        ),
                    )
                ),
                VehicleExternalSharedPreferencesModel(
                    id = 3
                )
            )
            val data = CardSharedPreferencesModel(
                vehicleExternalList = listBefore
            )
            cardDatasource.save(data)
            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.vehicleExternalList,
                listBefore
            )
            val result =  datasource.deletePassengerInvolved(2, 1)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = cardDatasource.get().getOrThrow()
            val listAfter = listOf(
                VehicleExternalSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        PeopleExternalSharedPreferencesModel(
                            id = 1
                        ),
                        PeopleExternalSharedPreferencesModel(
                            id = 2
                        ),
                        PeopleExternalSharedPreferencesModel(
                            id = 3
                        )
                    )
                ),
                VehicleExternalSharedPreferencesModel(
                    id = 2,
                    passengerInvolvedList = listOf(
                        PeopleExternalSharedPreferencesModel(
                            id = 2
                        )
                    )
                ),
                VehicleExternalSharedPreferencesModel(
                    id = 3
                )
            )
            assertEquals(
                modelAfter.vehicleExternalList,
                listAfter
            )
        }

    @Test
    fun `deletePhoto - Check delete data correct in sharedPreferences internal`() =
        runTest {
            val list = listOf("test1", "test2", "test3")
            val data = CardSharedPreferencesModel(
                urlPhotoList = list
            )
            cardDatasource.save(data)
            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.urlPhotoList,
                list
            )
            val result =  datasource.deletePhoto("test2")
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(
                modelAfter.urlPhotoList,
                listOf("test1", "test3")
            )
        }

}