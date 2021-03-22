package com.example.pos.MainBody.dashboard.ordersActivites

import android.app.Application
import com.mazenrashed.printooth.Printooth

/* Application class is used to print out something. */
class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        Printooth.init(this)
    }
}