package com.gaurav.epharmaassignment.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gaurav.epharmaassignment.R
import com.gaurav.epharmaassignment.data.entity.Medicine
import com.gaurav.epharmaassignment.utils.Constants
import kotlinx.android.synthetic.main.activity_medicine.*

class MedicineActivity : AppCompatActivity() {

    var medicine: Medicine? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)

        val intent = intent
        if (intent != null && intent.hasExtra(Constants.INTENT_OBJECT)) {
            val medicine: Medicine = intent.getParcelableExtra(Constants.INTENT_OBJECT)
            this.medicine = medicine
            prePopulateData(medicine)
        }

        title = if (medicine != null) getString(R.string.viewOrEditMedicine) else getString(R.string.createMedicine)

        btnAdd.setOnClickListener {
            saveMedicine()
        }
    }

    private fun prePopulateData(medicine: Medicine) {
        etName.setText(medicine.name)
        etDesc.setText(medicine.description)
        etQuantity.setText(medicine.quantity)
        etPrice.setText(medicine.price)
    }

    private fun saveMedicine() {
        if (validateInputs()) {
            val id = if (medicine != null) medicine?.id else null
            val todo = Medicine(id = id, name = etName.text.toString(), description = etDesc.text.toString(),
                                quantity = etQuantity.text.toString(),
                                price = etPrice.text.toString())
            val intent = Intent()
            intent.putExtra(Constants.INTENT_OBJECT, todo)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun validateInputs(): Boolean {
        if (etName.text.isEmpty()) {
            etName.error = getString(R.string.pleaseEnterName)
            etName.requestFocus()
            return false
        }

        if (etDesc.text.isEmpty()) {
            etDesc.error = getString(R.string.pleaseEnterDescription)
            etDesc.requestFocus()
            return false
        }

        if (etQuantity.text.isEmpty()) {
            etQuantity.error = getString(R.string.pleaseEnterQuantity)
            etQuantity.requestFocus()
            return false
        }

        if (etPrice.text.isEmpty()) {
            etPrice.error = getString(R.string.pleaseEnterPrice)
            etPrice.requestFocus()
            return false
        }
        return true
    }
}