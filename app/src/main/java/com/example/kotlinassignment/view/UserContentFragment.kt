package com.example.kotlinassignment.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlinassignment.R
import com.example.kotlinassignment.model.LocalData
import com.example.kotlinassignment.model.UserContent
import com.example.kotlinassignment.viewmodel.CallVolley
import com.example.kotlinassignment.viewmodel.NetworkCheck
import com.example.kotlinassignment.viewmodel.RowClickListener
import com.example.kotlinassignment.viewmodel.VolleyRequestClass
import com.google.gson.Gson
import kotlinx.android.synthetic.main.usercontent_fragment.*

class UserContentFragment : Fragment(), CallVolley, RowClickListener, SwipeRefreshLayout.OnRefreshListener {
    var toolTitle : String? = null
    var userContent : UserContent? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.usercontent_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LocalData.initaliseSharedPref(context!!)
        pullDownToRefresh.setOnRefreshListener(this)
        /*network check if present call the api request else get it from local data sharedpreference*/
        if(NetworkCheck.networkConnected(context!!)) {
            pullDownToRefresh.setRefreshing(true)
            VolleyRequestClass.getInstance(context!!, this).getRequest(getString(R.string.url))
        }else{
            var localData : String = LocalData.retrieveUserContentData()
            setDataToView(localData)
        }
    }

    override fun requestResponse(response: String) {
        pullDownToRefresh.setRefreshing(false)
        if(isNetworkPresent()){
            LocalData.storeUserContentData(response)
            setDataToView(response)
        }else{
            var localData : String = LocalData.retrieveUserContentData()
            setDataToView(localData)
        }
    }

    fun setDataToView( userData : String){
        userContent = Gson().fromJson(userData, UserContent::class.java)

        toolTitle = userContent?.title ?: getString(R.string.title)
        activity!!.setTitle(toolTitle)

        val userContent_recyclerView: RecyclerView = view!!.findViewById(R.id.recyclerViewLayout)
        userContent_recyclerView.layoutManager =
            LinearLayoutManager(view!!.context, RecyclerView.VERTICAL, false)

        val userContentAdapter = UserContentAdapter(userContent!!.listRows, this, view!!.context)
        userContent_recyclerView.adapter = userContentAdapter
    }

    fun checkTitleNotNull( ):Boolean {
        return toolTitle.isNullOrEmpty()
    }

    fun isNetworkPresent():Boolean{
        return NetworkCheck.networkConnected(context!!)
    }


    override fun onRowClick(position: Int, title: String) {
        Toast.makeText(view!!.context, title, Toast.LENGTH_LONG).show()
    }

    /* pull down to refresh implemented using SwipeRefreshLayout */
    override fun onRefresh() {
        pullDownToRefresh.setRefreshing(true)
        Toast.makeText(context, getString(R.string.data_refreshed), Toast.LENGTH_SHORT).show()
        if(isNetworkPresent()) {
            VolleyRequestClass.getInstance(context!!, this).getRequest(getString(R.string.url))
        }
    }
}
