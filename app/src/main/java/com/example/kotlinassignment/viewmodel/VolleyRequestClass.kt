package com.example.kotlinassignment.viewmodel

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class VolleyRequestClass {
    private var mRequestQueue: RequestQueue? = null
    private var mContext: Context? = null
    private var mCallVolley: CallVolley? = null

    val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null)
                mRequestQueue = Volley.newRequestQueue(mContext!!.applicationContext)
            return mRequestQueue!!
        }

    private constructor(context: Context, volley: CallVolley) {
        this.mContext = context
        this.mCallVolley = volley
        this.mRequestQueue = requestQueue
    }

    private constructor(context: Context) {
        this.mContext = context
        this.mRequestQueue = requestQueue
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    fun getRequest(url: String) {
        val getRequest = JsonObjectRequest(Request.Method.GET, url,null, Response.Listener { response ->
            mCallVolley!!.requestResponse(response.toString())}, Response.ErrorListener { error ->
            mCallVolley!!.requestResponse(error.message!!)
        })
        addToRequestQueue(getRequest)
    }

    companion object {
        private var mInstantce : VolleyRequestClass? = null
        @Synchronized
        fun getInstance(context: Context): VolleyRequestClass{
            if(mInstantce == null){
                mInstantce = VolleyRequestClass(context)
            }
            return mInstantce!!
        }

        @Synchronized
        fun getInstance(context: Context, volley: CallVolley): VolleyRequestClass{
            if(mInstantce == null){
                mInstantce = VolleyRequestClass(context, volley)
            }
            return mInstantce!!
        }
    }
}



