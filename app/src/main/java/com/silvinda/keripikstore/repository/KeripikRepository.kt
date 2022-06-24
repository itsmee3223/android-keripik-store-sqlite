package com.silvinda.keripikstore.repository

import androidx.lifecycle.LiveData
import com.silvinda.keripikstore.data.KeripikDao
import com.silvinda.keripikstore.model.Keripik

class KeripikRepository(private val keripikDao: KeripikDao) {
    val readAllData: LiveData<List<Keripik>> = keripikDao.readAllData()
    val dataTerlaris: LiveData<List<Keripik>> = keripikDao.dataTerlaris()
    val dataLainnya: LiveData<List<Keripik>> = keripikDao.dataLainnya()

    suspend fun addKeripik(keripik: Keripik){
        keripikDao.addProduk(keripik)
    }

    suspend fun updateKeripik(keripik: Keripik){
        keripikDao.updateKeripik(keripik)
    }

    suspend fun deleteKeripik(keripik: Keripik){
        keripikDao.deleteKeripik(keripik)
    }
}