package com.example.clearscore.unit_test

import com.example.clearscore.api.ApiServiceInterface
import com.example.clearscore.data.DataRepository
import com.example.clearscore.model.CreditReportResponse
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataRepositoryTest {
    @Mock
    private lateinit var apiServiceInterface: ApiServiceInterface

    @InjectMocks
    private lateinit var dataRepository: DataRepository

    @Test
    fun getCreditReport() {
        runBlocking {
            val response = dataRepository.getCreditReport()
            verify(apiServiceInterface, times(1)).getDataFromApi()
            Assert.assertEquals(CreditReportResponse.Status.SUCCESS, response.status)
        }
    }

    @Test
    fun getCreditReportThrowException() {
        runBlocking {
            whenever(apiServiceInterface.getDataFromApi()).thenThrow(NullPointerException())
            val response = dataRepository.getCreditReport()
            verify(apiServiceInterface, times(1)).getDataFromApi()
            Assert.assertEquals(CreditReportResponse.Status.FAILURE, response.status)
        }
    }
}