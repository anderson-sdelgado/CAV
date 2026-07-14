package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.variable.VehicleOwn
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISaveCardTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISaveCard(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if hasLocal return is null`() =
        runTest {
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISaveCard -> Cannot invoke \"java.lang.Boolean.booleanValue()\" because \"<local2>\" is null"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "null"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository hasLocal`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.hasLocal",
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
                "ISaveCard -> ICardRepository.hasLocal"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return false if CardRepository hasLocal is false`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                Result.success(false)
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                false
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository listIdNature`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listIdNature",
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
                "ISaveCard -> ICardRepository.listIdNature"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return false if CardRepository listIdNature is emptyList`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                cardRepository.listIdNature()
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
                false
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository listIdTypeAccident`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(listOf(1))
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
                "ISaveCard -> ICardRepository.listIdTypeAccident"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return false if CardRepository listIdTypeAccident is emptyList`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listIdTypeAccident()
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
                false
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository listIdDataLocal`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listIdDataLocal",
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
                "ISaveCard -> ICardRepository.listIdDataLocal"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return false if CardRepository listIdDataLocal is emptyList`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                Result.success(listOf(1))
            )
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
                false
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository listVehicleOwn`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                Result.success(listOf(1))
            )
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
                "ISaveCard -> ICardRepository.listVehicleOwn"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return false if CardRepository listVehicleOwn is emptyList`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                Result.success(listOf(1))
            )
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
                false
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository save`() =
        runTest {
            whenever(
                cardRepository.hasLocal()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listVehicleOwn()
            ).thenReturn(
                Result.success(listOf(VehicleOwn()))
            )
            whenever(
                cardRepository.save()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.save",
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
                "ISaveCard -> ICardRepository.save"
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
                cardRepository.hasLocal()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listIdTypeAccident()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                Result.success(listOf(1))
            )
            whenever(
                cardRepository.listVehicleOwn()
            ).thenReturn(
                Result.success(listOf(VehicleOwn()))
            )
            val result = usecase()
            verify(cardRepository, atLeastOnce()).save()
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