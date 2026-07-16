package br.com.usinasantafe.cav.external.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import br.com.usinasantafe.cav.external.room.dao.stable.*
import br.com.usinasantafe.cav.external.room.dao.variable.*
import br.com.usinasantafe.cav.infra.models.room.stable.*
import br.com.usinasantafe.cav.infra.models.room.variable.*
import br.com.usinasantafe.cav.lib.VERSION_DB
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

@Database(
    entities = [
        ColabRoomModel::class,
        EquipRoomModel::class,
        ItemDataLocalRoomModel::class,
        NatureRoomModel::class,
        OptionDataLocalRoomModel::class,
        DataLocalRoomModel::class,
        SupportTeamsRoomModel::class,
        TypeAccidentRoomModel::class,
        CardRoomModel::class,
        PassengerColabRoomModel::class,
        EquipSecRoomModel::class,
        InvolvedRoomModel::class,
        VehicleInvolvedRoomModel::class,
        VehicleOwnRoomModel::class,
        PassengerInvolvedRoomModel::class,
        WitnessRoomModel::class
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
    abstract fun rOptionItemDataLocalDao(): DataLocalDao
    abstract fun supportTeamsDao(): SupportTeamsDao
    abstract fun typeAccidentDao(): TypeAccidentDao
    abstract fun cardDao(): CardDao
    abstract fun colabCardDao(): PassengerColabDao
    abstract fun equipCardDao(): EquipSecDao
    abstract fun involvedDao(): InvolvedDao
    abstract fun vehicleInvolvedDao(): VehicleInvolvedDao
    abstract fun vehicleOwnDao(): VehicleOwnDao
    abstract fun passengerInvolvedDao(): PassengerInvolvedDao
    abstract fun witnessDao(): WitnessDao
}

class Converters {

    private val gson = Gson()

    // Date

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    // List<Int>

    @TypeConverter
    fun fromIntList(value: List<Int>?): String =
        gson.toJson(value ?: emptyList<Int>())

    @TypeConverter
    fun toIntList(value: String?): List<Int> {
        if (value.isNullOrEmpty()) return emptyList()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, type)
    }

    // List<String>

    @TypeConverter
    fun fromStringList(value: List<String>?): String =
        gson.toJson(value ?: emptyList<String>())

    @TypeConverter
    fun toStringList(value: String?): List<String> {
        if (value.isNullOrEmpty()) return emptyList()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }
}