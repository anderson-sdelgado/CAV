package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import kotlin.test.Test
import kotlin.test.assertEquals

class ISetEquipTest {

    private val equipRepository = mock<EquipRepository>()
    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetEquip(
        equipRepository = equipRepository,
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if value of field is incorrect`() =
        runTest {
            val result = usecase(
                nroEquip = "de25",
                option = Option.INSERT,
                flowNote = FlowNote.EQUIP,
                idMain = 0,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetEquip -> toLong"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NumberFormatException: For input string: \"de25\""
            )
        }

}