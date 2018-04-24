package c.ebookfrenzy.colorpicker

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

interface ColorDao {

    @Query("SELECT * FROM ColorDataEntity")
    fun getAllColors(): List<ColorDataEntity>

    @Insert(onConflict = REPLACE)
    fun insert (colorDataEntity : ColorDataEntity)

    @Delete
    fun delete(colorDataEntity: ColorDataEntity)

    @Delete
    fun deleteAll()
}