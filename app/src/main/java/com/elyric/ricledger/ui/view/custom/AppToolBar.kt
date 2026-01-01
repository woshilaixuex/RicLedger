package com.elyric.ricledger.ui.view.custom

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.elyric.ricledger.R

class AppToolBar@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val toolBar: View
    private val topicBack: ImageView
    private val topicLabel: TextView
    private var backClickListener: OnClickListener? = null
    init {
        inflate(context, R.layout.app_toolbar, this)
        toolBar = findViewById(R.id.topic)
        topicBack = findViewById(R.id.topic_back)
        topicLabel = findViewById(R.id.topic_label)
        // 读取自定义属性
        context.theme.obtainStyledAttributes(attrs, R.styleable.AppToolBar, 0, 0).apply {
            try {
                getString(R.styleable.AppToolBar_title)?.let { setTitle(it) }
                getDrawable(R.styleable.AppToolBar_backIcon)?.let { topicBack.setImageDrawable(it) }
                val visible = getBoolean(R.styleable.AppToolBar_backVisible, true)
                topicBack.visibility = if (visible) View.VISIBLE else View.GONE
            } finally {
                recycle()
            }
        }
        topicBack.setOnClickListener {
            backClickListener?.onClick(it) ?: run {
                (context as? Activity)?.finish()
            }
        }
    }
    fun setTitle(title: String) {
        topicLabel.text = title
    }

    fun setOnBackClickListener(listener: OnClickListener) {
        backClickListener = listener
    }

    fun setBackIcon(drawable: Drawable) {
        topicBack.setImageDrawable(drawable)
    }

    fun showBackButton(show: Boolean) {
        topicBack.visibility = if (show) View.VISIBLE else View.GONE
    }
    fun showTitle(show: Boolean) {
        topicLabel.visibility = if (show) View.VISIBLE else View.GONE
    }
}
