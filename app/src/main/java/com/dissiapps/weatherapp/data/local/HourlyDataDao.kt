package com.dissiapps.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dissiapps.weatherapp.data.local.models.OneHourData
import kotlinx.coroutines.flow.Flow

@Dao
interface HourlyDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourlyDataList(oneHourData: List<OneHourData>)

    @Query("SELECT * FROM hourly_data_table")
    fun getHourlyData() : Flow<List<OneHourData>>

    @Query("DELETE FROM hourly_data_table")
    suspend fun nukeTheHourlyDataTable()

}