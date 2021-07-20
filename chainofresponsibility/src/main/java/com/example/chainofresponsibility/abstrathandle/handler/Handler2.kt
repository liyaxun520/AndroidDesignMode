package com.example.chainofresponsibility.abstrathandle.handler

import android.util.Log
import com.example.chainofresponsibility.abstrathandle.AbstractHandler
import com.example.chainofresponsibility.abstrathandle.AbstractRequest

class Handler2: AbstractHandler() {

    private val TAG :String ?=Handler2::class.java.simpleName

    override fun getHandleLevel(): Int {
        return 2
    }

    override fun handle(request: AbstractRequest) {
        Log.e(TAG,"---Handler2处理该请求------"+request.getRequestLevel())
    }
}