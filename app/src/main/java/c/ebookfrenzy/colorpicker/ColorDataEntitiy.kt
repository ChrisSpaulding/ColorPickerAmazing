package c.ebookfrenzy.colorpicker

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class ColorDataEntitiy (
    @PrimaryKey (autoGenerate = true)
    val name : String,
    val red : Int,
    val green: Int,
    val blue :Int
)