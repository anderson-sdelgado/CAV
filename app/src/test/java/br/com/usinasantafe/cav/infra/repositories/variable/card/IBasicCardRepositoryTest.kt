package br.com.usinasantafe.cav.infra.repositories.variable.card

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

class IBasicCardRepositoryTest {

    private val cardSharedPreferencesDatasource = mock<CardSharedPreferencesDatasource>()
    private val repository = IBasicCardRepository(
        cardSharedPreferencesDatasource = cardSharedPreferencesDatasource
    )

    @Test
    fun `setRegAttendant - Check return failure if have error in CardSharedPreferencesDatasource setRegAttendant`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setRegAttendant(123456)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setRegAttendant",
                    "-",
                    Exception()
                )
            )
            val result = repository.setRegAttendant(
                regColab = 123456
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IBasicCardRepository.setRegAttendant -> ICardSharedPreferencesDatasource.setRegAttendant"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setRegAttendant - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setRegAttendant(123456)
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .setRegAttendant(123456)
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setIdCar - Check return failure if have error in CardSharedPreferencesDatasource setIdCar`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setIdCar(1)
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setIdCar",
                    "-",
                    Exception()
                )
            )
            val result = repository.setIdCar(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IBasicCardRepository.setIdCar -> ICardSharedPreferencesDatasource.setIdCar"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setIdCar - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setIdCar(1)
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .setIdCar(1);
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setLocal - Check return failure if have error in CardSharedPreferencesDatasource setLocal`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setLocal(LocalSharedPreferencesModel())
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setLocal",
                    "-",
                    Exception()
                )
            )
            val result = repository.setLocal(Local())
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IBasicCardRepository.setLocal -> ICardSharedPreferencesDatasource.setLocal"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setLocal - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setLocal(Local())
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .setLocal(LocalSharedPreferencesModel())
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
                "IBasicCardRepository.listIdNature -> ICardSharedPreferencesDatasource.listIdNature"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listIdNature - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.listIdNature()
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .listIdNature()
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setIdNatureList - Check return failure if have error in CardSharedPreferencesDatasource setIdNatureList`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setIdNatureList(listOf(1))
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setIdNatureList",
                    "-",
                    Exception()
                )
            )
            val result = repository.setIdNatureList(listOf(1))
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IBasicCardRepository.setIdNatureList -> ICardSharedPreferencesDatasource.setIdNatureList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setIdNatureList - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setIdNatureList(listOf(1))
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .setIdNatureList(listOf(1))
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
                "IBasicCardRepository.getRegAttendant -> ICardSharedPreferencesDatasource.getRegAttendant"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getRegAttendant - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.getRegAttendant()
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .getRegAttendant()
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `getIdCar - Check return failure if have error in CardSharedPreferencesDatasource getIdCar`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getIdCar()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getIdCar",
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
                "IBasicCardRepository.getIdCar -> ICardSharedPreferencesDatasource.getIdCar"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getIdCar - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.getIdCar()
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .getIdCar()
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `listIdTypeAccident - Check return failure if have error in CardSharedPreferencesDatasource listIdTypeAccident`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.listIdTypeAccident()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.listIdTypeAccident",
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
                "IBasicCardRepository.listIdTypeAccident -> ICardSharedPreferencesDatasource.listIdTypeAccident"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listIdTypeAccident - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.listIdTypeAccident()
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .listIdTypeAccident()
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setIdTypeAccidentList - Check return failure if have error in CardSharedPreferencesDatasource setIdTypeAccidentList`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setIdTypeAccidentList(listOf(1))
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setIdTypeAccidentList",
                    "-",
                    Exception()
                )
            )
            val result = repository.setIdTypeAccidentList(listOf(1))
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IBasicCardRepository.setIdTypeAccidentList -> ICardSharedPreferencesDatasource.setIdTypeAccidentList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setIdTypeAccidentList - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setIdTypeAccidentList(listOf(1))
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .setIdTypeAccidentList(listOf(1))
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
                "IBasicCardRepository.getLocal -> ICardSharedPreferencesDatasource.getLocal"
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
                Result.success(LocalSharedPreferencesModel())
            )
            val result = repository.getLocal()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                Local()
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
                "IBasicCardRepository.listIdDataLocal -> ICardSharedPreferencesDatasource.listIdDataLocal"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listIdDataLocal - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.listIdDataLocal()
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .listIdDataLocal()
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setIdDataLocalList - Check return failure if have error in CardSharedPreferencesDatasource setIdDataLocalList`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setIdDataLocalList(listOf(1))
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setIdDataLocalList",
                    "-",
                    Exception()
                )
            )
            val result = repository.setIdDataLocalList(listOf(1))
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IBasicCardRepository.setIdDataLocalList -> ICardSharedPreferencesDatasource.setIdDataLocalList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setIdDataLocalList - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setIdDataLocalList(listOf(1))
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .setIdDataLocalList(listOf(1))
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
                "IBasicCardRepository.listIdSupportTeams -> ICardSharedPreferencesDatasource.listIdSupportTeams"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `listIdSupportTeams - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.listIdSupportTeams()
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .listIdSupportTeams()
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setIdSupportTeamsList - Check return failure if have error in CardSharedPreferencesDatasource setIdSupportTeamsList`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setIdSupportTeamsList(listOf(1))
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setIdSupportTeamsList",
                    "-",
                    Exception()
                )
            )
            val result = repository.setIdSupportTeamsList(listOf(1))
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IBasicCardRepository.setIdSupportTeamsList -> ICardSharedPreferencesDatasource.setIdSupportTeamsList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setIdSupportTeamsList - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setIdSupportTeamsList(listOf(1))
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .setIdSupportTeamsList(listOf(1))
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `getObs - Check return failure if have error in CardSharedPreferencesDatasource getObs`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getObs()
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.getObs",
                    "-",
                    Exception()
                )
            )
            val result = repository.getObs()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IBasicCardRepository.getObs -> ICardSharedPreferencesDatasource.getObs"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getObs - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.getObs()
            ).thenReturn(
                Result.success("TESTE OBS")
            )
            val result = repository.getObs()
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .getObs()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                "TESTE OBS"
            )
        }

    @Test
    fun `setObs - Check return failure if have error in CardSharedPreferencesDatasource setObs`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setObs("TESTE OBS")
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setObs",
                    "-",
                    Exception()
                )
            )
            val result = repository.setObs("TESTE OBS")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IBasicCardRepository.setObs -> ICardSharedPreferencesDatasource.setObs"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setObs - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setObs("TESTE OBS")
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .setObs("TESTE OBS")
            assertEquals(
                result.isSuccess,
                true
            )
        }

    @Test
    fun `setPhoto- Check return failure if have error in CardSharedPreferencesDatasource setPhoto`() =
        runTest {
            whenever(
                cardSharedPreferencesDatasource.setPhoto("test")
            ).thenReturn(
                resultFailure(
                    "ICardSharedPreferencesDatasource.setPhotoList",
                    "-",
                    Exception()
                )
            )
            val result = repository.setPhoto("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IBasicCardRepository.setPhotoList -> ICardSharedPreferencesDatasource.setPhotoList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `setPhotoList - Check return correct if function execute successfully`() =
        runTest {
            val result = repository.setPhoto("test")
            verify(cardSharedPreferencesDatasource, atLeastOnce())
                .setPhoto("test")
            assertEquals(
                result.isSuccess,
                true
            )
        }

}
