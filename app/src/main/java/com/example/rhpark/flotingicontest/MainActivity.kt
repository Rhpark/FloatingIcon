package com.example.rhpark.flotingicontest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.example.rhpark.flotingicontest.flotring_icon.ServiceFlotingIcon
import com.example.rhpark.flotingicontest.utils.Logger
import com.example.rhpark.fticon.drawerlayout_views.communicate.DrawerItemSend
import com.example.rhpark.fticon.drawerlayout_views.communicate.DrawerItemShare
import com.example.rhpark.fticon.drawerlayout_views.single.DrawerItemGallery
import com.example.rhpark.fticon.drawerlayout_views.single.DrawerItemImport
import com.example.rhpark.fticon.drawerlayout_views.single.DrawerItemNavManage
import com.example.rhpark.fticon.drawerlayout_views.single.DrawerItemSlideshow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var intentService: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        cbFlotingIcon.setOnCheckedChangeListener { cbFlotingIcon, isChecked ->
            if (true == isChecked)
            {
                intentService = Intent(applicationContext, ServiceFlotingIcon::class.java)
                startService(intentService)
            }
            else
            {
                stopService(intentService)
            }
            Logger.d("isChecked $isChecked")
        }
        checkDrawOverlayPermission()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else if ( llyDrawerItemView.visibility == View.VISIBLE)
        {
            llyDrawerItemView.removeAllViews()
            llyDrawerItemView.visibility = View.GONE
        }
        else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        llyDrawerItemView.removeAllViews()
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
                llyDrawerItemView.addView( DrawerItemImport(this.applicationContext).view)
            }
            R.id.nav_gallery -> {
                llyDrawerItemView.addView( DrawerItemGallery(this.applicationContext).view)
            }
            R.id.nav_slideshow -> {
                llyDrawerItemView.addView( DrawerItemSlideshow(this.applicationContext).view)
            }
            R.id.nav_manage -> {
                llyDrawerItemView.addView( DrawerItemNavManage(this.applicationContext).view)
            }
            R.id.nav_share -> {
                llyDrawerItemView.addView( DrawerItemShare(this.applicationContext).view)
            }
            R.id.nav_send -> {
                llyDrawerItemView.addView( DrawerItemSend(this.applicationContext).view)
            }
        }

        llyDrawerItemView.visibility = View.VISIBLE
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /** code to post/handler request for permission */
   val REQUEST_CODE = 5469

    fun checkDrawOverlayPermission()
    {
        if (!Settings.canDrawOverlays(this)) {
            /** if not construct intent to request permission */
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()))
            /** request permission via start activity for result */
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            /** if so check once again if we have permission */
            if (Settings.canDrawOverlays(this)) {
                // continue here - permission was granted
            }
        }

    }
}
