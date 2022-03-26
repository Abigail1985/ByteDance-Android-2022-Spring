package com.bytedance.jstu.homework.homework5.api

import com.bytedance.jstu.homework.homework5.api.TranslatorBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TranslatorService {
    @GET("jsonapi")
    fun getWordInfo(@Query("q") q: String): Call<TranslatorBean>
}