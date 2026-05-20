package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.SupportTeams
import br.com.usinasantafe.cav.domain.repositories.stable.SupportTeamsRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetSupportTeamsTest {

    private val cardRepository = mock<CardRepository>()
    private val supportTeamsRepository = mock<SupportTeamsRepository>()
    private val usecase = IGetSupportTeams(
        cardRepository = cardRepository,
        supportTeamsRepository = supportTeamsRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listIdSupportTeams`() =
        runTest {
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
                "IGetSupportTeams -> ICardRepository.listIdSupportTeams"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in SupportTeamsRepository listByIdList`() =
        runTest {
            whenever(
                cardRepository.listIdSupportTeams()
            ).thenReturn(
                Result.success(listOf(1, 2))
            )
            whenever(
                supportTeamsRepository.listByIdList(listOf(1, 2))
            ).thenReturn(
                resultFailure(
                    "ISupportTeamsRepository.listByIdList",
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
                "IGetSupportTeams -> ISupportTeamsRepository.listByIdList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if listIdSupportTeams return null and listByIdList return null`() =
        runTest {
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetSupportTeams -> Cannot invoke \"java.util.List.isEmpty()\" because \"entityList\" is null"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "null"
            )
        }

    @Test
    fun `Check return failure if listIdSupportTeams return emptyList and listByIdList return null`() =
        runTest {
            whenever(
                cardRepository.listIdSupportTeams()
            ).thenReturn(
                Result.success(emptyList())
            )
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetSupportTeams -> Cannot invoke \"java.util.List.isEmpty()\" because \"entityList\" is null"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "null"
            )
        }

    @Test
    fun `Check return correct if listIdSupportTeams return emptyList and listByIdList return emptyList`() =
        runTest {
            whenever(
                cardRepository.listIdSupportTeams()
            ).thenReturn(
                Result.success(emptyList())
            )
            whenever(
                supportTeamsRepository.listByIdList(emptyList())
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
                "-"
            )
        }

    @Test
    fun `Check return correct if listIdSupportTeams return idList and listByIdList return emptyList`() =
        runTest {
            whenever(
                cardRepository.listIdSupportTeams()
            ).thenReturn(
                Result.success(listOf(1, 2))
            )
            whenever(
                supportTeamsRepository.listByIdList(listOf(1, 2))
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
                "-"
            )
        }

    @Test
    fun `Check return correct if listIdSupportTeams return idList and listByIdList return list of data`() =
        runTest {
            whenever(
                cardRepository.listIdSupportTeams()
            ).thenReturn(
                Result.success(listOf(1, 2))
            )
            whenever(
                supportTeamsRepository.listByIdList(listOf(1, 2))
            ).thenReturn(
                Result.success(
                    listOf(
                        SupportTeams(
                            id = 1,
                            description = "Item 1"
                        ),
                        SupportTeams(
                            id = 2,
                            description = "Item 2"
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
                "Item 1 - Item 2"
            )
        }
}