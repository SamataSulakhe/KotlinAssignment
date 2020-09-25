package com.example.kotlinassignment

import com.example.kotlinassignment.view.MainActivity
import com.example.kotlinassignment.view.UserContentFragment
import org.junit.Assert
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTestCases {

    @Test
    fun checkTitle_NotNull(){
        val userContentFragment = UserContentFragment()
        assert(userContentFragment.checkTitleNotNull())
    }

    @Test
    fun checkNetworkstate(){
        val userContentFragment = UserContentFragment()
        assert(!userContentFragment.isNetworkPresent())
    }

}