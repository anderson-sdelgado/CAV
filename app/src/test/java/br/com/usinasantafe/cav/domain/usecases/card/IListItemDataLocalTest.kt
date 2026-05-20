package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.ItemDataLocal
import br.com.usinasantafe.cav.domain.entities.stable.DataLocal
import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IListItemDataLocalTest {

    private val dataLocalRepository = mock<DataLocalRepository>()
    private val cardRepository = mock<CardRepository>()
    private val usecase = IListItemDataLocal(
        dataLocalRepository = dataLocalRepository,
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in DataLocalRepository listROptionItemByIdOption`() =
        runTest {
            whenever(
                dataLocalRepository.listDataLocalByIdOption(1)
            ).thenReturn(
                resultFailure(
                    "IDataLocalRepository.listROptionItemByIdOption",
                    "-",
                    Exception()
                )
            )
            val result = usecase(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IListItemDataLocal -> IDataLocalRepository.listROptionItemByIdOption"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in DataLocalRepository listItemByIdList`() =
        runTest {
            whenever(
                dataLocalRepository.listDataLocalByIdOption(1)
            ).thenReturn(
                Result.success(
                    listOf(
                        DataLocal(
                            id = 1,
                            idOption = 10,
                            idItem = 100
                        ),
                        DataLocal(
                            id = 2,
                            idOption = 20,
                            idItem = 200
                        ),
                        DataLocal(
                            id = 3,
                            idOption = 30,
                            idItem = 300
                        ),
                        DataLocal(
                            id = 4,
                            idOption = 40,
                            idItem = 400
                        )
                    )
                )
            )
            whenever(
                dataLocalRepository.listItemByIdList(listOf(100, 200, 300, 400))
            ).thenReturn(
                resultFailure(
                    "IDataLocalRepository.listItemByIdList",
                    "-",
                    Exception()
                )
            )
            val result = usecase(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IListItemDataLocal -> IDataLocalRepository.listItemByIdList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository idROptionItemDataLocalList`() =
        runTest {
            whenever(
                dataLocalRepository.listDataLocalByIdOption(1)
            ).thenReturn(
                Result.success(
                    listOf(
                        DataLocal(
                            id = 1,
                            idOption = 10,
                            idItem = 100
                        ),
                        DataLocal(
                            id = 2,
                            idOption = 20,
                            idItem = 200
                        ),
                        DataLocal(
                            id = 3,
                            idOption = 30,
                            idItem = 300
                        ),
                        DataLocal(
                            id = 4,
                            idOption = 40,
                            idItem = 400
                        )
                    )
                )
            )
            whenever(
                dataLocalRepository.listItemByIdList(listOf(100, 200, 300, 400))
            ).thenReturn(
                Result.success(
                    listOf(
                        ItemDataLocal(
                            id = 100,
                            description = "ITEM 1"
                        ),
                        ItemDataLocal(
                            id = 200,
                            description = "ITEM 2"
                        ),
                        ItemDataLocal(
                            id = 300,
                            description = "ITEM 3"
                        ),
                        ItemDataLocal(
                            id = 400,
                            description = "ITEM 4"
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
            val result = usecase(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IListItemDataLocal -> ICardRepository.listIdDataLocal"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if there is a missing item`() =
        runTest {
            whenever(
                dataLocalRepository.listDataLocalByIdOption(1)
            ).thenReturn(
                Result.success(
                    listOf(
                        DataLocal(
                            id = 1,
                            idOption = 10,
                            idItem = 100
                        ),
                        DataLocal(
                            id = 2,
                            idOption = 20,
                            idItem = 200
                        ),
                        DataLocal(
                            id = 3,
                            idOption = 30,
                            idItem = 300
                        ),
                        DataLocal(
                            id = 4,
                            idOption = 40,
                            idItem = 400
                        )
                    )
                )
            )
            whenever(
                dataLocalRepository.listItemByIdList(listOf(100, 200, 300, 400))
            ).thenReturn(
                Result.success(
                    listOf(
                        ItemDataLocal(
                            id = 100,
                            description = "ITEM 1"
                        ),
                        ItemDataLocal(
                            id = 200,
                            description = "ITEM 2"
                        ),
                        ItemDataLocal(
                            id = 300,
                            description = "ITEM 3"
                        ),
                    )
                )
            )
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                Result.success(emptyList())
            )
            val result = usecase(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IListItemDataLocal -> Collection contains no element matching the predicate."
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "null"
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            whenever(
                dataLocalRepository.listDataLocalByIdOption(1)
            ).thenReturn(
                Result.success(
                    listOf(
                        DataLocal(
                            id = 1,
                            idOption = 10,
                            idItem = 100
                        ),
                        DataLocal(
                            id = 2,
                            idOption = 20,
                            idItem = 200
                        ),
                        DataLocal(
                            id = 3,
                            idOption = 30,
                            idItem = 300
                        ),
                        DataLocal(
                            id = 4,
                            idOption = 40,
                            idItem = 400
                        )
                    )
                )
            )
            whenever(
                dataLocalRepository.listItemByIdList(listOf(100, 200, 300, 400))
            ).thenReturn(
                Result.success(
                    listOf(
                        ItemDataLocal(
                            id = 100,
                            description = "ITEM 1"
                        ),
                        ItemDataLocal(
                            id = 200,
                            description = "ITEM 2"
                        ),
                        ItemDataLocal(
                            id = 300,
                            description = "ITEM 3"
                        ),
                        ItemDataLocal(
                            id = 400,
                            description = "ITEM 4"
                        )
                    )
                )
            )
            whenever(
                cardRepository.listIdDataLocal()
            ).thenReturn(
                Result.success(
                    listOf(1, 3)
                )
            )
            val result = usecase(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(
                    ItemCheckBoxScreenModel(
                        id = 1,
                        desc = "ITEM 1",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 2,
                        desc = "ITEM 2",
                        flag = false
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
                    )
                )
            )
        }

}