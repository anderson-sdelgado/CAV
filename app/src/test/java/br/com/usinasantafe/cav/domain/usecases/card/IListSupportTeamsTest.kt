package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.SupportTeams
import br.com.usinasantafe.cav.domain.repositories.stable.SupportTeamsRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IListSupportTeamsTest {

    private val supportTeamsRepository = mock<SupportTeamsRepository>()
    private val cardRepository = mock<CardRepository>()
    private val usecase = IListSupportTeams(
        supportTeamsRepository = supportTeamsRepository,
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in SupportTeamsRepository listAll`() =
        runTest {
            whenever(
                supportTeamsRepository.listAll()
            ).thenReturn(
                resultFailure(
                    "ISupportTeamsRepository.listAll",
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
                "IListSupportTeams -> ISupportTeamsRepository.listAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository listIdSupportTeams`() =
        runTest {
            whenever(
                supportTeamsRepository.listAll()
            ).thenReturn(
                Result.success(
                    listOf(
                        SupportTeams(
                            id = 1,
                            description = "Test"
                        )
                    )
                )
            )
            whenever(
                cardRepository.listIdSupportTeams()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listIdSupportTeams",
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
                "IListSupportTeams -> ICardRepository.listIdSupportTeams"
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
                supportTeamsRepository.listAll()
            ).thenReturn(
                Result.success(
                    listOf(
                        SupportTeams(
                            id = 1,
                            description = "Test"
                        ),
                        SupportTeams(
                            id = 2,
                            description = "Test2"
                        )
                    )
                )
            )
            whenever(
                cardRepository.listIdSupportTeams()
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
                    ItemCheckBoxScreenModel(
                        id = 1,
                        desc = "Test",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 2,
                        desc = "Test2",
                        flag = false
                    )
                )
            )
        }

}