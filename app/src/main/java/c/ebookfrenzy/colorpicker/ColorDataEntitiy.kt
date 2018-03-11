package c.ebookfrenzy.colorpicker

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Spaulding on 3/11/2018.
 */

@Entity
data class ColorDataEntitiy (
    @PrimaryKey (autoGenerate = true)
    val name : String,
    val red : Int,
    val green: Int,
    val blue :Int
)