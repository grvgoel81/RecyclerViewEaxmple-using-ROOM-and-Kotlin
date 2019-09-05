package com.gaurav.epharmaassignment.ui.activities

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gaurav.epharmaassignment.R
import com.gaurav.epharmaassignment.data.entity.Medicine
import com.gaurav.epharmaassignment.ui.adapter.MedicineAdapter
import com.gaurav.epharmaassignment.ui.viewmodel.MedicineViewModel
import com.gaurav.epharmaassignment.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MedicineAdapter.MedicineEvents {

    private lateinit var medicineViewModel: MedicineViewModel
    private lateinit var medicineAdapter: MedicineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerviewMedicines.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        medicineAdapter = MedicineAdapter(this)
        recyclerviewMedicines.adapter = medicineAdapter

        medicineViewModel = ViewModelProviders.of(this).get(MedicineViewModel::class.java)
        medicineViewModel.getAllMedicineList().observe(this, Observer {
            it?.let { it1 -> medicineAdapter.setAllMedicines(it1) }
        })

        btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, MedicineActivity::class.java)
            startActivityForResult(intent, Constants.INTENT_CREATE_MEDICINE)
        }
    }

    override fun onDeleteClicked(medicine: Medicine) {
        medicineViewModel.deleteMedicine(medicine)
    }

    override fun onViewClicked(medicine: Medicine) {
        val intent = Intent(this@MainActivity, MedicineActivity::class.java)
        intent.putExtra(Constants.INTENT_OBJECT, medicine)
        startActivityForResult(intent, Constants.INTENT_UPDATE_MEDICINE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val medicine = data?.getParcelableExtra<Medicine>(Constants.INTENT_OBJECT)!!
            when (requestCode) {
                Constants.INTENT_CREATE_MEDICINE -> {
                    medicineViewModel.saveMedicine(medicine)
                }
                Constants.INTENT_UPDATE_MEDICINE -> {
                    medicineViewModel.updateMedicine(medicine)
                }
            }
        }
    }
}
