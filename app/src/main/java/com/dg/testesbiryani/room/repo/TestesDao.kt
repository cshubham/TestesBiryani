package com.dg.testesbiryani.room.repo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TestesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(testesTable: TestesEntity)

    @Query("SELECT * FROM sementable")
    fun getAll(): Flow<List<TestesEntity>>

}