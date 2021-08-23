package com.example.clearscore.di

import com.example.clearscore.CreditScoreFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface AppComponent {

    fun inject(creditScoreFragment: CreditScoreFragment)
}