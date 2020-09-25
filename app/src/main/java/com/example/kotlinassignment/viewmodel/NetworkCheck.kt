package com.example.kotlinassignment.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkCheck {
    companion object{
        val Context.isConnected: Boolean
            get() {
                return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                    .activeNetworkInfo?.isConnected == true
            }
        fun networkConnected(context: Context) : Boolean{
            return context.isConnected!!
        }

    }
}