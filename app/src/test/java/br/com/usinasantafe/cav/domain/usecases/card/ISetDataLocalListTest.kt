package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.DataLocal
import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISetDataLocalListTest {

    private val dataLocalRepository = mock<DataLocalRepository>()
    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetDataLocalList(
        dataLocalRepository = dataLocalRepository,
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in DataLocalRepository listROptionItemByIdOption`() =
        runTest {
            whenever(
                dataLocalRepository.listROptionItemByIdOption(10)
            ).thenReturn(
                resultFailure(
                    "IDataLocalRepository.listROptionItemByIdOption",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                idOption = 10,
                list = listOf(
                    ItemCheckBoxScreenModel(
                        id = 1,
                        desc = "ITEM 1",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 2,
                        desc = "ITEM 2",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 3,
                        desc = "ITEM 3",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 4,
                        desc = "ITEM 4",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 5,
                        desc = "ITEM 5",
                        flag = true
                    )
                )
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDataLocalList -> IDataLocalRepository.listROptionItemByIdOption"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository listIdDataLocal`() =
        runTest {
            whenever(
                dataLocalRepository.listROptionItemByIdOption(5)
            ).thenReturn(
                Result.success(
                    listOf(
                        DataLocal(
                            id = 4,
                            idOption = 5,
                            idItem = 10
                        ),
                        DataLocal(
                            id = 4,
                            idOption = 5,
                            idItem = 30
                        ),
                        DataLocal(
                            id = 5,
                            idOption = 5,
                            idItem = 60
                        )
                    )
                )
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
            val result = usecase(
                idOption = 5,
                list = listOf(
                    ItemCheckBoxScreenModel(
                        id = 10,
                        desc = "ITEM 1",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 20,
                        desc = "ITEM 2",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 30,
                        desc = "ITEM 3",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 40,
                        desc = "ITEM 4",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 50,
                        desc = "ITEM 5",
                        flag = true
                    )
                )
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDataLocalList -> ICardRepository.listIdDataLocal"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository setIdDataLocalList`() =
        runTest {
            whenever(
                dataLocalRepository.listROptionItemByIdOption(5)
            ).thenReturn(
                Result.success(
                    listOf(
                        DataLocal(
                            id = 4,
                            idOption = 5,
                            idItem = 10
                        ),
                        DataLocal(
                            id = 5,
                            idOption = 5,
                            idItem = 30
                        ),
                        DataLocal(
                            id = 6,
                            idOption = 5,
                            idItem = 60
                        )
                    )
                )
            )
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                Result.success(listOf(1, 3, 5, 6, 8, 12))
            )
            whenever(
                cardRepository.setIdDataLocalList(
                    listOf(1, 3, 8, 12, 20, 30, 50)
                )
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setIdDataLocalList",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                idOption = 5,
                list = listOf(
                    ItemCheckBoxScreenModel(
                        id = 10,
                        desc = "ITEM 1",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 20,
                        desc = "ITEM 2",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 30,
                        desc = "ITEM 3",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 40,
                        desc = "ITEM 4",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 50,
                        desc = "ITEM 5",
                        flag = true
                    )
                )
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetDataLocalList -> ICardRepository.setIdDataLocalList"
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
                dataLocalRepository.listROptionItemByIdOption(5)
            ).thenReturn(
                Result.success(
                    listOf(
                        DataLocal(
                            id = 4,
                            idOption = 5,
                            idItem = 10
                        ),
                        DataLocal(
                            id = 5,
                            idOption = 5,
                            idItem = 30
                        ),
                        DataLocal(
                            id = 6,
                            idOption = 5,
                            idItem = 60
                        )
                    )
                )
            )
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                Result.success(listOf(1, 3, 5, 6, 8, 12))
            )
            val result = usecase(
                idOption = 5,
                list = listOf(
                    ItemCheckBoxScreenModel(
                        id = 10,
                        desc = "ITEM 1",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 20,
                        desc = "ITEM 2",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 30,
                        desc = "ITEM 3",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 40,
                        desc = "ITEM 4",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 50,
                        desc = "ITEM 5",
                        flag = true
                    )
                )
            )
            verify(cardRepository, atLeastOnce()).setIdDataLocalList(listOf(1, 3, 8, 12, 20, 30, 50))
            assertEquals(
                result.isSuccess,
                true
            )
        }

}