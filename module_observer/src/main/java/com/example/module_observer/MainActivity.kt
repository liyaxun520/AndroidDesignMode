package com.example.module_observer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.module_observer.observable.DevTechFrontier
import com.example.module_observer.observer.Coder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var devTechFrontier = DevTechFrontier()

        var coder = Coder("lison")
        var coder1 = Coder("lison1")
        var coder2 = Coder("lison2")
        var coder3 = Coder("lison3")
        //将观察者注册到可观察对象的观察者列表中
        devTechFrontier.addObserver(coder)
        devTechFrontier.addObserver(coder1)
        devTechFrontier.addObserver(coder2)
        devTechFrontier.addObserver(coder3)

        devTechFrontier.postNewPublication("扯淡的人生！！！")
    }
}