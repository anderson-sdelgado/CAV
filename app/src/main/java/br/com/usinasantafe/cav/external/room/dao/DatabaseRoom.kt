package br.com.usinasantafe.cav.external.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import br.com.usinasantafe.cav.external.room.dao.stable.ColabDao
import br.com.usinasantafe.cav.external.room.dao.stable.EquipDao
import br.com.usinasantafe.cav.infra.models.room.stable.ColabRoomModel
import br.com.usinasantafe.cav.infra.models.room.stable.EquipRoomModel
import br.com.usinasantafe.cav.lib.VERSION_DB
import java.util.Date

@Database(
    entities = [
        ColabRoomModel::class,
        EquipRoomModel::class
    ],
    version = VERSION_DB, exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class DatabaseRoom : RoomDatabase() {
    abstract fun colabDao(): ColabDao
    abstract fun equipDao(): EquipDao
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