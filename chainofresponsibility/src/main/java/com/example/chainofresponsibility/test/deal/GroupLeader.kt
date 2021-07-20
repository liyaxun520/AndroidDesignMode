package com.example.chainofresponsibility.test.deal

import android.util.Log

class GroupLeader :Leader(){
    private val TAG:String = GroupLeader::class.java.simpleName

    override fun handle(money: Double) {
        Log.e(TAG, "handle: 组长批复  $money 元")
    }

    override fun limit(): Double {
        return 1000.toDouble()
    }

}