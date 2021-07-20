package com.example.chainofresponsibility.test.deal

import android.util.Log

class ManagerLeader:Leader() {

    private val TAG:String = ManagerLeader::class.java.simpleName

    override fun handle(money: Double) {
        Log.e(TAG, "handle: 经理批复  $money 元")
    }


    override fun limit(): Double {
        return 10000.toDouble()
    }
}