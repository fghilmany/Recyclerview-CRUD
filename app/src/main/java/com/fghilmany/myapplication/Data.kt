package com.fghilmany.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data (
    var id: String? = null,
    var name: String,
    var desc: String
    ): Parcelable

fun listData(): MutableList<Data>{
    val list = mutableListOf<Data>()
    list.add(Data("0", "Beras", "Makanan pokok Indonesia"))
    list.add(Data("1", "Ayam", "Bahan makanan"))
    list.add(Data("2", "Daging", "Bahan Makanan"))
    list.add(Data("3", "Cilok", "Cemilan asal Bandung"))
    list.add(Data("4", "Kopi", "Minuman berkafein"))
    list.add(Data("5", "Susu", "Minuman penmabah nafsu makan"))
    return list
}