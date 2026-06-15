package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.Equip
import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.domain.entities.variable.EquipCard
import br.com.usinasantafe.cav.domain.entities.variable.VehicleOwn
import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.VehicleScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IListVehicleOwnTest {

    private val cardRepository = mock<CardRepository>()
    private val colabRepository = mock<ColabRepository>()
    private val equipRepository = mock<EquipRepository>()
    private val usecase = IListVehicleOwn(
        cardRepository = cardRepository,
        colabRepository = colabRepository,
        equipRepository = equipRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listVehicleOwn`() =
        runTest {
            whenever(
                cardRepository.listVehicleOwn()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listVehicleOwn",
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
                "IListVehicleOwn -> ICardRepository.listVehicleOwn"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in ColabRepository getNameByReg`() =
        runTest {
            whenever(
                cardRepository.listVehicleOwn()
            ).thenReturn(
                Result.success(
                    listOf(
                        VehicleOwn(
                            id = 1,
                            equipCard = EquipCard(id = 10, idEquip = 100),
                            colabCard = ColabCard(id = 1, reg = 123456L)
                        )
                    )
                )
            )
            whenever(
                colabRepository.getNameByReg(123456L)
            ).thenReturn(
                resultFailure(
                    "IColabRepository.getNameByReg",
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
                "IListVehicleOwn -> IColabRepository.getNameByReg"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in EquipRepository getById`() =
        runTest {
            whenever(
                cardRepository.listVehicleOwn()
            ).thenReturn(
                Result.success(
                    listOf(
                        VehicleOwn(
                            id = 1,
                            equipCard = EquipCard(id = 10, idEquip = 100),
                            colabCard = ColabCard(id = 1, reg = 123456L)
                        )
                    )
                )
            )
            whenever(
                colabRepository.getNameByReg(123456L)
            ).thenReturn(
                Result.success("COLAB 1")
            )
            whenever(
                equipRepository.getById(10)
            ).thenReturn(
                resultFailure(
                    "IEquipRepository.getById",
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
                "IListVehicleOwn -> IEquipRepository.getById"
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
                cardRepository.listVehicleOwn()
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
                cardRepository.listVehicleOwn()
            ).thenReturn(
                Result.success(
                    listOf(
                        VehicleOwn(
                            id = 1,
                            equipCard = EquipCard(id = 10, idEquip = 100),
                            colabCard = ColabCard(id = 1, reg = 123456L)
                        ),
                        VehicleOwn(
                            id = 2,
                            equipCard = EquipCard(id = 20, idEquip = 200),
                            colabCard = ColabCard(id = 2, reg = 456789L)
                        )
                    )
                )
            )
            whenever(
                colabRepository.getNameByReg(123456L)
            ).thenReturn(
                Result.success("COLAB 1")
            )
            whenever(
                colabRepository.getNameByReg(456789L)
            ).thenReturn(
                Result.success("COLAB 2")
            )
            whenever(
                equipRepository.getById(10)
            ).thenReturn(
                Result.success(
                    Equip(
                        id = 10,
                        nro = 100,
                        description = "EQUIPMENT 1"
                    )
                )
            )
            whenever(
                equipRepository.getById(20)
            ).thenReturn(
                Result.success(
                    Equip(
                        id = 20,
                        nro = 200,
                        description = "EQUIPMENT 2"
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
                        vehicle = "100 - EQUIPMENT 1",
                        driver = "123456 - COLAB 1"
                    ),
                    VehicleScreenModel(
                        id = 2,
                        vehicle = "200 - EQUIPMENT 2",
                        driver = "456789 - COLAB 2"
                    )
                )
            )
        }

}