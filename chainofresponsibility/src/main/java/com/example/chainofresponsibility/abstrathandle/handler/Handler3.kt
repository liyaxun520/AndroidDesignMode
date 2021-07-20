package com.example.chainofresponsibility.abstrathandle.handler

import android.util.Log
import com.example.chainofresponsibility.abstrathandle.AbstractHandler
import com.example.chainofresponsibility.abstrathandle.AbstractRequest

class Handler3: AbstractHandler() {

    private val TAG :String ?=Handler3::class.java.simpleName

    override fun getHandleLevel(): Int {
        return 3
    }

    override fun handle(request: AbstractRequest) {
        Log.e(TAG,"---Handler3处理该请求------"+request.getRequestLevel())
    }
}