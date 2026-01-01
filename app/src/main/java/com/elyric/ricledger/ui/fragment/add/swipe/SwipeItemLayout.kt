package com.elyric.ricledger.ui.fragment.add.swipe

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.elyric.ricledger.R

class SwipeItemLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs){
    private lateinit var wrapperLayout: View
    private lateinit var contentView: View
    private lateinit var actionView: View
    private var downX = 0f
    private var downY = 0f
    private var isOpen = false
    override fun onFinishInflate() {
        super.onFinishInflate()
        wrapperLayout = findViewById(R.id.wrapperLayout)
        contentView = findViewById(R.id.contentLayout)
        actionView = findViewById(R.id.actionLayout)
    }
    // 判断是上下还是左右划动
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                downY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = ev.x - downX
                val dy = ev.y - downY
                if (kotlin.math.abs(dx) > kotlin.math.abs(dy)) {
                    parent.requestDisallowInterceptTouchEvent(true)
                    return true
                }
            }
        }
        return super.onInterceptTouchEvent(ev)
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action){
            MotionEvent.ACTION_DOWN -> downX = event.x
            MotionEvent.ACTION_MOVE -> {
                // -dx为左，+dx为右
                val dx = event.x - downX
                wrapperLayout.animate().cancel()
                val translationX = when {
                    isOpen -> (dx - actionView.width).coerceIn(-actionView.width.toFloat(), 0f)
                    else -> dx.coerceIn(-actionView.width.toFloat(), 0f)
                }
                wrapperLayout.translationX = translationX
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (-wrapperLayout.translationX > actionView.width / 4) {
                    open()
                } else {
                    close()
                }
            }
        }
        return true
    }
    fun open() {
        wrapperLayout.animate().translationX(-actionView.width.toFloat()).setDuration(200).start()
        isOpen = true
    }

    fun close() {
        wrapperLayout.animate().translationX(0f).setDuration(200).start()
        isOpen = false
    }
}