package com.example.module_observer.observer

import android.util.Log
import java.util.*

class Coder:Observer {

    private var name:String ?=null
    private val TAG:String = Coder::class.java.simpleName

    constructor(name: String){
        this.name = name
    }

    override fun update(observable: Observable, any: Any) {
        Log.e(TAG, "Hi: $name ,开发者社区已更新，内容：$any")
    }

    override fun toString(): String {
        return "Coder(name=$name)"
    }

}