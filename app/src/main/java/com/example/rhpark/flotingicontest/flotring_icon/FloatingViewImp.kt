package com.example.rhpark.flotingicontest.flotring_icon

import android.graphics.Point
import android.view.WindowManager
import com.example.rhpark.flotingicontest.flotring_icon.model.FloatingMainIconModelPoint
import com.example.rhpark.flotingicontest.flotring_icon.view.FloatingMainView

/**
 * Created by Rhpark on 2017-11-19.
 */
interface FloatingViewImp {

    interface View
    {

    }

    interface Presenter
    {
        var view: FloatingViewImp.View
        var model: FloatingViewImp.Model

        fun onTouchIconDown(x:Float,y:Float)
        fun onTouchIconUp(x:Float,y:Float)
        fun onTouchIconMove(x:Float,y:Float)

        fun onSelectItem(index:Int)

        fun onAddItem(index:Int)

        fun getWindowParams():WindowManager.LayoutParams
    }

    interface Model
    {
        fun getPoint(): Point
        fun setPoint(x:Float, y:Float)
        fun savePoint(x:Float, y:Float)

        fun onTouchIconDown(initPoint:Point, currentPoint:Point)
        fun onTouchIconUp(x:Float, y:Float)
        fun onTouchIconMove(x:Float, y:Float)
        fun getWindowParams(): WindowManager.LayoutParams
    }
}