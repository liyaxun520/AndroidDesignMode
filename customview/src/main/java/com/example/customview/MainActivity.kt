package com.example.customview

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mLoveLayout: LoveLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var customView = findViewById<TitleView>(R.id.cusTitleView)
        customView.setOnBackListener(object :TitleView.OnBackListener{
            override fun onBackClick() {
                Toast.makeText(applicationContext,"返回",Toast.LENGTH_LONG).show()
            }
        })

        mLoveLayout = findViewById(R.id.loveLayout)
        customView.setTitle("测试信息")

        Thread(Runnable {
            try {
                while (true) {
                    Thread.sleep(400)
                    handler.sendEmptyMessage(0x123)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }).start()
    }


    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 0x123) {
                mLoveLayout!!.addLove()
            }
        }
    }
}