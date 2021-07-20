package com.example.chainofresponsibility.abstrathandle.request

import com.example.chainofresponsibility.abstrathandle.AbstractRequest

class Request3 (obj: Any):AbstractRequest(obj){

    override fun getRequestLevel(): Int {
        return 3
    }

}