package com.example.xortranslator.fragment.roulette

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.*

class CustomRoulette(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var paint: Paint? = null // 用于绘制的画笔
    private var numbers: IntArray? = null // 存储每个扇区的数字
    private var numSlices = 0 // 扇区的数量

    // 构造函数
    init {
        init()
    }

    private fun init() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint!!.textAlign = Paint.Align.CENTER
        paint!!.textSize = 30f // 设置文字大小
        paint!!.style = Paint.Style.FILL
    }

    fun setNumbers(numbers: IntArray) {
        this.numbers = numbers
        this.numSlices = numbers.size
        invalidate() // 重绘视图
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (numbers == null || numbers!!.size == 0) return

        val width = width
        val height = height
        val radius = (min(width.toDouble(), height.toDouble()) / 2).toInt()
        val cx = width / 2
        val cy = height / 2

        var currentAngle = -90f // 开始的角度（顶部中间）
        val sweepAngle = 360f / numSlices // 每个扇形的角度

        val oval = RectF((cx - radius).toFloat(), (cy - radius).toFloat(), (cx + radius).toFloat(), (cy + radius).toFloat())

        for (i in 0 until numSlices) {
            // 设置画笔颜色，这里可以自定义或生成
            paint!!.color = Color.argb(255, (Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt())
            // 绘制扇形
            canvas.drawArc(oval, currentAngle, sweepAngle, true, paint!!)

            // 绘制文字
            val angle = Math.toRadians((currentAngle + sweepAngle / 2).toDouble())
            val textX = (cx + (radius * 0.75) * cos(angle)).toFloat()
            val textY = (cy + (radius * 0.75) * sin(angle)).toFloat() - (paint!!.ascent() + paint!!.descent()) / 2
            paint!!.color = Color.BLACK
            canvas.drawText(numbers!![i].toString(), textX, textY, paint!!)

            currentAngle += sweepAngle
        }
    }
}