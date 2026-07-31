package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.variable.PeopleExternal
import br.com.usinasantafe.cav.domain.entities.variable.Vehicle
import br.com.usinasantafe.cav.domain.entities.variable.VehicleExternal
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.VehicleScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IListVehiclePeopleExternalTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IListVehicleInvolved(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listVehicleInvolved`() =
        runTest {
            whenever(
                cardRepository.listVehicleInvolved()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listVehicleInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IListVehicleInvolved -> ICardRepository.listVehicleInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return emptyList if function execute successfully with empty list`() =
        runTest {
            whenever(
                cardRepository.listVehicleInvolved()
            ).thenReturn(
                Result.success(emptyList())
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                emptyList()
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardRepository.listVehicleInvolved()
            ).thenReturn(
                Result.success(
                    listOf(
                        VehicleExternal(
                            id = 1,
                            vehicle = Vehicle(
                                plate = "ABC1234",
                                brand = "FORD"
                            ),
                            driver = PeopleExternal(
                                id = 100,
                                document = "12345678900",
                                name = "DRIVER 1"
                            ),
                            passengerPeopleExternalList = emptyList()
                        ),
                        VehicleExternal(
                            id = 2,
                            vehicle = Vehicle(
                                plate = "XYZ9876",
                                brand = "HONDA"
                            ),
                            driver = PeopleExternal(
                                id = 101,
                                document = "98765432100",
                                name = "DRIVER 2"
                            ),
                            passengerPeopleExternalList = emptyList()
                        )
                    )
                )
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(
                    VehicleScreenModel(
                        id = 1,
                        vehicle = "ABC1234 - FORD",
                        driver = "12345678900 - DRIVER 1"
                    ),
                    VehicleScreenModel(
                        id = 2,
                        vehicle = "XYZ9876 - HONDA",
                        driver = "98765432100 - DRIVER 2"
                    )
                )
            )
        }

    @Test
    fun `Check return correct if function execute successfully with null driver values`() =
        runTest {
            whenever(
                cardRepository.listVehicleInvolved()
            ).thenReturn(
                Result.success(
                    listOf(
                        VehicleExternal(
                            id = 1,
                            vehicle = Vehicle(
                                plate = "ABC1234",
                                brand = "FORD"
                            ),
                            driver = PeopleExternal(
                                id = 100,
                                document = null,
                                name = null
                            ),
                            passengerPeopleExternalList = emptyList()
                        )
                    )
                )
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(
                    VehicleScreenModel(
                        id = 1,
                        vehicle = "ABC1234 - FORD",
                        driver = "- - -"
                    )
                )
            )
        }

}