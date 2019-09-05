package com.gaurav.epharmaassignment.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "medicine")
@Parcelize
data class Medicine(@PrimaryKey(autoGenerate = true) val id: Long?,
                    @ColumnInfo(name = "name") val name: String,
                    @ColumnInfo(name = "description") val description: String,
                    @ColumnInfo(name = "quantity") val quantity: String,
                    @ColumnInfo(name = "price") val price: String) : Parcelable