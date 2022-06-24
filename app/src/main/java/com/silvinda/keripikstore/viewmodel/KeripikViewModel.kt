package com.silvinda.keripikstore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.silvinda.keripikstore.data.KeripikDatabase
import com.silvinda.keripikstore.model.Keripik
import com.silvinda.keripikstore.repository.KeripikRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KeripikViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Keripik>>
    val dataTerlaris: LiveData<List<Keripik>>
    val dataLainnya: LiveData<List<Keripik>>

    private val repository: KeripikRepository

    init {
        val keripikDao = KeripikDatabase.getDatabase(application).keripikDao()
        repository = KeripikRepository(keripikDao)
        readAllData = repository.readAllData
        dataTerlaris = repository.dataTerlaris
        dataLainnya = repository.dataLainnya
    }

    fun addKeripik(keripik: Keripik){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addKeripik(keripik)
        }
    }

    fun updateKeripik(keripik: Keripik){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateKeripik(keripik)
        }
    }

    fun deleteKeripik(keripik: Keripik){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteKeripik(keripik)
        }
    }
}