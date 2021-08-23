package com.example.clearscore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clearscore.data.DataRepository
import com.example.clearscore.model.CreditReport
import com.example.clearscore.model.CreditReportResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {
    var creditReportLiveData: MutableLiveData<CreditReport>
    var serverErrorLiveData: MutableLiveData<Boolean>

    init {
        creditReportLiveData = MutableLiveData()
        serverErrorLiveData = MutableLiveData()
    }

    fun getCreditReportObserver(): MutableLiveData<CreditReport> {
        return creditReportLiveData
    }

    fun getServerErrorLiveDataObserver(): MutableLiveData<Boolean> {
        return serverErrorLiveData
    }

    fun getCreditReport() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = dataRepository.getCreditReport()

            when(response.status) {
                CreditReportResponse.Status.SUCCESS -> creditReportLiveData.postValue(response.creditReport)
                CreditReportResponse.Status.FAILURE -> serverErrorLiveData.postValue(true)
            }
        }
    }
}