package com.gaurav.epharmaassignment.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gaurav.epharmaassignment.data.dao.MedicineDao
import com.gaurav.epharmaassignment.data.entity.Medicine

@Database(entities = [Medicine::class], version = 1, exportSchema = false)
abstract class MedicineDatabase : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao

    companion object {
        private var INSTANCE: MedicineDatabase? = null

        fun getInstance(context: Context): MedicineDatabase? {
            if (INSTANCE == null) {
                synchronized(MedicineDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context,
                        MedicineDatabase::class.java,
                        "medicine_db")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}