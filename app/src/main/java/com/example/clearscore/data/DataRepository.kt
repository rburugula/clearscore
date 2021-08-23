package com.example.clearscore.data

import com.example.clearscore.api.ApiServiceInterface
import com.example.clearscore.model.CreditReportResponse
import java.lang.Exception
import javax.inject.Inject

class DataRepository @Inject constructor(
        private val apiServiceInterface: ApiServiceInterface
) {

    suspend fun getCreditReport(): CreditReportResponse {
        return try {
            val creditReport = apiServiceInterface.getDataFromApi()
            CreditReportResponse(creditReport, CreditReportResponse.Status.SUCCESS)
        } catch (e: Exception) {
            CreditReportResponse(null, CreditReportResponse.Status.FAILURE)
        }
    }
}