package com.example.clearscore.api

import com.example.clearscore.model.CreditReport
import retrofit2.http.GET

interface ApiServiceInterface {
    @GET("endpoint.json")
    suspend fun getDataFromApi(): CreditReport
}