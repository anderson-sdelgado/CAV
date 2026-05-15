package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.TypeAccident
import br.com.usinasantafe.cav.domain.repositories.stable.TypeAccidentRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IListTypeAccidentTest {

    private val typeAccidentRepository = mock<TypeAccidentRepository>()
    private val cardRepository = mock<CardRepository>()
    private val usecase = IListTypeAccident(
        typeAccidentRepository = typeAccidentRepository,
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in TypeAccidentRepository listAll`() =
        runTest {
            whenever(
                typeAccidentRepository.listAll()
            ).thenReturn(
                resultFailure(
                    "ITypeAccidentRepository.listAll",
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
                "IListTypeAccident -> ITypeAccidentRepository.listAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository listIdTypeAccident`() =
        runTest {
            whenever(
                typeAccidentRepository.listAll()
            ).thenReturn(
                Result.success(
                    listOf(
                        TypeAccident(
                            id = 1,
                            description = "Test"
                        )
                    )
                )
            )
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listIdTypeAccident",
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
                "IListTypeAccident -> ICardRepository.listIdTypeAccident"
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
                typeAccidentRepository.listAll()
            ).thenReturn(
                Result.success(
                    listOf(
                        TypeAccident(
                            id = 1,
                            description = "Test"
                        ),
                        TypeAccident(
                            id = 2,
                            description = "Test2"
                        )
                    )
                )
            )
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                Result.success(
                    listOf(1)
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
                        flag = true
                    ),
                    ItemCheckBoxModel(
                        id = 2,
                        desc = "Test2",
                        flag = false
                    )
                )
            )
        }

}