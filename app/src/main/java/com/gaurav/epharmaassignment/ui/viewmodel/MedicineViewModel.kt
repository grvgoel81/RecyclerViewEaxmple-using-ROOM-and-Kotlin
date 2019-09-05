package com.gaurav.epharmaassignment.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.gaurav.epharmaassignment.data.entity.Medicine
import com.gaurav.epharmaassignment.data.repository.MedicineRepository

class MedicineViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MedicineRepository = MedicineRepository(application)
    private val allMedicineList: LiveData<List<Medicine>> = repository.getAllMedicine()

    fun saveMedicine(medicine: Medicine) {
        repository.saveMedicine(medicine)
    }

    fun updateMedicine(medicine: Medicine){
        repository.updateMedicine(medicine)
    }

    fun deleteMedicine(medicine: Medicine) {
        repository.deleteMedicine(medicine)
    }

    fun getAllMedicineList(): LiveData<List<Medicine>> {
        return allMedicineList
    }

}