package com.example.chainofresponsibility.test.deal

import android.util.Log

class DirectorLeader:Leader() {

    private val TAG:String = DirectorLeader::class.java.simpleName

    override fun handle(money: Double) {
        Log.e(TAG, "handle: 主管批复  $money 元")
    }


    override fun limit(): Double {
        return 5000.toDouble()
    }
}