package com.example.a1lab

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class ClockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var hour: Int = 10
    private var minute: Int = 15

    fun setTime(hour: Int, minute: Int) {
        this.hour = hour % 12
        this.minute = minute % 60
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = min(centerX, centerY) * 0.9f

        // Рисуем циферблат
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8f
        canvas.drawCircle(centerX, centerY, radius, paint)

        // Рисуем метки часов
        paint.strokeWidth = 5f
        for (i in 0 until 12) {
            val angle = Math.toRadians((i * 30 - 90).toDouble())
            val startX = centerX + (radius * 0.9 * cos(angle)).toFloat()
            val startY = centerY + (radius * 0.9 * sin(angle)).toFloat()
            val stopX = centerX + (radius * cos(angle)).toFloat()
            val stopY = centerY + (radius * sin(angle)).toFloat()
            canvas.drawLine(startX, startY, stopX, stopY, paint)
        }

        val hourAngle = Math.toRadians(((hour % 12) * 30 + minute * 0.5 - 90).toDouble())
        paint.strokeWidth = 12f
        val hourX = centerX + (radius * 0.5 * cos(hourAngle)).toFloat()
        val hourY = centerY + (radius * 0.5 * sin(hourAngle)).toFloat()
        canvas.drawLine(centerX, centerY, hourX, hourY, paint)

        val minuteAngle = Math.toRadians((minute * 6 - 90).toDouble())
        paint.strokeWidth = 8f
        val minuteX = centerX + (radius * 0.7 * cos(minuteAngle)).toFloat()
        val minuteY = centerY + (radius * 0.7 * sin(minuteAngle)).toFloat()
        canvas.drawLine(centerX, centerY, minuteX, minuteY, paint)
    }
}
