package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.ROptionItemDataLocal
import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IListDataLocalTest {

    private val cardRepository = mock<CardRepository>()
    private val dataLocalRepository = mock<DataLocalRepository>()
    private val usecase = IListDataLocal(
        cardRepository = cardRepository,
        dataLocalRepository = dataLocalRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listDataLocal`() =
        runTest {
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listDataLocal",
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
                "IListDataLocal -> ICardRepository.listDataLocal"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully and return is emptyList`() =
        runTest {
            whenever(
                cardRepository.listIdDataLocal()
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
    fun `Check return failure if have error in DataLocalRepository getROptionItemById`() =
        runTest {
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                Result.success(
                    listOf(1)
                )
            )
            whenever(
                dataLocalRepository.getROptionItemById(1)
            ).thenReturn(
                resultFailure(
                    "IDataLocalRepository.getROptionItemById",
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
                "IListDataLocal -> IDataLocalRepository.getROptionItemById"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in DataLocalRepository getDescOptionById`() =
        runTest {
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                Result.success(
                    listOf(1)
                )
            )
            whenever(
                dataLocalRepository.getROptionItemById(1)
            ).thenReturn(
                Result.success(
                    ROptionItemDataLocal(
                        id = 1,
                        idOption = 2,
                        idItem = 3
                    )
                )
            )
            whenever(
                dataLocalRepository.getDescOptionById(2)
            ).thenReturn(
                resultFailure(
                    "IDataLocalRepository.getDescOptionById",
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
                "IListDataLocal -> IDataLocalRepository.getDescOptionById"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in DataLocalRepository getDescItemById`() =
        runTest {
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                Result.success(
                    listOf(1)
                )
            )
            whenever(
                dataLocalRepository.getROptionItemById(1)
            ).thenReturn(
                Result.success(
                    ROptionItemDataLocal(
                        id = 1,
                        idOption = 2,
                        idItem = 3
                    )
                )
            )
            whenever(
                dataLocalRepository.getDescOptionById(2)
            ).thenReturn(
                Result.success("Option 1")
            )
            whenever(
                dataLocalRepository.getDescItemById(3)
            ).thenReturn(
                resultFailure(
                    "IDataLocalRepository.getDescItemById",
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
                "IListDataLocal -> IDataLocalRepository.getDescItemById"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                Result.success(
                    listOf(1)
                )
            )
            whenever(
                dataLocalRepository.getROptionItemById(1)
            ).thenReturn(
                Result.success(
                    ROptionItemDataLocal(
                        id = 1,
                        idOption = 2,
                        idItem = 3
                    )
                )
            )
            whenever(
                dataLocalRepository.getDescOptionById(2)
            ).thenReturn(
                Result.success("Option 1")
            )
            whenever(
                dataLocalRepository.getDescItemById(3)
            ).thenReturn(
                Result.success("Item 1")
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(
                    "Option 1" to "Item 1"
                )
            )
        }

}