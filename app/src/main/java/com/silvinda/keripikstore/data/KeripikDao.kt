package com.silvinda.keripikstore.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.silvinda.keripikstore.model.Keripik

@Dao
interface KeripikDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduk(keripik: Keripik)

    @Update
    suspend fun updateKeripik(keripik: Keripik)

    @Delete
    suspend fun deleteKeripik(keripik: Keripik)

    @Query("SELECT * FROM keripik_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Keripik>>

    @Query("SELECT * FROM keripik_table WHERE terlaris=1 ORDER BY id ASC")
    fun dataTerlaris(): LiveData<List<Keripik>>

    @Query("SELECT * FROM keripik_table WHERE terlaris=0 ORDER BY id ASC")
    fun dataLainnya(): LiveData<List<Keripik>>

    @Query("SELECT * FROM keripik_table WHERE id=:keripik_id")
    suspend fun getKeripik(keripik_id: Int) : List<Keripik>
}