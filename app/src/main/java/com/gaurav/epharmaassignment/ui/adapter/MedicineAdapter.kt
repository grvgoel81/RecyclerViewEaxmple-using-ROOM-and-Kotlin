package com.gaurav.epharmaassignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gaurav.epharmaassignment.R
import com.gaurav.epharmaassignment.data.entity.Medicine
import kotlinx.android.synthetic.main.item_medicine.view.*

class MedicineAdapter(medicineEvents: MedicineEvents) : androidx.recyclerview.widget.RecyclerView.Adapter<MedicineAdapter.ViewHolder>() {

    private var medicineList: List<Medicine> = arrayListOf()
    private val listener: MedicineEvents = medicineEvents

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medicine, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = medicineList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(medicineList[position], listener)
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bind(medicine: Medicine, listener: MedicineEvents) {
            itemView.tvName.text = "Name: " + medicine.name
            itemView.tvDescription.text = "Description: " + medicine.description
            itemView.tvQuantity.text = "Quantity: " + medicine.quantity
            itemView.tvPrice.text = "Price: " + medicine.price

            itemView.iv_item_delete.setOnClickListener {
                listener.onDeleteClicked(medicine)
            }

            itemView.setOnClickListener {
                listener.onViewClicked(medicine)
            }
        }
    }

    fun setAllMedicines(medicine: List<Medicine>) {
        this.medicineList = medicine
        notifyDataSetChanged()
    }

    interface MedicineEvents {
        fun onDeleteClicked(medicine: Medicine)
        fun onViewClicked(medicine: Medicine)
    }
}