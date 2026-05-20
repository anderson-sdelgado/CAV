package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.entities.variable.Local
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.LocalSharedPreferencesModel
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
        cardSharedPreferencesDatasource = cardSharedPreferencesDatasource
    )

    @Test
    fun `setRegAttendant - Check return failure if have error in CardSharedPreferencesDatasource setRegAttendant`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setRegAttendant(19759)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setRegAttendant",
                    "-",
                    Exception()
                )
            )
            val result = repository.setRegAttendant(19759)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.setRegAttendant -> ICardSharedPreferencesDatasource.setRegAttendant"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setRegAttendant - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setRegAttendant(19759)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).setRegAttendant(19759)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setIdCar - Check return failure if have error in CardSharedPreferencesDatasource setIdCar`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setIdCar(200)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setIdCar",
                    "-",
                    Exception()
                )
            )
            val result = repository.setIdCar(200)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.setIdCar -> ICardSharedPreferencesDatasource.setIdCar"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setIdCar - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setIdCar(200)
            verify(cardSharedPreferencesDatasource, atLeastOnce()).setIdCar(200)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setLocal - Check return failure if have error in CardSharedPreferencesDatasource setLocal`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setLocal(
                    LocalSharedPreferencesModel(
                        address = "Test",
                        latitude = 0.0,
                        longitude = 0.0
                    )
                )
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setLocal",
                    "-",
                    Exception()
                )
            )
            val result = repository.setLocal(
                Local(
                    address = "Test",
                    latitude = 0.0,
                    longitude = 0.0
                )
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.setLocal -> ICardSharedPreferencesDatasource.setLocal"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setLocal - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setLocal(
                Local(
                    address = "Test",
                    latitude = 0.0,
                    longitude = 0.0
                )
            )
            verify(cardSharedPreferencesDatasource, atLeastOnce()).setLocal(
                LocalSharedPreferencesModel(
                    address = "Test",
                    latitude = 0.0,
                    longitude = 0.0
                )
            )
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `listIdNature - Check return failure if have error in CardSharedPreferencesDatasource listIdNature`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listIdNature()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.listIdNature",
                    "-",
                    Exception()
                )
            )
            val result = repository.listIdNature()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.listIdNature -> ICardSharedPreferencesDatasource.listIdNature"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listIdNature - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listIdNature()
            ).thenReturn(
                Result.success(
                    listOf(1, 2)
                )
            )
            val result = repository.listIdNature()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(1, 2)
            )
        }

    @Test
    fun `setIdNatureList - Check return failure if have error in CardSharedPreferencesDatasource setIdNatureList`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setIdNatureList(listOf(1, 2))
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setIdNatureList",
                    "-",
                    Exception()
                )
            )
            val result = repository.setIdNatureList(listOf(1, 2))
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.setIdNatureList -> ICardSharedPreferencesDatasource.setIdNatureList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setIdNatureList - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setIdNatureList(listOf(1, 2))
            verify(cardSharedPreferencesDatasource, atLeastOnce()).setIdNatureList(listOf(1, 2))
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `getRegAttendant - Check return failure if have error in CardSharedPreferencesDatasource getRegAttendant`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getRegAttendant()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getRegAttendant",
                    "-",
                    Exception()
                )
            )
            val result = repository.getRegAttendant()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.getRegAttendant -> ICardSharedPreferencesDatasource.getRegAttendant"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getRegAttendant - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getRegAttendant()
            ).thenReturn(
                Result.success(19759)
            )
            val result = repository.getRegAttendant()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                19759
            )
        }

    @Test
    fun `getIdCar - Check return failure if have error in CardSharedPreferencesDatasourceDatasource getIdCar`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getIdCar()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasourceDatasource.getIdCar",
                    "-",
                    Exception()
                )
            )
            val result = repository.getIdCar()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.getIdCar -> ICardSharedPreferencesDatasourceDatasource.getIdCar"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getIdCar - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getIdCar()
            ).thenReturn(
                Result.success(1)
            )
            val result = repository.getIdCar()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                1
            )
        }

    @Test
    fun `listIdTypeAccident - Check return failure if have error in CardSharedPreferencesDatasourceDatasource listIdTypeAccident`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listIdTypeAccident()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasourceDatasource.listIdTypeAccident",
                    "-",
                    Exception()
                )
            )
            val result = repository.listIdTypeAccident()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.listIdTypeAccident -> ICardSharedPreferencesDatasourceDatasource.listIdTypeAccident"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listIdTypeAccident - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listIdTypeAccident()
            ).thenReturn(
                Result.success(listOf(1,2))
            )
            val result = repository.listIdTypeAccident()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(1, 2)
            )
        }

    @Test
    fun `setIdTypeAccidentList - Check return failure if have error in CardSharedPreferencesDatasource setIdTypeAccidentList`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setIdTypeAccidentList(listOf(1, 2))
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setIdTypeAccidentList",
                    "-",
                    Exception()
                )
            )
            val result = repository.setIdTypeAccidentList(listOf(1, 2))
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.setIdTypeAccidentList -> ICardSharedPreferencesDatasource.setIdTypeAccidentList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setIdTypeAccidentList - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setIdTypeAccidentList(listOf(1, 2))
            verify(cardSharedPreferencesDatasource, atLeastOnce()).setIdTypeAccidentList(listOf(1, 2))
            assertEquals(
                result.isSuccess,
                true
            )
        }

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
            verify(cardSharedPreferencesDatasource, atLeastOnce()).clean()
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `getLocal - Check return failure if have error in CardSharedPreferencesDatasource getLocal`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getLocal()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getLocal",
                    "-",
                    Exception()
                )
            )
            val result = repository.getLocal()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.getLocal -> ICardSharedPreferencesDatasource.getLocal"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getLocal - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getLocal()
            ).thenReturn(
                Result.success(
                    LocalSharedPreferencesModel(
                        address = "Test",
                        latitude = 25.0,
                        longitude = 26.36
                    )
                )
            )
            val result = repository.getLocal()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                Local(
                    address = "Test",
                    latitude = 25.0,
                    longitude = 26.36
                )
            )
        }

    @Test
    fun `listIdDataLocal - Check return failure if have error in CardSharedPreferencesDatasource listIdDataLocal`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listIdDataLocal()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.listIdDataLocal",
                    "-",
                    Exception()
                )
            )
            val result = repository.listIdDataLocal()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.listIdDataLocal -> ICardSharedPreferencesDatasource.listIdDataLocal"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listIdDataLocal - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listIdDataLocal()
            ).thenReturn(
                Result.success(listOf(1))
            )
            val result = repository.listIdDataLocal()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(1)
            )
        }

    @Test
    fun `setIdDataLocalList - Check return failure if have error in CardSharedPreferencesDatasource setIdDataLocalList`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setIdDataLocalList(listOf(1, 2))
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setIdDataLocalList",
                    "-",
                    Exception()
                )
            )
            val result = repository.setIdDataLocalList(listOf(1, 2))
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.setIdDataLocalList -> ICardSharedPreferencesDatasource.setIdDataLocalList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setIdDataLocalList - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setIdDataLocalList(listOf(1, 2))
            verify(cardSharedPreferencesDatasource, atLeastOnce()).setIdDataLocalList(listOf(1, 2))
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `listIdSupportTeams - Check return failure if have error in CardSharedPreferencesDatasource listIdSupportTeams`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listIdSupportTeams()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.listIdSupportTeams",
                    "-",
                    Exception()
                )
            )
            val result = repository.listIdSupportTeams()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.listIdSupportTeams -> ICardSharedPreferencesDatasource.listIdSupportTeams"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listIdSupportTeams - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listIdSupportTeams()
            ).thenReturn(
                Result.success(listOf(1))
            )
            val result = repository.listIdSupportTeams()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(1)
            )
        }

    @Test
    fun `setIdSupportTeamsList - Check return failure if have error in CardSharedPreferencesDatasource setIdSupportTeamsList`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setIdSupportTeamsList(listOf(1, 2))
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setIdSupportTeamsList",
                    "-",
                    Exception()
                )
            )
            val result = repository.setIdSupportTeamsList(listOf(1, 2))
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICardRepository.setIdSupportTeamsList -> ICardSharedPreferencesDatasource.setIdSupportTeamsList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setIdSupportTeamsList - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setIdSupportTeamsList(listOf(1, 2))
            verify(cardSharedPreferencesDatasource, atLeastOnce()).setIdSupportTeamsList(listOf(1, 2))
            assertEquals(
                result.isSuccess,
                true
            )
        }

}