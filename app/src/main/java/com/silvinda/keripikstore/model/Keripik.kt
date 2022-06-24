package com.silvinda.keripikstore.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "keripik_table")
data class Keripik (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var kategori: String ,
    var judul: String ,
    var rating: String ,
    var price: String ,
    var terlaris: Boolean,
    var gambar: String,
    var deskripsi: String
    ) : Parcelable