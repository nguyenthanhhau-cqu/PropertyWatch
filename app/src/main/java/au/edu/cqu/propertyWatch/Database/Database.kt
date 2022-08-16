package au.edu.cqu.propertyWatch.Database
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ Property::class ], version=1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun propertyDao():PropertyDao

}
