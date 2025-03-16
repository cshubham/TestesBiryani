package com.dg.testesbiryani.retrofit

import retrofit2.http.GET

interface ApiService {
    @GET("/orgs/octokit/repos")
    suspend fun getData() : List<ResponseData>
}