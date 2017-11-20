package com.example.rhpark.flotingicontest.flotring_icon

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.rhpark.flotingicontest.flotring_icon.view.FloatingMainView
import com.example.rhpark.flotingicontest.utils.Logger

/**
 * Created by Rhpark on 2017-11-19.
 */
class ServiceFlotingIcon: Service()
{
    companion object {
        lateinit var mInstance : ServiceFlotingIcon
    }

    lateinit var favoriteMainView: FloatingMainView

    override fun onCreate() {
        super.onCreate()

        Logger.d()
//        var  lf = LayoutInflater.from(this)
//        lf.inflate(R.layout.view_floting_service,null,false)
        favoriteMainView = FloatingMainView(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mInstance = this
        return Service.START_NOT_STICKY
//        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        favoriteMainView.onDestroy()
    }
}