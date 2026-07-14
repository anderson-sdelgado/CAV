package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.cav.external.sharedpreferences.datasource.ICardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleInvolvedSharedPreferencesModel
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
                        EquipSharedPreferencesModel(
                            id = 1
                        ),
                        EquipSharedPreferencesModel(
                            id = 2
                        ),
                        EquipSharedPreferencesModel(
                            id = 3
                        )
                    )
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 2,
                    equipSecList = listOf(
                        EquipSharedPreferencesModel(
                            id = 1
                        ),
                        EquipSharedPreferencesModel(
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
                        EquipSharedPreferencesModel(
                            id = 1
                        ),
                        EquipSharedPreferencesModel(
                            id = 3
                        )
                    )
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 2,
                    equipSecList = listOf(
                        EquipSharedPreferencesModel(
                            id = 1
                        ),
                        EquipSharedPreferencesModel(
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
                VehicleInvolvedSharedPreferencesModel(
                    id = 1
                ),
                VehicleInvolvedSharedPreferencesModel(
                    id = 2
                ),
                VehicleInvolvedSharedPreferencesModel(
                    id = 3
                )
            )
            val data = CardSharedPreferencesModel(
                vehicleInvolvedList = list
            )
            cardDatasource.save(data)
            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.vehicleInvolvedList,
                list
            )
            val result =  datasource.deleteVehicleInvolved(2)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(
                modelAfter.vehicleInvolvedList,
                listOf(
                    VehicleInvolvedSharedPreferencesModel(
                        id = 1
                    ),
                    VehicleInvolvedSharedPreferencesModel(
                        id = 3
                    )
                )
            )
        }

    @Test
    fun `deleteInvolved - Check delete data correct in sharedPreferences internal`() =
        runTest {
            val list = listOf(
                InvolvedSharedPreferencesModel(
                    id = 1
                ),
                InvolvedSharedPreferencesModel(
                    id = 2
                ),
                InvolvedSharedPreferencesModel(
                    id = 3
                )
            )
            val data = CardSharedPreferencesModel(
                involvedList = list
            )
            cardDatasource.save(data)
            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.involvedList,
                list
            )
            val result =  datasource.deleteInvolved(2)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(
                modelAfter.involvedList,
                listOf(
                    InvolvedSharedPreferencesModel(
                        id = 1
                    ),
                    InvolvedSharedPreferencesModel(
                        id = 3
                    )
                )
            )
        }

    @Test
    fun `deleteWitness - Check delete data correct in sharedPreferences internal`() =
        runTest {
            val list = listOf(
                InvolvedSharedPreferencesModel(
                    id = 1
                ),
                InvolvedSharedPreferencesModel(
                    id = 2
                ),
                InvolvedSharedPreferencesModel(
                    id = 3
                )
            )
            val data = CardSharedPreferencesModel(
                involvedList = list
            )
            cardDatasource.save(data)
            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.involvedList,
                list
            )
            val result =  datasource.deleteInvolved(2)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(
                modelAfter.involvedList,
                listOf(
                    InvolvedSharedPreferencesModel(
                        id = 1
                    ),
                    InvolvedSharedPreferencesModel(
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
                        ColabSharedPreferencesModel(
                            id = 1
                        ),
                        ColabSharedPreferencesModel(
                            id = 2
                        ),
                        ColabSharedPreferencesModel(
                            id = 3
                        )
                    )
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 2,
                    passengerColabList = listOf(
                        ColabSharedPreferencesModel(
                            id = 1
                        ),
                        ColabSharedPreferencesModel(
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
                        ColabSharedPreferencesModel(
                            id = 1
                        ),
                        ColabSharedPreferencesModel(
                            id = 3
                        )
                    )
                ),
                VehicleOwnSharedPreferencesModel(
                    id = 2,
                    passengerColabList = listOf(
                        ColabSharedPreferencesModel(
                            id = 1
                        ),
                        ColabSharedPreferencesModel(
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
                VehicleInvolvedSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        InvolvedSharedPreferencesModel(
                            id = 1
                        ),
                        InvolvedSharedPreferencesModel(
                            id = 2
                        ),
                        InvolvedSharedPreferencesModel(
                            id = 3
                        )
                    )
                ),
                VehicleInvolvedSharedPreferencesModel(
                    id = 2,
                    passengerInvolvedList = listOf(
                        InvolvedSharedPreferencesModel(
                            id = 1
                        ),
                        InvolvedSharedPreferencesModel(
                            id = 2
                        ),
                    )
                ),
                VehicleInvolvedSharedPreferencesModel(
                    id = 3
                )
            )
            val data = CardSharedPreferencesModel(
                vehicleInvolvedList = listBefore
            )
            cardDatasource.save(data)
            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.vehicleInvolvedList,
                listBefore
            )
            val result =  datasource.deletePassengerInvolved(2, 1)
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = cardDatasource.get().getOrThrow()
            val listAfter = listOf(
                VehicleInvolvedSharedPreferencesModel(
                    id = 1,
                    passengerInvolvedList = listOf(
                        InvolvedSharedPreferencesModel(
                            id = 1
                        ),
                        InvolvedSharedPreferencesModel(
                            id = 2
                        ),
                        InvolvedSharedPreferencesModel(
                            id = 3
                        )
                    )
                ),
                VehicleInvolvedSharedPreferencesModel(
                    id = 2,
                    passengerInvolvedList = listOf(
                        InvolvedSharedPreferencesModel(
                            id = 2
                        )
                    )
                ),
                VehicleInvolvedSharedPreferencesModel(
                    id = 3
                )
            )
            assertEquals(
                modelAfter.vehicleInvolvedList,
                listAfter
            )
        }

    @Test
    fun `deletePhoto - Check delete data correct in sharedPreferences internal`() =
        runTest {
            val list = listOf("test1", "test2", "test3")
            val data = CardSharedPreferencesModel(
                photoList = list
            )
            cardDatasource.save(data)
            val modelBefore = cardDatasource.get().getOrThrow()
            assertEquals(
                modelBefore.photoList,
                list
            )
            val result =  datasource.deletePhoto("test2")
            assertEquals(
                result.isSuccess,
                true
            )
            val modelAfter = cardDatasource.get().getOrThrow()
            assertEquals(
                modelAfter.photoList,
                listOf("test1", "test3")
            )
        }

}