package com.example.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

class TitleView(context: Context, attrs: AttributeSet?) :
    FrameLayout(context, attrs),View.OnClickListener {



    private val TAG:String = TitleView::class.java.simpleName
    private val tvTitle: TextView
    private var onBackListener: OnBackListener? = null
    fun setOnBackListener(onBackListener: OnBackListener?) {
        this.onBackListener = onBackListener
    }

    interface OnBackListener {
        fun onBackClick()
    }

    fun setTitle(title: String?) {
        tvTitle.text = title
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_title, this)
        findViewById<View>(R.id.ivBack).setOnClickListener(this)
        tvTitle = findViewById<View>(R.id.tvTitle) as TextView
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.ivBack-> onBackListener!!.onBackClick()
            else->{
                Log.e(TAG, "onClick: "+p0.id )
            }
        }
    }
}