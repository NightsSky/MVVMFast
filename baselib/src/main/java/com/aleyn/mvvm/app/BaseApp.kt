package com.aleyn.mvvm.app

import android.app.Application

open class BaseApp:Application() {
    companion object {
        private  var instance:Application?=null
        fun getInstance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}