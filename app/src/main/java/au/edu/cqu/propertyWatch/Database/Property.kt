package au.edu.cqu.propertyWatch.Database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*
import java.io.Serializable

@Entity
data class Property (
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @NotNull var address : String,
    var price : Int = 0,
    @NotNull var phone : String,
    var lat : Double = 0.0,
    var lon : Double = 0.0
): Serializable


