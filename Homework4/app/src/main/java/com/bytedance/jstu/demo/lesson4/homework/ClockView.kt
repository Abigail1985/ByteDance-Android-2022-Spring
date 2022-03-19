package com.bytedance.jstu.demo.lesson4.homework

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.webkit.WebSettings
import java.util.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


/**
 *  author : 鲁君一
 *  time   : 2022/3/18
 *  desc   :
 */

//Context context 是一个 View 的 Context，一般并不需要注意，但 View 是通过这个 Context 获取主题 Theme， Resources 等资源的。可能涉及字体加载，语言问题，因此一定要注意当前的 Context。
//AttributeSet attrs 是一个布局的属性，在xml布局中的属性都将在这个对象中。
//int defStyleAttr 是当前主题中的一个属性，它包含对样式资源的引用，该样式资源为 View 提供默认值。
//int defStyleRes 是样式资源的资源标识符，它为 View 提供默认值，仅在 defStyleAttr  = 0 或在主题中找不到时使用。


//从View继承而来并自定义一个ClockView
class ClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var cal: Calendar = Calendar.getInstance()
    private var curHourOfDay=cal.get(Calendar.HOUR_OF_DAY).toFloat()
    private var curHour= cal.get(Calendar.HOUR).toFloat()
    private var curMinute= cal.get(Calendar.MINUTE).toFloat()
    private var curSecond= cal.get(Calendar.SECOND).toFloat()
    private var hourDegree: Float= (curHour*30+curMinute*0.5+curSecond*0.1).toFloat() //curHour/12 *360
    private var minuteDegree: Float= (curMinute*6+curSecond*0.1).toFloat()//curMinute/60 *360
    private var secondDegree: Float=curSecond*6 //curSecond/60 *360

    //设置抗锯齿标志位，表示绘制时候启用抗锯齿功能
    private var ClockHourScalePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply { 
        color=Color.WHITE
        strokeWidth = 10f
        strokeCap=Paint.Cap.ROUND
    }
    private var ClockMinScalePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color=Color.WHITE
        strokeWidth = 5f
        strokeCap=Paint.Cap.ROUND
    }

    private var HourHandPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color=Color.WHITE
        strokeWidth = 10f
        strokeCap=Paint.Cap.ROUND
    }

    private var MinHandPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color=Color.GRAY
        strokeWidth = 5f
        strokeCap=Paint.Cap.ROUND
    }
    private var SecHandPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color=Color.BLACK
        strokeWidth = 3f
        strokeCap=Paint.Cap.ROUND
    }
    private var NumerPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color=Color.WHITE
        strokeWidth = 3f
        textSize=50f
        typeface= Typeface.DEFAULT
        textAlign=Paint.Align.LEFT
        strokeCap=Paint.Cap.ROUND
    }
    private var DigitalPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color=Color.WHITE
        strokeWidth = 3f
        textSize=60f
        typeface= Typeface.DEFAULT
        textAlign=Paint.Align.CENTER
        strokeCap=Paint.Cap.ROUND
    }
    private var outRadius=min(width,height)/2//钟表外半径
    private var HourHandLength=outRadius*0.4
    private var MinuteHandLength=outRadius*0.6
    private var SecondHandLength=outRadius*0.8
    private val strNumber = arrayOf("3", "2", "1", "12", "11", "10", "9", "8", "7", "6", "5", "4")

    init{
        Thread {
            while (true){
                Thread.sleep(1000)
//                Log.i("iii","thread start")
                cal = Calendar.getInstance()
                curHourOfDay=cal.get(Calendar.HOUR_OF_DAY).toFloat()
                curHour = cal.get(Calendar.HOUR).toFloat()
                curMinute = cal.get(Calendar.MINUTE).toFloat()
                curSecond = cal.get(Calendar.SECOND).toFloat()
                hourDegree= (curHour*30+curMinute*0.5+curSecond*0.1).toFloat() //curHour/12 *360
                minuteDegree= (curMinute*6+curSecond*0.1).toFloat()//curMinute/60 *360
                secondDegree=curSecond*6 //curSecond/60 *360
                //之前没有再thread里更新Degree导致重绘失败
                postInvalidate()
            }
        }.start()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width=MeasureSpec.getSize(widthMeasureSpec)
        val height=MeasureSpec.getSize(heightMeasureSpec)
        outRadius=min(width,height)/2
        HourHandLength=outRadius*0.4
        MinuteHandLength=outRadius*0.5
        SecondHandLength=outRadius*0.7
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        Log.i("iii", "Start one Draw")
//        Log.i("iii","Time Before ReDraw: hour:${curHour}, min:${curMinute},sec: ${curSecond}")
        drawClockScale(canvas)
        drawClockHand(canvas)
        drawDigitClock(canvas)
//        Log.i("iii","Time After ReDraw: hour:${curHour}, min:${curMinute},sec: ${curSecond}")
    }


    /**绘制时钟刻度*/
    private fun drawClockScale(canvas: Canvas?) {
        for (i in 0..59) {
            if (i % 5 == 0) {
                //获取刻度路径
//                Log.i("iiii", "Var give to getClockScalePaths:${width / 2}, ${height/2}, ${outRadius},${outRadius*5/6},${-i*6} ")
                val HourPaths = getClockScalePaths(
                    width / 2,
                    height / 2,
                    outRadius = outRadius,
                    innerRadius = outRadius * 5 / 6,
                    angle = -i * 6
                )
//                Log.i("iii","Hour:${HourPaths[0]}, ${HourPaths[1]}, ${HourPaths[2]}, ${HourPaths[3]}")
                canvas?.drawLines(HourPaths,ClockHourScalePaint)
                val NumberPaths = getClockScalePaths(
                    width / 2,
                    height / 2,
                    outRadius = outRadius,
                    innerRadius = outRadius * 3 / 4,
                    angle = -i * 6
                )
                canvas?.drawText(strNumber[i/5], NumberPaths[2]-15,NumberPaths[3]+15,NumerPaint)

            }
            else{
//                Log.i("i", "Var give to getClockScalePaths:${width / 2}, ${height/2}, ${outRadius},${outRadius*7/8},${-i*6} ")
                val MinPaths = getClockScalePaths(
                    width / 2,
                    height / 2,
                    outRadius = outRadius,
                    innerRadius = outRadius * 7 / 8,
                    angle = -i * 6
                )
//                Log.i("iii","Min: ${MinPaths[0]}, ${MinPaths[1]}, ${MinPaths[2]}, ${MinPaths[3]}")
                canvas?.drawLines(MinPaths, ClockMinScalePaint)
            }
        }
    }

    /**通过改变角度值,获取不同角度方向的外圆一点到圆心连线过内圆一点的路径坐标集合（时钟刻度路径）*/
    private fun getClockScalePaths(x0: Int, y0: Int, outRadius: Int, innerRadius: Int, angle: Int): FloatArray {
        val paths = FloatArray(4)
//        Log.i("i","Var in getClockScalePath:${x0}, ${y0}, ${outRadius}, ${innerRadius}")
        paths[0] = (x0 + outRadius * cos(angle * PI / 180)).toFloat()
        paths[1] = (y0 + outRadius * sin(angle * PI / 180)).toFloat()
        paths[2] = (x0 + innerRadius * cos(angle * PI / 180)).toFloat()
        paths[3] = (y0 + innerRadius * sin(angle * PI / 180)).toFloat()
//        Log.i("ii","IngetClockScalePath:${paths[0]}, ${paths[1]}, ${paths[2]}, ${paths[3]}")
        return paths
    }



    /**根据指针路径绘制指针*/
    private fun drawClockHand(canvas:Canvas?){
        //hour
        var HourHand=getClockHandPaths(1)
        canvas?.drawLines(HourHand,HourHandPaint)

        //minute
        var MinHand=getClockHandPaths(2)
        canvas?.drawLines(MinHand,MinHandPaint)

        //second
        var SecHand=getClockHandPaths(3)
        canvas?.drawLines(SecHand,SecHandPaint)

    }

    /**默认零度并不是正上方*/
    private fun getRealDegree(degree: Float): Float {
        return if (degree < 0) degree + 270 else degree - 90
    }

    /**根据当前时间获取指针路径*/
    private fun getClockHandPaths(handtype: Int):FloatArray{
        var paths=FloatArray(4)
//        Log.i("iiii","Time: hour:${curHour}, min:${curMinute},sec: ${curSecond}")
//        Log.i("iii","Degree: hour:${hourDegree}, min:${minuteDegree},sec: ${secondDegree}")
//        Log.i("iii","Length: hour:${HourHandLength}, min:${MinuteHandLength},sec: ${SecondHandLength}")
        when(handtype){
            1->{//hour
                paths[0]= (width/2).toFloat()
                paths[1]= (height/2).toFloat()
                paths[2]= (width/2 + HourHandLength * cos(getRealDegree(hourDegree) * PI / 180)).toFloat()
                paths[3]= (height/2 + HourHandLength * sin(getRealDegree(hourDegree) * PI / 180)).toFloat()
            }

            2->{//minute
                paths[0]= (width/2).toFloat()
                paths[1]= (height/2).toFloat()
                paths[2]= (width/2 + MinuteHandLength * cos(getRealDegree(minuteDegree) * PI / 180)).toFloat()
                paths[3]= (height/2 + MinuteHandLength * sin(getRealDegree(minuteDegree) * PI / 180)).toFloat()
            }

            3->{//second
                paths[0]= (width/2).toFloat()
                paths[1]= (height/2).toFloat()
                paths[2]= (width/2 + SecondHandLength * cos(getRealDegree(secondDegree) * PI / 180)).toFloat()
                paths[3]= (height/2 + SecondHandLength * sin(getRealDegree(secondDegree) * PI / 180)).toFloat()
            }
        }
//        Log.i("iiii","getClockHandPaths return:${paths[0]}, ${paths[1]}, ${paths[2]}, ${paths[3]}")
        return paths
    }

    private fun drawDigitClock(canvas:Canvas?){

        var DigitNumber="${curHourOfDay.toInt()} : ${curMinute.toInt()} : ${curSecond.toInt()}"

        canvas?.drawText( DigitNumber, (width/2).toFloat(), (height*3/4).toFloat(),DigitalPaint)
    }

}
