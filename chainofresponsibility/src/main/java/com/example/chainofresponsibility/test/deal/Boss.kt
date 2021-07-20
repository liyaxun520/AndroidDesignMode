package com.example.chainofresponsibility.test.deal

import android.util.Log

class Boss: Leader() {

    private val TAG:String = Boss::class.java.simpleName

    override fun handle(money: Double) {
        Log.e(TAG, "handle: 老板批复  $money 元")
    }

    override fun limit() :Double{
        return Double.MAX_VALUE
    }
}