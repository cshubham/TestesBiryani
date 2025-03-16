package com.dg.testesbiryani.room.repo

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

//class UserPagingSource(
//    private val userDao: UserOrderDao
//) : PagingSource<Int, User>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
//        val position = params.key ?: 0
//        Log.d("shch", "Loading data from position: $position with load size: ${params.loadSize}")
//
//        return try {
//            val data = userDao.getUsers(position, params.loadSize)
//            LoadResult.Page(
//                data = data,
//                prevKey = if (position == 0) null else position - 1,
//                nextKey =  position + 1
//            )
//        } catch (exception: Exception) {
//            LoadResult.Error(exception)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//}