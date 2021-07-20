package com.example.chainofresponsibility

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import com.example.chainofresponsibility.abstrathandle.AbstractHandler
import com.example.chainofresponsibility.abstrathandle.AbstractRequest
import com.example.chainofresponsibility.abstrathandle.handler.Handler1
import com.example.chainofresponsibility.abstrathandle.handler.Handler2
import com.example.chainofresponsibility.abstrathandle.handler.Handler3
import com.example.chainofresponsibility.abstrathandle.request.Request1
import com.example.chainofresponsibility.abstrathandle.request.Request2
import com.example.chainofresponsibility.abstrathandle.request.Request3
import com.example.chainofresponsibility.handle.concrete.ConcreteHanler1
import com.example.chainofresponsibility.handle.concrete.ConcreteHanler2
import com.example.chainofresponsibility.test.deal.Boss
import com.example.chainofresponsibility.test.deal.DirectorLeader
import com.example.chainofresponsibility.test.deal.GroupLeader
import com.example.chainofresponsibility.test.deal.ManagerLeader

class MainActivity : AppCompatActivity() {


    private val TAG:String = MainActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun chainClick(view: View) {
        var handler1 = ConcreteHanler1()
        var handler2 = ConcreteHanler2()

        handler1.successor = handler2
        handler1.handleRequest("ConcreteHanler2")


        handler1.successor = handler2
        handler1.handleRequest("ConcreteHanler1")

        Log.e(TAG,"=====================================================")

        var abHandler1 :AbstractHandler= Handler1()
        var abHandler2 :AbstractHandler= Handler2()
        var abHandler3 :AbstractHandler= Handler3()

        abHandler1.nextHandler = abHandler2
        abHandler2.nextHandler = abHandler3

        var request1 :AbstractRequest= Request1("Request1")
        var request2 :AbstractRequest= Request2("Request2")
        var request3 :AbstractRequest= Request3("Request3")

        Log.e(TAG, "chainClick: "+request1.getObjectContent() )
        Log.e(TAG, "chainClick: "+request2.getObjectContent() )
        Log.e(TAG, "chainClick: "+request3.getObjectContent() )
        abHandler1.handleRequest(request1)
        abHandler1.handleRequest(request2)
        abHandler1.handleRequest(request3)


        Log.e(TAG,"=====================================================")
        var groupLeader = GroupLeader()
        var directorLeader = DirectorLeader()
        var managerLeader = ManagerLeader()
        var boss = Boss()

        groupLeader.nextHandler = directorLeader
        directorLeader.nextHandler = managerLeader
        managerLeader.nextHandler = boss

        Thread{
            kotlin.run {
                groupLeader.handleRequest(800.toDouble())
                SystemClock.sleep(1000)
                groupLeader.handleRequest(4000.toDouble())
                SystemClock.sleep(1000)
                groupLeader.handleRequest(8000.toDouble())
                SystemClock.sleep(1000)
                groupLeader.handleRequest(20000.toDouble())
            }
        }.start()

    }
}