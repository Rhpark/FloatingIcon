package com.example.rhpark.flotingicontest.flotring_icon.presenter

import android.content.Context
import android.graphics.PixelFormat
import android.graphics.Point
import android.view.Gravity
import android.view.WindowManager
import com.example.rhpark.flotingicontest.flotring_icon.FloatingViewImp
import com.example.rhpark.flotingicontest.flotring_icon.model.FloatingMainIconModelPoint
import com.example.rhpark.flotingicontest.flotring_icon.view.FloatingMainView
import com.example.rhpark.flotingicontest.utils.Logger

/**
 * Created by Rhpark on 2017-11-19.
 */
class FloatingMainPresenter:FloatingViewImp.Presenter
{
    override var view: FloatingViewImp.View
    override var model: FloatingViewImp.Model

    var context:Context

    lateinit var initPointXY: Point
    lateinit var initalPointXY: Point
    constructor(view: FloatingMainView, context: Context)
    {
        this.view = view
        this.context = context
        model = FloatingMainIconModelPoint(context)
    }

    override fun getWindowParams(): WindowManager.LayoutParams = model.getWindowParams()

    override fun onTouchIconDown(x: Float, y: Float)
    {
        val currentPoint = Point(x.toInt(),y.toInt())
        val initPoint = Point(getWindowParams().x, getWindowParams().y)

//        maxX = context.resources.displayMetrics.widthPixels - DrawerDisplayUtils.DPToPixel(50, context) as Int
//        maxY = context.resources.displayMetrics.heightPixels - DrawerDisplayUtils.DPToPixel(50, context) as Int

        model.onTouchIconDown(initPoint, currentPoint)
        Logger.d("ACTION_DOWN ")
    }

    override fun onTouchIconUp(x: Float, y: Float)
    {
        Logger.d("ACTION_UP ")

        model.savePoint(x,y)
    }

    private fun isClickCheck(x: Float,y: Float)
    {

    }

    override fun onTouchIconMove(x: Float, y: Float)
    {
        model.onTouchIconMove(x,y)
        Logger.d("ACTION_MOVE x = $x , y = $y")
        model.setPoint(x,y)
    }

    override fun onSelectItem(index: Int)
    {
    }

    override fun onAddItem(index: Int)
    {
    }
}
