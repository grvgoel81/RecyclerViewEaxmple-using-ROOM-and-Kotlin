package com.gaurav.epharmaassignment.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gaurav.epharmaassignment.data.entity.Medicine

@Dao
interface MedicineDao {

    @Insert
    fun saveMedicine(medicine: Medicine)

    @Delete
    fun deleteMedicine(medicine: Medicine)

    @Update
    fun updateMedicine(medicine: Medicine)

    @Query("SELECT * FROM medicine ORDER BY id DESC")
    fun getAllMedicine(): LiveData<List<Medicine>>
}