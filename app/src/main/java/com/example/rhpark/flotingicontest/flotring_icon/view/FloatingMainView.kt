package com.example.rhpark.flotingicontest.flotring_icon.view

import android.widget.FrameLayout
import com.example.rhpark.flotingicontest.R
import com.example.rhpark.flotingicontest.flotring_icon.ServiceFlotingIcon
import com.example.rhpark.flotingicontest.utils.Logger
import kotlinx.android.synthetic.main.view_floting_service.view.*
import android.content.Context.WINDOW_SERVICE
import android.view.*
import com.example.rhpark.flotingicontest.flotring_icon.FloatingViewImp
import com.example.rhpark.flotingicontest.flotring_icon.presenter.FloatingMainPresenter


/**
 * Created by Rhpark on 2017-11-19.
 */
class FloatingMainView(service:ServiceFlotingIcon):FrameLayout(service.applicationContext)
        ,View.OnTouchListener,FloatingViewImp.View
{
    var presenter:FloatingMainPresenter
    var view: View
    var manager:WindowManager
    init {
        Logger.d()
        var  lf = LayoutInflater.from(service)
        view = lf.inflate(R.layout.view_floting_service,null)

        view.ivFavoriteIcon.setOnTouchListener(this)

        presenter = FloatingMainPresenter(this@FloatingMainView,view.context)

        manager = service.getSystemService(WINDOW_SERVICE) as WindowManager
        manager.addView(view, presenter.getWindowParams())
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean
    {
        event?.let {
            val x = event.rawX
            val y = event.rawY

            when(event.action)
            {
                MotionEvent.ACTION_UP   -> onTouchUp(x, y)
                MotionEvent.ACTION_DOWN -> onTouchDown(x, y)
                MotionEvent.ACTION_MOVE -> onTouchMove(x, y)
            }
            manager.updateViewLayout(view, presenter.getWindowParams())
        }
        return true
    }

    fun onTouchDown(x: Float, y: Float)
    {
        presenter.onTouchIconDown(x, y)
//        manager.addView(view, presenter.getWindowParams())
    }

    fun onTouchUp(x: Float, y: Float)
    {
//        manager.removeView(view)
        presenter.onTouchIconUp(x, y)
    }

    fun onTouchMove(x: Float, y: Float)
    {
        presenter.onTouchIconMove(x, y)
        manager.updateViewLayout(view,presenter.getWindowParams())
    }

    fun onDestroy()
    {
        view.visibility = View.GONE
        view.destroyDrawingCache()
        destroyDrawingCache()
    }
}