package com.example.clearscore.model

data class CreditReportResponse constructor(val creditReport: CreditReport?, val status: Status) {
    enum class Status {
        SUCCESS, FAILURE
    }
}