package com.example.chainofresponsibility.abstrathandle.handler

import android.util.Log
import com.example.chainofresponsibility.abstrathandle.AbstractHandler
import com.example.chainofresponsibility.abstrathandle.AbstractRequest

class Handler1: AbstractHandler() {

    private val TAG :String ?=Handler1::class.java.simpleName

    override fun getHandleLevel(): Int {
        return 1
    }

    override fun handle(request: AbstractRequest) {
        Log.e(TAG,"---Handler1处理该请求------"+request.getRequestLevel())
    }
}