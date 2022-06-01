package com.dissiapps.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dissiapps.weatherapp.data.local.models.ExtraInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface ExtraInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExtraInfoList(extraInfo: List<ExtraInfo>)

    @Query("SELECT * FROM extra_info_table")
    fun getExtraInfoList() : Flow<List<ExtraInfo>>

    @Query("DELETE FROM extra_info_table")
    suspend fun nukeTheExtraInfoTable()
}