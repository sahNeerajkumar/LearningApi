package com.example.learningapi

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiServer {
    @GET("posts")
    fun getData(): Observable<List<UserDataItem>>

    @POST("posts")
    fun addPost(@Body userPostData: UserPostData): Observable<UserPostData>

    @PUT("posts/{id}")
    fun updatePost(
        @Body userUpdateData: UserDataItem,
        @Path("id") id: Int
    ): Observable<UserDataItem>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int): Call<Void>

    companion object Factory {
        fun createRetrofit(): ApiServer {
            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()
                .create(ApiServer::class.java)
        }
    }
}