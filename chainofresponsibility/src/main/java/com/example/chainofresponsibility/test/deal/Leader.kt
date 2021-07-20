package com.example.chainofresponsibility.test.deal

abstract class Leader {

    var nextHandler:Leader ?=null


    fun handleRequest(money:Double){
       if( money <= limit()){
           handle(money)
       }else{
           if (nextHandler != null) {
               nextHandler!!.handleRequest(money)
           }
       }
    }

    abstract fun handle(money: Double)

    abstract fun limit() :Double
}