package c.ebookfrenzy.colorpicker

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.net.nsd.NsdManager

@Database (entities = arrayOf(ColorDataEntitiy::class), version =1)
abstract  class AppDatabase : RoomDatabase(){
    abstract fun colorDao() : ColorDao
}

