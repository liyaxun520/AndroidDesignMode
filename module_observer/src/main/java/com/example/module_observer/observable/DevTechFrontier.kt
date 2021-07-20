package com.example.module_observer.observable

import java.util.*

class DevTechFrontier : Observable() {

    fun postNewPublication(content:String){
        //标识状态或内容发生改变
        setChanged()
        //通知所有观察者
        notifyObservers(content)
    }
}