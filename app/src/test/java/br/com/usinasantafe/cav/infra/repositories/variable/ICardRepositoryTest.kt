package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ICardRepositoryTest {

    private val cardSharedPreferencesDatasource = mock<CardSharedPreferencesDatasource>()
    private val repository = ICardRepository(
        basicRepository = mock(),
        insertRepository = mock(),
        recoverDataRepository = mock(),
        updateRepository = mock(),
        deleteRepository = mock(),
        sendCardRepository = mock(),
        cardSharedPreferencesDatasource = cardSharedPreferencesDatasource
    )

    @Test
    fun `clean - Check return failure if have error in CardSharedPreferencesDatasource clean`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.clean()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.clean",
                    "-",
                    Exception()
                )
            )
            val result = repository.clean()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.clean -> ICardSharedPreferencesDatasource.clean"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `clean - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.clean()
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .clean()
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `has - Check return failure if have error in CardSharedPreferencesDatasource has`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.has()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.has",
                    "-",
                    Exception()
                )
            )
            val result = repository.has()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.has -> ICardSharedPreferencesDatasource.has"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `has - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.has()
            ).thenReturn(
                Result.success(true)
            )
            val result = repository.has()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                true
            )
        }

}