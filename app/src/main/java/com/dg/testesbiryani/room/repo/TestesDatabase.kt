package com.dg.testesbiryani.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dg.testesbiryani.room.repo.Order
import com.dg.testesbiryani.room.repo.TestesDao
import com.dg.testesbiryani.room.repo.TestesEntity
import com.dg.testesbiryani.room.repo.User
import com.dg.testesbiryani.room.repo.UserOrderDao
import com.dg.testesbiryani.room.repo.UserWithOrders

@Database(
    entities = [TestesEntity::class, User::class, Order::class],
    version = 2,
    exportSchema = false
)
abstract class TestesDatabase : RoomDatabase() {
    abstract fun testesDao(): TestesDao
    abstract fun userDao(): UserOrderDao
}