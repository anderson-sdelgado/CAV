package br.com.usinasantafe.cav.external.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import br.com.usinasantafe.cav.external.room.dao.stable.ColabDao
import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.external.room.dao.stable.ItemDataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.NatureDao
import br.com.usinasantafe.cav.external.room.dao.stable.OptionDataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.ROptionItemDataLocalDao
import br.com.usinasantafe.cav.external.room.dao.stable.SupportTeamsDao
import br.com.usinasantafe.cav.external.room.dao.stable.TypeAccidentDao
import br.com.usinasantafe.cav.infra.models.room.stable.ColabRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.ItemDataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.NatureRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.OptionDataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.ROptionItemDataLocalRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.SupportTeamsRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.TypeAccidentRoomModel
import br.com.usinasantafe.cav.lib.VERSION_DB
import java.util.Date

@Database(
    entities = [
        ColabRoomModel::class,
        EquipRoomModel::class,
        ItemDataLocalRoomModel::class,
        NatureRoomModel::class,
        OptionDataLocalRoomModel::class,
        ROptionItemDataLocalRoomModel::class,
        SupportTeamsRoomModel::class,
        TypeAccidentRoomModel::class,
    ],
    version = VERSION_DB, exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class DatabaseRoom : RoomDatabase() {
    abstract fun colabDao(): ColabDao
    abstract fun equipDao(): EquipDao
    abstract fun itemDataLocalDao(): ItemDataLocalDao
    abstract fun natureDao(): NatureDao
    abstract fun optionDataLocalDao(): OptionDataLocalDao
    abstract fun rOptionItemDataLocalDao(): ROptionItemDataLocalDao
    abstract fun supportTeamsDao(): SupportTeamsDao
    abstract fun typeAccidentDao(): TypeAccidentDao
}

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}