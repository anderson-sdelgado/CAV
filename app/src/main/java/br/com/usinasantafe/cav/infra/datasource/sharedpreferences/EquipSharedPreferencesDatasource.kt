package br.com.usinasantafe.cav.infra.datasource.sharedpreferences

import br.com.usinasantafe.cav.utils.EmptyResult

interface EquipSharedPreferencesDatasource {
    suspend fun setIdEquip(idEquip: Int): EmptyResult
}