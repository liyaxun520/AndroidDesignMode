package com.example.chainofresponsibility.abstrathandle

abstract class AbstractRequest {

    lateinit var obj:Any

    constructor(obj:Any){
        this.obj = obj
    }

    /**
     * 获取具体的内容对象
     */
    fun getObjectContent():Any{
        return obj
    }

    abstract fun getRequestLevel():Int

}