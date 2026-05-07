package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.Nature
import br.com.usinasantafe.cav.domain.repositories.stable.NatureRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IListNatureTest {

    private val natureRepository = mock<NatureRepository>()
    private val usecase = IListNature(
        natureRepository = natureRepository
    )

    @Test
    fun `Check return failure if have error in NatureRepository listAll`() =
        runTest {
            whenever(
                natureRepository.listAll()
            ).thenReturn(
                resultFailure(
                    "INatureRepository.listAll",
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
                "IListNature -> INatureRepository.listAll"
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
                natureRepository.listAll()
            ).thenReturn(
                Result.success(
                    listOf(
                        Nature(
                            id = 1,
                            desc = "Test"
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
                    ItemCheckBoxModel(
                        id = 1,
                        desc = "Test",
                        flag = false
                    )
                )
            )
        }

}