package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.OptionDataLocal
import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IListOptionDataLocalTest {

    private val dataLocalRepository = mock<DataLocalRepository>()
    private val usecase = IListOptionDataLocal(
        dataLocalRepository = dataLocalRepository
    )

    @Test
    fun `Check return failure if have error in DataLocalRepository listAllOption`() =
        runTest {
            whenever(
                dataLocalRepository.listAllOption()
            ).thenReturn(
                resultFailure(
                    "IDataLocalRepository.listAllOption",
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
                "IListOptionDataLocal -> IDataLocalRepository.listAllOption"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return emptyList if function execute successfully and return is emptyList`() =
        runTest {
            whenever(
                dataLocalRepository.listAllOption()
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
                dataLocalRepository.listAllOption()
            ).thenReturn(
                Result.success(
                    listOf(
                        OptionDataLocal(
                            id = 1,
                            description = "Test"
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
                    ItemListScreenModel(
                        id = 1,
                        desc = "Test"
                    )
                )
            )
        }

}