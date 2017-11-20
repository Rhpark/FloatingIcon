package com.example.rhpark.flotingicontest.flotring_icon.model

import android.content.Context
import android.graphics.PixelFormat
import android.graphics.Point
import android.view.Gravity
import android.view.WindowManager
import com.example.rhpark.flotingicontest.flotring_icon.FloatingViewImp
import com.example.rhpark.flotingicontest.utils.Logger
import com.rx.example.kotlintest001.model.sharedpf.SharedPfBase

/**
 * Created by Rhpark on 2017-11-19.
 */
class FloatingMainIconModelPoint: SharedPfBase,FloatingViewImp.Model
{
    private val KEY_DATA_TABLE = "KEY_FLOATING_POINT"
    private val KEY_DATA_VALUE_X = "KEY_FLOATING_POINT_X"
    private val KEY_DATA_VALUE_Y = "KEY_FLOATING_POINT_Y"

    private val Deafult_Point_X = 100
    private val Deafult_Point_Y = 200

    var pointXY:Point
    var initPointXY:Point
    var maxPoint:Point

    lateinit var params: WindowManager.LayoutParams

    constructor(context: Context) : super(context)
    {
        sp = context.getSharedPreferences(KEY_DATA_TABLE, Context.MODE_PRIVATE)
        pointXY = Point(loadPoint())
        initPointXY = Point()
        maxPoint = Point()
    }

    override fun getWindowParams(): WindowManager.LayoutParams {
        //            layoutType = layoutType | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE ;
        val layoutType = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE, layoutType, PixelFormat.TRANSLUCENT)

        params.gravity = ( Gravity.CENTER or Gravity.BOTTOM)
        params.x = loadPoint().x
        params.y = loadPoint().y
        return params
    }

    override fun getPoint(): Point = pointXY

    override fun setPoint(x: Float, y: Float)
    {
        pointXY.x = x.toInt()
        pointXY.y = y.toInt()
    }

    fun loadPoint():Point
    {
        val x = openInt(KEY_DATA_VALUE_X, Deafult_Point_X)
        val y = openInt(KEY_DATA_VALUE_Y, Deafult_Point_Y)
        return Point(x ,y)
    }


    override fun savePoint(x: Float, y: Float)
    {
        setPoint(x,y)
        saveInt(KEY_DATA_VALUE_X, x.toInt())
        saveInt(KEY_DATA_VALUE_Y, y.toInt())
    }

    override fun onTouchIconDown(initPoint:Point, currentPoint:Point)
    {
        this.initPointXY = Point(getWindowParams().x, getWindowParams().y)
        this.pointXY = Point(currentPoint)
    }

    override fun onTouchIconUp(x: Float, y: Float)
    {
        savePoint(x,y)
    }

    override fun onTouchIconMove(x: Float, y: Float)
    {
        params.x = initPointXY.x + (x.toInt() - pointXY.x)
        params.y = initPointXY.y + (y.toInt() - pointXY.y)
    }
}