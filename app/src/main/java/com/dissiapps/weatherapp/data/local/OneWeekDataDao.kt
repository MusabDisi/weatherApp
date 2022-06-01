package com.dissiapps.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dissiapps.weatherapp.data.local.models.OneDaysData
import kotlinx.coroutines.flow.Flow

@Dao
interface OneWeekDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneWeekData(oneDaysDataList: List<OneDaysData>)

    @Query("SELECT * FROM one_week_data_table")
    fun getOneWeekData() : Flow<List<OneDaysData>>

    @Query("DELETE FROM one_week_data_table")
    suspend fun nukeTheOneWeekDataTable()
}