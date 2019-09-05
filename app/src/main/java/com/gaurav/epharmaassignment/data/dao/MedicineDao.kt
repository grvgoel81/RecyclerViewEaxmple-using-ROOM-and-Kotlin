package com.gaurav.epharmaassignment.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gaurav.epharmaassignment.data.entity.Medicine

@Dao
interface MedicineDao {

    @Insert
    fun saveMedicine(todoRecord: Medicine)

    @Delete
    fun deleteMedicine(todoRecord: Medicine)

    @Update
    fun updateMedicine(todoRecord: Medicine)

    @Query("SELECT * FROM medicine ORDER BY id DESC")
    fun getAllMedicine(): LiveData<List<Medicine>>
}