package com.roman.mlkittcamereses.scanActiviti

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Size
import android.view.View
import android.view.ViewTreeObserver.OnDrawListener
import com.google.mlkit.vision.common.PointF3D
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark
import java.text.AttributedCharacterIterator.Attribute

class LendmarkView(
    private val context:Context,
    private val atribute: AttributeSet

    ):View(context, atribute) {

    private var viewSize = Size(0, 0)
    private var mainPaint = Paint(ANTI_ALIAS_FLAG)
    private var detectorPose: Pose? = null
    private var sourceSize = Size(0, 0)
    init {
        mainPaint.color = Color.RED
        mainPaint.style = Paint.Style.FILL
    }

    fun setPose(newPose: Pose, newSize: Size){
        detectorPose = newPose
        sourceSize = newSize
        invalidate()
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewSize = Size(w, h)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        DrawPoints(canvas)
        DrawLines(canvas)
    }
    private fun ConvertPoint(targetPoint: PointF3D): PointF {
        val x1 = targetPoint.x
        val y1 = targetPoint.y
        val w1 = sourceSize.width
        val h1 = sourceSize.height
        val w2 = viewSize.width
        val h2 = viewSize.height
        val x2 = x1 * w2/w1
        val y2 = y1 * h2/h1

        return PointF(x2, y2)
    }


    private fun DrawPoint(center: PointF3D, canvas: Canvas?){
        val convertedPoint = ConvertPoint(center)
        canvas?.drawCircle(
            convertedPoint.x,
            convertedPoint.y,
            15f,
            mainPaint
        )


    }
    private fun DrawPoints(canvas: Canvas?){
        var currentPoint = detectorPose?.getPoseLandmark(PoseLandmark.NOSE)
        if (currentPoint != null)
        {
            DrawPoint(currentPoint.position3D, canvas)
        }

        currentPoint = detectorPose?.getPoseLandmark(PoseLandmark.LEFT_EYE)
        if (currentPoint != null)
        {
            DrawPoint(currentPoint.position3D, canvas)
        }

        currentPoint = detectorPose?.getPoseLandmark(PoseLandmark.RIGHT_EYE)
        if (currentPoint != null)
        {
            DrawPoint(currentPoint.position3D, canvas)
        }

        currentPoint = detectorPose?.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)
        if (currentPoint != null)
        {
            DrawPoint(currentPoint.position3D, canvas)
        }

        currentPoint = detectorPose?.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)
        if (currentPoint != null)
        {
            DrawPoint(currentPoint.position3D, canvas)
        }

        currentPoint = detectorPose?.getPoseLandmark(PoseLandmark.LEFT_ANKLE)
        if (currentPoint != null)
        {
            DrawPoint(currentPoint.position3D, canvas)
        }
    }

    private fun DrawLine(start: PointF3D, end: PointF3D, canvas: Canvas?){
        val convertedStart = ConvertPoint(start)
        val convertedEnd = ConvertPoint(end)
        canvas?.drawLine(
            start.x,
            start.y,
            end.x,
            end.y,
            mainPaint
        )



    }
    private fun DrawLines(canvas: Canvas?){
        var startPoint = detectorPose?.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)
        var endPoint = detectorPose?.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)
        if(startPoint != null && endPoint != null) {
            DrawLine(startPoint.position3D, endPoint.position3D, canvas)
        }
        startPoint = detectorPose?.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)
        endPoint = detectorPose?.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)
        if(startPoint != null && endPoint != null){
            DrawLine(startPoint.position3D, endPoint.position3D, canvas)
        }

    }
}