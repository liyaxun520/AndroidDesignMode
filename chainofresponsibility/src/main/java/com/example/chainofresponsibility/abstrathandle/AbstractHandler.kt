package com.example.chainofresponsibility.abstrathandle

import android.util.Log

abstract class AbstractHandler {

    var nextHandler :AbstractHandler?= null   //下一节点上的处理者对象
    private val TAG:String = AbstractHandler::class.java.simpleName

    fun handleRequest(request: AbstractRequest){
        if (request.getRequestLevel() === getHandleLevel()) {
            //一致则由该处理对象处理
            handle(request)
        }else{
            //负责将请求对象转发给下一个节点上的请求对象
            if (nextHandler != null) {
                nextHandler!!.handleRequest(request)
            }else{
                Log.e(TAG,"---------所有处理者均不能处理的请求--------------")
            }
        }
    }

    /**
     * 获取处理者对象的处理级别
     */
    abstract fun getHandleLevel():Int
    /**
     * 每个对象处理者的具体处理方式
     */
    abstract fun handle(request: AbstractRequest)


}