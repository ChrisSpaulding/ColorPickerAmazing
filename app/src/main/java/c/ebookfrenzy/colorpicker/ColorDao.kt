package c.ebookfrenzy.colorpicker

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by Spaulding on 3/11/2018.
 */
interface ColorDao {

    @Query("SELECT * FROM ColorDataEntity")
    fun getAllColors(): List<ColorDataEntitiy>

    @Insert
    fun insert (colorDataEntity : ColorDataEntitiy)

    @Delete
    fun delete(colorDataEntity: ColorDataEntitiy)

    @Delete
    fun deleteAll()
}