package com.gaurav.epharmaassignment.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.gaurav.epharmaassignment.data.MedicineDatabase
import com.gaurav.epharmaassignment.data.dao.MedicineDao
import com.gaurav.epharmaassignment.data.entity.Medicine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MedicineRepository(application: Application) {

    private val medicineDao: MedicineDao
    private val allMedicines: LiveData<List<Medicine>>

    init {
        val database = MedicineDatabase.getInstance(application.applicationContext)
        medicineDao = database!!.medicineDao()
        allMedicines = medicineDao.getAllMedicine()
    }

    fun saveMedicine(medicine: Medicine) = runBlocking {
        this.launch(Dispatchers.IO) {
            medicineDao.saveMedicine(medicine)
        }
    }

    fun updateMedicine(medicine: Medicine) = runBlocking {
        this.launch(Dispatchers.IO) {
            medicineDao.updateMedicine(medicine)
        }
    }


    fun deleteMedicine(medicine: Medicine) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                medicineDao.deleteMedicine(medicine)
            }
        }
    }

    fun getAllMedicine(): LiveData<List<Medicine>> {
        return allMedicines
    }
}