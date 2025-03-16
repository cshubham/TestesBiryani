package com.dg.testesbiryani.room.repo

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction

@Entity
data class User(
    @PrimaryKey val userId: String,
    val userName: String
)


@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE // If user is deleted, orders are also deleted
    )]
)
data class Order(
    @PrimaryKey val orderId: String,
    val userId: String, // Foreign Key
    val orderAmount: Double
) {
    //  fun some() {
   // init {
       // val user = User("", "")
       // user.userId
    //}
      //  user.userId
    //}


}


data class UserWithOrders(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val orders: List<Order>
)

@Dao
interface UserOrderDao {
    @Transaction
    @Query("SELECT * FROM User")
    suspend fun getUsersWithOrders(): List<UserWithOrders>

    @Query("SELECT * FROM User")
     fun getUsers(): PagingSource<Int, User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)
}