package com.example.kotlinassignment.model

import android.content.Context
import android.content.SharedPreferences
/*local data to be stored and fetched once there is not network*/
class LocalData {
    companion object{
        private var sharedPreferences: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null

        fun initaliseSharedPref(context : Context){
            sharedPreferences =
                context.getSharedPreferences("sharedFile", Context.MODE_PRIVATE)
            editor = sharedPreferences!!.edit()
        }

        fun storeUserContentData(userContentDataString :String){
            editor!!.putString("USER_CONTENT_DATA", userContentDataString)
            editor!!.commit()
        }

        fun retrieveUserContentData() : String{
            val storedData = sharedPreferences!!.getString("USER_CONTENT_DATA","NoData")
            return storedData!!
        }
    }

}