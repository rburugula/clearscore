package com.example.clearscore.unit_test

import com.example.clearscore.MainActivityViewModel
import com.example.clearscore.data.DataRepository
import com.example.clearscore.model.CreditReportResponse
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {
    @Mock
    private lateinit var dataRepository: DataRepository
    @InjectMocks
    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Test
    fun getCreditReport() {
        runBlockingTest {
            mainActivityViewModel.getCreditReport()
            verify(dataRepository).getCreditReport()
        }
    }

    @Test
    fun getCreditReportWithStatusSuccess() {
        runBlockingTest {
        whenever(dataRepository.getCreditReport()).thenReturn(CreditReportResponse(null, CreditReportResponse.Status.SUCCESS))
            mainActivityViewModel.getCreditReport()
            verify(dataRepository).getCreditReport()
            val response = dataRepository.getCreditReport()
            Assert.assertEquals(CreditReportResponse.Status.SUCCESS, response.status)
        }

    }

    @Test
    fun getCreditReportWithStatusFailure() {
        runBlockingTest {
            whenever(dataRepository.getCreditReport()).thenReturn(CreditReportResponse(null, CreditReportResponse.Status.FAILURE))
            mainActivityViewModel.getCreditReport()
            verify(dataRepository).getCreditReport()
            val response = dataRepository.getCreditReport()
            Assert.assertEquals(CreditReportResponse.Status.FAILURE, response.status)
        }
    }
}