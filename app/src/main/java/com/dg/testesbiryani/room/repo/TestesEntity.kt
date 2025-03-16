package com.dg.testesbiryani.room.repo

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "sementable")
data class TestesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val user: String
)

