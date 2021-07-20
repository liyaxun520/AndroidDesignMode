package com.example.chainofresponsibility.handle

abstract class Handler {

    var successor:Handler ?= null   //下一节点的处理者

    /**
     * 请求处理
     * @param condition 请求条件
     */
    abstract fun handleRequest(condition:String)
}