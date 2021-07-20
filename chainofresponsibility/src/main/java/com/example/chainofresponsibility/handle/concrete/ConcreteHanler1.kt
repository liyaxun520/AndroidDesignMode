package com.example.chainofresponsibility.handle.concrete

import android.util.Log
import com.example.chainofresponsibility.handle.Handler

class ConcreteHanler1 : Handler() {

    private val TAG: String = ConcreteHanler1::class.java.simpleName

    override fun handleRequest(condition: String) {
        if (condition === "ConcreteHanler1") {
            Log.d(TAG, "ConcreteHanler1  处理请求")
            return
        } else {
            successor?.handleRequest(condition)
        }
    }

}