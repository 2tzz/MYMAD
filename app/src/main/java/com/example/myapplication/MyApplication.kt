package com.example.myapplication



import android.app.Application
import com.example.myapplication.database.AppDatabase

class MyApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getInstance(this)
    }
}
