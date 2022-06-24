package com.silvinda.keripikstore.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.silvinda.keripikstore.model.Keripik

@Database(entities = [Keripik::class], version = 1, exportSchema = false)
abstract class KeripikDatabase : RoomDatabase() {

    abstract fun keripikDao(): KeripikDao

    companion object {
        @Volatile
        private var INSTANCE: KeripikDatabase? = null

        fun getDatabase(context: Context): KeripikDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KeripikDatabase::class.java,
                    "keripik_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}